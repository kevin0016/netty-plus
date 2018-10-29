package com.itkevin.nettyplus.clientcommunication.core.utils.files;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Created by wken on 16/6/13.
 */
public interface IClassScanner {
	
    Collection<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> annotation);

    <T> Collection<Class<? extends T>> getSubTypesOf(final Class<T> type);
}
