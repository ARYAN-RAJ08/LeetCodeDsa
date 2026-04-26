class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        int left = 0, right = 0;
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0; 
        while (right < s.length()) {
            char c = s.charAt(right);
            
            if (need[c] > 0) {
                count--;
            }
            need[c]--;
            right++;
            while (count == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char leftChar = s.charAt(left);
                need[leftChar]++;
                
                if (need[leftChar] > 0) {
                    count++;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}