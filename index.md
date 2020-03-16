## Lambda技术专题学习

Lambda是从Java8引入的重要的特性。lambda函数式编程提供了方法级别的复用性。基于Lambda，整个开发的编程风格、带可读性、设计思想都受到极大的冲击。学好Lambda是一个现代JAVA攻城狮的必备技能。

---

## Lambda技术专题目录

* [Day1 lambda简化代码](#day1)
* [Day2 lambda提供函数的复用](#day2)
* [Day3 lambda默认函数式接口](#day3)

---

## lambda学习之旅

本专题的学习以小知识点为主，每篇内容尽量控制在3分钟之内。争取利用零碎时间片段看过就学会。作者也建议您详细阅读完整代码，或者自己动手实践每个知识点。

#### <a id="day1">Day1. lambda提高代码可读性</a>

JAVA8推出lambda之后极大的提高了代码的可读性。有Lambda之前，我们使用内部类实现方法的复用

lambda之前：
```
Calculate addition0 = new Calculate() {
  @Override
  public Integer algorithm(Integer x, Integer y) {
    return x + y;
  }
};
```

lambda之后：

```
Calculate addition = (x, y) -> x + y;
```

[day1]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day1_basic.java "basic"
[day1完整例子][day1]

***

#### <a id="day2">Day2. lambda提供函数的复用</a>

平时开发中需要重用方法吗？我们平时开发中常用的策略模式首当其中得益于lambda表达式，如：Comparator、Runnable。

以Comparator为例：我们时长需要为列表排序，排序的策略需要指定不同的Comparator。

lambda之前：

```
Comparator<Integer> increase = new Comparator<Integer>() {
  @Override
   public int compare(Integer o1, Integer o2) {
    return o2 - o1;
  }
};

```

lambda之后：
```
Comparator<Integer> increase = (o1, o2) -> o1 - o2;
```

[day2]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day2_compare.java "compare"
[day2完整例子][day2]

***

#### <a id="day3">Day3. lambda默认函数式接口</a>

如果你经常定义自己的函数式接口，你很容易发现这些常用的接口有几个固定的模式。可能有输入没输出。可能没输入有输出。也可能既有输入也有输出。JDK8中总结了大家常用的使用方式形成函数式接口，并把这些接口广泛用于类库的开发中。

	比如：
		○ java.lang.Cloneable提供接口： E clone();
		○ java.util.Iterator提供接口：Iterator<E> iterator()

这样的2个接口在函数式中可以用Supplier<T>来表示。以上只是列举了一元的几个，后面会一个一个介绍。并引入二元、和柯里化等概念。

[day3]: https://github.com/wzdacyl/lambda/blob/master/src/test/java/com/ibm/leo/share/lambda/Day3_Default_Lambda_Functional_Interface.java "default lambda interface"
[day3完整例子][day3]

***