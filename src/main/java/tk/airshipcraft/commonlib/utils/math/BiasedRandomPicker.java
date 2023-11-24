package tk.airshipcraft.commonlib.utils.math;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

/**
 * This class allows for random selection of objects based on assigned probabilities.
 * It is particularly useful when objects need to be picked with non-uniform chances,
 * enabling the creation of biased random outcomes according to specified weights.
 *
 * @param <E> The type of object that can be picked.
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-03
 */
public class BiasedRandomPicker<E> {

    private Map<E, Double> originalChances;
    private TreeMap<Double, E> chances;
    private Random rng;

    /**
     * Constructs a BiasedRandomPicker with a map of objects and their associated probabilities.
     * The total sum of all provided chances must equal 1, within a tolerance of 10^(-6).
     * A chance of 0 indicates that the object will never be picked, while a chance of 1 indicates certainty.
     *
     * @param chances A map where each key is an object to pick and each value is the chance of picking that object.
     * @throws IllegalArgumentException If the provided map is null, contains null values, or if the sum of chances is not 1.
     */
    public BiasedRandomPicker(Map<E, Double> chances) {
        if (chances == null) {
            throw new IllegalArgumentException("Can not instantiate BiasedRandomPicker with null chance map");
        }
        this.originalChances = chances;
        this.rng = new Random();
        this.chances = new TreeMap<>();
        double totalChance = 0.0;
        for (Entry<E, Double> entry : chances.entrySet()) {
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("You may not specify null as spawn chance");
            }
            double chance = entry.getValue();
            if (chance == 0.0) {
                continue;
            }
            this.chances.put(totalChance, entry.getKey());
            totalChance += chance;
        }
        if (Math.abs(totalChance - 1.0) > Math.pow(10, -6)) {
            throw new IllegalArgumentException("Chances did not sum up to 1.0, total sum was: " + totalChance);
        }
    }

    /**
     * Randomly selects an object from the pool, considering the assigned chances.
     * If the configuration is malformed, there is an extremely low probability that this method
     * may return null, but such a case is highly unlikely (less than 10^(-120)).
     *
     * @return A randomly picked element based on the biased chances.
     */
    public E getRandom() {
        int retries = 0;
        Entry<Double, E> entry = null;
        while (entry == null && retries < 20) {
            entry = chances.floorEntry(rng.nextDouble());
            retries++;
        }
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    /**
     * Retrieves the probability of an object being picked by this BiasedRandomPicker instance.
     * If the object was not assigned an explicit chance, a default of 0.0 is returned.
     *
     * @param e The object for which to retrieve the selection chance.
     * @return The probability of the object being picked.
     */
    public double getChance(E e) {
        Double chance = originalChances.get(e);
        if (chance == null) {
            return 0.0;
        }
        return chance;
    }
}