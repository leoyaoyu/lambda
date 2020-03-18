## Lambda技术专题学习

Lambda是从Java8引入的重要的特性。lambda函数式编程提供了方法级别的复用性。基于Lambda，整个开发的编程风格、带可读性、设计思想都受到极大的冲击。学好Lambda是一个现代JAVA攻城狮的必备技能。

---

## Lambda专题目录

* [Day1 初亏lambda表达式](#day1)
* [Day2 开发中需要重用方法吗？](#day2)
* [Day3 默认的函数式接口](#day3)
* [Day4 lambda默认接口Predicate](#day4)
* [Day5 lambda默认接口Function](#day5)
* [Day6 lambda默认接口Consumer](#day6)

---

## lambda学习之旅

本专题的学习以小知识点为主，每篇内容尽量控制在3分钟之内。争取利用零碎时间片段看过就学会。作者也建议您详细阅读完整代码，或者自己动手实践每个知识点。

#### <a id="day1">Day1. 初亏lambda表达式</a>

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


[day1]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day1_basic.java "basic"
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

[day2]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day2_compare.java "compare"
[day2完整例子][day2]

***

#### <a id="day3">Day3. 默认的函数式接口</a>

如果你经常定义自己的函数式接口，你很容易发现这些常用的接口有几个固定的模式。可能有输入没输出。可能没输入有输出。也可能既有输入也有输出。JDK8中总结了大家常用的使用方式形成函数式接口，并把这些接口广泛用于类库的开发中。

    比如：
	java.lang.Cloneable提供接口： E clone();
	java.util.Iterator提供接口：Iterator<E> iterator()

这样的2个接口在函数式中可以用Supplier<T>来表示。以上只是列举了一元的几个，后面会一个一个介绍。并引入二元、和柯里化等概念。

[day3]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day3_Default_Lambda_Functional_Interface.java "default lambda interface"
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

[day4]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day4_Predicate.java "Predicate interface"
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


[day5]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day5_Function.java 
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
Consumer<Exception> exceptionLog = (ex2) -> log.warn("User found exception: {}", ex2.getMessage());
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
Consumer<Exception> exceptionLog = (ex2) -> log.warn("User found exception: {}", ex2.getMessage());

//output both kinds of logs
exceptionLog.andThen(detailedExceptionLog).accept(ex);
```

[day6]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day6_Consumer.java 
"Function interface"
[day6完整例子][day6]

***