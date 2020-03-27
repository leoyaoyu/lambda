package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Function;

@Slf4j
public class Day11_Currying {

    /**
     * f(x,y,z) -> f(x)(y)(z)
     *
     * e.g.
     * f(x,y,z) = (x-y)*z
     * f(6,y,z)=g(y,z)=(6-y)*z
     * f(6,5,z)=g(4,5)=(6-5)*z
     * f(6,5,4)=g(4,5)=(6-5)*4
     */
    @Test
    public void test(){
        Function<Integer,Function<Integer,Function<Integer,Integer>>> f =
                z -> y -> x -> (x - y) * z;
        int x =6, y =5, z=4;
        Integer result = f.apply(z).apply(y).apply(x);
        log.info("result is {}", result.toString());
    }

}
