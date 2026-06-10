import java.util.*;
class Solution {
    class SparseTable {
        int n, LOG;
        int[][] mx;
        int[][] mn;
        int[] lg;
        SparseTable(int[] nums) {
            n = nums.length;
            LOG = 17;
            while ((1 << LOG) <= n) LOG++;
            mx = new int[n][LOG];
            mn = new int[n][LOG];
            lg = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                lg[i] = lg[i >> 1] + 1;
            }
            for (int i = 0; i < n; i++) {
                mx[i][0] = nums[i];
                mn[i][0] = nums[i];
            }
            for (int j = 1; j < LOG; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    mx[i][j] = Math.max(
                            mx[i][j - 1],
                            mx[i + (1 << (j - 1))][j - 1]
                    );
                    mn[i][j] = Math.min(
                            mn[i][j - 1],
                            mn[i + (1 << (j - 1))][j - 1]
                    );
                }
            }
        }
        int queryMax(int l, int r) {
            int k = lg[r - l + 1];
            return Math.max(
                    mx[l][k],
                    mx[r - (1 << k) + 1][k]
            );
        }
        int queryMin(int l, int r) {
            int k = lg[r - l + 1];
            return Math.min(
                    mn[l][k],
                    mn[r - (1 << k) + 1][k]
            );
        }
        long value(int l, int r) {
            return (long) queryMax(l, r) - queryMin(l, r);
        }
    }
    class Node {
        long val;
        int l;
        int r;
        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTable st = new SparseTable(nums);
        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));
        for (int l = 0; l < n; l++) {
            int r = n - 1;
            pq.offer(new Node(st.value(l, r), l, r));
        }
        long ans = 0;
        while (k-- > 0) {
            Node cur = pq.poll();
            ans += cur.val;
            if (cur.r > cur.l) {
                int nr = cur.r - 1;
                pq.offer(new Node(
                        st.value(cur.l, nr),
                        cur.l,
                        nr
                ));
            }
        }
        return ans;
    }
}