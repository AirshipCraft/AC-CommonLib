/**
 * <p>Provides a suite of mathematical utilities for various operations such as biased random picking,
 * common math functions, and raycasting. These classes are designed to facilitate complex mathematical
 * computations commonly required in game development and other computational applications.</p>
 *
 * <h2>Classes in this package:</h2>
 * <ul>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.BiasedRandomPicker}</b>: A class for randomly selecting an item from a collection based on
 *   weighted probabilities.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.Maths}</b>: A utility class containing static methods for common mathematical operations such
 *   as clamping and normalization.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.RayCast}</b>: A class for performing raycasting operations in 3D space, useful for collision
 *   detection, line of sight, and more.</li>
 * </ul>
 *
 * <h3>Usage Examples:</h3>
 *
 * <h4>BiasedRandomPicker:</h4>
 * <pre>{@code
 * Map<String, Double> weightMap = Map.of("option1", 0.5, "option2", 0.3, "option3", 0.2);
 * BiasedRandomPicker<String> picker = new BiasedRandomPicker<>(weightMap);
 * String randomChoice = picker.getRandom();
 * System.out.println(randomChoice); // Outputs a randomly chosen option based on weights.
 * }</pre>
 *
 * <h4>Maths:</h4>
 * <pre>{@code
 * int clampedValue = Maths.clamp(15, 0, 10);
 * System.out.println(clampedValue); // Outputs 10, as it's the max clamp value.
 *
 * double normalizedValue = Maths.norm(75, 50, 100);
 * System.out.println(normalizedValue); // Outputs 0.5, halfway between min and max.
 * }</pre>
 *
 * <h4>RayCast:</h4>
 * <pre>{@code
 * RayCast raycaster = new RayCast();
 * Entity hitEntity = raycaster.raycast(player, player.getDirection(), 100);
 * if (hitEntity != null) {
 *     System.out.println("Hit: " + hitEntity.getName());
 * }
 * }</pre>
 *
 * <p>These utilities can be combined and used in various aspects of game mechanics,
 * such as AI decision making, user input processing, and environmental interactions.</p>
 *
 * @since 1.0
 */
package tk.airshipcraft.commonlib.utils.math;
