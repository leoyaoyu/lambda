package com.ibm.leo.share.lambda.Day14_Factory.impl;

import com.ibm.leo.share.lambda.Day14_Factory.Convertor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NokiaConvertor implements Convertor {

    @Override
    public void convert() {
        log.info("this is a Nokia convertor");
    }

    @Override
    public String toString() {
        return "Nokia convertor";
    }
}
