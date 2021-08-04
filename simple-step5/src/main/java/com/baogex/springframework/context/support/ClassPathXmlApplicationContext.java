package com.baogex.springframework.context.support;

/**
 * @author : baogex.com
 * @since : 2021-08-04
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private final String[] configLocations;


    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
