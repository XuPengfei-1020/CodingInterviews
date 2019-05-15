package chapter6;

import datastructure.TowWayLinkedNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 题目一:滑动窗口的最大值。
 * 给定一个数组和滑动窗口的大小,请找出所有滑动窗口里的最大值。
 * 例如,如果输入数组(2,3,4,2,6,2,5,1及滑动窗口的大小3,那么一共存在6个滑动窗口,
 * 它们的最大值分别为{4,4,6,6,6,5},如表6.3所示。
 *
 * @author flying
 */
public class Q59_1_MaxValueInQueue {
    /**
     * 使用一个双向链表，辅助一个算法，记录一个队列中数据的最大值。
     */
    private class MaxRecord {
        TowWayLinkedNode head = null;
        TowWayLinkedNode tail = null;

        public void add(int e) {
            if (head == null) {
                head = tail = new TowWayLinkedNode();
                head.value = e;
                return;
            }

            if (e <= tail.value) {
                tail.next = new TowWayLinkedNode();
                tail.next.value = e;
                tail.next.prev = tail;
                tail = tail.next;
                return;
            }

            while (tail != null && e > tail.value) {
                tail = tail.prev;
            }

            if (tail == null) {
                head = tail = new TowWayLinkedNode();
                tail.value = e;
                return;
            }

            tail.next = new TowWayLinkedNode();
            tail.next.value = e;
            tail.next.prev = tail;
            tail = tail.next;
        }

        public void del(int e) {
            if (head.value == e) {
                head = head.next;
            }

            if (head == null) {
                tail = null;
            }
        }

        public int max() {
            if (head == null) {
                throw new IllegalStateException("IllegalState, no element");
            }

            return head.value;
        }
    }

    /**
     * 主要算法都在 {@link MaxRecord} 中，这个方法就不多介绍了。
     */
    private int[] maxValueInWindow(int[] arr, int windowSize) {
        if (arr == null || arr.length < windowSize) {
            throw new IllegalArgumentException("param is invalid");
        }

        MaxRecord maxRecord = new MaxRecord();
        Queue<Integer> window = new LinkedBlockingQueue<>();
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < windowSize; i++) {
            window.add(arr[i]);
            maxRecord.add(arr[i]);
        }

        results.add(maxRecord.max());

        for (int i = windowSize; i < arr.length; i++) {
            maxRecord.del(window.poll());
            window.add(arr[i]);
            maxRecord.add(arr[i]);
            results.add(maxRecord.max());
        }

        return Arrays.stream(results.toArray(new Integer[0])).mapToInt(i -> i).toArray();
    }

    @Test
    public void test() {
        MaxRecord record = new MaxRecord();
        int[] queueSequence = new int[] {
            1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,10,15,3
        };

        int[] benchMark = new int[] {
            1,2,3,4,5,6,7,8,9,9,9,9,9,9,9,9,9,10,15,15
        };

        for (int i = 0; i < queueSequence.length; i++) {
            record.add(queueSequence[i]);
            Assert.assertTrue(record.max() == benchMark[i]);
        }

        benchMark = new int[] {
            15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,3
        };

        for (int i = 0; i < queueSequence.length - 1; i++) {
            record.del(queueSequence[i]);
            Assert.assertTrue(record.max() == benchMark[i]);
        }

        Assert.assertTrue(Arrays.equals(maxValueInWindow(new int[] {2,3,4,2,6,2,5,1}, 3), new int[] {4,4,6,6,6,5}));
    }
}