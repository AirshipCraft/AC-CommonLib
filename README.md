# AC-CommonLib

AC-CommonLib is a comprehensive utility library designed to streamline the development of plugins for the AirshipCraft: Reborn (AC:R) project. It provides a broad API to simplify common tasks and implement advanced features with minimal boilerplate.

## Features

The library encapsulates a wide range of functionalities including, but not limited to:

- **Particle Systems**: Easy creation and management of particle effects.
- **Custom Enchantments**: Framework for adding and handling custom enchantments.
- **NMS Operations**: Utilities for interacting with Minecraft's native code (NMS) to extend beyond Bukkit's API.
- **Command Framework**: Simplified command creation with annotated methods and automatic registration.

## Getting Started

### Prerequisites

Ensure that you have Maven installed and configured correctly to manage dependencies for your Minecraft plugin development.

### Adding AC-CommonLib as a Dependency

To include AC-CommonLib in your project, add the following dependency snippet to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>tk.airshipcraft</groupId>
        <artifactId>commonlib</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Replace `0.0.1-SNAPSHOT` with the latest version of AC-CommonLib.

### Repositories

Add the following repository to your `pom.xml` to fetch AC-CommonLib:

```xml
<repositories>
    <repository>
        <id>ac-commonlib</id>
        <url>https://zuner.xyz/repository/maven-public/</url>
    </repository>
</repositories>
```

### Usage

After adding AC-CommonLib as a dependency, you can begin utilizing its APIs in your plugin. Here's a simple example of creating a custom particle effect:

```java
import tk.airshipcraft.commonlib.particles.ParticleEffectManager;

// ...

ParticleEffectManager.createEffect(player.getLocation(), "EXPLOSION_HUGE", 1, 0.5, 0.5, 0.5, 0.1);
```

## Contributing

We welcome contributions to AC-CommonLib. If you have a feature request, bug report, or a pull request, please submit them to our issue tracker or repository.

## Building from Source

To compile AC-CommonLib, clone the repository and run `mvn clean install`.

```bash
git clone https://github.com/YourUsername/AC-CommonLib.git
cd AC-CommonLib
mvn clean install
```

This will build the library and install it to your local Maven repository.

## License

AC-CommonLib is released under the [MIT License](LICENSE).

## Acknowledgements

- Thanks to all the contributors who have helped shape AC-CommonLib.
  - [@Locutusque](https://github.com/Locutusque)
- Special thanks to the PaperMC and Bukkit communities for their foundational work in Minecraft plugin development.

---

**Note**: This project is still under construction, and more features are being actively developed. Stay tuned for updates!