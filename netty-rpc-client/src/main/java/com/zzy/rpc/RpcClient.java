package com.zzy.rpc;

import com.zzy.rpc.annotation.RpcAutowired;
import com.zzy.rpc.connect.ConnectionManager;
import com.zzy.rpc.discovery.ServiceDiscovery;
import com.zzy.rpc.proxy.ObjectProxy;
import com.zzy.rpc.proxy.RpcService;
import com.zzy.rpc.util.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description: RPC Client（Create RPC proxy）
 */
public class RpcClient implements ApplicationContextAware, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);
    private ServiceDiscovery serviceDiscovery;
    private static ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.createThreadPool(RpcClient.class.getSimpleName(),8, 16);

    public RpcClient(String address) {
        this.serviceDiscovery = new ServiceDiscovery(address);
    }

    public static void submit(Runnable task) {
        threadPoolExecutor.submit(task);
    }

    @SuppressWarnings("unchecked")
    public static <T, P> T createService(Class<T> interfaceClass, String version) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new ObjectProxy<T, P>(interfaceClass, version)
        );
    }

    public static <T, P> RpcService createAsyncService(Class<T> interfaceClass, String version) {
        return new ObjectProxy<T, P>(interfaceClass, version);
    }
    public void stop() {
        threadPoolExecutor.shutdown();
        serviceDiscovery.stop();
        ConnectionManager.getInstance().stop();
    }

    @Override
    public void destroy() throws Exception {
        this.stop();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            Field[] fields = bean.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    RpcAutowired rpcAutowired = field.getAnnotation(RpcAutowired.class);
                    if (rpcAutowired != null) {
                        String version = rpcAutowired.version();
                        field.setAccessible(true);
                        field.set(bean, createService(field.getType(), version));
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.toString());
            }
        }
    }
}
