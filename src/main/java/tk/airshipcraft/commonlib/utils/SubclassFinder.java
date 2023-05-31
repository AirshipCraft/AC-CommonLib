package tk.airshipcraft.commonlib.utils;

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

        // Get all the classes in the classpath
        ClassPathScanner classPathScanner = new ClassPathScanner();
        List<Class<?>> classes = classPathScanner.scanClasses();

        // Check each class if it is a subclass of the given superclass
        for (Class<?> clazz : classes) {
            if (this.superclass.isAssignableFrom(clazz) && !this.superclass.equals(clazz)) {
                subclasses.add(clazz);
                CommonLib.mainInstance.getLogger().warning("Found" + clazz.getName());
            }
        }

        return subclasses;
    }
}

