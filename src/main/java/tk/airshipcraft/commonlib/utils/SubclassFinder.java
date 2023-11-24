package tk.airshipcraft.commonlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality to search and retrieve subclasses of a given superclass dynamically.
 * This is particularly useful in Bukkit plugin development where plugins may define
 * multiple subclasses that extend common superclasses or interfaces.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-05-31
 */
public class SubclassFinder {

    private Class<?> superclass;

    /**
     * Constructs a new {@code SubclassFinder} that will search for subclasses of the provided superclass.
     *
     * @param superclass The parent class for which subclasses will be found.
     */
    public SubclassFinder(Class<?> superclass) {
        this.superclass = superclass;
    }

    /**
     * Searches through all loaded Bukkit plugins and retrieves classes that are subclasses
     * of the superclass associated with this {@code SubclassFinder}. Only classes that
     * are direct descendants and not the superclass itself are returned.
     *
     * @return A list of {@code Class} objects representing the found subclasses.
     */
    public List<Class<?>> getSubclasses() {
        List<Class<?>> subclasses = new ArrayList<>();
        Package[] packages = Package.getPackages();
        for (Package pkg : packages) {
            String packageName = pkg.getName();
            for (@NotNull Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.getClass().getPackage().getName().equals(packageName)) {
                    Class<?>[] classes = new Class[]{plugin.getClass()};
                    for (Class<?> clazz : classes) {
                        if (superclass.isAssignableFrom(clazz) && !superclass.equals(clazz)) {
                            subclasses.add(clazz);
                        }
                    }
                }
            }
        }
        return subclasses;
    }
}
