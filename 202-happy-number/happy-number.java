class Solution {
    public boolean isHappy(int n) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getSum(n);
        }

        return n == 1;
    }

    private int getSum(int n) {
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
}