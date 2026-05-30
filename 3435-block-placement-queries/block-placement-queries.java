import java.util.*;

class Solution {
    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            idx++;
            while (idx <= n + 1) {
                bit[idx] = Math.max(bit[idx], val);
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            idx++;
            int res = 0;
            while (idx > 0) {
                res = Math.max(res, bit[idx]);
                idx -= idx & -idx;
            }
            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int MAX = 50000;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(MAX);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        Fenwick bit = new Fenwick(MAX + 2);

        Integer prev = null;
        for (int x : obstacles) {
            if (prev != null) {
                bit.update(x, x - prev);
            }
            prev = x;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 1) {
                int x = q[1];

                Integer l = obstacles.lower(x);
                Integer r = obstacles.higher(x);

                obstacles.remove(x);

                if (l != null && r != null) {
                    bit.update(r, r - l);
                }
            } else {
                int x = q[1];
                int sz = q[2];

                Integer p = obstacles.floor(x);
                if (p == null) p = 0;

                int best = bit.query(p);
                int tail = x - p;

                ans.add(Math.max(best, tail) >= sz);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}