/**
 * Provides classes for defining and working with various geometric areas within the Minecraft world.
 * These areas can be used for a variety of purposes such as event handling, entity management, custom mechanics, and more.
 *
 * <p>The key abstractions provided by this package include:</p>
 *
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.world.IArea} - An interface for general area definitions in a world.</li>
 *   <li>{@link tk.airshipcraft.commonlib.world.AbstractYLimitedArea} - An abstract class that implements IArea
 *       and adds vertical (Y-level) boundaries to an area.</li>
 *   <li>{@link tk.airshipcraft.commonlib.world.EllipseArea} - Defines an elliptical area based on a center point
 *       and radii along the X and Z axes.</li>
 *   <li>{@link tk.airshipcraft.commonlib.world.GlobalYLimitedArea} - Represents a globally spanning area limited only
 *       by Y levels, ignoring X and Z constraints.</li>
 *   <li>{@link tk.airshipcraft.commonlib.world.PseudoChunk} - Represents a chunk by its coordinates without holding
 *       its actual data, useful for lightweight chunk referencing.</li>
 *   <li>{@link tk.airshipcraft.commonlib.world.RectangleArea} - Defines a rectangular area based on a center point
 *       and dimensions along the X and Z axes.</li>
 * </ul>
 *
 * <p>Each area type is designed to efficiently manage the inclusion checks and retrieval of chunks or pseudo chunks.
 * They can be extended or used as-is to fit the requirements of the plugin or server mechanics.</p>
 *
 * <p>Usage examples:</p>
 * <pre>{@code
 * // Define an elliptical area
 * Location center = ...; // Some location in the world
 * EllipseArea ellipseArea = new EllipseArea(50, 70, center, 10, 15);
 *
 * // Check if a location is inside the ellipse
 * boolean isInEllipse = ellipseArea.isInArea(someLocation);
 *
 * // Get all chunks within the ellipse
 * Collection<Chunk> chunks = ellipseArea.getChunks();
 * }</pre>
 *
 * <p>It is crucial to consider performance when working with large areas, as operations such as {@code getChunks()}
 * may carry significant overhead. It is recommended to use {@code getPseudoChunks()} where appropriate and to
 * carefully manage chunk loading and unloading.</p>
 *
 * <p>Implementations should handle any edge cases such as areas crossing world borders or chunk boundaries,
 * and ensure thread-safety if areas are accessed from multiple threads.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
package tk.airshipcraft.commonlib.world;
