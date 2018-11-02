package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: CommandHolder
 * @Description: 所有的命令集合
 * @Author: Kevin
 * @CreateDate: 18/11/2 上午11:12
 * @UpdateUser:
 * @UpdateDate: 18/11/2 上午11:12
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public final class CommandHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHolder.class);

    private static volatile boolean initialized = false;//是否已经被初始化过了

    private static IProxyFactory PROXY_FACTORY;

    /**
     * 根据mapping获取代理类
     *
     * @param mapping - String
     * @return IProxyStub
     */
    public static IProxyStub getProxy(String mapping) {
        return PROXY_FACTORY.getProxy(mapping);
    }

    /**
     * 随服务器启动
     *
     * @param packageName
     */
    public static void init(String packageName, IProxyFactory proxyFactory) {
        if (initialized) {
            LOGGER.warn("command holder has been initialized!");
            return;
        }
        synchronized (CommandHolder.class) {
            if (!initialized) {
                PROXY_FACTORY = proxyFactory;
                LOGGER.info("commands wraped. . .");
                try {
                    //创建代理工厂
                    PROXY_FACTORY.init(scan(packageName));
                } catch (Exception e) {
                    LOGGER.error("commands is not wraped. . .", e);
                }
            }
        }
    }

    /**
     * 扫描指定的包，获取数据库类对象的初始化信息
     *
     * @param packageName - 要扫描的包路径
     * @return Map<String   ,       CommandInfo>
     * @throws Exception
     */
    private static Map<String, CommandInfo> scan(String packageName) throws Exception {
        LOGGER.info("scan package : " + packageName);

        Map<String, CommandInfo> commands = new HashMap<>();
        //反射类获取定义
        Reflections reflections = new Reflections(packageName);
        //获取Command标记的类
        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(Command.class);
        LOGGER.info("commandClasses is size : " + (commandClasses != null ? commandClasses.size() : 0));

        if (commandClasses != null && !commandClasses.isEmpty()) {
            for (Class<?> clazz : commandClasses) {
                LOGGER.info("start scan class {}", clazz.getName());
                //获取对应的标记的类中的method，不包含继承过来的类
                for (Method method : clazz.getDeclaredMethods()) {
                    CommandMapping mapping = method.getAnnotation(CommandMapping.class);
                    if (mapping != null) {
                        LOGGER.info("scan class {}.{}", clazz.getName(), method.getName());

                        String command = mapping.value();
                        if (commands.containsKey(command)) {
                            throw new RuntimeException("command " + command + " has been existed,check it...,className " + clazz.getName() + " , methodName " + method.getName());
                        } else {
                            commands.put(command, new CommandInfo(method, command));
                        }
                    }
                }
                LOGGER.info("end scan class {}", clazz.getName());
            }
        }

        return commands;
    }

}
