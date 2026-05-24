class Solution {
    int[] dp;

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(arr, d, i));
        }

        return ans;
    }

    private int dfs(int[] arr, int d, int idx) {
        if (dp[idx] != 0) {
            return dp[idx];
        }

        int max = 1;

        // Move right
        for (int i = idx + 1; i <= Math.min(arr.length - 1, idx + d); i++) {

            // Stop if blocked
            if (arr[i] >= arr[idx]) {
                break;
            }

            max = Math.max(max, 1 + dfs(arr, d, i));
        }

        // Move left
        for (int i = idx - 1; i >= Math.max(0, idx - d); i--) {

            // Stop if blocked
            if (arr[i] >= arr[idx]) {
                break;
            }

            max = Math.max(max, 1 + dfs(arr, d, i));
        }

        return dp[idx] = max;
    }
}