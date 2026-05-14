class Solution {
    public boolean containsDuplicate(int[] nums) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }
        return false;
    }
}