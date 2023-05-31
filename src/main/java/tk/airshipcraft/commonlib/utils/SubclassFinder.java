package tk.airshipcraft.commonlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import tk.airshipcraft.commonlib.CommonLib;

import java.util.ArrayList;
import java.util.List;

public class SubclassFinder {
    private Class<?> superclass;
    public SubclassFinder(Class<?> superclass) {
        this.superclass = superclass;
    }
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

