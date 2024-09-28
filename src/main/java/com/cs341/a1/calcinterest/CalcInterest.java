package com.cs341.a1.calcinterest;

/**
 *
 * @author labeeb
 */
public class CalcInterest {

    private static final int ERROR_CASE = -1; // Error condition

    private static final int ZERO = 0;

    // Loan Amount Range
    private static final int HUNDRED_THOUSAND = 100000;
    private static final int FIVE_HUNDRED_THOUSAND = 500000;

    // Interest Value
    private static final float FIVE_POINT_FIVE_PERCENT = 5.5f;
    private static final float SIX_POINT_FIVE_PERCENT = 6.5f;
    private static final float SEVEN_PERCENT = 7.0f;
    private static final float EIGHT_PERCENT = 8.0f;
    private static final float EIGHT_POINT_FIVE_PERCENT = 8.5f;
    private static final float TWELVE_PERCENT = 12.0f;

    // Loan Types
    private static final int LOAN_TYPE_HOME = 1;
    private static final int LOAN_TYPE_PROPERTY = 2;

    // Year Loans
    private static final int FIVE_YEARS = 5;
    private static final int TEN_YEARS = 10;
    private static final int ELEVEN_YEARS = 11;

    // Method that computes simple interest based on provided params.
    // This section uses ternary operators to calculate based on loanType.
    // Format: (condition) ? doIfTrue : doIfFalse.
    // Explanation: If the condition is true, the expression right after '?' is executed (IF part). 
    // Otherwise, the expression after ':' is executed (ELSE part).
    // Using ternary operators reduces the amount of code and ensures all cases are covered.
    public static float computeLoanInterest(double loanAmount, int yearLoan, int loanType) {
        if (yearLoan <= ZERO) {
            return ERROR_CASE;
        }
        if (loanAmount <= ZERO) {
            return ERROR_CASE;
        }
        if (loanType != LOAN_TYPE_HOME && loanType != LOAN_TYPE_PROPERTY) {
            return ERROR_CASE;
        }
        String message = "";

        float interestValue;
        if (loanAmount < HUNDRED_THOUSAND) {
            message = "loanAmount < $100k";
            interestValue = (yearLoan <= FIVE_YEARS)
                    ? (loanType == LOAN_TYPE_HOME ? EIGHT_PERCENT : TWELVE_PERCENT)
                    : ((loanType == LOAN_TYPE_HOME) ? yearLoan <= TEN_YEARS : yearLoan < TEN_YEARS)
                            ? (loanType == LOAN_TYPE_HOME ? SIX_POINT_FIVE_PERCENT : EIGHT_POINT_FIVE_PERCENT)
                            : (yearLoan >= ELEVEN_YEARS)
                                    ? (loanType == LOAN_TYPE_HOME ? FIVE_POINT_FIVE_PERCENT : SEVEN_PERCENT)
                                    : ERROR_CASE;
        } else if (loanAmount < FIVE_HUNDRED_THOUSAND) {
            message = "loanAmount < $500k";
            interestValue = ((loanType == LOAN_TYPE_HOME) ? yearLoan <= TEN_YEARS : yearLoan < TEN_YEARS)
                    ? (loanType == LOAN_TYPE_HOME ? SIX_POINT_FIVE_PERCENT : EIGHT_POINT_FIVE_PERCENT)
                    : ERROR_CASE;
        } else {
            message = "loanAmount >= $500k";
            interestValue = (yearLoan >= ELEVEN_YEARS)
                    ? (loanType == LOAN_TYPE_HOME ? FIVE_POINT_FIVE_PERCENT : SEVEN_PERCENT)
                    : ERROR_CASE;
        }

        if (interestValue == ERROR_CASE) {
            System.out.println("Error Case in: " + message + " Result: " + ERROR_CASE);
            return ERROR_CASE;
        }
        

        double result = Math.round(((loanAmount * yearLoan * interestValue) / 100) * 100) / 100d;

        System.out.println(message + " Result: " + result);
        
        return (float) result;
    }

    public static void main(String[] args) {
        double loanAmount = 1;
        int yearLoan = 64;
        int loanType = 1;
        float interestToPay = computeLoanInterest(loanAmount, yearLoan, loanType);

        if (interestToPay != ERROR_CASE) {
            float totalAmount = (float) (loanAmount + interestToPay);
            System.out.println("Interest To Pay: $" + interestToPay);
            System.out.println("Total Value To Pay: $" + totalAmount);
        } else {
            System.out.println("Error Occured, please check your input values.");
        }
    }
}
