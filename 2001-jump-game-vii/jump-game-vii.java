class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        reachable[0] = true;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (i - minJump >= 0 && reachable[i - minJump]) {
                count++;
            }
            if (i - maxJump - 1 >= 0 && reachable[i - maxJump - 1]) {
                count--;
            }
            if (count > 0 && s.charAt(i) == '0') {
                reachable[i] = true;
            }
        }
        return reachable[n - 1];
    }
}