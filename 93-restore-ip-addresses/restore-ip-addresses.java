import java.util.*;
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s, 0, new ArrayList<>());
        return result;
    } 
    private void backtrack(List<String> result, String s, int index, List<String> path) {
        if (path.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }
        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String part = s.substring(index, index + len);
            if (!isValid(part)) continue;  
            path.add(part);
            backtrack(result, s, index + len, path);
            path.remove(path.size() - 1);
        }
    }   
    private boolean isValid(String s) {
        if (s.length() > 1 && s.charAt(0) == '0') return false;
        int num = Integer.parseInt(s);
        return num <= 255;
    }
}