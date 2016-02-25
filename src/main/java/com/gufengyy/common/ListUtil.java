package com.gufengyy.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 有关列表处理的工具类。
 *
 * @author zhanglei
 * @date 2016年2月25日 下午4:40:12 
 *
 */
public abstract class ListUtil {
    /**
     * 对input列表中的元素遍历执行function操作，然后返回新的list.
     * 
     * 
     * @param input
     * @param function
     * @return
     */
    public static <R, T> List<R> operator(List<T> input, Function<T, R> function) {
        return input.stream().map(t -> function.apply(t))
                .collect(Collectors.toList());
    }

}
