package com.gdutelc.recruit.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author TUFSolareyes
 * @date 22/08/13
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext app;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(app == null){
            app = applicationContext;
        }
    }

    public static <T> T getBeanByClazz(Class<T> clazz){
        return app.getBean(clazz);
    }
}
