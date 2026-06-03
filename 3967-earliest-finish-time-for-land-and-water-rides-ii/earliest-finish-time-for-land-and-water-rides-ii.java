class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
 int[] waterStartTime, int[] waterDuration) {
        long ans = Long.MAX_VALUE;
        ans = Math.min(ans,
                solve(landStartTime, landDuration,
                      waterStartTime, waterDuration));
        ans = Math.min(ans,
                solve(waterStartTime, waterDuration,
                      landStartTime, landDuration));

        return (int) ans;
    }
    private long solve(int[] startA, int[] durA,
                       int[] startB, int[] durB) {

        int m = startB.length;
        int[][] rides = new int[m][2];
        for (int i = 0; i < m; i++) {
            rides[i][0] = startB[i];
            rides[i][1] = durB[i];
        }
        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));
        int[] starts = new int[m];
        long[] prefDur = new long[m];
        long[] suffStartDur = new long[m];
        for (int i = 0; i < m; i++) {
            starts[i] = rides[i][0];
        }
        prefDur[0] = rides[0][1];
        for (int i = 1; i < m; i++) {
            prefDur[i] = Math.min(prefDur[i - 1], rides[i][1]);
        }
        suffStartDur[m - 1] = (long) rides[m - 1][0] + rides[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            suffStartDur[i] = Math.min(
                    suffStartDur[i + 1],
                    (long) rides[i][0] + rides[i][1]
            );
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < startA.length; i++) {
            long finishA = (long) startA[i] + durA[i];
            int pos = upperBound(starts, (int) finishA);
            if (pos > 0) {
                res = Math.min(res, finishA + prefDur[pos - 1]);
            }
            if (pos < m) {
                res = Math.min(res, suffStartDur[pos]);
            }
        }
        return res;
    }
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}