package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@DisplayName("ProgressUtils Tests")
public class ProgressUtilsTest {

    @Nested
    @DisplayName("Weight Validation Tests")
    class WeightValidationTests {

        @Test
        @DisplayName("Should validate valid weights")
        void testIsValidWeightValid() {
            assertTrue(ProgressUtils.isValidWeight(30.0));
            assertTrue(ProgressUtils.isValidWeight(70.5));
            assertTrue(ProgressUtils.isValidWeight(150.0));
            assertTrue(ProgressUtils.isValidWeight(300.0));
        }

        @Test
        @DisplayName("Should reject invalid weights")
        void testIsValidWeightInvalid() {
            assertFalse(ProgressUtils.isValidWeight(29.9));
            assertFalse(ProgressUtils.isValidWeight(0.0));
            assertFalse(ProgressUtils.isValidWeight(-10.0));
            assertFalse(ProgressUtils.isValidWeight(300.1));
            assertFalse(ProgressUtils.isValidWeight(500.0));
        }

        @Test
        @DisplayName("Should handle boundary values for weight")
        void testIsValidWeightBoundaryValues() {
            assertTrue(ProgressUtils.isValidWeight(30.0));
            
            assertTrue(ProgressUtils.isValidWeight(300.0));
            
            assertFalse(ProgressUtils.isValidWeight(29.9));
            
            assertFalse(ProgressUtils.isValidWeight(300.1));
        }

        @Test
        @DisplayName("Should validate middle range weights")
        void testIsValidWeightMiddleRange() {
            assertTrue(ProgressUtils.isValidWeight(50.0));
            assertTrue(ProgressUtils.isValidWeight(100.0));
            assertTrue(ProgressUtils.isValidWeight(200.0));
            assertTrue(ProgressUtils.isValidWeight(250.0));
        }

        @ParameterizedTest
        @ValueSource(doubles = {30.0, 50.0, 75.0, 100.0, 150.0, 200.0, 250.0, 300.0})
        @DisplayName("Should validate various valid weights")
        void testIsValidWeightWithValidValues(double weight) {
            assertTrue(ProgressUtils.isValidWeight(weight));
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.0, 29.9, 300.1, 500.0, -10.0, -1.0})
        @DisplayName("Should reject various invalid weights")
        void testIsValidWeightWithInvalidValues(double weight) {
            assertFalse(ProgressUtils.isValidWeight(weight));
        }
    }

    @Nested
    @DisplayName("BMI Validation Tests")
    class BmiValidationTests {

        @Test
        @DisplayName("Should validate valid BMI values")
        void testIsValidBmiValid() {
            assertTrue(ProgressUtils.isValidBmi(10.0));
            assertTrue(ProgressUtils.isValidBmi(18.5));
            assertTrue(ProgressUtils.isValidBmi(25.0));
            assertTrue(ProgressUtils.isValidBmi(35.0));
            assertTrue(ProgressUtils.isValidBmi(50.0));
        }

        @Test
        @DisplayName("Should reject invalid BMI values")
        void testIsValidBmiInvalid() {
            assertFalse(ProgressUtils.isValidBmi(9.9));
            assertFalse(ProgressUtils.isValidBmi(0.0));
            assertFalse(ProgressUtils.isValidBmi(-5.0));
            assertFalse(ProgressUtils.isValidBmi(50.1));
            assertFalse(ProgressUtils.isValidBmi(100.0));
        }

        @Test
        @DisplayName("Should handle boundary values for BMI")
        void testIsValidBmiBoundaryValues() {
            assertTrue(ProgressUtils.isValidBmi(10.0));
            
            assertTrue(ProgressUtils.isValidBmi(50.0));
            
            assertFalse(ProgressUtils.isValidBmi(9.9));
            
            assertFalse(ProgressUtils.isValidBmi(50.1));
        }

        @Test
        @DisplayName("Should validate middle range BMI values")
        void testIsValidBmiMiddleRange() {
            assertTrue(ProgressUtils.isValidBmi(15.0));
            assertTrue(ProgressUtils.isValidBmi(22.0));
            assertTrue(ProgressUtils.isValidBmi(30.0));
            assertTrue(ProgressUtils.isValidBmi(40.0));
        }

        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.0, 18.5, 22.0, 25.0, 30.0, 35.0, 40.0, 50.0})
        @DisplayName("Should validate various valid BMI values")
        void testIsValidBmiWithValidValues(double bmi) {
            assertTrue(ProgressUtils.isValidBmi(bmi));
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.0, 9.9, 50.1, 100.0, -5.0, -1.0})
        @DisplayName("Should reject various invalid BMI values")
        void testIsValidBmiWithInvalidValues(double bmi) {
            assertFalse(ProgressUtils.isValidBmi(bmi));
        }
    }

    @Nested
    @DisplayName("Date Validation Tests")
    class DateValidationTests {

        @Test
        @DisplayName("Should validate current date")
        void testIsValidDateCurrent() {
            Date currentDate = new Date();
            assertTrue(ProgressUtils.isValidDate(currentDate));
        }

        @Test
        @DisplayName("Should reject null date")
        void testIsValidDateNull() {
            assertFalse(ProgressUtils.isValidDate(null));
        }

        @Test
        @DisplayName("Should validate past date")
        void testIsValidDatePast() {
            Date pastDate = new Date(System.currentTimeMillis() - 86400000);
            assertTrue(ProgressUtils.isValidDate(pastDate));
        }

        @Test
        @DisplayName("Should validate future date")
        void testIsValidDateFuture() {
            Date futureDate = new Date(System.currentTimeMillis() + 86400000);
            assertTrue(ProgressUtils.isValidDate(futureDate));
        }

        @Test
        @DisplayName("Should validate very old date")
        void testIsValidDateVeryOld() {
            Date veryOldDate = new Date(System.currentTimeMillis() - 10L * 365 * 24 * 60 * 60 * 1000);
            assertTrue(ProgressUtils.isValidDate(veryOldDate));
        }

        @Test
        @DisplayName("Should validate very future date")
        void testIsValidDateVeryFuture() {
            Date veryFutureDate = new Date(System.currentTimeMillis() + 10L * 365 * 24 * 60 * 60 * 1000);
            assertTrue(ProgressUtils.isValidDate(veryFutureDate));
        }

        @ParameterizedTest
        @ValueSource(ints = {-365, -30, -7, -1, 0, 1, 7, 30, 365})
        @DisplayName("Should validate various date offsets")
        void testIsValidDateWithVariousOffsets(int daysOffset) {
            Date date = new Date(System.currentTimeMillis() + (long) daysOffset * 24 * 60 * 60 * 1000);
            assertTrue(ProgressUtils.isValidDate(date));
        }
    }

    @Nested
    @DisplayName("Range Validation Tests")
    class RangeValidationTests {

        @Test
        @DisplayName("Should validate entire valid weight range")
        void testValidWeightRange() {
            for (double weight = 30.0; weight <= 300.0; weight += 10.0) {
                assertTrue(ProgressUtils.isValidWeight(weight), 
                    "Weight " + weight + " should be valid");
            }
        }

        @Test
        @DisplayName("Should validate entire valid BMI range")
        void testValidBmiRange() {
            for (double bmi = 10.0; bmi <= 50.0; bmi += 5.0) {
                assertTrue(ProgressUtils.isValidBmi(bmi), 
                    "BMI " + bmi + " should be valid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid weight range below minimum")
        void testInvalidWeightRangeBelowMinimum() {
            for (double weight = 0.0; weight < 30.0; weight += 5.0) {
                assertFalse(ProgressUtils.isValidWeight(weight), 
                    "Weight " + weight + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid weight range above maximum")
        void testInvalidWeightRangeAboveMaximum() {
            for (double weight = 301.0; weight <= 500.0; weight += 50.0) {
                assertFalse(ProgressUtils.isValidWeight(weight), 
                    "Weight " + weight + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid BMI range below minimum")
        void testInvalidBmiRangeBelowMinimum() {
            for (double bmi = 0.0; bmi < 10.0; bmi += 2.0) {
                assertFalse(ProgressUtils.isValidBmi(bmi), 
                    "BMI " + bmi + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid BMI range above maximum")
        void testInvalidBmiRangeAboveMaximum() {
            for (double bmi = 51.0; bmi <= 100.0; bmi += 10.0) {
                assertFalse(ProgressUtils.isValidBmi(bmi), 
                    "BMI " + bmi + " should be invalid");
            }
        }
    }
}