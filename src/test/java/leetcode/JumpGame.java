package leetcode;

/**
 * 跳跃游戏：https://leetcode.com/problems/jump-game-ii/
 * 最坏的时间复杂度 o(n),空间复杂度常量级别。一骑绝尘！
 *
 * leetcode 结果：1ms, 37.8mb 双 100%.
 * @author flying
 */
public class JumpGame {
    /**
     * 解题思路: 从前往后，找到能跳的最远的下一个点。一直到跳到终点。
     * （让我领悟到了在职业生涯中，正确的跳槽频率是多么重要！哈哈哈）
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int step = 0;

        for (int i = 0; i < nums.length;) {
            step++;
            int range = i + nums[i];

            if (range >= nums.length - 1) {
                return step;
            }

            int farMax = 0;

            for (int j = i + 1; j <= range; j++) {
                if (j + nums[j] > farMax) {
                    farMax = j + nums[j];
                    i = j;
                }
            }
        }
        
        return step;
    }
}