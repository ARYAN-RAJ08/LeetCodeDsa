import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : grid) {
            for (int val : row) {
                list.add(val);
            }
        }
        int base = list.get(0);
        for (int val : list) {
            if ((val - base) % x != 0) {
                return -1;
            }
        }
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        int operations = 0;
        for (int val : list) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}