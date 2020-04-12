package com.ibm.leo.share.lambda.Day15_Builder;

public class MandatoryParamException extends RuntimeException {

    String paraName;

    public MandatoryParamException(String paraName){
        super("parameter is mandatory for builder!" + paraName);
    }
}
