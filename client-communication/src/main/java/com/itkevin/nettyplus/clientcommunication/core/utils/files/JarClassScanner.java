package com.itkevin.nettyplus.clientcommunication.core.utils.files;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Created by wken on 16/6/13.
 */
class JarClassScanner implements IClassScanner {

    private Reflections reflections;
    
    public JarClassScanner(String scannerPackage) {
    	this.reflections = new Reflections(scannerPackage);
    }

    @Override
    public Collection<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        return this.reflections.getTypesAnnotatedWith(annotation);
    }

    @Override
    public <T> Collection<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return this.reflections.getSubTypesOf(type);
    }
}
