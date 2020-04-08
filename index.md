## Lambda技术专题学习

Lambda是从Java8引入的重要的特性。lambda函数式编程提供了方法级别的复用性。基于Lambda，整个开发的编程风格、带可读性、设计思想都受到极大的冲击。学好Lambda是一个现代JAVA攻城狮的必备技能。

---

## Lambda专题目录

* [Day1 初窥lambda表达式](#day1)
* [Day2 开发中需要重用方法吗？](#day2)
* [Day3 默认的函数式接口](#day3)
* [Day4 lambda默认接口Predicate](#day4)
* [Day5 lambda默认接口Function](#day5)
* [Day6 lambda默认接口Consumer](#day6)
* [Day7 lambda默认接口Supplier](#day7)
* [Day8 lambda默认接口UnaryOperator](#day8)
* [Day9 lambda默认接口BiFunction](#day9)
* [Day10 lambda默认接口BinaryOperator](#day10)
* [Day11 lambda函数柯里化Currying](#day11)
* [Day12 自定义lambda函数式接口](#day12)
* [Day13 lambda函数的方法引用](#day13)
* [Day14 lambda与设计模式：工厂模式](#day14)

---

## lambda学习之旅

本专题的学习以小知识点为主，每篇内容尽量控制在3分钟之内。争取利用零碎时间片段看过就学会。作者也建议您详细阅读完整代码，或者自己动手实践每个知识点。

#### <a id="day1">Day1. 初窥lambda表达式</a>

漫天都是lambda表达式，它长啥样呢？来看一个lambda表达式的例子：
```
Calculate addition = (x, y) -> x + y;
```

自从JAVA8推出lambda之后很多Java代码都变成这个风格的了，这看起来好像不是很好理解。简单解释一下其中的意思：x、y是输入参数。箭头后面的部分是计算方法。addition
是可被重用的方法的名字。具体使用的时候传入x、y
的值即可:，如：addition.algorithm(3,6)，可以获得答案9。


有Lambda之前，这种方法的复用是怎么实现的呢？lambda之前方法复用的实现方式是：内部类。我们实现一个和上面lambda表达式一样的内部类，代码如下：
```
Calculate addition0 = new Calculate() {
  @Override
  public Integer algorithm(Integer x, Integer y) {
    return x + y;
  }
};
```


[day1]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day1_basic.java "basic"
[day1完整例子][day1]

***

#### <a id="day2">Day2. 开发中需要重用方法吗？</a>

平时开发中需要重用方法吗？由于JAVA之前的开发中要复用方法的实现很不方便，所以从开发习惯上大家用得不是很多。但是在弱类型的JS中方法重用是家常便饭。对于JAVA
来讲，也有不少需要类库层面就鼓励我们方法重用的例子。如：Comparator
、Runnable（它们都是策略模式，策略模式最直观的可以用上lambda，建议大家在自己的开发中遇到策略模式直接选用lambda实现）。

以Comparator为例：我们时长需要为列表排序，排序的策略需要指定不同的Comparator。有lambda之前，大家往往会这么实现：

```
Comparator<Integer> increase = new Comparator<Integer>() {
  @Override
   public int compare(Integer o1, Integer o2) {
    return o2 - o1;
  }
};

```

lambda之后，代码变得更简单清晰了：
```
Comparator<Integer> increase = (o1, o2) -> o1 - o2;
```

[day2]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day2_compare.java "compare"
[day2完整例子][day2]

***

#### <a id="day3">Day3. 默认的函数式接口</a>

如果你经常定义自己的函数式接口，你很容易发现这些常用的接口有几个固定的模式。可能有输入没输出。可能没输入有输出。也可能既有输入也有输出。JDK8中总结了大家常用的使用方式形成函数式接口，并把这些接口广泛用于类库的开发中。

    比如：
	java.lang.Cloneable提供接口： E clone();
	java.util.Iterator提供接口：Iterator<E> iterator()

这样的2个接口在函数式中可以用Supplier<T>来表示。以上只是列举了一元的几个，后面会一个一个介绍。并引入二元、和柯里化等概念。

[day3]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day3_Default_Lambda_Functional_Interface.java "default lambda interface"
[day3完整例子][day3]

***

#### <a id="day4">Day4. lambda默认接口Predicate</a>

Predicate接口用来做各种真假判断。函数式默认接口是test()方法。指定类型T的输入，返回都是boolean值。

```
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```
除了函数式接口test()之外，还提供了几个default实现，用来组合Predicate做并、交和否的操作。

* 例子：判断字符串不为空、并且等于我们指定的两个中的一个：

```
Predicate<String> p1 = (str) -> str == null;
Predicate<String> p2 = (str) -> !str.isEmpty();
Predicate<String> p3 = (str) -> str.equals(SERVICE_NAME1);
Predicate<String> p4 = (str) -> str.equals(SERVICE_NAME2);
if(p1.negate().and(p2).and(p3.or(p4)).test(input)){
	...
}
```

[day4]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day4_Predicate.java "Predicate interface"
[day4完整例子][day4]

***

#### <a id="day5">Day5. lambda默认接口Function</a>
Function<T,R> 提供一个apply方法，其输入是T类型，输出是R类型。具体接口定义如下：
```
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```
例子：
```
Function<Integer, String> threadName = (x) -> "Thread_" + x;
String result = threadName.apply(6);
```
定义了一个函数，输入Integer类型的数字(如:6)，输出是一个字符串(Thread_6)。

###### [Default method]
除了基本的接口定义，还提供了两个default方法compose和andThen。快速的理解一下这两个方法。假设有两个函数f(x)和g(x)。
* compose: f.compose(g).apply(x)的意思是x作为输入，先执行g(x)，然后再把g(x)的结果作为输入执行 f(x)函数；
* andThen: f.andThen(g).apply(x)的意思是x作为输入，先执行f(x)，然后再把f(x)的结果作为输入执行 g(x)函数；

因此g.compose(f)等价于f.andThen(g)。

这两个方法很实用，可以把Function方法串起来使用实现函数的高阶运算。

例如: 当我们需要计算(x+6)^2时，可以用两个函数来实现：f(x) = x+6; g(x) = x*x; (x+6)^2 = g(f(x)); 
使用Function的这两个方法就是：g.compose(f).apply(x)或者f.andThen(g).apply(x)。


[day5]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day5_Function.java 
"Function interface"
[day5完整例子][day5]

***

#### <a id="day6">Day6. lambda默认接口Consumer</a>
Consumer<T> 提供一个accept方法，它仅消费一个T类型的入参，并不输出任何值。具体接口定义如下：
```
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```
例子：
```
Exception ex = new NullPointerException("can not find the user with id AD34CVER3");
Consumer<Exception> exceptionLog = (ex2) -> 
    log.warn("User found exception: {}", ex2.getMessage());
exceptionLog.accept(ex);
```
exceptionLog定义了一个把exception的message打印出来的函数，输入是一个Exception，并不需要输出任何值。

###### [Default method]
除了基本的接口定义，还提供了1个default方法andThen。andThen方法可以让两个consumer共享同一个输入。

例如上面的例子中我们需要把Exception的message信息打印到Warning
的日志中。如果在debug level的日志输出，我们希望把更细节的exception的trace也全部打印到log日志中。两种打印方法使用的都是同一个Exception作为输入。这个时候即可用andThen
方法把两个函数串起来使用，代码如下所示：

```
Exception ex = new NullPointerException("can not find the user with id AD34CVER3");

//output detailed log stack trace in debug log;
Consumer<Exception> detailedExceptionLog = (ex1) ->{
    StackTraceElement[] elements = ex1.getStackTrace();
    Stream.of(elements).forEach(trace ->
        log.debug("User found exception: {}", trace)
    );
};

//only output exception message in warning log;
Consumer<Exception> exceptionLog = (ex2) -> 
    log.warn("User found exception: {}", ex2.getMessage());

//output both kinds of logs
exceptionLog.andThen(detailedExceptionLog).accept(ex);
```

[day6]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day6_Consumer.java 
"Function interface"
[day6完整例子][day6]

***

#### <a id="day7">Day7. lambda默认接口Supplier</a>
Supplier<T> 提供一个get方法，无输入，生产一个T类型的输出。具体接口定义如下：
```
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

```
Supplier主要是用于提供数据。它时常用于懒加载，既在需要数据的时候才去生成相应的数据，已节约初始化时的资源或者降低时间开销。在没有Supplier之前我们常采用Proxy代理模式的方式去懒加载一个资源。但是有了Supplier
之后资源的懒加载变得更简单，与代理模式相比代码可读性也变强很多。例子：

```
Supplier<Double> doubleSupplier = () ->
       IntStream.range(1,3) //(1,50000)
              .boxed()
              .map(i -> Double.valueOf(i*i))
              .peek(i -> log.info(i.toString()))
              .reduce((acc, item) ->  acc + item)
              .get();
log.info("calculated result is : {}", doubleSupplier.get());
```
代码中Stream的部分不必看懂，可以简单理解为用数值的map/reduce模拟了一个复杂计算。实际的计算可能比这个复杂N倍。但我们只在真的需要这个计算结果的时候才去调用supplier方法以获得结果。

[day7]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day7_Supplier.java 
"Suppiler interface"
[day7完整例子][day7]

***

#### <a id="day8">Day8. lambda默认接口UnaryOperator</a>
UnaryOperator<T>其实是一个Function<T,R>接口的简化版本。Function输入是T类型，输出是R类型。而UnaryOperator输入输出都是同一个类型。具体接口定义如下：
```
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}
```
简化的例子：
```
Function<Integer, String> threadName1 = (str) -> "Thread_" + str;
UnaryOperator<String> threadName2 = (str) -> "Thread_" + str;
```
threadName1与threadName2实现的是同样的功能，threadName2比threadName1更简单一些。

*UnaryOperator和Function都有一个static identity方法，暂时不用细扣，这个会留到后面做介绍。static会在自定义函数式接口中做介绍，identity方法会留到Stream收集器中做介绍。

[day8]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day8_UnaryOperator.java 
"UnaryOperator interface"
[day8完整例子][day8]

***

#### <a id="day9">Day9. lambda默认接口BiFunction</a>
有了Function一个输入一个输出，如果有两个输入怎么办？JDK8提供了另一个函数式接口BiFunction<T, U, R> 提供一个apply方法，其输入是T和U类型，输出是R类型。具体接口定义如下：
```
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```
例子：
```
BiFunction<Double, Double, Double> rectangle = (x, y) -> x * y;
rectangle.apply(4.0, 5.0) //20.0
```
定义了一个函数rectangle来计算长方形的面积，输入长方形的长宽(如:4.0, 5.0)，调用apply之后输出它的面积(20.0)。

###### [Default method]
除了基本的接口定义，还提供了1个default方法andThen。andThen的用法和[Function接口的类似](#Day5)，但是要注意的是andThen的串联上的是一个Function函数而不是BiFunction。
```
default <V> BiFunction<T, U, V> 
            andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (T t, U u) -> after.apply(apply(t, u));
}
```
我们可以使用andThen来实现几种规则平面的面积计算，例子如下。
```
Function<Double, String> area = (x) -> "area is " + x;
BiFunction<Double, Double, Double> rectangle = (x, y) -> x * y;
BiFunction<Double, Double, Double> triangle = (a, h) -> a * h / 2;
Function<Double, Double> cycle = (r) -> r * 3.14159265354;

rectangle.andThen(area).apply(4.0, 5.0);
triangle.andThen(area).apply(4.0, 5.0);
cycle.andThen(area).apply(4.0);

```

[day9]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day9_BiFunction.java 
"Function interface"
[day9完整例子][day9]

***

#### <a id="day10">Day10. lambda默认接口BinaryOperator</a>
BinaryOperator<T>是BiFunction<T, U, V>函数的简化版本。与UnaryOperator<T>是Function<T,
R>接口的简化版本类似（可参考day8），BinaryOperator的所有输入输出都是同一个类型。具体接口定义如下：
```
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    ...
}
```
一个实现二元运算的例子：
```
private Integer calculation(Integer x, Integer y, BinaryOperator<Integer> operation) {
    return operation.apply(x, y);
}
int a =8, b =9;
log.info("plus: "+ calculation(a,b, (x, y) -> x + y).toString());
log.info("minus: "+ calculation(a,b, (x, y) -> x - y).toString());
log.info("multiple: "+ calculation(a,b, (x, y) -> x * y).toString());
log.info("divide: "+ calculation(a,b, (x, y) -> x / y).toString());
log.info("reminder: "+ calculation(a,b, (x, y) -> x % y).toString());
```

###### [Default method]
BinaryOperator提供两个default方法：minBy/maxBy，接收一个Comparator来对输入的两个值排序，例子：
```
Comparator<Integer> comparator = (x, y) -> y - x;
BinaryOperator<Integer> max = BinaryOperator.maxBy(comparator);
BinaryOperator<Integer> min = BinaryOperator.minBy(comparator);
log.info("BinaryOperator max:"+ max.apply(a,b).toString());
log.info("BinaryOperator min:"+ min.apply(a,b).toString());
```
上面的例子按照从大到小的顺序排列，所以maxBy反而输出的是小的值，minBy输出的是大值。这两个default方法在后面Stream中做元素的提取时能起到很大的作用。

[day10]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day10_BinaryOperator.java 
"UnaryOperator interface"
[day10完整例子][day10]

***

#### <a id="day11">Day11. lambda函数柯里化(Currying)</a>
我们在Day5介绍了Function<T,R>，它是给1个输入返回一个输出，在Day9中介绍的BiFunction<T, U, 
V>是给2个输入一个输出。如果是3个输入、4个输入、5个输入...N个输入呢？TriFunction? TetraFunction? PentaFunction? ...这么定义下去无穷尽了。函数的柯里化就是用来解决这个问题的。

柯里化的数学表示是f(x,y,z) = f(x)f(y)f(z)。在此不做数学证明，直接使用这个结论。仅举例说明一下，例如想计算：f(x,y,z) = (x-y)*z
假设x=6, y=5已经知道了，那f(x,y,z) = (x-y)*z就转化为 f(z) = (6 - 5) * z;
同理，假设x=6, z=4已经知道了，那f(x,y,z) = (x-y)*z就转化为 f(y) = (6 - y) * 4;
同理，假设y=5, z=4已经知道了，那f(x,y,z) = (x-y)*z就转化为 f(x) = (x - 5) * 4;

柯里化后，我们可以把输入参数一个一个的传进去：f(x,y,z) = f(x(f(y(f(z)))))，因此f(x,y,z)用lambda表示就是Function<Integer,Function<Integer,
Function<Integer,Integer>>>。

计算f(x,y,z) = f(x) = (x - 5) * 4的代码例子如下：
```
Function<Integer,Function<Integer,Function<Integer,Integer>>> f =
        z -> y -> x -> (x - y) * z;
int x =6, y =5, z=4;
Integer result = f.apply(z).apply(y).apply(x);
log.info("result is {}", result.toString());
```
lambda的柯里化中需要特别注意apply的顺序：f(x(f(y(f(z)))))的顺序是从最里面的函数开始apply，因此是f.apply(z).apply(y).apply(x)。

[day11]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day11_Currying.java 
"currying"
[day11完整例子][day11]

***

#### <a id="day12">Day12. 自定义lambda函数式接口</a>
看了这么多默认的函数式接口，我们该如何自定义一个自己的函数式接口呢？将详细介绍自动以的函数式接口。其中包括三个方面。
* 一个接口方法；
* default方法 & static方法；

I. 首先，一个函数式接口只能有一个接口方法，并在类上使用@FunctionalInterface标签标注（@FunctionalInterface不是必须的，只是用于编译的时候做类型检查）。例如Iterable接口就只有一个方法。
```java
public interface Iterable<T> {
  Iterator<T> iterator();
}
```
但Iterator就不是一个函数式接口，他提供多个方法，它就没法用函数式的方式调用这个接口。
```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
}
```

类似的，我们可以扩展一下day1的例子，来定义一个二元运算的接口如下：
```java
@FunctionalInterface
public interface Calculate<T> {
    T algorithm(T x, T y);
}
```

II. 其次，JDK8开始为接口引入了default和static方法，这两种方法在lambda的函数式接口设计中非常实用，大大扩展了函数式接口的能力，如之前介绍的几个默认函数式接口中的default方法compose/andThen，
还有static方法maxBy/minBy。

1). default方法。它用来实现一些与实例相关的扩展操作（注意区分面向对象中的"类"和"实例"）。例如：我们实现类似Function和BiFunction一样的andThen来让我们定义的Calculate<T>可以接一个Function方法，可以这么实现：
```
default BinaryOperator<T> andThen
            (Function<? super T, ? extends T> after){
    Objects.requireNonNull(after);
    return (T x, T y) -> after.apply(algorithm(x, y));
}
```
default方法是可以重载的，类似的也可以用andThen接一个Consumer<T>：
```
default BiConsumer<T, T> andThen(Consumer<? super T> after){
    Objects.requireNonNull(after);
    return (T x, T y) -> {
        T z = algorithm(x, y);
        after.accept(z);
    };
}
```
注：以上两个方法，都直接使用了this.algorithm(x, y)，因此与类的实例有关，是对接口的扩展。


2). Static方法用来实现一些与"类"相关的操作，与具体实例无关(不可以调用this)。例如：
```
static Class<Calculate> getCalulateClass(){
    return Calculate.class;
}
```
[day12]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day12_SelfLambda.java 
"self functional interface"
[day12完整例子][day12]


***

#### <a id="day13">Day13. lambda函数的方法引用</a>
昨天介绍了如何自定义lambda函数，本节将介绍如何使用lambda函数。除了day1里介绍的最简单的使用方法，还可以用方法引用的方式使用lambda函数式。方法引用在各种设计模式和Stream
中极为常见。本节分两类介绍方法引用，一类是构造方法的方法引用，一类是一般方法的方法引用。以下举例说明,先给一个自定义的Dog类。本节建议[结合实例][day13]阅读。

```java
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
```

###### I. 构造方法的方法引用

我们可以直接使用lambda默认的函数式接口来为类中的构造方法创建函数式的方法引用，如下：
```java
Supplier<Dog> dogSupplier1 = Dog::new;
Function<String, Dog> dogSupplier2 = Dog::new;
BiFunction<String, Integer, Dog> dogSupplier3 = Dog::new;

log.info("supplier1 built : {}", dogSupplier1.get());
log.info("supplier2 built : {}", dogSupplier2.apply("Goofy"));
log.info("supplier3 built : {}", dogSupplier3.apply("Goofy", 9));
```

###### II. 一般方法的方法引用

也可以为类中一般方法创造函数式的方法引用，如下，
```java
Dog goofy = new Dog("Goofy");
Consumer<Dog> consumer = Dog::bark;
consumer.accept(goofy);

Consumer<String> eatConsumer = goofy::eat;
eatConsumer.accept("bone1");

BiConsumer<Dog, String> biConsumer = Dog::eat;
biConsumer.accept(goofy, "bone2");
```

[day13]: https://github.com/leoyaoyu/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day13_FunctionReference.java
"FunctionReference"
[day13完整例子][day13]

***

#### <a id="day14">Day14. lambda与设计模式：工厂模式</a>

后面几节介绍lambda在设计模式中的写法变化，需要一定的设计模式的基础知识。lambda的引入并不一定简化了设计模式的写法。引入这个模块一方面体现一些lambda之后的写法变化，一方面借机会复习一下设计模式。

##### 工厂模式
把对象的创建抽象到一个工厂类中实现。

##### 使用场景

1.工厂模式在创建使用类的时候有大量的if/else分支做判断创建不同的对象，而且可能增加或者修改这些对象的创建方法时，考虑将这一大坨if/else抽取出来放到工厂中实现。

2.当对象的创建过程比较复杂的时候（例如：需要组合其他类，需要初始化操作）。在创建对象时建议封装到工厂类中。


##### 实现方法
1.简单工厂; 2.工厂方法; 3.抽象工厂

我们以最常用的简单工厂为例进行lambda扩展。举一个实际的例子，之前在做电信系统的时候，有一个场景是需要把不同厂商的网元信息采集并汇总到统一的监控系统中，从而监控网元是否工作正常。虽然各家厂商都遵循NGOSS
的规范，但是各家厂商的网元信息格式是不同的。我们需要抽取出一个统一的格式进行存储。因此需要对各家厂商做一个转化器(Convertor)来转化网元信息为统一格式。主流厂商有Huawei、Ericsson、Alcatel-Lucent、ZTE、Nokia，也就对应有5个convertor。可能还有更多，如三星。

lambda前

```java
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
```

lambda后
```java
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
```

[day14]: https://github.com/leoyaoyu/lambda/tree/master/src/test/java/com/ibm/leo/share/lambda/Day14_factory
"lambda in factory design pattern"
[day14完整例子][day14]

***