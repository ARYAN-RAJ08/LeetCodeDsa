class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        int[][] athletes = new int[n][2];
        for (int i = 0; i < n; i++) {
            athletes[i][0] = score[i];
            athletes[i][1] = i;
        }
        Arrays.sort(athletes, (a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            int idx = athletes[i][1];
            if (i == 0) {
                result[idx] = "Gold Medal";
            } else if (i == 1) {
                result[idx] = "Silver Medal";
            } else if (i == 2) {
                result[idx] = "Bronze Medal";
            } else {
                result[idx] = String.valueOf(i + 1);
            }
        }
        return result;
    }
}