/**
 * <p>The <b>GUI package</b> in <code>tk.airshipcraft.commonlib</code> offers a robust framework for creating and managing
 * graphical user interfaces (GUIs) within a Minecraft server environment. It facilitates the development of interactive
 * and dynamic interfaces for server plugins, enhancing player experience on the server.</p>
 *
 * <p><b>Subpackages:</b></p>
 * <ul>
 *   <li><code>events</code>: This subpackage contains classes dedicated to handling events related to GUI interactions,
 *       enabling responsive and interactive UI elements within the game.</li>
 *   <li><code>objects</code>: Comprises classes for specific GUI components, such as scoreboards (Sidebar), action bars
 *       (Bossbar), and player-specific displays (BelowName). These classes provide implementations for various
 *       standard and custom GUI elements.</li>
 * </ul>
 *
 * <p><b>Key Classes:</b></p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.gui.GuiBuilder}: Employs the builder pattern to facilitate the easy
 *       construction of GUIs. It allows for a fluent, chainable interface, making GUI creation straightforward
 *       and intuitive.</li>
 *   <li>{@link tk.airshipcraft.commonlib.gui.UiDesigner}: An abstract class designed to serve as a foundational
 *       framework for UI design. It requires extension and customization for specific use cases, particularly in
 *       implementing interactive elements and click actions.</li>
 *   <li>{@link tk.airshipcraft.commonlib.gui.GuiManager}: Manages the lifecycle and interactions of GUI instances,
 *       ensuring efficient handling and operation of multiple GUI elements within the server.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>The package is designed to be versatile and extensible, suitable for a range of applications from simple
 * inventory interfaces to complex interactive displays. It provides both foundational structures (via
 * UiDesigner) and quick-setup tools (via GuiBuilder) to cater to diverse GUI development needs.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
package tk.airshipcraft.commonlib.gui;
