package tk.airshipcraft.commonlib;

import tk.airshipcraft.commonlib.utils.SubclassFinder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class representing a CommonLib plugin.
 * This class provides the framework for enabling and disabling plugins that extend CommonLib.
 */
public abstract class ACRPlugin extends CommonLib {

    private static final Map<Class<? extends ACRPlugin>, ACRPlugin> plugins = new HashMap<>();
    private static SubclassFinder subclassFinder;

    /**
     * Enables all subclasses of ACRPlugin.
     * This method uses reflection to find and initialize subclasses, and then calls their onPluginEnable method.
     */
    public static void enableSubclasses() {
        subclassFinder = new SubclassFinder(ACRPlugin.class);
        List<Class<?>> subclasses = subclassFinder.getSubclasses();

        for (Class<?> subclass : subclasses) {
            try {
                ACRPlugin pluginInstance = plugins.get(subclass);

                // Instantiate the subclass if not already done
                if (pluginInstance == null) {
                    pluginInstance = (ACRPlugin) subclass.getDeclaredConstructor().newInstance();
                    plugins.put(subclass.asSubclass(ACRPlugin.class), pluginInstance);
                }

                // Invoke the onPluginEnable() method
                Method method = subclass.getDeclaredMethod("onPluginEnable");
                method.invoke(pluginInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Disables all subclasses of ACRPlugin.
     * This method calls the onPluginDisable method of each subclass.
     */
    public static void disableSubclasses() {
        subclassFinder = new SubclassFinder(ACRPlugin.class);
        List<Class<?>> subclasses = subclassFinder.getSubclasses();

        for (Class<?> subclass : subclasses) {
            try {
                ACRPlugin pluginInstance = plugins.get(subclass);

                // Only proceed if the instance exists
                if (pluginInstance != null) {
                    // Invoke the onPluginDisable() method
                    Method method = subclass.getDeclaredMethod("onPluginDisable");
                    method.invoke(pluginInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Abstract method to be implemented by subclasses.
     * This method is called when the plugin is enabled.
     */
    public abstract void onPluginEnable();

    /**
     * Abstract method to be implemented by subclasses.
     * This method is called when the plugin is disabled.
     */
    public abstract void onPluginDisable();
}
