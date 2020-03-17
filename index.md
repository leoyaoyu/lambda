## Lambda技术专题学习

Lambda是从Java8引入的重要的特性。lambda函数式编程提供了方法级别的复用性。基于Lambda，整个开发的编程风格、带可读性、设计思想都受到极大的冲击。学好Lambda是一个现代JAVA攻城狮的必备技能。

---

## Lambda专题目录

* [Day1 初亏lambda表达式](#day1)
* [Day2 开发中需要重用方法吗？](#day2)
* [Day3 默认的函数式接口](#day3)
* [Day4 lambda默认接口Predicate](#day4)

---

## lambda学习之旅

本专题的学习以小知识点为主，每篇内容尽量控制在3分钟之内。争取利用零碎时间片段看过就学会。作者也建议您详细阅读完整代码，或者自己动手实践每个知识点。

#### <a id="day1">Day1. 初亏lambda表达式</a>

漫天都是lambda表达式，它长啥样呢？来看一个lambda表达式的例子：
```
Calculate addition = (x, y) -> x + y;
```

自从JAVA8推出lambda之后很多Java代码都编程这个风格的了，这看起来好像不是很好理解。简单解释一下其中的意思：x、y是输入参数。箭头后面的部分是计算方法。addition是可被重用的方法的名字。具体使用的时候传入x、y
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