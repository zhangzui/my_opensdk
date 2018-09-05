package com.zz.opensdk.web.test.jarslink;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhangzuizui
 * @date 2018/9/5 18:52
 */
public class MyModuleClassLoader extends URLClassLoader{

    public MyModuleClassLoader(URL[] urls,ClassLoader parent) {
        super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = null;
        synchronized (MyModuleClassLoader.class) {
            result = loadClassForOverriding(name, resolve);
            if (result != null) {
                //链接类
                if (resolve) {
                    resolveClass(result);
                }
                return result;
            }
        }
        //使用默认类加载方式
        return super.loadClass(name, resolve);

    }
    /**
     * 加载一个子模块覆盖的类
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    private Class<?> loadClassForOverriding(String name, boolean resolve) throws ClassNotFoundException {
        //查找已加载的类
        Class<?> result = findLoadedClass(name);
        if (result == null) {
            //加载类
            try {
                result = findClass(name);
            } catch (ClassNotFoundException e) {
                //当前子模块系统加载不到时尝试从父容器中加载
                result = super.loadClass(name, resolve);
            }
        }
        return result;
    }
}
