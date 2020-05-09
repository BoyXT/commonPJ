package com.ytw.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public final class SpringUtil implements ApplicationContextAware
{
    private static final SpringUtil INSTANCE = new SpringUtil();

    private ApplicationContext applicationContext;

    private SpringUtil()
    {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    private static SpringUtil getInstance()
    {
        return INSTANCE;
    }

    public static <T> T getBean(Class<T> clazz)
    {
        return getInstance().applicationContext.getBean(clazz);
    }
}
