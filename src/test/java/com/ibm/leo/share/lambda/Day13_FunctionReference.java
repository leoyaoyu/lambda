package com.ibm.leo.share.lambda;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.*;

@Slf4j
public class Day13_FunctionReference {

    @Data
    @ToString
    class Dog{
        private String name;
        private Integer age;

        public Dog(){}

        public Dog(String name){this.name = name;}

        public Dog(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public void bark(){
            log.info("{} is barking!", this.name);
        }

        public void eat(String food) {
            log.info("{} is eating {}", this.name, food);
        }
    }

    @Test
    public void testConstructor(){
        Supplier<Dog> dogSupplier1 = Dog::new;
        Function<String, Dog> dogSupplier2 = Dog::new;
        BiFunction<String, Integer, Dog> dogSupplier3 = Dog::new;

        log.info("supplier1 built : {}", dogSupplier1.get());
        log.info("supplier2 built : {}", dogSupplier2.apply("Goofy"));
        log.info("supplier3 built : {}", dogSupplier3.apply("Goofy", 9));
    }

    @Test
    public void testReference(){
        Dog goofy = new Dog("Goofy");
        Consumer<Dog> consumer = Dog::bark;
        consumer.accept(goofy);

        Consumer<String> eatConsumer = goofy::eat;
        eatConsumer.accept("bone1");

        BiConsumer<Dog, String> biConsumer = Dog::eat;
        biConsumer.accept(goofy, "bone2");
    }

}
