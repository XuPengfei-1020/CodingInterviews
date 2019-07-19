package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/submissions/
 * 时空 o(n) 算法
 * 截至提交时：1 ms, faster than 99.60%，Memory Usage: 40.2 MB, less than 83.66%
 * （关于使用内存，ArrayList 可能是个优化的点，或者是利用 intervals，直接在 intervals 上修改，不过此方法有局限性）
 *
 * @author flying
 */
public class InsertInterval {
    /**
     * 解体思路：确定 o(n) 循环后划分逻辑分支。
     *
     * 先引入一个概念：区间干涉，干涉指的是 两个区间发生了交叉包含。比如 [1, 3] 和 [2, 4], [1, 5] 和 [2, 4] 都发生了干涉。
     *
     * 1. 声明 mark 变量用于标记 newInterval 的处理状态。
     *    mark:0 表示遍历过的元素目前还没有和 newInterval 产生干涉
     *    mark:1 表示在之前的遍历中已经处理完毕干涉，newInterval 已无价值，把 intervals 后面的区间按序输出就行了。
     *    mark:2 表示 newInterval 已经和之前遍历到的部分产生了干涉，并且还没确定最终的右边界，需要继续遍历判断
     *
     * 2. 定义 ls 和 le，[ls, le] 表示之前遍历的部分和 newInterval 合并干涉后的区间。
     *
     * 3. 循环 intervals，对于每一次循环到的区间，记作 interval
     *    3.1: 如果 mark 为 0 ，表示之前还未发生干涉的情况，那么当前 interval 和 newInterval 有三种位置关系可能，
     *       3.1.1: 当前 interval 在 newInterval 的左侧，不干涉，那么输出当前 interval，进入下一次循环。
     *       3.1.2: 当前 interval 在 newInterval 的右侧，不干涉，输出 newInterval 和当前 interval，
     *          并且标记 mark 为 1（表示干涉已经处理完毕），进入下一次循环。
     *       3.1.3: 当前 interval 包含了 newInterval，干涉，但是此种类型的干涉可以直接合并并且确定左右边界就是当前
     *           interval 的边界，输出 interval，然后标记 mark 为 1（表示干涉已经处理完毕），进入下一次循环。
     *       3.1.4: 当前 interval 和 newInterval 产生了干涉，且无法确定最终的右边界。
     *          前三种情况都好处理，此种情况不太好处理，我们需要把干涉后的范围(叠加区间)记下来，
     *          新的合并区间为 [min(interval.left, newInterval.left), max(interval.right, newInterval.right)],
     *          记录为 [ls，le]，并且标记 mark 为 1，表示正在处理干涉，并且进入下一次循环。
     *
     *    3.2: 如果 mark 为 1，表示正在处理干涉，那么需要判断上一次干涉后的叠加区间 [ls，le] 和 当前 interval 的关系
     *       我们知道 ls 是一定在当前的 interval 的左侧的，那么根据 le 和 interval 的位置关系分为两种情况处理
     *       3.2.1：如果 le 在当前 interval 的左侧，那么 [ls, le] 和当前的 interval 不干涉，
     *          输出 [ls, le] 以及当前 interval，并且标记 mark 为 1 表示干涉处理完毕了。
     *       3.2.2: 如果 le 在当前 interval 的中间或者右侧，那么 [ls, le] 和当前的 interval 仍然有干涉，
     *          我们把 [ls, le] 和当前的 interval 叠加一下 => [ls, max(le, interval.right)] 然后进入下一次循环，
     *          继续处理干涉问题。
     *
     *     3.3 如果 mark 为 2，表示干涉已经处理完毕了，此使把 interval 后续的区间都输出出来就行了。
     *
     *     3.4 最后，判断一下 mark 的值，如果
     *        3.4.1 mark == 0，说明 newInterval 一直没有被处理，那么 newInterval 在所有 intervals 的右侧，把 newInterval
     *           输出，作为新的 interval 的最后一个元素就行了。
     *        3.4.1 mark == 2， 说明干涉还没有处理完毕，interval 已经遍历完了，那么把[ls, le] 输出，
     *           作为新的 interval 的最后一个元素就行了。
     *
     * @param intervals 给定的一些区间
     * @param newInterval 需要新插入的区间
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<int[]>();
        int mark = 0;
        int ls = 0, le = 0;

        for (int i = 0; intervals != null && i < intervals.length; i++) {
            int s = intervals[i][0], e = intervals[i][1];

            if (mark == 0) {
                if (newInterval[1] < s) {
                    result.add(newInterval);
                    result.add(intervals[i]);
                    mark = 1;
                    continue;
                }
                
                if (e < newInterval[0]) {
                    result.add(intervals[i]);
                    continue;
                }
                
                if (s <= newInterval[0] && e >= newInterval[1]) {
                    return intervals;
                }
                
                ls = s > newInterval[0] ? newInterval[0]: s;
                le = e > newInterval[1] ? e : newInterval[1];
                mark = 2;
                continue;
            }
            
            if (mark == 1) {
                result.add(intervals[i]);
                continue;
            }
            
            if (mark == 2) {
                if (le < s) {
                    result.add(new int[] {ls, le});
                    result.add(intervals[i]);
                    mark = 1;
                    continue;
                }
                
                le = Math.max(e, le);
            }
        }
        
        if (mark == 0) {
            result.add(newInterval);
        }
        
        if (mark == 2) {
            result.add(new int[] {ls, le});
        }

        return result.toArray(new int[result.size()][]);
    }
}