package com.baogex.springframework.apo.aspectj;

import com.baogex.springframework.apo.Advisor;
import com.baogex.springframework.apo.Pointcut;
import com.baogex.springframework.apo.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * <p>
 *
 * </p>
 *
 * @author : zuomin.yu
 * @date : 2021-08-11
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体拦截方法
    private Advice advice;
    // 解析式
    private String expression;

    @Override
    public Pointcut getPointcut() {
        return pointcut != null ? pointcut : new AspectJExpressionPointcut(expression);
    }

    @Override
    public Advice getAdvisor() {
        return this.advice;
    }

    public void setAdvisor(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
