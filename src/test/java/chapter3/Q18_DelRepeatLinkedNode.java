package chapter3;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目二：删除链表中重复的节点。
 * 在一个排序的链表中，如何删除重复的节点？例如，在图3．4(a)中
 * 重复的节点被删除之后，链表如图3，4(b)所示。
 *
 * 比如删除前(排序好的)：
 * 1 > 2 > 2 > 3 > 4 > 5 > 6 > 6 > 7 > 8 > 9 > 9 > 9
 * 删除后：
 * 1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 > 9
 */
public class Q18_DelRepeatLinkedNode {
    /**
     * O(n) 时间内搞定。
     * 判断当前节点是否和下一个节点是相等的，是的话把当前节点和下下个节点相连。
     * 重复上一个判断，直到当前节点和下一个节点不同
     * 遍历到最后一个节点，结束
     */
    private void delRepeatNode(SimpleLinkedNode node) {
        while (node != null) {
            while (node.next != null && node.value == node.next.value) {
                node.next = node.next.next;
            }

            node = node.next;
        }
    }

    @Test
    public void test() {
        delRepeatNode(null);
        SimpleLinkedNode head = SimpleLinkedNode.build("1 > 2 > 2 > 3 > 4 > 5 > 6 > 6 > 7 > 8 > 9 > 9 > 9");
        delRepeatNode(head);
        Assert.assertTrue(head.serialize().equals("1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 > 9"));
        head = SimpleLinkedNode.build("1 > 1 > 1 > 1");
        delRepeatNode(head);
        Assert.assertTrue(head.serialize().equals("1"));
    }
}