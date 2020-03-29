// [0, zero): zero
// (zero, one): one
// (two, ...): two
// [one, two]: exploring area 
class Solution {
    public void sortColors(int[] nums) {
        int zero = 0;
        int one = 0; 
        int two = nums.length - 1;
        while (one <= two) {
            if (nums[one] == 0) {
                swap(nums, zero++, one++);
            } else if (nums[one] == 1) {
                one++;
            } else {
                swap(nums, two--, one);
            }
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}