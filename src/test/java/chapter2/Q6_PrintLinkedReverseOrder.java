package chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 题目：输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
 * 链表节点定义如下：
 * @author flying
 */
public class Q6_PrintLinkedReverseOrder {

    /**
     * 非递归
     */
    private int[] printSingleLinkedListReverseOrder(SingleLinkedNode rootNode) {
        if (rootNode == null) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();

        while (rootNode != null) {
            stack.add(rootNode.value);
            rootNode = rootNode.next;
        }

        int[] result = new int[stack.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }

        return result;
    }

    /**
     * 递归方式遍历
     */
    private void printSingleLinkedListReverseOrderRecursive(SingleLinkedNode rootNode, ArrayList<Integer> result) {
        if (rootNode == null) {
            return;
        }

        printSingleLinkedListReverseOrderRecursive(rootNode.next, result);
        result.add(rootNode.value);
    }

    /**
     * 非递归, 双向
     * @param rootNode
     */
    private Integer[] printTowWayLinkedListReverseOrder(TwoWayLinkedNode rootNode) {
        if (rootNode == null) {
            return null;
        }

        while (rootNode.next != null) {
            rootNode = rootNode.next;
        }

        ArrayList<Integer> results = new ArrayList<>();

        while (rootNode != null) {
            results.add(rootNode.value);
            rootNode = rootNode.prev;
        }

        return results.toArray(new Integer[0]);
    }

    @Test
    public void test() {
        SingleLinkedNode root = new SingleLinkedNode();
        root.value = 0;
        SingleLinkedNode singleLinkedNode = root;
        int length = 0;
        int[] expect =  new int[11];
        expect[0] = 10;

        while (length++ < 10) {
            expect[expect.length - length] = length - 1;
            singleLinkedNode.next = new SingleLinkedNode();
            singleLinkedNode.next.value = length;
            singleLinkedNode = singleLinkedNode.next;
        }


        Assert.assertTrue(Arrays.equals(printSingleLinkedListReverseOrder(root), expect));
        ArrayList<Integer> resultList = new ArrayList();
        printSingleLinkedListReverseOrderRecursive(root, resultList);
        Assert.assertTrue(
            Arrays.equals(Arrays.stream(resultList.toArray(new Integer[0])).mapToInt(i->i).toArray() , expect));

        TwoWayLinkedNode twoWayLinkedNode = new TwoWayLinkedNode();
        twoWayLinkedNode.value = --length;

        while (--length >= 0) {
            twoWayLinkedNode.prev = new TwoWayLinkedNode();
            twoWayLinkedNode.prev.value = length;
            twoWayLinkedNode.prev.next = twoWayLinkedNode;
            twoWayLinkedNode = twoWayLinkedNode.prev;
        }

        Assert.assertTrue(Arrays.equals(
            Arrays.stream(printTowWayLinkedListReverseOrder(twoWayLinkedNode)).mapToInt(i->i).toArray(), expect));
    }

    /**
     * 单向链表
     */
    public static class SingleLinkedNode {
        public int value;
        public SingleLinkedNode next;
    }

    /**
     * 双向链表
     */
    public static class TwoWayLinkedNode extends SingleLinkedNode {
        public TwoWayLinkedNode next;
        public TwoWayLinkedNode prev;
    }
}