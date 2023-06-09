package tk.airshipcraft.commonlib.plugin;

import org.bukkit.plugin.PluginLoadOrder;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Processes the {@link Plugin} annotation and generates a plugin.yml file.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"tk.airshipcraft.commonlib.plugin.Plugin", "tk.airshipcraft.commonlib.plugin.PluginDependency"})
public class PluginProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Set<? extends Element> annotatedElements = env.getElementsAnnotatedWith(Plugin.class);
        if (annotatedElements.isEmpty()) {
            return false;
        }

        if (annotatedElements.size() > 1) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "More than one @Plugin element found.");
            return false;
        }

        Element element = annotatedElements.iterator().next();

        if (!(element instanceof TypeElement)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@Plugin element is not instance of TypeElement");
            return false;
        }

        TypeElement type = ((TypeElement) element);
        Map<String, Object> data = new LinkedHashMap<>();
        Plugin annotation = type.getAnnotation(Plugin.class);

        data.put("name", annotation.name());

        String version = annotation.version();
        if (!version.isEmpty()) {
            data.put("version", version);
        } else {
            data.put("version", new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date(System.currentTimeMillis())));
        }

        data.put("main", type.getQualifiedName().toString());

        String description = annotation.description();
        if (!description.isEmpty()) {
            data.put("description", description);
        }

        PluginLoadOrder order = annotation.load();
        if (order != PluginLoadOrder.POSTWORLD) {
            data.put("load", order.name());
        }

        String apiVersion = annotation.apiVersion();
        if (!apiVersion.isEmpty()) {
            data.put("api-version", apiVersion);
        }

        String[] authors = annotation.authors();
        if (authors.length == 1) {
            data.put("author", authors[0]);
        } else if (authors.length > 1) {
            data.put("authors", new ArrayList<>(Arrays.asList(authors)));
        }

        String website = annotation.website();
        if (!website.isEmpty()) {
            data.put("website", website);
        }

        PluginDependency[] depends = annotation.depends();
        List<String> hard = new ArrayList<>();
        List<String> soft = new ArrayList<>();

        for (PluginDependency depend : depends) {
            if (depend.soft()) {
                soft.add(depend.value());
            } else {
                hard.add(depend.value());
            }
        }

        hard.addAll(Arrays.asList(annotation.hardDepends()));
        soft.addAll(Arrays.asList(annotation.softDepends()));

        if (!hard.isEmpty()) {
            data.put("depend", hard);
        }

        if (!soft.isEmpty()) {
            data.put("softdepend", soft);
        }

        String[] loadBefore = annotation.loadBefore();
        if (loadBefore.length != 0) {
            data.put("loadbefore", new ArrayList<>(Arrays.asList(loadBefore)));
        }

        String[] libraries = annotation.libraries();
        if (libraries.length != 0) {
            data.put("libraries", new ArrayList<>(Arrays.asList(libraries)));
        }

        try {
            Yaml yaml = new Yaml();
            FileObject resource = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.yml");

            try (Writer writer = resource.openWriter(); BufferedWriter bw = new BufferedWriter(writer)) {
                yaml.dump(data, bw);
                bw.flush();
            }

            return true;
        } catch (IOException e) {
            throw new RuntimeException("Cannot serialize plugin descriptor: " + e.getMessage(), e);
        }
    }
}