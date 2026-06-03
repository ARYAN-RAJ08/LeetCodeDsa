class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '-') continue;

            if (sb.length() > 0 && sb.length() % (k + 1) == k) {
                sb.append('-');
            }

            sb.append(Character.toUpperCase(c));
        }

        return sb.reverse().toString();
    }
}