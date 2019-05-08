# Aop
Spring &amp; AOP Experiment for Middleware Course

准备操作

熟悉IDEA 和Spring环境编程；

熟悉spring或者spring boot基于MVC的编程；

查看并运行例子： spring-boot-sample-aop.zip

安装mysql数据库 https://www.mysql.com/

实验内容： 

假设学号后4位为n，x=(n%3+1) ，则对应学号为n的同学做2.x的题目。

利用Spring技术实现【实验二】中的校友信息网站。要求采用MVC框架，同时要求加入面向切面的编程。

2.1 构建一个日志记录的切面。要求：

a. 对于所有的Alumni表的查询操作，记录各个操作的时间、用户，读取内容，存入ReadLog表格中。

b. 对于所有的Alumni表的更新（更新和删除）操作，记录各个操作的时间、用户，修改的新值和旧值，存入UpdateLog表格中（删除的新值为null）。

 

2.2 构建一个用户记录的切面。要求：

a. 对于所有的登录操作，记录各各次登录的时间、用户，存入UserLog表格中。

b. 对于所有的登出操作，记录各各次登录的时间、用户，存入UserLog表格中。

c. 对于用户新的输入操作，记录其表单值，存入InsertLog表格中。

 

2.3 构建一个安全验证的切面。要求：

a. 对于所有的Alumni表的查询操作，验证用户已经登录；如果用户没有登录，先导航到登录页面；

b. 对于所有的Alumni表的更新（更新和删除）操作，在Read权限的基础上验证用户具有Update的权限。如果没有，该操作取消，并导航到错误页面。

c. 对于所有的Alumni表的汇总和下载操作，验证用户具有Aggregate权限；如果没有，该操作取消，并导航到错误页面。

 

提示：可以采用@Around annotation.

https://howtodoinjava.com/spring-aop/aspectj-around-annotation-example/

 

 public class Jkl{
     public void executeAround(ProceedingJoinPoint pjp) {
        //certain validations
        if(isValid){
            // optionally enclose within try .. catch
            pjp.proceed();  // this line will invoke your advised method
        } else {
            // log something or do nothing
        }
     }
 }
 

 

作业提交时间：5月7号；

作业抽查时间：5月8号；