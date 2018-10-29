package com.itkevin.nettyplus.clientcommunication.core.utils.files;

import com.itkevin.nettyplus.clientcommunication.core.config.ClientConstant;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wken on 16/6/13.
 */
class DexJarClassScanner implements IClassScanner {

    private final static String DEX_JAR_EXTENSION = "dex.jar";
    private final Logger logger = LoggerFactory.getLogger(DexJarClassScanner.class);
    private DexClassLoader dexClassLoader;
    private List<Class<?>> classes = new ArrayList<>();
    private String cachePath = ClientConstant.CACHE_PATH;
    private String scannerPackage;
    
    public DexJarClassScanner(String scannerPackage) {
    	this.scannerPackage = scannerPackage;
    	
    	initialize();
    }

    @Override
    public Collection<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        List<Class<?>> result = new ArrayList<>();
        for (Class<?> clazz : this.classes) {
            if (clazz.isAnnotationPresent(annotation)) {
                result.add(clazz);
            }
        }
        for (Class<?> clazz : result) {
            this.logger.info("getTypesAnnotatedWith class : {} ", clazz.getName());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> Collection<Class<? extends T>> getSubTypesOf(Class<T> type) {
        List<Class<? extends T>> result = new ArrayList<>();
        for (Class<?> clazz : this.classes) {
            if (type.isAssignableFrom(clazz)) {
                result.add((Class<T>) clazz);
            }
        }
        for (Class<?> clazz : result) {
            this.logger.info("getSubTypesOf class : {} ", clazz.getName());
        }
        return result;
    }

    public void initialize() {
        String dexpath = ClientConstant.DEX_PATH;

        this.logger.error("dexpath {}", dexpath);
        this.logger.error("cachePath {}", this.cachePath);
        {
            File cache = new File(this.cachePath);
            if (!cache.exists()) {
                cache.mkdirs();
                cache.setReadable(true, false);
                cache.setWritable(true, false);
                cache.setExecutable(true, false);
            }
        }

        List<File> files = this.loadFiles(dexpath);
        if (dexpath == null || dexpath.isEmpty() || this.cachePath == null || this.cachePath.isEmpty()) {
            throw new RuntimeException("settings is empty.");
        }

        {
            String separeater = ":";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < files.size(); i++) {
                if (i == 0) {
                    sb.append(String.format("%s", files.get(i).getAbsoluteFile()));
                } else {
                    sb.append(String.format("%s%s", separeater, files.get(i).getAbsoluteFile()));
                }
            }
            this.dexClassLoader = new DexClassLoader(sb.toString(), this.cachePath, null, DexJarClassScanner.class.getClassLoader());
        }
        {
            List<String> names = this.loadClassNames(files);
            this.classes = this.loadClasses(names, this.dexClassLoader);
        }

    }

    private List<Class<?>> loadClasses(List<String> names, ClassLoader classLoader) {
        List<Class<?>> result = new ArrayList<>();
        for (String name : names) {
            try {
                Class<?> clazz = classLoader.loadClass(name);
                result.add(clazz);
            } catch (ClassNotFoundException e) {
                this.logger.error("Error", e);
            }
        }
        return result;
    }

    private List<String> loadClassNames(List<File> files) {
        List<String> result = new ArrayList<>();
        for (File file : files) {
            DexFile dexFile = null;
            try {
                dexFile = new DexFile(file.getAbsolutePath());
                List<String> names = Collections.list(dexFile.entries());
                for (String name : names) {
                    if (name.toLowerCase().startsWith(this.scannerPackage)) {
                        result.add(name);
                    }
                }

            } catch (IOException e) {
                logger.error("Error", e);
            } finally {
                if (dexFile != null) {
                    try {
                        dexFile.close();
                    } catch (IOException e) {
                        logger.error("Error", e);
                    }
                }
            }
        }
        return result;
    }

    private List<File> loadFiles(String path) {
        List<File> result = new ArrayList<>();
        File root = new File(path);
        if (root.isFile()) {
            if (root.getName().toLowerCase().endsWith(DEX_JAR_EXTENSION.toLowerCase())) {
                result.add(root);
            }
        } else {
            File[] dexFiles = root.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(DEX_JAR_EXTENSION.toLowerCase());
                }
            });
            for (File file : dexFiles) {
                result.add(file);
            }
        }
        return result;
    }
}
