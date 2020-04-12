package com.ibm.leo.share.lambda.Day17_Proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PIIncircle implements PICaclculator {

    @Override
    public Double calculate() {
        log.info("incircle PI calculator is called!");
        int n = 12;
        double y=1.0, pi = 0.0;
        for(int i=0;i<=n;i++){
            pi = 3 * Math.pow(2, i) * y;
            //System.out.println("第"+i+"次切割,为正"+(6+6*i)+"边形，圆周率π≈"+pi);
            y=Math.sqrt(2-Math.sqrt(4-y*y));
        }
        return pi;
    }

}
