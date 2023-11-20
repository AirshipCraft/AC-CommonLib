package tk.airshipcraft.commonlib.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields in classes implementing IPlayerPreference that should be
 * considered as player preference settings.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PlayerPref {
    /**
     * An optional default value string for the preference.
     * This can be used when resetting preferences or when a value is not yet set.
     *
     * @return The default value for the preference as a string.
     */
    String defaultValue() default "";
}
