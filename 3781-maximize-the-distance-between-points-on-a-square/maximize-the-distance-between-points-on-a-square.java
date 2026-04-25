import java.util.*;

class Solution {
    long sideLen;

    private long getDist(int[] p) {
        int x = p[0], y = p[1];

        if (y == 0) return x;
        if (x == sideLen) return sideLen + y;
        if (y == sideLen) return 3 * sideLen - x;
        return 4 * sideLen - y;
    }

    private boolean canPlace(long[] arr, int k, long d) {
        int n = arr.length / 2;

        for (int i = 0; i < n; i++) {
            int count = 1;
            long first = arr[i];
            long last = arr[i];
            int idx = i;

            while (count < k) {
                long target = last + d;

                int next = Arrays.binarySearch(arr, idx + 1, i + n, target);

                if (next < 0) next = -next - 1;
                if (next >= i + n) break;

                last = arr[next];
                idx = next;
                count++;
            }

            if (count == k) {
                if ((first + 4 * sideLen) - last >= d) {
                    return true;
                }
            }
        }
        return false;
    }

    public int maxDistance(int side, int[][] points, int k) {
        sideLen = side;

        int n = points.length;
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = getDist(points[i]);
        }

        Arrays.sort(arr);
        long[] extended = new long[2 * n];
        for (int i = 0; i < n; i++) {
            extended[i] = arr[i];
            extended[i + n] = arr[i] + 4L * side;
        }
        long low = 0, high = 2L * side;
        long ans = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (canPlace(extended, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }
}