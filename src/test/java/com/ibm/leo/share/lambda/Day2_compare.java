package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class Day2_compare {

    List<Integer> list = Arrays.asList(2,6,4,9,3,7);

    @Test
    public void test1() {
        log.info("before compare: {}",list);
        Comparator<Integer> increase = (o1, o2) -> o2 - o1;
        list.sort(increase);
        log.info("after compare: {}",list);
    }

    @Test
    public void test2() {
        log.info("before compare: {}",list);
        Comparator<Integer> increase = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        list.sort(increase);
        log.info("after compare: {}",list);
    }
}
