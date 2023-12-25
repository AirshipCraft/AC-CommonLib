/**
 * <p>Provides a comprehensive suite of mathematical utilities tailored for game development in Minecraft.
 * This package includes classes for biased random selection, general math operations, random number generation,
 * raycasting in a Minecraft world, and vector math operations. These utilities are essential for creating
 * immersive and dynamic gameplay experiences in Minecraft plugins.</p>
 *
 * <h2>Classes in this package:</h2>
 * <ul>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.BiasedRandomPicker}</b>: Enables biased random selection of objects based on assigned probabilities, useful for non-uniform random outcomes.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.Maths}</b>: Offers a collection of general mathematical functions such as clamping, normalization, linear interpolation, and specialized rounding methods.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.RandomMath}</b>: Provides methods for generating random numbers within specified ranges, including Gaussian distribution and boolean values.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.RayCast}</b>: Facilitates ray casting in Minecraft, enabling detection of blocks or entities along a line, useful for shooting mechanics, line of sight calculations, and more.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.math.VectorMath}</b>: Contains methods for vector mathematical operations including rotation, randomization, and scaling, crucial for managing entity movements and interactions.</li>
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
