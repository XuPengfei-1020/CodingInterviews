package chapter4;

import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;
import sun.awt.image.ImageWatched;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小
 * 元素的min函数。在该栈中，调用min、push及POP的时间复杂度都是0(1)。
 * @author flying
 */
public class Q30_StackWithMinimumFucntion {
    /**
     * 使用另外一个栈 s'，记住那些曾经的最小值就好了。
     * 存放元素的时候，如果这个元素比 s' 栈顶部的元素还小，那么把这个元素压入 s' 栈，作为当前栈的最小元素。
     * 弹出元素的时候，如果弹出的元素是 s' 栈顶部的元素，那么把这个元素从 s' 中也弹出来。s' 中剩下的元素仍然是最小元素。
     */
    private class Stack {
        int index = -1;
        int[] elements = new int[20];
        // 使用链表模拟栈吧。
        LinkedList<Integer> minRecord = new LinkedList<>();

        /**
         * 存放元素
         */
        public void push(int i) {
            checkRange();
            elements[++index] = i;

            if (minRecord.size() == 0 || i <= minRecord.getLast()) {
                minRecord.add(i);
            }
        }

        /**
         * 弹出栈顶元素
         */
        public int pop() {
            if (index == -1) {
                throw new RuntimeException("只想让 stack 弹出元素，又给不 stack 喂元素。。");
            }

            int i = elements[index--];

            if (i == minRecord.getLast()) {
                minRecord.removeLast();
            }

            return i;
        }

        /**
         * 返回最小元素
         */
        public int min () {
            if (minRecord.size() == 0) {
                throw new RuntimeException("stack 中没有元素啊。。。");
            }

            return minRecord.getLast();
        }

        // 扩容。
        private void checkRange() {
            if (index == elements.length  - 1) {
                int[] newElements =
                    new int[elements.length < Integer.MAX_VALUE / 2 ? elements.length * 2 : Integer.MAX_VALUE];
                System.arraycopy(elements, 0, newElements, 0, elements.length);
                elements = newElements;
            }
        }
    }

    @Test
    public void test() {
        Stack stack = new Stack();
        int range = 101;

        // 生产出来的数字，奇数都是等于自己 range - index 位置的，偶数都等于 100 + index 的。
        // 这样的话就有利于做功能测试了。

        for (int i = 0; i < range; i++) {
            stack.push((i & 1) == 1 ? (range - i) : range + i);
            Assert.assertTrue(stack.min() == ((i & 1) == 1 ? range - i : i == 0 ? range : range - i + 1));
        }

        for (int i = 0; i < range; i++) {
            Assert.assertTrue(stack.min() == ((i & 1) == 1 ? i + 1 : i == range - 1 ? range : i + 2));
            stack.pop();
        }
    }
}