package com.ibm.leo.share.lambda.Day14_Factory.impl;

import com.ibm.leo.share.lambda.Day14_Factory.Convertor;
import com.ibm.leo.share.lambda.Day14_Factory.ConvertorType;
import com.ibm.leo.share.lambda.Day14_Factory.Factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Factory3NormalSingletonImpl implements Factory {

    final static Map<ConvertorType, Convertor> map = new ConcurrentHashMap<>();

    static{
        map.put(ConvertorType.HUAWEI, new HUAWEIConvertor());
        map.put(ConvertorType.ZHONGXING, new ZTEConvertor());
        map.put(ConvertorType.ALCATELLUCENT, new ALUConvertor());
        map.put(ConvertorType.ERICSSON, new EricssonConvertor());
        map.put(ConvertorType.NOKIA, new NokiaConvertor());
    }

    public Convertor generateConvertor(ConvertorType type){
        return this.map.get(type);
    }
}
