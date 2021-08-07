## Step2

### XD

1. Factory后缀类负责根据beanDefinition信息创建Bean实例对象
2. Registry后缀类负责注册实例到容器
3. 添加 **BeanFactoryPostProcessor** **BeanPostProcessor** 负责在创建bean是添加自定义流程