import java.util.*;
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, result, new ArrayList<>(), used);
        return result;
    }
    private void backtrack(int[] nums, List<List<Integer>> result,
                           List<Integer> current, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            current.add(nums[i]);
            used[i] = true;
            backtrack(nums, result, current, used);
current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}