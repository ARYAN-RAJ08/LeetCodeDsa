class Solution {
    private static final long MOD = 1_000_000_007L;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long[] up = new long[m + 1];
        long[] down = new long[m + 1];
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;
            down[v] = m - v;
        }
        if (n == 2) {
            long ans = 0;
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
            return (int) ans;
        }
        for (int len = 3; len <= n; len++) {
            long[] upNew = new long[m + 1];
            long[] downNew = new long[m + 1];
            long[] prefDown = new long[m + 1];
            long[] prefUp = new long[m + 1];
            for (int v = 1; v <= m; v++) {
                prefDown[v] = (prefDown[v - 1] + down[v]) % MOD;
                prefUp[v] = (prefUp[v - 1] + up[v]) % MOD;
            }
            long totalUp = prefUp[m];
            for (int v = 1; v <= m; v++) {
                upNew[v] = prefDown[v - 1];
                downNew[v] = (totalUp - prefUp[v] + MOD) % MOD;
            }
            up = upNew;
            down = downNew;
        }
        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }
        return (int) ans;
    }
}