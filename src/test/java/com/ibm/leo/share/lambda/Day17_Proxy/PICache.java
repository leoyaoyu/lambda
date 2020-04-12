package com.ibm.leo.share.lambda.Day17_Proxy;

public class PICache implements PICaclculator {

    Double result = 0.0;

    private PICaclculator calculator;

    public PICache(PICaclculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Double calculate() {
        if(this.result == 0.0){
            this.result =  this.calculator.calculate();
        }
        return result;
    }

}
