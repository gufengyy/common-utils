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

    /**
     * 将Number类型的列表转化为String类型的列表.
     * 
     * @param input
     * @return
     */
    public static List<String> number2String(List<? extends Number> input) {
        return operator(input, x -> String.valueOf(x));
    }

    /**
     * 将String类型的列表转化为Integer类型的列表.
     * 
     * @param input
     * @return
     * 
     * @exception NumberFormatException
     *                - if the string does not contain a parsable integer
     */
    public static List<Integer> string2Int(List<String> input) {
        return operator(input, x -> Integer.parseInt(x));
    }

    /**
     * 将String类型的列表转化为Long类型的列表.
     * 
     * @param input
     * @return
     * 
     * @exception NumberFormatException
     *                - if the string does not contain a parsable long
     */
    public static List<Long> string2Long(List<String> input) {
        return operator(input, x -> Long.parseLong(x));
    }

    public static List<? extends Number> string2NumberQuietly(
            List<String> input, Function<String, ? extends Number> function) {
        return operator(input, x -> {
            try {
                return function.apply(x);
            } catch (Exception ignore) {
                return null;
            }
        }).stream().filter(x -> {
            if (x != null) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    /**
     * 将String类型的列表转化为Integer类型的列表。不抛出异常，直接将非Integer类型的元素舍弃。
     * 
     * @param input
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> string2IntQuietly(List<String> input) {
        return (List<Integer>) string2NumberQuietly(input,
                x -> Integer.parseInt(x));
    }

    /**
     * 将String类型的列表转化为Long类型的列表。不抛出异常，直接将非Long类型的元素舍弃。
     * 
     * @param input
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Long> string2LongQuietly(List<String> input) {
        return (List<Long>) string2NumberQuietly(input, x -> Long.parseLong(x));
    }

    /**
     * 将String类型的列表转化为Float类型的列表。不抛出异常，直接将非Float类型的元素舍弃。
     * 
     * @param input
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Float> string2FloatQuietly(List<String> input) {
        return (List<Float>) string2NumberQuietly(input,
                x -> Float.parseFloat(x));
    }

    /**
     * 将String类型的列表转化为Double类型的列表。不抛出异常，直接将非Double类型的元素舍弃。
     * 
     * @param input
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Double> string2DoubleQuietly(List<String> input) {
        return (List<Double>) string2NumberQuietly(input,
                x -> Double.parseDouble(x));
    }

    public static List<? extends Number> string2NumberIngoreException(
            List<String> input, Function<String, ? extends Number> function,
            Number defaultValue) {
        return operator(input, x -> {
            try {
                return function.apply(x);
            } catch (Exception ignore) {
                return defaultValue;
            }
        });
    }

    /**
     * 将String类型的列表转化为Integer类型的列表。不抛出异常，将非Integer类型的元素由defaultVaule代替。
     * 
     * @param input
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> string2Int(List<String> input,
            Integer defaultValue) {
        return (List<Integer>) string2NumberIngoreException(input,
                x -> Integer.parseInt(x), defaultValue);
    }

    /**
     * 将String类型的列表转化为Long类型的列表。不抛出异常，将非Long类型的元素由defaultVaule代替。
     * 
     * @param input
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Long> string2Long(List<String> input, Long defaultValue) {
        return (List<Long>) string2NumberIngoreException(input,
                x -> Long.parseLong(x), defaultValue);
    }

    /**
     * 将String类型的列表转化为Float类型的列表。不抛出异常，将非Long类型的元素由defaultValue代替。
     * 
     * @param input
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Float> string2Float(List<String> input, Float defaultValue) {
        return (List<Float>) string2NumberIngoreException(input,
                x -> Float.parseFloat(x), defaultValue);
    }

    /**
     * 将String类型的列表转化为Double类型的列表。不抛出异常，将非Long类型的元素由defaultValue代替。
     * 
     * @param input
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Double> string2Double(List<String> input,
            Double defaultValue) {
        return (List<Double>) string2NumberIngoreException(input,
                x -> Double.parseDouble(x), defaultValue);
    }
}
