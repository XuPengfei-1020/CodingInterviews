package leetcode;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 合并排序的思想，O(n) 算法，时间空间都很高效，打败 99% 的提交者。
 *
 * @author flyings
 */
class MedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return 0;
        }
        
        int length = nums1.length + nums2.length;
        
        if (length == 1) {
            return (double) (nums1.length == 0 ? nums2[0] : nums1[0]);
        }

        boolean tow = length % 2 == 0;
        int mid = (length + 1) / 2;
        int i = 0, j = 0;
        
        for (; i < nums1.length && j < nums2.length;) {
            int num = 0;

            if (nums1[i] <= nums2[j]) {
                num = nums1[i];
                i ++;
            } else {
                num = nums2[j];
                j ++;
            }

            if (i + j == mid) {
                if (!tow) {
                    return (double) num;
                }
                
                int next;
                
                if (i >= nums1.length) {
                    next = nums2[j];
                } else if (j >= nums2.length) {
                    next = nums1[i];
                } else {
                    next = nums2[j] <= nums1[i] ? nums2[j] : nums1[i];
                }
                
                return ((double) (next + num)) / 2;
            }
        }
        
        
        for (; i < nums1.length; i++) {
            if (i + j + 1 == mid) {
                return tow ? ((double) nums1[i] + nums1[i + 1]) / 2 : (double) nums1[i];
            }
        }
        
        for (; j < nums2.length; j++) {
            if (i + j + 1 == mid) {
                return tow ? ((double) nums2[j] + nums2[j + 1]) / 2 : (double) nums2[j];
            }
        }
        
        return 0;
    }
}