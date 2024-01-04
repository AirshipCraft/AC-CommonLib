package tk.airshipcraft.commonlib.calendar.clock;

public class CustomDate {

    private int day;
    private int month;
    private int year;

    private static final int DAYS_PER_MONTH = 24;
    private static final int MONTHS_PER_YEAR = 12; // Adjust if you have a different number of months

    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Method to add days and automatically adjust the month and year
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
