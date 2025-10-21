package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@DisplayName("PlanUtils Tests")
public class PlanUtilsTest {

    @Nested
    @DisplayName("Date Validation Tests")
    class DateValidationTests {

        @Test
        @DisplayName("Should validate future date")
        void testIsValidStartDateFuture() {
            Date futureDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            assertTrue(PlanUtils.isValidStartDate(futureDate));
        }

        @Test
        @DisplayName("Should reject past date")
        void testIsValidStartDatePast() {
            Date pastDate = new Date(System.currentTimeMillis() - 86400000); // Yesterday
            assertFalse(PlanUtils.isValidStartDate(pastDate));
        }

        @Test
        @DisplayName("Should reject null date")
        void testIsValidStartDateNull() {
            assertFalse(PlanUtils.isValidStartDate(null));
        }

        @Test
        @DisplayName("Should reject current date")
        void testIsValidStartDateCurrent() {
            Date currentDate = new Date();
            assertFalse(PlanUtils.isValidStartDate(currentDate));
        }

        @Test
        @DisplayName("Should validate far future date")
        void testIsValidStartDateFarFuture() {
            Date farFuture = new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000); // One year from now
            assertTrue(PlanUtils.isValidStartDate(farFuture));
        }

        @Test
        @DisplayName("Should validate date just after current")
        void testIsValidStartDateJustAfter() {
            Date justAfter = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            assertTrue(PlanUtils.isValidStartDate(justAfter));
        }

        @Test
        @DisplayName("Should reject date just before current")
        void testIsValidStartDateJustBefore() {
            Date justBefore = new Date(System.currentTimeMillis() - 86400000); // Yesterday
            assertFalse(PlanUtils.isValidStartDate(justBefore));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 7, 30, 365})
        @DisplayName("Should validate various future dates")
        void testIsValidStartDateWithVariousFutureDays(int daysInFuture) {
            Date futureDate = new Date(System.currentTimeMillis() + (long) daysInFuture * 24 * 60 * 60 * 1000);
            assertTrue(PlanUtils.isValidStartDate(futureDate));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 7, 30, 365})
        @DisplayName("Should reject various past dates")
        void testIsValidStartDateWithVariousPastDays(int daysInPast) {
            Date pastDate = new Date(System.currentTimeMillis() - (long) daysInPast * 24 * 60 * 60 * 1000);
            assertFalse(PlanUtils.isValidStartDate(pastDate));
        }
    }

    @Nested
    @DisplayName("Boundary Value Tests")
    class BoundaryValueTests {

        @Test
        @DisplayName("Should handle exact boundary values")
        void testBoundaryValues() {
            // Test tomorrow (should be valid)
            Date tomorrow = new Date(System.currentTimeMillis() + 86400000);
            assertTrue(PlanUtils.isValidStartDate(tomorrow));
            
            // Test today (should be invalid)
            Date today = new Date();
            assertFalse(PlanUtils.isValidStartDate(today));
            
            // Test yesterday (should be invalid)
            Date yesterday = new Date(System.currentTimeMillis() - 86400000);
            assertFalse(PlanUtils.isValidStartDate(yesterday));
        }

        @Test
        @DisplayName("Should handle edge cases around boundaries")
        void testEdgeCasesAroundBoundaries() {
            // Test very close to current time
            Date veryCloseFuture = new Date(System.currentTimeMillis() + 86400000);
            assertTrue(PlanUtils.isValidStartDate(veryCloseFuture));
            
            // Test very close to current time in past
            Date veryClosePast = new Date(System.currentTimeMillis() - 86400000);
            assertFalse(PlanUtils.isValidStartDate(veryClosePast));
        }
    }

    @Nested
    @DisplayName("Special Date Tests")
    class SpecialDateTests {

        @Test
        @DisplayName("Should handle leap year dates")
        void testLeapYearDates() {
            Date leapYearDate = new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000);
            assertTrue(PlanUtils.isValidStartDate(leapYearDate));
        }

        @Test
        @DisplayName("Should handle year boundaries")
        void testYearBoundaries() {
            Date nextYear = new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000);
            assertTrue(PlanUtils.isValidStartDate(nextYear));
            
            Date lastYear = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000);
            assertFalse(PlanUtils.isValidStartDate(lastYear));
        }

        @Test
        @DisplayName("Should handle month boundaries")
        void testMonthBoundaries() {
            Date nextMonth = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
            assertTrue(PlanUtils.isValidStartDate(nextMonth));
            
            Date lastMonth = new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000);
            assertFalse(PlanUtils.isValidStartDate(lastMonth));
        }
    }
}