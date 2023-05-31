package tk.airshipcraft.commonlib.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SubclassFinder {
    private static Class<?> superClass;
    public SubclassFinder(Class<?> superClass) {
        this.superClass = superClass;
    }

    public List<Class<?>> getSubclasses() {
        List<Class<?>> subclasses = new ArrayList<>();

        List<Class<?>> classes = getClasses();

        for (Class<?> cls : classes) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                subclasses.add(cls);
            }
        }

        return subclasses;
    }

    private List<Class<?>> getClasses() {
        List<Class<?>> classes = new ArrayList<>();
        String packageName = "";  // Set your base package name here
        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                findClasses(directory, packageName, classes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private void findClasses(File directory, String packageName, List<Class<?>> classes) {
        if (!directory.exists()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> cls = Class.forName(className);
                    classes.add(cls);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

