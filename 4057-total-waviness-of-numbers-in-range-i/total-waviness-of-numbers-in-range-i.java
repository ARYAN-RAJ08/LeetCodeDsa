class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        for (int num = num1; num <= num2; num++) {
            String s = String.valueOf(num);
            if (s.length() < 3) continue;
            for (int i = 1; i < s.length() - 1; i++) {
                int left = s.charAt(i - 1) - '0';
                int cur = s.charAt(i) - '0';
                int right = s.charAt(i + 1) - '0';
                if ((cur > left && cur > right) ||
                    (cur < left && cur < right)) {
                    total++;
                }
            }
        }
        return total;
    }
}