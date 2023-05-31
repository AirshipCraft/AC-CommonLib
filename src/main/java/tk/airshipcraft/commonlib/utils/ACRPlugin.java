package tk.airshipcraft.commonlib.utils;

import tk.airshipcraft.commonlib.CommonLib;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class ACRPlugin extends CommonLib {
    private static List<ACRPlugin> plugins = new ArrayList<>();

    public ACRPlugin() {
    }

    public static void enableSubclasses() {
        List<Class<? extends ACRPlugin>> subclasses = getSubclassesOf(ACRPlugin.class);
        for (Class<? extends ACRPlugin> subclass : subclasses) {
            try {
                ACRPlugin plugin = subclass.getDeclaredConstructor().newInstance();
                plugins.add(plugin);
                plugin.onPluginEnable(); // Call onPluginEnable() for each enabled plugin
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableSubclasses() {
        for (int i = plugins.size() - 1; i >= 0; i--) {
            ACRPlugin plugin = plugins.get(i);
            if (plugin.isEnabled()) {
                plugin.onPluginDisable();
                plugins.remove(i);
            }
        }
    }

    public static <T> List<Class<? extends T>> getSubclassesOf(Class<T> clazz) {
        List<Class<? extends T>> subclasses = new ArrayList<>();
        Package classPackage = clazz.getPackage();
        if (classPackage != null) {
            String packageName = classPackage.getName();
            String packagePath = packageName.replace('.', '/');
            URL packageURL = clazz.getClassLoader().getResource(packagePath);
            if (packageURL != null) {
                File packageDirectory = new File(packageURL.getFile());
                String[] classNames = packageDirectory.list();
                if (classNames != null) {
                    for (String className : classNames) {
                        if (className.endsWith(".class")) {
                            try {
                                String fullClassName = packageName + '.' + className.substring(0, className.length() - 6);
                                Class<?> c = Class.forName(fullClassName);
                                System.out.println(clazz);
                                subclasses.add(c.asSubclass(clazz));
                            } catch (ClassNotFoundException e) {
                                throw new NullPointerException("Class not found: " + e);
                            }
                        }
                    }
                }
            }
        }
        return subclasses;
    }

    public abstract void onPluginEnable();

    public abstract void onPluginDisable();
}
