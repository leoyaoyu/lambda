package com.ibm.leo.share.lambda;

import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Slf4j
public class Day7_Supplier {

    Supplier<Double> doubleSupplier = () ->
            IntStream.range(1,3) //(1,50000)
                    .boxed()
                    .map(i -> Double.valueOf(i*i))
                    .peek(i -> log.info(i.toString()))
                    .reduce((acc, item) ->  acc + item)
                    .get();

    @Test
    public void test() {
        log.info("calculated result is : {}", doubleSupplier.get());
    }

    /**
     * supplier will be executed each time
     */
    @Test
    public void test2() {
        log.info("calculated result is : {}", doubleSupplier.get());
    }

    /**
     * guava involves new functions to let suppiler executed result can be cached.
     */
    com.google.common.base.Supplier<Double> guavaSupplier = () ->
            IntStream.range(1,3) //(1,50000)
                    .boxed()
                    .map(i -> Double.valueOf(i*i))
                    .peek(i -> log.info(i.toString()))
                    .reduce((acc, item) ->  acc + item)
                    .get();

    /**
     * always cache
     */
    @Test
    public void testGuava1() {

        com.google.common.base.Supplier<Double> memorizedDoubleSupplier
                = Suppliers.memoize(guavaSupplier);
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
    }

    /**
     * cache result in a timeslot
     *
     * @throws InterruptedException
     */
    @Test
    public void testGuava2() throws InterruptedException {
        com.google.common.base.Supplier<Double> doubleSupplier = () ->
                IntStream.range(1,11) //(1,50000)
                        .boxed()
                        .map(i -> Double.valueOf(i*i))
                        .peek(i -> log.info(i.toString()))
                        .reduce((acc, item) ->  acc + item)
                        .get();

        com.google.common.base.Supplier<Double> memorizedDoubleSupplier
                = Suppliers.memoizeWithExpiration(doubleSupplier, 50, TimeUnit.MILLISECONDS);
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
        TimeUnit.MILLISECONDS.sleep(50);
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
        log.info("calculated result is : {}", memorizedDoubleSupplier.get());
    }
}
