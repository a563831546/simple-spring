## Step6

### XD

1. 支持bean初始化、销毁方法 xml或接口方式执行（InitializingBean、DisposableBean）
2. 添加disposableBean适配接口-DisposableBeanAdapter
3. 在抽象类 AbstractAutowireCapableBeanFactory initializeBean添加初始化方法调用，.afterPropertiesSet优先级高于 > init-method
4. 在抽象类 AbstractAutowireCapableBeanFactory registerDisposableBeanIfNecessary自定义销毁方法类似单例bean注册至集合
