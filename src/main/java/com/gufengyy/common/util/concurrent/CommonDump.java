package com.gufengyy.common.util.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommonDump<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final int THREAD_NUM = 10;
    private static final int PAGE_SIZE = 1000;
    private CompletedThreadPoolExecutor pool;
    private int totalCount = 0;
    private int pageSize = 1000;
    private int totalPage = 0;
    private static AtomicInteger completedCount = new AtomicInteger(0);

    public CommonDump(int totalCount) {
        this(THREAD_NUM, PAGE_SIZE, totalCount);
    }

    public CommonDump(int threadNum, int pageSize, int totalCount) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        if (this.pageSize > this.totalCount) {
            logger.warn("Current totalCount[" + totalCount + "] > pageSize["
                    + pageSize + "], auto adjuest pageSize=" + totalCount);
            this.pageSize = this.totalCount;
        }
        totalPage = getTotalPage(totalCount, this.pageSize);
        pool = new CompletedThreadPoolExecutor(threadNum, threadNum * 2, 300,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public void shutdownAndWaitCompleted() throws InterruptedException {
        this.pool.shutdown();
        this.pool.awaitTerminated();
    }

    private void showProgress() {
        int current = completedCount.incrementAndGet();
        float percent = (current * 100) / (float) totalCount;
        logger.debug("Current percent:" + String.format("%.2f", percent) + "%.");
    }

    protected abstract List<T> findTaskByPage(int pageNo, int pageSize);

    protected abstract CommonDump<T> newInstance();

    protected abstract void handlerTask(T task);

    public void work() {
        this.beforeHandlerTask();
        List<T> dueTaskList = new ArrayList<>();
        for (int i = 0; i <= this.totalPage; i++) {
            CommonDump<T> commonDump = newInstance();
            commonDump.doWork(commonDump, dueTaskList);
            try {
                // 预加载下一页数据
                if (i < this.totalPage) {
                    dueTaskList = findTaskByPage(i, pageSize);
                }
                // 等待执行，释放内存
                commonDump.shutdownAndWaitCompleted();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("DumpAll InterruptedException", e);
            }
            if (i > 0) {
                logger.info("Page[" + i + "/" + totalPage + "] has completed.");
            }
        }
        this.afterHandlerTask();
    }

    private void doWork(CommonDump<T> commonDump, List<T> taskList) {
        Iterator<T> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            T task = iterator.next();
            commonDump.pool.execute(() -> {
                try {
                    handlerTask(task);
                } catch (Exception e) {
                    logger.error("Dump Error!" + task, e);
                }
                showProgress();
            });
        }
    }

    protected void beforeHandlerTask() {
    }

    protected void afterHandlerTask() {
        logger.info("Dump completed! Target Size:" + totalCount
                + ", Actual Size:" + completedCount.get());
    }

    private static int getTotalPage(int totalNumber, int pageSize) {
        return (totalNumber + pageSize - 1) / pageSize;
    }

}
