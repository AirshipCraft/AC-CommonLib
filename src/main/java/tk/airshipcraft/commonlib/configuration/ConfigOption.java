package tk.airshipcraft.commonlib.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>The {@code ConfigOption} annotation is used to mark fields in a class that represent configuration options.
 * It allows specifying a configuration key and a default value for that key. This annotation is intended
 * to be used in conjunction with a configuration manager that can process these annotations to automatically
 * load, save, and repair configuration settings.</p>
 *
 * <p>When a field in a class is annotated with {@code ConfigOption}, it indicates that this field corresponds
 * to a specific setting in the plugin's configuration file. The {@code key} element specifies the unique
 * configuration key, and the {@code defaultValue} element provides a default value to be used if the configuration
 * does not already contain a value for the key.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * public class SomeClass {
 *     @ConfigOption(key = "database.url", defaultValue = "jdbc:mysql://localhost:3306/minecraft")
 *     private String databaseUrl;
 *
 *     // Other configuration fields...
 * }}</pre>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigOption {
    String key();

    String defaultValue() default "";
}
