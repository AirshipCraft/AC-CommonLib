package tk.airshipcraft.commonlib.world;

import org.bukkit.Location;

/**
 * An abstract implementation of a Y-limited area. This class provides a basic structure
 * for areas that are bounded vertically between two Y levels. It can be extended to create
 * specific area types with additional constraints on the X and Z axes.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-01
 */
public abstract class AbstractYLimitedArea implements IArea {

    private double lowerYBound;
    private double upperYBound;

    /**
     * Constructs an {@code AbstractYLimitedArea} with specified lower and upper Y bounds.
     *
     * @param lowerYBound The minimum Y value that is considered part of the area.
     * @param upperYBound The maximum Y value that is considered part of the area.
     * @throws IllegalArgumentException If the lowerYBound is greater than the upperYBound.
     */
    public AbstractYLimitedArea(double lowerYBound, double upperYBound) {
        if (lowerYBound > upperYBound) {
            throw new IllegalArgumentException("Lower Y bound cannot be greater than upper Y bound.");
        }
        this.lowerYBound = lowerYBound;
        this.upperYBound = upperYBound;
    }

    /**
     * Determines whether a given location falls within the vertical bounds of the area.
     *
     * @param loc The location to check.
     * @return True if the Y value of the location is within the area's bounds, false otherwise.
     */
    @Override
    public boolean isInArea(Location loc) {
        double y = loc.getY();
        return y >= lowerYBound && y <= upperYBound;
    }

    /**
     * Retrieves the lower Y boundary of the area.
     *
     * @return The minimum Y value included in the area.
     */
    public double getLowerYBound() {
        return lowerYBound;
    }

    /**
     * Retrieves the upper Y boundary of the area.
     *
     * @return The maximum Y value included in the area.
     */
    public double getUpperYBound() {
        return upperYBound;
    }
}
