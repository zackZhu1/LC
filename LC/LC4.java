// 每次砍掉一半: binary search
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        if (len % 2 != 0)
            return kth(nums1, 0, nums2, 0, len / 2 + 1);
        else 
            return (kth(nums1, 0, nums2, 0, len / 2) + kth(nums1, 0, nums2, 0, len/2+1) ) / 2.0;
    }
    
    private double kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        if (aLeft >= a.length) return b[bLeft + k - 1];
        if (bLeft >= b.length) return a[aLeft + k - 1];
        if (k == 1) return Math.min(a[aLeft], b[bLeft]);
        
        int aMid = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;
        int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
        int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
        if (aVal <= bVal) return kth(a, aMid + 1, b, bLeft, k - k / 2);
        else return kth(a, aLeft, b, bMid + 1, k - k / 2);
    }
}