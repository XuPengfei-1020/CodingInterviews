package chapter3;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入两个递增排序的链表，合并这两个链表并使新链表中的节
 * 点仍然是递增排序的。例如，输入图3，il中的链表1和链表2，则合并之
 * 后的升序链表如链表3所示。链表节点定义如下：
 */
public class Q25_MergeSortedLinked {
    /**
     * 话不多说，就是干啊。默认从小到大排序。
     */
    private SimpleLinkedNode merge(SimpleLinkedNode lined1, SimpleLinkedNode lined2) {
        if(lined1 == null || lined2 == null) {
            return lined2 == null ? lined1 : lined2;
        }

        SimpleLinkedNode head = new SimpleLinkedNode();
        SimpleLinkedNode tail = head;

        while (lined1 != null && lined2 != null) {
            if (lined1.value < lined2.value) {
                tail.next = lined1;
                lined1 = lined1.next;
            } else {
                tail.next = lined2;
                lined2 = lined2.next;
            }

            tail = tail.next;
        }

        SimpleLinkedNode remain = lined1 != null ? lined1 : lined2;

        while (remain != null) {
            tail.next = remain;
            tail = tail.next;
            remain = remain.next;
        }

        return head.next;
    }

    @Test
    public void test() {
        SimpleLinkedNode linkedNode1 = SimpleLinkedNode.build("1 > 2 > 4 > 5 > 7 > 8 > 9");
        SimpleLinkedNode linkedNode2 = SimpleLinkedNode.build("3 > 5 > 6");
        Assert.assertTrue(merge(linkedNode1, linkedNode2).serialize().equals("1 > 2 > 3 > 4 > 5 > 5 > 6 > 7 > 8 > 9"));
        linkedNode1 = SimpleLinkedNode.build("1 > 1 > 2 > 4 > 5 > 7 > 8 > 9");
        linkedNode2 = SimpleLinkedNode.build("3 > 5 > 6 > 10");
        Assert.assertTrue(merge(linkedNode1, linkedNode2).serialize().equals("1 > 1 > 2 > 3 > 4 > 5 > 5 > 6 > 7 > 8 > 9 > 10"));
        linkedNode1 = SimpleLinkedNode.build("1");
        linkedNode2 = SimpleLinkedNode.build("1");
        Assert.assertTrue(merge(linkedNode1, linkedNode2).serialize().equals("1 > 1"));
        linkedNode2 = SimpleLinkedNode.build("1");
        Assert.assertTrue(merge(linkedNode1, null).serialize().equals("1"));
        Assert.assertTrue(merge(null, linkedNode1).serialize().equals("1"));
    }
}