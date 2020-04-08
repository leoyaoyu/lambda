package com.ibm.leo.share.lambda.Day14_factory.impl;

import com.ibm.leo.share.lambda.Day14_factory.Convertor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EricssonConvertor implements Convertor {
    @Override
    public void convert() {
        log.info("this is a Ericsson convertor");
    }
    @Override
    public String toString() {
        return "e/// convertor";
    }
}