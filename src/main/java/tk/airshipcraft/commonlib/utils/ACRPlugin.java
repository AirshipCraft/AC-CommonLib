package tk.airshipcraft.commonlib.utils;

import tk.airshipcraft.commonlib.CommonLib;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class ACRPlugin extends CommonLib {
    private static List<ACRPlugin> plugins = new ArrayList<>();
    private static SubclassFinder subclassFinder;

    public static void enableSubclasses() {
        subclassFinder = new SubclassFinder(ACRPlugin.class);
        List<Class<?>> subclasses = subclassFinder.getSubclasses();

        for (Class<?> subclass : subclasses) {
            try {
                // Check if the subclass is already instantiated
                if (plugins.contains(subclass)) {
                    continue; // Skip instantiation if already exists
                }

                // Instantiate the subclass
                ACRPlugin instance = (ACRPlugin) subclass.getDeclaredConstructor().newInstance();

                // Invoke the onPluginEnable() method using reflection
                Method method = subclass.getDeclaredMethod("onPluginEnable");
                method.invoke(instance);
                plugins.add(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void disableSubclasses() {
        subclassFinder = new SubclassFinder(ACRPlugin.class);
        List<Class<?>> subclasses = subclassFinder.getSubclasses();

        for (Class<?> subclass : subclasses) {
            try {
                // Instantiate the subclass
                ACRPlugin instance = (ACRPlugin) subclass.getDeclaredConstructor().newInstance();

                // Invoke the onPluginEnable() method using reflection
                Method method = subclass.getDeclaredMethod("onPluginDisable");
                method.invoke(instance);
                plugins.remove(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void onPluginEnable();

    public abstract void onPluginDisable();
}
