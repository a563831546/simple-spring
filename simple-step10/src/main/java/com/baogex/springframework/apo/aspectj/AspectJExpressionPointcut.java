package com.baogex.springframework.apo.aspectj;

import com.baogex.springframework.apo.ClassFilter;
import com.baogex.springframework.apo.MethodMatcher;
import com.baogex.springframework.apo.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * AOP解析式处理器
 *
 * @author : baogex.com
 * @since : 2021-08-10
 */
public class AspectJExpressionPointcut implements MethodMatcher, ClassFilter, Pointcut {
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;


    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 切入点应该应用于给定的接口还是目标类
     *
     * @param clazz 目标类
     * @return 是否匹配
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 获取类过滤器
     */
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * 获取函数匹配器
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
