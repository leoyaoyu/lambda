package com.ibm.leo.share.lambda.Day14_Factory;

public enum ConvertorType {

    HUAWEI("HUAWEI"),ZHONGXING("ZTE"),ALCATELLUCENT("ALU"),ERICSSON("E///"),NOKIA("NOKIA");

    private String name;

    private ConvertorType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
