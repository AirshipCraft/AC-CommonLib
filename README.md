[![Maven Package and Deploy](https://github.com/AirshipCraft/AC-CommonLib/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/AirshipCraft/AC-CommonLib/actions/workflows/maven-publish.yml) [![Maven Build and Release](https://github.com/AirshipCraft/AC-CommonLib/actions/workflows/maven-build-and-release.yml/badge.svg)](https://github.com/AirshipCraft/AC-CommonLib/actions/workflows/maven-build-and-release.yml) ![GitHub release (with filter)](https://img.shields.io/github/v/release/AirshipCraft/AC-CommonLib)

# AC-CommonLib

AC-CommonLib is a comprehensive utility library designed to streamline the development of plugins for the AirshipCraft:
Reborn (AC:R) project. It provides a broad API to simplify common tasks and implement advanced features with minimal
boilerplate.

## Features

The library encapsulates a wide range of functionalities including, but not limited to:

- **Particle Systems**: Easy creation and management of particle effects.
- **Custom Enchantments**: Framework for adding and handling custom enchantments.
- **NMS Operations**: Utilities for interacting with Minecraft's native code (NMS) to extend beyond Bukkit's API.
- **Command Framework**: Simplified command creation with annotated methods and automatic registration.

## Getting Started

### Prerequisites

Ensure that you have Maven installed and configured correctly to manage dependencies for your Minecraft plugin
development.

### Adding AC-CommonLib as a Dependency

To include AC-CommonLib in your project, add the following dependency snippet to your `pom.xml` file:

```xml

<dependencies>
    <dependency>
        <groupId>tk.airshipcraft</groupId>
        <artifactId>commonlib</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Replace `VERSION` with the latest version of AC-CommonLib.

### Repositories

Add the following repository to your `pom.xml` to fetch AC-CommonLib:

```xml

<repositories>
    <repository>
        <id>airshipcraft-releases</id>
        <url>https://nexus.zuner.xyz/repository/airshipcraft-releases/</url>
    </repository>
</repositories>
```

For snapshot builds, add this as well:

```xml
    <repository>
        <id>airshipcraft-snapshots</id>
        <url>https://nexus.zuner.xyz/repository/airshipcraft-snapshots/</url>
    </repository>
```

### Usage

After adding AC-CommonLib as a dependency, you can begin utilizing its APIs in your plugin. Here's a simple example of
creating a custom particle effect:

```java
import tk.airshipcraft.commonlib.particles.ParticleEffectManager;

// ...

ParticleEffectManager.createEffect(player.getLocation(),"EXPLOSION_HUGE",1,0.5,0.5,0.5,0.1);
```

## Contributing

We welcome contributions to AC-CommonLib. If you have a feature request, bug report, or a pull request, please submit
them to our issue tracker or repository.

## Building from Source

To compile AC-CommonLib, clone the repository and run `mvn clean install`.

```bash
git clone https://github.com/AirshipCraft/AC-CommonLib.git
cd AC-CommonLib
mvn clean install
```

This will build the library and install it to your local Maven repository.

## Quick Note About GUI Related Classes

(will be moving this into the docs eventually but leaving this as a footnote for now)

The primary difference between the `UiDesigner` and `GuiBuilder` classes lies in their design philosophy, usage, and
functionality.

1. **Design Philosophy and Usage**:

- `UiDesigner`: This class is an abstract class meant to be extended. It provides a foundation for designing user
  interfaces but expects subclasses to implement specific behaviors, particularly in the `addClickAction` method. It's
  more about setting up a framework for UI management and allowing for extension and customization.
- `GuiBuilder`: This class follows the builder pattern. It's designed for direct use and allows for creating a GUI by
  chaining method calls. It provides a more straightforward and concise way to build GUIs without needing to extend or
  override methods.

2. **Functionality**:

- `UiDesigner`:
    - Includes static methods to create a GUI (`createGUI`) and to trigger actions based on inventory clicks across all
      subclasses (`callClickAction`).
    - Provides methods for managing inventory slots (`setSlot`, `fillSlots`, `setBorder`, `setFiller`), as well as for
      clearing and opening inventories.
    - Requires subclasses to define the behavior for click actions, making it more flexible for different use cases.
- `GuiBuilder`:
    - Focuses solely on building an inventory GUI.
    - Provides methods for adding items to specific slots (`setItem`) and for building the final inventory (`build`).
    - More suited for straightforward GUI creation without the need for handling click actions or other interactive
      elements.

3. **When to Use**:

- Use `UiDesigner` when:
    - You need a comprehensive system for UI management across multiple plugins or modules.
    - You require a framework that can be extended to define specific behaviors for different types of UIs.
    - You want to manage click actions and other interactive elements in a centralized way.
- Use `GuiBuilder` when:
    - You need a simple and straightforward way to create a GUI.
    - Your focus is more on the appearance and item placement within the GUI, rather than handling interactions.
    - You prefer a clean and chainable method to build GUIs without the need for subclassing or extending.

Both classes serve different purposes and can coexist in a larger system, each being used where its strengths are most
beneficial. The `UiDesigner` is more about setting up a system for UI interaction, while the `GuiBuilder` is about
quickly and efficiently creating GUIs.

## License

AC-CommonLib is released under the [MIT License](LICENSE).

## Acknowledgements

- Thanks to all the contributors who have helped shape AC-CommonLib.
    - [@Locutusque](https://github.com/Locutusque)
- Special thanks to the PaperMC and Bukkit communities for their foundational work in Minecraft plugin development.

---

**Note**: This project is still under construction, and more features are being actively developed. Stay tuned for
updates!
