package com.gufengyy.common;

import java.util.List;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

public class ListUtilTest extends TestCase {

    public void testOperator() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        List<String> result = ListUtil.operator(list, x -> String.valueOf(x));
        for (int i = 0; i < result.size(); i++) {
            assertTrue(result.get(i) instanceof String);
            assertEquals(String.valueOf(list.get(i)), result.get(i));
        }

        List<Double> doubleList = Lists.newArrayList(1.0d, 2.1d, 3.3d, 4.5d,
                5.6d);
        List<String> douleResult = ListUtil.operator(doubleList,
                x -> String.valueOf(x));
        for (int i = 0; i < result.size(); i++) {
            assertTrue(douleResult.get(i) instanceof String);
            assertEquals(String.valueOf(doubleList.get(i)), douleResult.get(i));
        }
    }

    public void testInt2String() {
        List<Integer> input = Lists.newArrayList(1, 2, 3, 4, 5);
        List<String> result = ListUtil.number2String(input);
        for (int i = 0; i < result.size(); i++) {
            assertTrue(result.get(i) instanceof String);
            assertEquals(String.valueOf(input.get(i)), result.get(i));
        }
    }

    public void testString2IntQuietly() {
        List<String> input = Lists.newArrayList("1", "A", null, "0", "-3", "a",
                "5b", String.valueOf(Integer.MAX_VALUE),
                String.valueOf(Integer.MIN_VALUE));
        List<Integer> result = ListUtil.string2IntQuietly(input);
        System.out.println(result);
    }
    
    public void testString2LongQuietly() {
        List<String> input = Lists.newArrayList("1", "A", null, "0", "-3", "a",
                "5b", String.valueOf(Long.MAX_VALUE),
                String.valueOf(Long.MIN_VALUE));
        List<Long> result = ListUtil.string2LongQuietly(input);
        System.out.println(result);
    }
    
    public void testString2DoubleQuietly() {
        List<String> input = Lists.newArrayList("1.333333333d", "A", null, "0", "-3.68", "a",
                "5b", String.valueOf(Double.MAX_VALUE),
                String.valueOf(Double.MIN_VALUE));
        List<Double> result = ListUtil.string2DoubleQuietly(input);
        System.out.println(result);
    }
    
    public void testString2FloatQuietly() {
        List<String> input = Lists.newArrayList("1.3333333f", "A", null, "0", "-3.68", "a",
                "5b", String.valueOf(Float.MAX_VALUE),
                String.valueOf(Float.MIN_VALUE));
        List<Float> result = ListUtil.string2FloatQuietly(input);
        System.out.println(result);
    }
    
    public void testString2IntDefaultValue() {
        List<String> input = Lists.newArrayList("1", "A", null, "0", "-3", "a", "3.0",
                "5b", String.valueOf(Integer.MAX_VALUE),
                String.valueOf(Integer.MIN_VALUE));
        List<Integer> result = ListUtil.string2Int(input, 10000);
        System.out.println(result);
    }
    
    public void testString2LongDefaultValue() {
        List<String> input = Lists.newArrayList("1", "A", null, "0", "-3", "a", "3.0",
                "5b", String.valueOf(Integer.MAX_VALUE),
                String.valueOf(Integer.MIN_VALUE));
        List<Long> result = ListUtil.string2Long(input, 10000L);
        System.out.println(result);
    }
    
    public void testString2DoubleDefaultValue() {
        List<String> input = Lists.newArrayList("1.333333333d", "A", null, "0", "-3.68", "a",
                "5b", String.valueOf(Double.MAX_VALUE),
                String.valueOf(Double.MIN_VALUE));
        List<Double> result = ListUtil.string2Double(input, 9.9999d);
        System.out.println(result);
    }
    
    public void testString2FloatDefaultValue() {
        List<String> input = Lists.newArrayList("1.3333333f", "A", null, "0", "-3.68", "a",
                "5b", String.valueOf(Float.MAX_VALUE),
                String.valueOf(Float.MIN_VALUE));
        List<Float> result = ListUtil.string2Float(input,9.999f);
        System.out.println(result);
    }

}
