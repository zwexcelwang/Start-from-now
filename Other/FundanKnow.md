# Java基础知识

## 1. 查漏补缺

建议String[] args，避免歧义和误读

javac Hello.java ---->  Hello.class（字节码程序） 编译
java Hello  解释执行

##### 访问控制修饰符

default (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。

private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）

public : 对所有类可见。使用对象：类、接口、变量、方法

protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。

##### 很有用的一些类
- Math
- Character
- String (因为String是不可修改的，要一个一个加的时候就用StringBuffer或StringBuilder)
StringBuilder 类在 Java 5 中被提出，它和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）。
由于 StringBuilder 相较于 StringBuffer 有速度优势，所以多数情况下建议使用 StringBuilder 类。
- Array
- Date

