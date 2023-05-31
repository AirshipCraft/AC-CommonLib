package tk.airshipcraft.commonlib.utils;

import org.checkerframework.checker.units.qual.A;
import tk.airshipcraft.commonlib.CommonLib;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class ACRPlugin {
    private static List<ACRPlugin> plugins = new ArrayList<>();
    public ACRPlugin() {
    }
    public static void enableSubclasses() {
        // Enable subclasses
        List<Class<? extends ACRPlugin>> subclasses = getSubclassesOf(ACRPlugin.class);
        for (Class<? extends ACRPlugin> subclass : subclasses) {
            try {
                ACRPlugin plugin = subclass.getDeclaredConstructor().newInstance();
                plugin.onPluginEnable();
                plugins.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableSubclasses() {
        for (ACRPlugin plugin : plugins) {
            if (plugin.isEnabled(plugin)) {
                plugin.onPluginDisable();
            }
        }
    }
        /**

         Returns a list of all the subclasses of the given class, including indirect subclasses. A variable that calls this method should be assigned like this:
         List<\Class<\? extends Hologram>> subclasses = getSubclassesOf(Your.class); (remove the backslashes)
         @param clazz the class to get the subclasses of
         @param <T> the type of the given class
         @return a list of subclasses of the given class
         @throws NullPointerException if the given class or its package is null
         */
        public static <T > List < Class < ? extends T >> getSubclassesOf(Class < T > clazz) {
            List<Class<? extends T>> subclasses = new ArrayList<>();
            String packageName = clazz.getPackage().getName();
            String packagePath = packageName.replace('.', '/');
            File packageDirectory = new File(Objects.requireNonNull(clazz.getClassLoader().getResource(packagePath)).getFile());
            String[] classNames = packageDirectory.list();
            assert classNames != null;
            for (String className : classNames) {
                if (className.endsWith(".class")) {
                    try {
                        String fullClassName = packageName + '.' + className.substring(0, className.length() - 6);
                        Class<?> c = Class.forName(fullClassName);
                        if (clazz.isAssignableFrom(c) && !clazz.equals(c)) {
                            subclasses.add((Class<? extends T>) c);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new NullPointerException("Class not found: " + e);
                    }
                }
            }
            return subclasses;
        }
        public Boolean isEnabled(ACRPlugin plugin) {
            return plugins.contains(plugin);
        }
        public abstract void onPluginEnable();

        public abstract void onPluginDisable();

}
