## step2

1. BeanDefinition类存储Object对象转换为Class类型信息
2. 添加**AbstractAutowireCapableBeanFactory**类负责实例化class为对象实例bean
3. 添加**AbstractBeanFactory**类，定义获取bean流程
4. 添加bean实例缓存，默认单例，获取bean时先从缓存获取，不存在时再由**AbstractAutowireCapableBeanFactory**创建实例对象
5. 添加**BeanDefinitionRegistry**负责bean定义的注册至缓存

