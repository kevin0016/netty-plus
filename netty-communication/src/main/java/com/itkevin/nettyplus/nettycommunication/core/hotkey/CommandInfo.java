package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName: CommandInfo
 * @Description: 命令描述
 * @Author: Kevin
 * @CreateDate: 18/11/2 上午10:12
 * @UpdateUser:
 * @UpdateDate: 18/11/2 上午10:12
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class CommandInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandInfo.class);

    /**
     * 所属类
     */
    private final Class<?> clazz;

    /**
     * 所属的方法
     */
    private final Method actionMethod;

    /**
     * 映射的URL
     */
    private final String mapping;

    /**
     * 方法的返回类型
     */
    private final Class<?> returnType;

    /**
     * 参数名称集合
     */
    private final String[] paramNames;

    /**
     * 参数类型集合
     */
    private final Class<?>[] paramTypes;

    public CommandInfo(Method method, String mapping) {
        this.clazz = method.getDeclaringClass();
        this.actionMethod = method;
        this.mapping = mapping;
        this.paramTypes = method.getParameterTypes();
        this.paramNames = getMethodParamNames();
        this.returnType = method.getReturnType();
    }

    private String[] getMethodParamNames() {
        Method method = this.actionMethod;
        try {
            ClassPool pool = ClassPool.getDefault();

            pool.insertClassPath(new ClassClassPath(clazz));

            CtClass cc = pool.get(clazz.getName());

            String[] paramTypeNames = new String[method.getParameterTypes().length];
            for (int i = 0; i < this.paramTypes.length; i++)
                paramTypeNames[i] = this.paramTypes[i].getName();
            CtMethod cm = cc.getDeclaredMethod(method.getName(), pool.get(paramTypeNames));

            MethodInfo methodInfo = cm.getMethodInfo();

            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                throw new RuntimeException("class:" + clazz.getName()
                        + ", have no LocalVariableTable, please use javac -g:{vars} to compile the source file");
            }

            int startIndex = getStartIndex(attr);
            String[] paramNames = new String[cm.getParameterTypes().length];
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;

            for (int i = 0; i < paramNames.length; i++) {
                paramNames[i] = attr.variableName(startIndex + i + pos);
            }
            for (int i = 0; i < paramNames.length; i++) {
                LOGGER.info(paramNames[i]);
            }

            return paramNames;
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return new String[0];
    }

    private int getStartIndex(LocalVariableAttribute attr) {
        int startIndex = 0;
        for (int i = 0; i < attr.length(); i++) {
            if ("this".equals(attr.variableName(i))) {
                startIndex = i;
                break;
            }
        }
        return startIndex;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public String getMapping() {
        return mapping;
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

}
