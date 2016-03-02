package com.gufengyy.common.util.concurrent;

import java.util.ArrayList;
import java.util.List;

public class DemoDump extends CommonDump<Integer> {

    public DemoDump() {
        super(100);
    }

    @Override
    public List<Integer> findTaskByPage(int pageNo, int pageSize) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            result.add(pageSize * pageNo + i);
        }
        return result;
    }

    @Override
    protected CommonDump<Integer> newInstance() {
        return new DemoDump();
    }

    @Override
    protected void handlerTask(Integer task) {
        logger.info("Consumer:" + task);
    }

    public static void main(String[] args) {
        new DemoDump().work();
    }
}
