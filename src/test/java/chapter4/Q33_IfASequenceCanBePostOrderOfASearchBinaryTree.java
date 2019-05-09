package chapter4;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍
 * 历结果。如果是则返回true，否则返回 false 。假设输入的数组的任意两个
 * 数字都互不相同。例如，输入数组{5, 7, 6, 9, 11, 10, 8}，则返回 true，因为
 * 这个整数序列是图4，9二叉搜索树的后序遍历结果。如果输入的数组是{7,
 * 4, 6, 5},则由于没有哪棵二叉搜索树的后序遍历结果是这个序列，因此返
 * 回false
 *
 * @author flying
 */
public class Q33_IfASequenceCanBePostOrderOfASearchBinaryTree {
    /**
     * 根据搜索二叉树的性质，如果左子树不空，那么左子树所有节点都小于 root 节点。右子树也是。
     * 1. 那么，倒数第一个节点一定是根节点
     * 2. 从根节点出发，如果可以找到第一个比它小的元素，那么从这个元素到第一个元素，都是左子树的内容，
     *     这些元素都得比根节点小。
     * 3. 从根节点出发，如果可以找到最后一个比它大的元素，那么从这个元素到第一个元素的下一个元素，都是右子树的内容，
     *      这些元素都得比根节点大。
     * 4. 递归对左子树和右子树做同样的上述检查，直到所有的元素检查完毕。或者查到中间有不满足规范的元素。
     *
     * 不用考虑重复的元素，让这道题简单了不少。。
     */
    private boolean sequenceCanBePostOrderOfSomeoneSearchBinaryTree(int[] sequence, int start, int end) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        if (start == end) {
            // 就一个元素
            return true;
        }

        int root = sequence[end];
        int index = end - 1;

        while (index >= start && sequence[index] > root) {
            index--;
        }

        if (index != end - 1) {
            // 存在右子树, 从 index + 1 ~ end - 1 中的内容都比 root 大，对右子树做同样的检查。
            if (!sequenceCanBePostOrderOfSomeoneSearchBinaryTree(sequence, index + 1, end - 1)) {
                return false;
            }
        }

        if (index >= start) {
            // 疑似存在左子树。检查一下
            for (int i = start; i <= index; i++) {
                if (sequence[i] > root) {
                    return false;
                }
            }

            if (!sequenceCanBePostOrderOfSomeoneSearchBinaryTree(sequence, start, index)) {
                return false;
            }
        }

        // 左右两侧都没有问题
        return true;
    }

    @Test
    public void test() {
        int[][][] testCases = new int[][][] {
            new int[][] {
                new int[] {5, 7, 6, 9, 11, 10, 8}, new int[] {1}
            },

            new int[][] {
                new int[] {8, 7, 6, 5, 4, 3, 2, 1}, new int[] {1}
            },

            new int[][] {
                new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1}
            },

            new int[][] {
                new int[] {7}, new int[] {1}
            },

            new int[][] {
                new int[] {7, 4, 6, 5}, new int[] {0}
            },

            new int[][] {
                new int[] {10, 7, 6, 5, 4, 3, 2, 9}, new int[] {0}
            },
        };

        for (int[][] testCase : testCases) {
            Assert.assertTrue(
                sequenceCanBePostOrderOfSomeoneSearchBinaryTree(testCase[0], 0, testCase[0].length - 1) ==
                    (testCase[1][0] == 1));
        }
    }
}