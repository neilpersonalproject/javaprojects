/**
 * Demonstration of recursion with various methods implemented recursively.
 */

public class Recursion {

    /**
     * Output the numbers in given integer range.
     * @param start The start value of the range.
     * @param end The end value of the range.
     */
    public void printNumbers(int start, int end) {
        if(start > end) { return; }
        System.out.println(start);
        printNumbers(start+1, end);
    }

    /**
     * Output the numbers in given integer range.
     * @param start The start value of the range.
     * @param end The end value of the range.
     */
    public void printNumbersOtherWay(int start, int end) {
        if(start > end) { return; }
        int mid = (start + end) / 2;
        printNumbersOtherWay(start, mid - 1);
        System.out.println(mid);
        printNumbersOtherWay(mid + 1, end);
    }

    /**
     * Output the numbers in given integer range in descending order.
     * @param start The start value of the range.
     * @param end The end value of the range.
     */
    public void printNumbersReverse(int start, int end) {
        if(start > end) { return; }
        printNumbersReverse(start+1, end);
        System.out.println(start);
    }

    /**
     * Find the smallest element in the given subarray.
     * @param a The array to search the smallest element in.
     * @param start The start index of the subarray.
     * @param end The end index of the subarray.
     */
    public int min(int[] a, int start, int end) {
        if(start == end) { return a[start]; }
        int mid = (start + end) / 2;
        int mleft = min(a, start, mid);
        int mright = min(a, mid + 1, end);
        return mleft < mright ? mleft : mright;
    }

    /**
     * Compute {@code a} raised to the power of {@code b}.
     * @param a The base of the exponentiation.
     * @param b The exponent.
     * @return {@code a} raised to the power of {@code b}.
     */
    public double power(double a, int b) {
        if(b == 0) { return 1.0; }
        if(b < 0) { return 1.0 / power(a, -b); }
        if(b % 2 == 0) { return power(a * a, b / 2); }
        return a * power(a, b - 1);
    }

    /**
     * Compute the harmonic sum of the first {@code n} integers.
     * @param n The number of terms in the harmonic sum.
     * @return The harmonic sum up to the term {@code n}.
     */
    public double harmonicSum(int n) {
        if(n < 1) { return 0; }
        return 1.0 / n + harmonicSum(n - 1);
    }

    /**
     * Construct a {@code StringBuilder} that contains {@code n} concatenated copies
     * of the given parameter string.
     * @param s The string to copy.
     * @param n The number of copies.
     * @return {@code n} concatenated copies of the parameter string.
     */
    public StringBuilder repeat(String s, int n) {
        if(n < 1) { return new StringBuilder(""); }
        return repeat(s, n-1).append(s);
    }
    
    /**
     * Count the number of occurrences of the given character inside the string.
     * @param s The string to count the occurrences in.
     * @param c The character to count for.
     * @return The number of occurrences of that character.
     */
    public int count(String s, char c) {
        if(s.length() == 0) { return 0; }
        return (s.charAt(0) == c ? 1 : 0) + count(s.substring(1), c);
    }

    // Private utility method needed by the next method.
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }
    
    /**
     * Remove all vowels from the given string.
     * @param s The string to disemvowel.
     * @param acc The accumulator of the result constructed so far.
     * @return The disemvoweled string, as a {@code StringBuilder}.
     */ 
    public StringBuilder disemvowel(String s, StringBuilder acc) {
        if(s.length() == 0) { return acc; }
        if(!isVowel(s.charAt(0))) { acc.append(s.charAt(0)); }
        return disemvowel(s.substring(1), acc);
    }

    /**
     * Using only integer arithmetic, compute the sum of digits of parameter {@code n}.
     * @param n The integers whose digits we sum.
     * @return The sum of digits of the parameter integer.
     */
    public int sumOfDigits(int n) {
        if(n < 0) { return sumOfDigits(-n); }
        if(n < 10) { return n; }
        else { return n % 10 + sumOfDigits(n / 10); }
    }

    /**
     * Using only integer arithmetic, reverse the digits of parameter {@code n}.
     * For example, 12345 would become 54321.
     * @param n The integers whose digits we reverse.
     * @return The number constructed from reversing the digits of the original number.
     */
    public int reverseDigits(int n, int acc) {
        if(n < 1) { return acc; }
        else return reverseDigits(n / 10, 10 * acc + n % 10);
    }

    /**
     * Given an integer array and a goal value, determine whether there exists a subset
     * of the first {@code n} elements in that array that add up exactly to the goal.
     * @param a The array to search the subset in.
     * @param goal The goal value to fulfill.
     * @param n The length of the array prefix that we are constrained into.
     * @return {@code true} if such a subset exists, {@code false} otherwise.
     */
    public boolean subsetSum(int[] a, int goal, int n) {
        if(goal == 0) { return true; }
        if(n == 0) { return false; }
        return subsetSum(a, goal - a[n-1], n - 1) || subsetSum(a, goal, n - 1);
    }
    
    /**
     * Given an integer array and a goal value, count how many subsets of the first
     * {@code n} elements in that array exist whose elements add up exactly to the goal.
     * @param a The array to search the subset in.
     * @param goal The goal value to fulfill.
     * @param n The length of the array prefix that we are constrained into.
     * @return The number of such subsets in the first {@code n} elements in the array.
     */
    public int subsetSumCount(int[] a, int goal, int n) {
        if(goal == 0) { return 1; }
        if(n == 0) { return 0; }
        return subsetSumCount(a, goal - a[n-1], n - 1) + subsetSumCount(a, goal, n - 1);
    }

}
