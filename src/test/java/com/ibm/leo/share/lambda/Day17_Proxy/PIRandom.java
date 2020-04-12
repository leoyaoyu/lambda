package com.ibm.leo.share.lambda.Day17_Proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PIRandom implements PICaclculator {

    @Override
    public Double calculate() {
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
    }

}
