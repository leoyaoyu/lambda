package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Slf4j
public class Day10_BinaryOperator {

    private Integer calculation(Integer x, Integer y,
                                BinaryOperator<Integer> operation) {
        return operation.apply(x, y);
    }

    @Test
    public void test(){
        int a =8;
        int b =9;

        log.info("plus: "+ calculation(a,b, (x, y) -> x + y).toString());
        log.info("minus: "+ calculation(a,b, (x, y) -> x - y).toString());
        log.info("multiple: "+ calculation(a,b, (x, y) -> x * y).toString());
        log.info("divide: "+ calculation(a,b, (x, y) -> x / y).toString());
        log.info("reminder: "+ calculation(a,b, (x, y) -> x % y).toString());

        Comparator<Integer> comparator = (x, y) -> y - x;
        BinaryOperator<Integer> max = BinaryOperator.maxBy(comparator);
        BinaryOperator<Integer> min = BinaryOperator.minBy(comparator);

        log.info("BinaryOperator max:"+ max.apply(a,b).toString());
        log.info("BinaryOperator min:"+ min.apply(a,b).toString());
    }

    User[] list = null;
    @Before
    public void init(){
        User u1 = User.builder().name("user1").age(24).salary(6000).build();
        User u2 = User.builder().name("user2").age(29).salary(5000).build();
        User u3 = User.builder().name("user3").age(34).salary(6000).build();
        User u4 = User.builder().name("user4").age(26).salary(8000).build();
        User u5 = User.builder().name("user5").age(38).salary(8000).build();
        list = new User[]{u1, u2, u3, u4, u5};
    }
    @Test
    public void testStream() {

        User result1 = Stream.of(list)
                .reduce(BinaryOperator.maxBy(
                        Comparator.comparing(User::getAge)))
                .get();

        User result2 = Stream.of(list)
                .reduce(BinaryOperator.minBy(
                        Comparator.comparing(User::getAge)))
                .get();

        log.info("list age max: " + result1.toString());
        log.info("list age mix: " + result2.toString());
        log.info("list salary max: " + Stream.of(list)
                .reduce(BinaryOperator.maxBy(Comparator.comparing(User::getSalary)))
                .get().toString());
        log.info("list salary mix: " + Stream.of(list)
                .reduce(BinaryOperator.minBy(Comparator.comparing(User::getSalary)))
                .get().toString());

    }
}
