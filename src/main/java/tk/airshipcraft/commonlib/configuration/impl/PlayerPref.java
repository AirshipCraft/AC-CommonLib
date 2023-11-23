package tk.airshipcraft.commonlib.configuration.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>The PlayerPref annotation is used to designate fields within classes that implement the IPlayerPreference interface as player preference settings.
 * This annotation serves as a marker to indicate which fields represent customizable settings for a player.
 * It also optionally allows for the specification of a default value for the preference,
 * which can be utilized during the initialization or resetting of preferences.</p>
 *
 * <p>Usage of this annotation simplifies the process of identifying and handling player-specific preference fields dynamically,
 * especially when working with reflection to automate preference management tasks.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PlayerPref {

    /**
     * Specifies an optional default value for the player preference.
     * This default value is used as a fallback when the preference has not been set previously or when resetting to defaults.
     * The value is provided as a string and should be convertible to the type of the field it annotates.
     *
     * @return The default value for the annotated preference field, represented as a String.
     *         If not specified, defaults to an empty string.
     */
    String defaultValue() default "";
}
