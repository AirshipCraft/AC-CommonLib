# CommonLib Configuration Guide

## Overview

The CommonLib plugin framework introduces a streamlined and efficient way to manage configurations for Minecraft plugins
extending `ACRPlugin`. This guide provides detailed instructions and best practices for using the `ConfigHelper` class
and the `@ConfigOption` annotation system.
The `ConfigHelper` and `@ConfigOption` system in CommonLib provides a powerful yet simple way to manage configurations
for Minecraft plugins. By following the guidelines outlined in this document, developers can ensure efficient and
error-free configuration management within their plugins.

## ConfigHelper Class

The `ConfigHelper` class serves as the central manager for loading, saving, and handling configuration settings. It
utilizes reflection to automatically initialize configuration values based on annotated fields within a given class.

### Key Features

- **Automatic Loading and Saving:** Automatically loads and saves configuration settings from a `config.yml` file.
- **Reflection-based Initialization:** Reflectively identifies fields annotated with `@ConfigOption` and manages their
  values.
- **Default Value Management:** Maintains default values for configuration settings, ensuring robustness and fallback
  options.

## @ConfigOption Annotation

The `@ConfigOption` annotation is used to mark fields within a class as configuration options. It allows specifying a
unique configuration key and a default value for that key.

### Usage

```java
public class SomeClass {
    @ConfigOption(key = "example.setting", defaultValue = "default value")
    private String exampleSetting;
}
```

- `key`: Represents the unique key for the configuration option in the `config.yml` file.
- `defaultValue`: Provides a default value to be used if the configuration does not contain a value for the key.

## Working with ConfigHelper

### Initialization

When creating a class that requires configuration management (e.g., `WorldClock`), instantiate `ConfigHelper` within the
constructor:

```java
public class WorldClock {
    private ConfigHelper configHelper;

    public WorldClock(CommonLib plugin) {
        configHelper = new ConfigHelper(plugin, this.getClass());
        configHelper.loadConfig();
        // Additional setup...
    }
}
```

### Loading Configurations

Configurations are loaded automatically during the instantiation of `ConfigHelper`:

```java
configHelper.loadConfig();
```

This loads values from `config.yml` or uses default values specified in `@ConfigOption`.

### Saving Configurations

To save configuration changes back to `config.yml`, use:

```java
configHelper.saveConfig();
```

### Accessing Configuration Values

Access configuration values directly from the fields annotated with `@ConfigOption`:

```java
long settingValue=realSecondsPerMinecraftDay;
```

### Example: Using ConfigHelper in WorldClock

```java
public class WorldClock {
    @ConfigOption(key = "realSecondsPerMinecraftDay", defaultValue = "3600")
    private long realSecondsPerMinecraftDay;

    public WorldClock(CommonLib plugin) {
        ConfigHelper configHelper = new ConfigHelper(plugin, this.getClass());
        configHelper.loadConfig();
        // realSecondsPerMinecraftDay is now initialized
    }
}
```

## Best Practices

- **Consistency:** Consistently use `@ConfigOption` for all configuration fields across your plugin classes.
- **Documentation:** Clearly document each configuration option within your code for ease of understanding and
  maintenance.
- **Validation:** Implement validation logic for configuration values to ensure they meet your plugin's requirements.
- **Default Values:** Always provide default values for configuration options to ensure robustness and fallback options.