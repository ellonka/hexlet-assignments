package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private String annotationParam;
    private String beanLog;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            annotationParam = bean.getClass().getAnnotation(Inspect.class).level();
            beanLog = beanName;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals(beanLog)) {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (annotationParam.equals("info")) {
                            LOGGER.info("Was called method: " + method.getName()
                                    + "() with arguments: " + Arrays.toString(args));
                        } else {
                            LOGGER.debug("Debug level. Was called method: " + method.getName()
                                    + "() with arguments: " + Arrays.toString(args));
                        }
                        return method.invoke(bean, args);
                    });
        }
        return bean;
    }
}
// END
