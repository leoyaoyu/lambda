package com.ibm.leo.share.lambda.Day14_Factory.impl;

import com.ibm.leo.share.lambda.Day14_Factory.Convertor;
import com.ibm.leo.share.lambda.Day14_Factory.ConvertorType;
import com.ibm.leo.share.lambda.Day14_Factory.Factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Factory2LambdaImpl implements Factory {

    final static Map<ConvertorType,Supplier<Convertor>> map = new ConcurrentHashMap<>();

    static{
        map.put(ConvertorType.HUAWEI, HUAWEIConvertor::new);
        map.put(ConvertorType.ZHONGXING, ZTEConvertor::new);
        map.put(ConvertorType.ALCATELLUCENT, ALUConvertor::new);
        map.put(ConvertorType.ERICSSON, EricssonConvertor::new);
        map.put(ConvertorType.NOKIA, NokiaConvertor::new);
    }

    public Convertor generateConvertor(ConvertorType type){
        return this.map.get(type).get();
    }
}
