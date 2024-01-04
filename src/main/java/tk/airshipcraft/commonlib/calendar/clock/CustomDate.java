package tk.airshipcraft.commonlib.calendar.clock;

import tk.airshipcraft.commonlib.configuration.ConfigOption;

import java.time.LocalDate;
import java.util.Objects;

public class CustomDate {

    private int day;
    private int month;
    private int year;

    @ConfigOption(key = "daysPerMonth", defaultValue = "24")
    private static final int DAYS_PER_MONTH = 24;
    @ConfigOption(key = "monthsPerYear", defaultValue = "12")
    private static final int MONTHS_PER_YEAR = 12; // Adjust if you have a different number of months
    @ConfigOption(key = "epochStart", defaultValue = "2024-01-01")
    private static final LocalDate EPOCH_START = LocalDate.of(2020, 1, 1);

    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

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

    // Convert a Minecraft day count to a CustomDate
    public static CustomDate fromMinecraftDays(long minecraftDays) {
        int years = (int) (minecraftDays / (DAYS_PER_MONTH * MONTHS_PER_YEAR));
        int months = (int) ((minecraftDays % (DAYS_PER_MONTH * MONTHS_PER_YEAR)) / DAYS_PER_MONTH);
        int days = (int) (minecraftDays % DAYS_PER_MONTH);

        return new CustomDate(years + 1, months + 1, days + 1); // +1 because you might want to start counting from 1, not 0
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomDate that = (CustomDate) obj;
        return day == that.day && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    // Convert CustomDate to a human-readable string
    @Override
    public String toString() {
        return String.format("Year %d, Month %d, Day %d", year, month, day);
    }

    // Getters and setters
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
