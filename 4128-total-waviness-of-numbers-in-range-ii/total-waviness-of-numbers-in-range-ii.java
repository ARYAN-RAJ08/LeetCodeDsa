class Solution {

    static class Pair {
        long cnt;
        long sum;

        Pair(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private char[] digits;
    private Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
    private long solve(long n) {
        if (n < 0) return 0;
        digits = String.valueOf(n).toCharArray();
        memo = new Pair[20][11][11][2][2];
        Pair res = dfs(0, 10, 10, 1, 0);
        return res.sum;
    }
    private Pair dfs(int pos, int prev2, int prev1, int tight, int started) {
        if (pos == digits.length) {
            return new Pair(1, 0);
        }
        if (memo[pos][prev2][prev1][tight][started] != null) {
            return memo[pos][prev2][prev1][tight][started];
        }
        int limit = tight == 1 ? digits[pos] - '0' : 9;
        long totalCnt = 0;
        long totalSum = 0;
        for (int d = 0; d <= limit; d++) {
            int ntight = (tight == 1 && d == limit) ? 1 : 0;
            if (started == 0 && d == 0) {
                Pair nxt = dfs(pos + 1, 10, 10, ntight, 0);
                totalCnt += nxt.cnt;
                totalSum += nxt.sum;
            } else {
                int add = 0;
                if (prev2 != 10 && prev1 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }
                int nprev2, nprev1;

                if (prev1 == 10) {
                    nprev2 = 10;
                    nprev1 = d;
                } else if (prev2 == 10) {
                    nprev2 = prev1;
                    nprev1 = d;
                } else {
                    nprev2 = prev1;
                    nprev1 = d;
                }
                Pair nxt = dfs(pos + 1, nprev2, nprev1, ntight, 1);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum + (long) add * nxt.cnt;
            }
        }
        return memo[pos][prev2][prev1][tight][started] =
                new Pair(totalCnt, totalSum);
    }
}