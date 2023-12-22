package tk.airshipcraft.commonlib.utils.classes;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for scanning the classpath to find classes of a particular type.
 * It can scan within specified packages or across the entire classpath.
 * This can be particularly useful for dynamically loading classes at runtime
 * for purposes such as plugin systems or application frameworks.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-05-31
 */
public class ClassPathScanner {

    /**
     * Default constructor.
     */
    public ClassPathScanner() {
    }

    /**
     * Constructor with package names to restrict the scanning scope.
     *
     * @param packages Array of package names to scan within.
     */
    public ClassPathScanner(String... packages) {
    }

    /**
     * Scans for all classes extending the specified superclass within the given package names.
     *
     * @param superClass The class to scan for subclasses.
     * @param packages   A list of package names to restrict the scanning scope.
     * @return A list of classes that extend the specified superclass.
     */
    public List<Class<?>> scanClasses(Class<?> superClass, String... packages) {
        List<Class<?>> classes = new ArrayList<>();

        for (Class<?> clazz : scanClasses()) {
            if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)) {
                classes.add(clazz);
            }
        }

        return classes;
    }

    /**
     * Scans for all classes extending the specified superclass within the given package names.
     *
     * @param superClass The class to scan for subclasses.
     * @param packages   A list of package names to restrict the scanning scope.
     * @param <T>        The type parameter representing the superclass.
     * @return A list of classes that extend the specified superclass.
     */
    public <T> List<Class<? extends T>> scanClasses(Class<T> superClass, List<String> packages) {
        List<Class<? extends T>> classes = new ArrayList<>();

        // Assuming scanClasses() returns all available classes, you would need to filter them by the package names provided.
        for (String pkg : packages) {
            for (Class<?> clazz : scanClasses()) { // scanClasses() needs to be implemented to return classes from a package
                if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)) {
                    // Check if the class is in the specified package.
                    if (clazz.getPackage().getName().equals(pkg)) {
                        // This unchecked cast is safe because isAssignableFrom checks if clazz is a subclass of superClass
                        @SuppressWarnings("unchecked")
                        Class<? extends T> castedClass = (Class<? extends T>) clazz;
                        classes.add(castedClass);
                    }
                }
            }
        }

        return classes;
    }

//    /**
//     * Scans for classes that extend the specified class within the provided package names.
//     *
//     * @param <T>        The type of the class to scan for.
//     * @param superClass The class to scan for subclasses of.
//     * @param packages   A list of package names to restrict the scanning scope.
//     * @return A list of classes that extend the specified class and are within the specified packages.
//     */
//    public <T> List<Class<? extends T>> scanClasses(Class<T> superClass, List<String> packages) {
//        List<Class<? extends T>> classes = new ArrayList<>();
//
//        for (Class<?> clazz : scanClasses()) {
//            if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)) {
//                // Checking if the class's package is in the list of packages provided
//                if (packages.contains(clazz.getPackage().getName())) {
//                    // This cast is safe due to the isAssignableFrom check above
//                    classes.add((Class<? extends T>) clazz);
//                }
//            }
//        }
//
//        return classes;
//    }
    // I don't know why this method is here but im too scared to delete it

    /**
     * Scans for all .class files available in the classpath of the current thread's context class loader.
     * It is assumed that this method will be used in an environment where the classes are unpacked or
     * directly available as .class files (not within a JAR).
     *
     * @return A list of {@code Class<?>} objects representing all the classes found in the classpath.
     */
    public List<Class<?>> scanClasses() {
        List<Class<?>> classes = new ArrayList<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL classPathRoot = classLoader.getResource("");
            if (classPathRoot != null) {
                URI classPathRootUri = classPathRoot.toURI();
                if (classPathRootUri.getScheme().equals("file")) {
                    Path classPathRootPath = Paths.get(classPathRootUri);
                    Files.walk(classPathRootPath)
                            .filter(Files::isRegularFile)
                            .filter(path -> path.toString().endsWith(".class"))
                            .forEach(path -> {
                                try {
                                    String className = getClassName(classPathRootPath, path);
                                    Class<?> clazz = Class.forName(className);
                                    classes.add(clazz);
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * Converts the file system path of a .class file to a fully qualified class name.
     * This method assumes that the path provided is a valid path for a .class file.
     *
     * @param root The root path to which the class file path is relative.
     * @param file The path to the .class file.
     * @return A string representing the fully qualified class name.
     */
    private String getClassName(Path root, Path file) {
        String relative = root.relativize(file).toString();
        String className = relative.replace(File.separatorChar, '.');
        className = className.substring(0, className.length() - ".class".length());
        return className;
    }
}
