package com.ibm.leo.share.lambda.Day16_Strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class PI2 {

    static Supplier<Double> random = () -> {
        log.info("random PI calculator is called!");
        int n= 1000000;
        int k=0;
        for(int i=0; i<=n; i++) {
            double x = Math.random();
            double y = Math.random();
            if((x*x + y*y)<=1)   //判断是否落入圆内
                k++;
        }
        return 4*k/(double)n;
    };

    static Supplier<Double> incircle = () -> {
        log.info("incircle PI calculator is called!");
        int n = 12;
        double y=1.0, pi = 0.0;
        for(int i=0;i<=n;i++){
            pi = 3 * Math.pow(2, i) * y;
            //System.out.println("第"+i+"次切割,为正"+(6+6*i)+"边形，圆周率π≈"+pi);
            y=Math.sqrt(2-Math.sqrt(4-y*y));
        }
        return pi;
    };

    public Double getPI(Supplier<Double> calculator) {
        return calculator.get();
    }

}
