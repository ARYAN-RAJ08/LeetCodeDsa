class Solution {
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            char prev = result.charAt(0);
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == prev) {
                    count++;
                } else {
                    temp.append(count).append(prev);
                    prev = result.charAt(j);
                    count = 1;
                }
            }
            temp.append(count).append(prev); // last group
            result = temp.toString();
        }
        return result;
    }
}