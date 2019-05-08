package chapter4;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 题目：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第
 * 二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，
 * 序列{1，2，3，4，5}是某栈的压栈序列，序列{4，5，3，2， 1}是该压栈序列对应的一
 * 个弹出序列，但{4，3，5，1，2}就不可能是该压栈序列的弹出序列。
 *
 */
public class Q31_SequenceOfStackPushAndPop {
    /**
     * 归纳如下：
     * 创建一个 stack，然后循环弹出序列，对于当前需要弹出的元素 i
     * 如果 i 在 栈顶，弹出它。
     * 如果 i 在剩余的入栈序列中，则把入栈序列中 i 之前的元素都入栈，然后入栈序列等于 i 之后的部分。
     * 如果 i 不在栈顶，也不在入栈序列中，那么给定的弹出序列就不可能从给定的入栈序列中弹出来。
     */
    private boolean checkIfSequenceIsValid(int[] pushSequence, int[] popSequence) {
        if (pushSequence == null || pushSequence.length == 0 || popSequence == null || popSequence.length == 0) {
            throw new IllegalArgumentException("param in invalid");
        }

        if (pushSequence.length != popSequence.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;

        for (int i = 0; i < popSequence.length; i++) {
            int poped = popSequence[i];

            if(!stack.isEmpty() && stack.peek() == poped) {
                stack.pop();
            } else {
                boolean notFound = true;

                while (pushIndex < pushSequence.length && (notFound = pushSequence[pushIndex] != poped)) {
                    stack.push(pushSequence[pushIndex]);
                    pushIndex++;
                }

                if (notFound) {
                    return false;
                } else {
                    pushIndex++;
                }
            }
        }

        return true;
    }

    @Test
    public void test() {
        int[] pushSequence = {1, 2, 3, 4, 5};
        int[] popSequence = new int[] {1, 2, 3, 4, 5};
        Assert.assertTrue(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {1, 2, 3, 5, 4};
        Assert.assertTrue(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {5, 4, 3, 2, 1};
        Assert.assertTrue(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {4, 5, 3, 2, 1};
        Assert.assertTrue(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {4, 3, 5, 1, 2};
        Assert.assertFalse(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {4, 2, 5, 1, 3};
        Assert.assertFalse(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {4, 1, 5, 2, 3};
        Assert.assertFalse(checkIfSequenceIsValid(pushSequence, popSequence));
        popSequence = new int[] {5, 3, 1, 4, 2};
        Assert.assertFalse(checkIfSequenceIsValid(pushSequence, popSequence));
    }
}