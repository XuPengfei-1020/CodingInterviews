package chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函
 * 数 appendTail 和 deleteHead ，分别完成在队列尾部插入节点和在队列头部删
 * 除节点的功能。
 */
public class Q9_TowStackSimulateQueue {
    private class StackQueue implements Queue {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        @Override
        public void append(int i) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            stack1.push(i);

            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        @Override
        public int deleteHead() {
            if (!stack1.isEmpty()) {
                return stack1.pop();
            }

            return -1;
        }

        @Override
        public int[] toArray() {
            int[] result = new int[stack1.size()];
            int i = 0;

            while (!stack1.isEmpty()) {
                result[i++] = stack1.pop();
            }

            for (i = result.length - 1; i >= 0; i--) {
                stack1.push(result[i]);
            }

            return result;
        }
    }

    @Test
    public void test() {
        Queue queue = new StackQueue();
        queue.deleteHead();
        queue.append(7);
        queue.append(0);
        queue.append(0);
        queue.append(1);
        queue.append(2);
        queue.append(3);
        queue.append(4);
        queue.append(5);
        queue.append(6);
        queue.deleteHead();
        queue.deleteHead();
        int[] elements = queue.toArray(); // 0, 1, 2, 3, 4, 5, 6
        elements = queue.toArray();

        for (int i = 0; i < elements.length; i++) {
            Assert.assertTrue(elements[i] == i);
        }
    }

    interface Queue {
        void append(int i);
        int deleteHead();
        int[] toArray();
    }
}