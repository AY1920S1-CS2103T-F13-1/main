package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Helper functions for handling LocalDates.
 */
public class DateUtil {
    /**
     * Get current system date.
     *
     * @return the current date from the system clock in the default time-zone.
     */
    public static LocalDate getTodayDate() {
        return LocalDate.now();
    }

    /**
     * Get the date that is days number of days after starting date.
     *
     * @param startDate Starting date.
     * @param days Number of days from starting date.
     * @return Date that is days number of days after starting date.
     */
    public static LocalDate extendDate(LocalDate startDate, int days) {
        return startDate.plusDays(days);
    }

    /**
     * Get the date that is days number of days after today.
     *
     * @param days Number of days from today.
     * @return Date that is days number of days after today.
     */
    public static LocalDate getTodayPlusDays(int days) {
        return extendDate(getTodayDate(), days);
    }

    /**
     * Get the number of days between startDate and endDate.
     *
     * @param startDate Starting date.
     * @param endDate Ending date.
     * @return Number of days between the two dates.
     */
    public static int getNumOfDaysBetween(LocalDate startDate, LocalDate endDate) {
        return (int) startDate.until(endDate, ChronoUnit.DAYS);
    }
}
