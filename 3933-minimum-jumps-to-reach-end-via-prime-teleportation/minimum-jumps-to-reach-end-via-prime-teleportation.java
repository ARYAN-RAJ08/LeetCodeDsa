import java.util.*;

class Solution {

    public int minJumps(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Smallest Prime Factor (SPF) sieve
        int[] spf = new int[maxVal + 1];

        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }

        // prime -> indices divisible by prime
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            Set<Integer> factors = new HashSet<>();

            while (x > 1) {
                int p = spf[x];
                factors.add(p);

                while (x % p == 0) {
                    x /= p;
                }
            }

            for (int p : factors) {
                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int i = q.poll();
                if (i == n - 1) {
                    return steps;
                }
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }
                int val = nums[i];
                if (val > 1 && spf[val] == val) {
                    List<Integer> next = map.get(val);
                    if (next != null) {
                        for (int idx : next) {
                            if (!visited[idx]) {
                                visited[idx] = true;
                                q.offer(idx);
                            }
                        }
                        map.remove(val);
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}