package com.ibm.leo.share.lambda.Day14_Factory;

import com.ibm.leo.share.lambda.Day14_Factory.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Day14_Factory {

    @Test
    public void test() {
        Factory factory1 = new Factory1NormalImpl();
        Factory factory3 = new Factory3NormalSingletonImpl();
        Factory factory4 = new Factory2LambdaImpl();
        factory1.generateConvertor(ConvertorType.HUAWEI).convert();
        factory3.generateConvertor(ConvertorType.HUAWEI).convert();
        factory4.generateConvertor(ConvertorType.HUAWEI).convert();
    }
}
