package tk.airshipcraft.commonlib.utils;

import org.checkerframework.checker.units.qual.A;
import tk.airshipcraft.commonlib.CommonLib;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
this class should be extended for any ACR plugins
*/
public abstract class ACRPlugin extends CommonLib {
    private static List<ACRPlugin> plugins = new ArrayList<>();
    public ACRPlugin() {
    }
    public static void enableSubclasses() {
        // Enable subclasses
        mainInstance.getLogger().warning("test");
        List<Class<? extends ACRPlugin>> subclasses = getSubclassesOf(ACRPlugin.class);
        for (Class<? extends ACRPlugin> subclass : subclasses) {
            try {
                ACRPlugin plugin = subclass.getDeclaredConstructor().newInstance();
                plugins.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Call onPluginEnable() for each enabled plugin
        for (ACRPlugin plugin : plugins) {
            plugin.onPluginEnable();
        }
    }


    public static void disableSubclasses() {
        // Iterate over the plugins in reverse order to ensure correct disabling
        for (int i = plugins.size() - 1; i >= 0; i--) {
            ACRPlugin plugin = plugins.get(i);
            if (plugin.isEnabled()) {
                plugin.onPluginDisable();
                plugins.remove(i);
            }
        }
    }

    /**

     Returns a list of all the subclasses of the given class, including indirect subclasses. A variable that calls this method should be assigned like this:
     List<\Class<\? extends YourClass>> subclasses = getSubclassesOf(Your.class); (remove the backslashes)
     @param clazz the class to get the subclasses of
     @param <T> the type of the given class
     @return a list of subclasses of the given class
     @throws NullPointerException if the given class or its package is null
     */
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
                                if (clazz.isAssignableFrom(c) && !clazz.equals(c)) {
                                    subclasses.add(c.asSubclass(clazz));
                                }
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
