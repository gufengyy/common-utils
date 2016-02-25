package com.gufengyy.common;

import java.util.List;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

public class ListUtilTest extends TestCase {

    public void testOperator(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        List<String> result = ListUtil.operator(list, x -> x + "");
        for(int i=0;i<result.size();i++){
            assertTrue(result.get(i) instanceof String);
            assertEquals(String.valueOf(list.get(i)), result.get(i));
        }
    }
}
