# Python基础知识

## 1. 变量和数据类型

1. 数字：整数或者小数
2. 字符串
3. 列表：方括号
4. 元组：圆括号
5. 字典
6. 对象

## 2. 编码规范

1. 分号：不加

2. 括号：宁缺勿滥，除非是用于实现行连接，否则不用在返回语句或条件语句中使用括号，不过在元组两边用是可以的

3. 缩进：用4个空格，行连接应该用垂直对齐

4. 空行：顶级定义之间空两行，方法之间定义空1行。

5. 空格：不要在逗号，分号，冒号前面加空格，应该在它们后面加（除了行尾）

6. 类继承：一般不继承其他类的时候就继承object

7. 导入：每个导入应该独占一行

8. **命名规范**(不能以数字开头）：module_name, **package_name**, **ClassName**(驼峰命名)，method_name, **ExceptionName**, function_name, GLOBAL_VAR_NAME，instance_var_name,  function_parameter_name, local_var_name

9. 注释规范：python里的文档字符串，文档字符串是包，模块，函数里的第一个语句，这些字符串可以通过对象的doc成员被自动提取

   ```python
   class Person(object):
   	# hahahha
   	pass
   print(Person.__doc__)
   hahahha
   ```

   - 类应该在其定义下有一个描述该类的文档字符串，如果类有公共属性（Attributes),那么文档中应该有一个属性段。

## 3. 数字

1. 进制：0b, 0o,0x ;转换函数bin(x),oct(x),hex(x)

2. 复数

3. 分数：使用分数需要导入Fraction模块

   ```python
   from fractions import Fraction
   f1 = Fraction(3, 4) # 3/4
   ```

4. 小数对象Decimal

   - 小数运算是不精确的，from decimal import Decimal进行精确运算

5. 数字内置模块

   - abs, pow（幂）,round（四舍五入），min,max
   - math: pi,e,floor(下),ceil(上取整),trunc(截取整数）
   - random: seed, shuffle

## 4. 序列

1. 操作符
   - +是连接
   - in，not in在不在序列中
   - *是重复
   - [][:][::]切片操作符
   
2. 索引：正-(0，len-1);负-(-len, -1)

3. 序列内置函数

   - str：转换成字符串

     ```python
     class Person(object):
     	def __init__(self, name, age):
     		self.name = name
     		self.age = age
     		
     	def __str__(self):
     		return self.name+','+str(self.age)
     		
     p1 = Person('hah', 6)
     print(p1) # str(p1) 输出hah,6
     
     ```

   - enumerate

   - min/max

     ```python
     d1 = {'id': 1009,'price': 599}
     d2 = {'id': 1002,'price': 1099}
     d3 = {'id': 1003,'price': 1}
     l = [d1, d2, d3]
     
     print(max(l,key=lambda x: x['id'])) #1009
     ```

   - sum
   
   - reversed
   
   - sorted
   
   - zip

## 5. 迭代器和生成器

1. 迭代器

   - iter()和next()

     ```python
     l = [1, 3, 4]
     it = iter(l)
     print(next(it1))# 1
     ```

   - StopIteration异常

     ```python
     try:
     	print(next(it1))
     except StopIteration as si:
     	print('迭代结束')
     	break
     ```

2. 生成器

   - 函数里使用了yield关键字，那么，这个函数就是一个生成器

   - 生成器是一种推导逻辑，调用生成器返回迭代器

   - 步骤：改列表推导式[]为()或者

     ```python
     l = [2*x for x in range(3)]
     gen = (2*x for x in range(3))
     print(next(gen))# 0
     
     def func():
     	print('step 1')
     	yield
     	print('step 2')
     	yield
     	print('step 3')
     	yield
     it = func()
     print(it)# step 1
     print(it)# step 2
     print(it)# step 3
     ```

     

