package tk.airshipcraft.commonlib.calendar.clock;

import tk.airshipcraft.commonlib.configuration.ConfigOption;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a date in the custom calendar system used in the AirshipCraft Minecraft server.
 * This custom calendar system defines a year as consisting of 12 months, each with 24 days, leading to a 288-day year.
 * This class provides functionalities for handling dates within this unique calendar system,
 * including operations like adding days and converting between the custom calendar and the Gregorian calendar.
 *
 * <p>Key characteristics of the custom calendar:</p>
 * <ul>
 *   <li><b>Months per Year:</b> 12 months</li>
 *   <li><b>Days per Month:</b> 24 days</li>
 *   <li><b>Total Days per Year:</b> 288 days (12 months * 24 days per month)</li>
 * </ul>
 *
 * <p>The CustomDate class also includes methods to convert Minecraft day counts to CustomDate,
 * considering each Minecraft day as a full day in the custom calendar.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * CustomDate date = new CustomDate(1, 1, 1); // Start of Year 1
 * date.addDays(10); // Advances the date by 10 days within the custom calendar
 * LocalDate equivalentGregorianDate = date.toLocalDate(); // Converts to the Gregorian calendar date
 * }</pre>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-04
 */
public class CustomDate {

    private int day;
    private int month;
    private int year;

    // currently these variables are hard-coded and not configurable, will add logic to them at a later date
    @ConfigOption(key = "daysPerMonth", defaultValue = "24")
    private static final int DAYS_PER_MONTH = 24;
    @ConfigOption(key = "monthsPerYear", defaultValue = "12")
    private static final int MONTHS_PER_YEAR = 12; // Adjust if you have a different number of months
    @ConfigOption(key = "epochStart", defaultValue = "2024-01-01")
    private static final LocalDate EPOCH_START = LocalDate.of(2024, 1, 1);

    /**
     * Initializes a new CustomDate.
     *
     * @param year  The year.
     * @param month The month.
     * @param day   The day.
     */
    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Adds the specified number of days to the current date.
     *
     * @param days The number of days to add.
     */
    public void addDays(int days) {
        day += days;
        while (day > DAYS_PER_MONTH) {
            day -= DAYS_PER_MONTH;
            month++;
            if (month > MONTHS_PER_YEAR) {
                month = 1;
                year++;
            }
        }
    }

    /**
     * Converts the number of Minecraft days since a fixed epoch to a CustomDate.
     * Assumes each Minecraft day corresponds to one day in the custom calendar.
     *
     * @param minecraftDays The number of Minecraft days since the epoch.
     * @return The CustomDate representing the equivalent date since the epoch.
     */
    public static CustomDate fromMinecraftDays(long minecraftDays) {
        int totalDays = (int) minecraftDays; // Assuming each Minecraft day is equivalent to one day in CustomDate

        int years = totalDays / (DAYS_PER_MONTH * MONTHS_PER_YEAR);
        int remainingDays = totalDays % (DAYS_PER_MONTH * MONTHS_PER_YEAR);
        int months = remainingDays / DAYS_PER_MONTH;
        int days = remainingDays % DAYS_PER_MONTH;

        // Adding 1 because CustomDate months and days start from 1, not 0
        return new CustomDate(years + 1, months + 1, days + 1);
    }

    /**
     * Calculates the number of days from this date to another date.
     *
     * @param other The date to calculate the days to.
     * @return The number of days until the other date.
     */
    public int daysUntil(CustomDate other) {
        int totalDaysThis = this.year * DAYS_PER_MONTH * MONTHS_PER_YEAR + this.month * DAYS_PER_MONTH + this.day;
        int totalDaysOther = other.year * DAYS_PER_MONTH * MONTHS_PER_YEAR + other.month * DAYS_PER_MONTH + other.day;

        return totalDaysOther - totalDaysThis;
    }

    /**
     * Converts this CustomDate to a LocalDate.
     * The conversion is based on the custom calendar system starting at EPOCH_START.
     *
     * @return The equivalent LocalDate.
     */
    public LocalDate toLocalDate() {
        // Calculate the total number of days in the custom calendar since the epoch start
        int totalCustomDaysSinceEpoch = (this.year - 1) * MONTHS_PER_YEAR * DAYS_PER_MONTH
                + (this.month - 1) * DAYS_PER_MONTH
                + (this.day - 1);

        // Convert the total days in the custom calendar to real-world days
        // Since 1 day in the custom calendar is equivalent to 1 hour in real life,
        // and there are 24 hours in a day, we divide the totalCustomDaysSinceEpoch by 24.
        int totalRealDaysSinceEpoch = totalCustomDaysSinceEpoch / 24;

        // Add the days to the epoch start date to get the equivalent LocalDate
        return EPOCH_START.plusDays(totalRealDaysSinceEpoch);
    }

    /**
     * Converts a LocalDate to a CustomDate.
     * The conversion is based on the custom calendar system starting at EPOCH_START.
     *
     * @param localDate The LocalDate to convert.
     * @return The equivalent CustomDate.
     */
    public static CustomDate fromLocalDate(LocalDate localDate) {
        // Calculate the total number of days in the real-world calendar since the epoch start
        int totalRealDaysSinceEpoch = (int) EPOCH_START.until(localDate).toDays();

        // Convert the total days in the real-world calendar to custom days
        // Since 1 day in the custom calendar is equivalent to 1 hour in real life,
        // and there are 24 hours in a day, we multiply the totalRealDaysSinceEpoch by 24.
        int totalCustomDaysSinceEpoch = totalRealDaysSinceEpoch * 24;

        // Convert the total number of days in the custom calendar to a CustomDate
        return fromMinecraftDays(totalCustomDaysSinceEpoch);
    }

    /**
     * Compares this CustomDate to another CustomDate.
     *
     * @param obj The other CustomDate to compare to.
     * @return true if the dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomDate that = (CustomDate) obj;
        return day == that.day && month == that.month && year == that.year;
    }

    /**
     * Returns a hash code for this CustomDate.
     *
     * @return A hash code value for this CustomDate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    /**
     * Returns a string representation of this CustomDate.
     *
     * @return A string representation of this CustomDate.
     */
    @Override
    public String toString() {
        return String.format("Year %d, Month %d, Day %d", year, month, day);
    }

    /**
     * Returns the day of the month represented by this CustomDate.
     *
     * @return The day of the month represented by this CustomDate.
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of the month represented by this CustomDate.
     *
     * @param day The day of the month to set.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Returns the month represented by this CustomDate.
     *
     * @return The month represented by this CustomDate.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month represented by this CustomDate.
     *
     * @param month The month to set.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Returns the year represented by this CustomDate.
     *
     * @return The year represented by this CustomDate.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year represented by this CustomDate.
     *
     * @param year The year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }
}
