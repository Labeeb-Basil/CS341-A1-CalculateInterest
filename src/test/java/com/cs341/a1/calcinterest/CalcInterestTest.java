/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.cs341.a1.calcinterest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 *
 * @author labeeb
 */
// The test class that uses Junit to test the computeLoanInterest functtion
public class CalcInterestTest {

    // This test reuses the same logic and runs with different parameters everytime.
    // Allows us to specify input and output values using a file source.
    @ParameterizedTest
    @CsvFileSource(resources = "/junitData.csv") // Use the csv file containing the amount, year, type and expected result.
    public void testCalculateLoanInterest_fromCsvFile(int testID, double loanAmount, int loanYear, int loanType, float expectedResult) {
        float result = CalcInterest.computeLoanInterest(loanAmount, loanYear, loanType);

        // Comparing the computed result with the expected result
        assertEquals(expectedResult, result, "Loan interest calculation failed for: " + testID);
    }

}
