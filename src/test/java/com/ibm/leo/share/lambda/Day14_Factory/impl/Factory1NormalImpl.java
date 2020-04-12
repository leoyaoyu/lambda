package com.ibm.leo.share.lambda.Day14_Factory.impl;

import com.ibm.leo.share.lambda.Day14_Factory.Convertor;
import com.ibm.leo.share.lambda.Day14_Factory.ConvertorType;
import com.ibm.leo.share.lambda.Day14_Factory.Factory;

public class Factory1NormalImpl implements Factory {

    @Override
    public Convertor generateConvertor(ConvertorType type) {
        switch (type) {
            case HUAWEI: return new HUAWEIConvertor();
            case ZHONGXING: return new ZTEConvertor();
            case NOKIA: return new NokiaConvertor();
            case ALCATELLUCENT: return new ALUConvertor();
            case ERICSSON: return new EricssonConvertor();
            default: return null;
        }
    }
}
