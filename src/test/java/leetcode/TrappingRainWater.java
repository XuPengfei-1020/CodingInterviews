package leetcode;

/**
 * leetcode 题目：https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 时空 o(n) 算法, 截至提交 leetcode 时，fast than 100%，memory less than 99%。
 * @author flying
 */
public class TrappingRainWater {
    /**
     * 思路阐述：
     *
     * 1. 使用一个辅助数组 a，数组中的元素 a[i] 表示自 height 数组中从 i 开始，右侧元素中的最大值。
     * 这个数组可以在 o(n)时间内计算出来。
     *
     * 2. 遍历 height 数组，对于每一个当前下标，记录当前下标左侧出现过的最大值。结合步骤 1 中预处理好的右侧的最大值，
     * 此时可以知道当前下标的左侧和右侧的最大值元素
     *
     * 3. 使用两个最大值中的较小的值（木桶原理，短板效应），减去当前下标的值。即为当前下标上的雨水值。
     * 如果计算出雨水值为负数，则表示当前下标上无法累积雨水，则记录为 0.
     *
     * 4. 重复 2~3 步骤，知道遍历到最后一个元素。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int[] rightMax = new int[height.length];
        rightMax[rightMax.length - 1] = height[height.length - 1];

        for (int i = height.length - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        int leftMax = height[0];
        int collect = 0;

        for (int i = 1; i < height.length - 1; i++) {
            collect += Math.max(0, Math.min(rightMax[i + 1], leftMax) - height[i]);
            leftMax = Math.max(leftMax, height[i]);
        }

        return collect;
    }
}