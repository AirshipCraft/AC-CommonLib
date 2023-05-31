package tk.airshipcraft.commonlib;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.Events.GuiClickEvent;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.UiDesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Main class file, plugins should extend this.
 */
public abstract class CommonLib extends JavaPlugin {
    private static final List<CommonLib> plugins = new ArrayList<>();
    public static CommonLib mainInstance;

    public static List<CommonLib> getPlugins() {
        return plugins;
    }
    @Override
    public final void onEnable() {
        mainInstance = this;
        plugins.add(this); // Add the current plugin instance to the list
        onPluginEnable();
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new HologramClickListener(), this);

        // Enable subclasses
        List<Class<? extends CommonLib>> subclasses = getSubclassesOf(CommonLib.class);
        for (Class<? extends CommonLib> subclass : subclasses) {
            try {
                CommonLib plugin = subclass.getDeclaredConstructor().newInstance();
                plugin.onEnable();
                plugins.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public final void onDisable() {
        onPluginDisable();
        // Disable subclasses
        for (CommonLib plugin : plugins) {
            if (plugin.isEnabled() && !plugin.equals(this)) {
                plugin.onPluginDisable();
            }
        }

        plugins.remove(this); // Remove the current plugin instance from the list
    }

    /**

     Returns a list of all the subclasses of the given class, including indirect subclasses. A variable that calls this method should be assigned like this:
     List<\Class<\? extends Hologram>> subclasses = getSubclassesOf(Your.class); (remove the backslashes)
     @param clazz the class to get the subclasses of
     @param <T> the type of the given class
     @return a list of subclasses of the given class
     @throws NullPointerException if the given class or its package is null
     */
    public static <T> List<Class<? extends T>> getSubclassesOf(Class<T> clazz) {
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



    public abstract void onPluginEnable();

    public abstract void onPluginDisable();
}
