package tk.airshipcraft.commonlib.utils;

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

public class ClassPathScanner {
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

    private String getClassName(Path classPathRoot, Path classFile) {
        String classFilePath = classFile.toString();
        String classFileName = classFilePath.substring(classPathRoot.toString().length() + 1);
        return classFileName.replace(File.separatorChar, '.')
                .substring(0, classFileName.length() - 6);
    }
}

