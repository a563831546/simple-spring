## step2

1. BeanDefinition类存储Object对象转换为Class类型信息
2. 添加**AbstractAutowireCapableBeanFactory**类负责实例化class为对象实例bean
3. 添加**AbstractBeanFactory**类，定义获取bean流程
4. 添加bean实例缓存，默认单例，获取bean时先从缓存获取，不存在时再由**AbstractAutowireCapableBeanFactory**创建实例对象
5. 添加**BeanDefinitionRegistry**负责bean定义的注册至缓存


### 总结
1. 定义核心接口```BeanFactory```,具备获取bean对象功能 getBean()
2. 定义核心接口```SingletonBeanRegistry``s`,具备容器注册功能
3. 定义完接口后则开始定义默认实现类```DefaultSingletonBeanFactory```,实现了基本的缓存注册bean和获取bean
4. 一般要获取某些功能则继承,要自己实现功能则实现接口,定义抽象类```AbstractBeanFactory``` ,抽象类一般定义大体框架流程，它继承了```DefaultSingletonBeanFactory```和实现了```BeanFactory```接口