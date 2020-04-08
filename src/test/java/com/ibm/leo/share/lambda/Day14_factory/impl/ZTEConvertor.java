package com.ibm.leo.share.lambda.Day14_factory.impl;

import com.ibm.leo.share.lambda.Day14_factory.Convertor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZTEConvertor implements Convertor {

    @Override
    public void convert() {
        log.info("this is a zhong xing convertor");
    }
    @Override
    public String toString() {
        return "zte convertor";
    }
}
