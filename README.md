# 什么是参数校验
    比如有个参数要求是一个金额，前端没做限制或者用户绕过前端判断，传入一个负数，或者一个字母，那么我们的接口就会报错。
    所以，通常我们需要在方法的开始处，对入参的参数进行校验，不符合要求就报错返回，不往下进行。这个校验的过程就是参数校验。
# 为什么要使用统一参数校验
    如果是一个方法，那很容易，在方法执行开始的之后if-else判断返回就可以。
    但是如果一个项目的所有接口方法都需要校验呢？会出现很多重复且繁琐的代码，而且校验代码会和业务代码混在一起。
    所以就需要在一个位置上添加参数的统一校验，解决提到的耦合、重复等问题。
# 现有的校验框架
    一个是javax的`validation-api`(现在更名为`jakarta.validation-api`)，使用@Valid注解。遵循的是标准JSR-303规范，声明了@Valid这类接口，而`Hibernate-validator`对其进行了实现。
    另一个是Spring的`Validator`框架，使用@Validated注解进行参数判断
# JSR-303
    JSR是Java Specification Requests的缩写，意思是Java规范提案。任何人都可以提交JSR，都可以向Java平台增添新的API和服务。
    JSR-303就是JavaEE6.0中为`Bean Validation`提出的一项子规范。官网地址 https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#preface
# 能实现的功能
    `Bean Validation`中内置的注解判断
    空检查
    @Null			验证对象是否为null
    @NotNull		验证对象是否不为null, 无法查检长度为0的字符串
    @NotBlank		检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.

    Booelan检查
    @AssertTrue		验证 Boolean 对象是否为 true  
    @AssertFalse	验证 Boolean 对象是否为 false
    
    长度检查
    @Size(min=, max=)		验证对象（Array,Collection,Map,String）长度是否在给定的范围之内，如果为null,不进行验证，算通过验证。
    
    日期检查
    @Past		验证 Date 和 Calendar 对象是否在当前时间之前  
    @Future		验证 Date 和 Calendar 对象是否在当前时间之后  
    @Pattern	验证 String 对象是否符合正则表达式的规则
    
    数值检查，建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为"",Integer为null
    @Min			验证 Number 和 String 对象是否大等于指定的值，如果为null,不进行验证，算通过验证。
    @Max			验证 Number 和 String 对象是否小等于指定的值，如果为null,不进行验证，算通过验证。
    @DecimalMax		被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
    @DecimalMin		被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
    @Digits			验证 Number 和 String 的构成是否合法  
    @Digits(integer=,fraction=)		验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。


    `Hibernate Validator`扩展的注解判断
    @NotEmpty		        检查约束元素是否为NULL或者是EMPTY.
    @Length(min=, max=)		验证注解的元素值长度在min和max区间内
    @Range(min=, max=)	    验证注解的元素值在最小值和最大值之间
    @Email                  验证是否是邮件地址，如果为null,不进行验证，算通过验证。

# 两种注解区别
    位置：
    @Validated：可以用在类型、方法和方法参数上。但是不能用在成员属性（字段）上
    @Valid：可以用在方法、构造函数、方法参数和成员属性（字段）上
    功能：
    @Validated spring扩展了分组校验功能
    @Valid作为标准JSR-303规范，支持加在字段上，所以支持嵌套校验。
# 如何使用这两个框架
    1. 添加validation依赖
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>

    2. 添加异常校验处理 处理参数校验产生的异常
![img](/src/main/resources/static/img.png)

    3. 对方法参数进行拦截判断
      * 对@RequestParam和@PathVariable的参数判断
      * 对get请求对象,对get请求表单判断
      * 对post/put/delete请求@RequestBody判断
# 具体拦截方法
## 对get请求 @RequestParam和PathVariable类型参数校验
```java
//注意:使用@RequestParam 或者@PathVariable 的方式传递参数,必须在类上添加@Validated注解
@Validated
public class StudentWebValidatedController {
    /**
     * get请求url参数或者 form表单参数,必须在class上添加@Validated才能生效
     *
     * @param id
     * @return
     */
    @GutMapping
    public StudentValidatedDto findById(@Min(value = 1, message = "id不能小于1") @RequestParam Integer id,
                                        @Min(value = 1, message = "id不能小于1") @PathVariable Integer id) {
        return null;
    }
}
```
## 对get请求 对象类型参数校验
```java
@Validated
public class StudentWebValidatedController {
    /**
     * get请求对象
     *
     * @param dto
     * @return
     */
    @GetMapping(value = "query")
    public List<StudentValidatedDto> query(@Validated StudentValidatedDto dto) {
        return null;
    }
}
@Data
public class StudentValidatedDto {
    /**
     * 主键
     */
    @NotNull(message = "学生id不能为空")
    private Integer id;
    /**
     * 标题
     */
    @NotEmpty(message = "学生名称不能为空")
    private String title;
}
```
## 对post,put,delete请求 对象类型参数校验
```java
@Validated
public class StudentWebValidatedController {
    /**
     * body参数
     *
     * @param dto
     * @return
     */
    @PostMapping
    public boolean insert(@Validated @RequestBody StudentValidatedDto dto) {
        return null;
    }
}
@Data
public class StudentValidatedDto {
    /**
     * 主键
     */
    @NotNull(message = "学生id不能为空")
    private Integer id;
    /**
     * 标题
     */
    @NotEmpty(message = "学生名称不能为空")
    private String title;
}
```
# 常见疑问点
## `spring-boot-starter-validation`依赖是否是必须的,为什么有些项目没有引入也可以使用统一验证
    1. 在springboot2.3之前是默认引入`spring-boot-starter-web`的,但是在2.3之后需要用户自行选择是否需要添加进来
![img_2](/src/main/resources/static/img_2.png)

    2. 真正实现统一拦截校验的是`hibernate-validator`,所以如果项目中已经引入`hibernate-validator`,就可以直接使用统一验证
![img_3](/src/main/resources/static/img_1.png)

# 参考文档
## spring-validation教程
    https://www.baeldung.com/spring-boot-bean-validation
## JSR-303规范
    `Java Specification Requests`java规范提案。JSR-303就是JavaEE6.0中为`Bean Validation`提出的规范。这项规范的是由`Hibernate-validator`实现。
    `Jakarta Bean Validation 2.0`在本质上是套壳版的`Bean Validation 2.0`，因为前者只是将maven坐标由`javax.validation:javax.validation-api`更新为`jakarta.validation:jakarta.validation-api`
    参考文档 https://zhuanlan.zhihu.com/p/86060320
## Hibernate 不止是ORM
    https://hibernate.org
## @Validated和@Valid教程
    https://blog.csdn.net/wounler/article/details/125233260
    https://juejin.cn/post/7213634349233111100
## spring-mvc进行统一验证原理
    https://juejin.cn/post/7243337546825187385
## 测试验证使用demo
    https://github.com/chen-junl/spring-valid-demo