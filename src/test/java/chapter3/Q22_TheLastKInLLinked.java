package chapter3;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一个链表，输出该链表中倒数第k个节点。为了符合大多
 * 数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例
 * 如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、
 * 6。这个链表的倒数第3个节点是值为4的节点。链表节点定义如下：
 *
 * @author flying
 */
public class Q22_TheLastKInLLinked {
    /**
     * 双指针
     */
    private SimpleLinkedNode theLastKInLinked(SimpleLinkedNode node, int k) {
        if (k < 1) {
            throw new IllegalArgumentException("param is invalid!");
        }

        if (node == null) {
            return null;
        }

        SimpleLinkedNode forward = node;
        SimpleLinkedNode ghost = node;

        for (int i = 1; i < k; i++) {
            if (forward.next == null) {
                return null;
            }

            forward = forward.next;
        }

        while (forward.next != null) {
            forward = forward.next;
            ghost = ghost.next;
        }

        return ghost;
    }

    @Test
    public void test() {
        SimpleLinkedNode node = SimpleLinkedNode.build("9 > 8 > 7 > 6 > 5 > 4 > 3 > 2 > 1");

        for (int i = 1 ; i < 10; i++) {
            Assert.assertTrue(theLastKInLinked(node, i).value == i);
        }

        Assert.assertTrue(theLastKInLinked(node, 20) == null);
        Assert.assertTrue(theLastKInLinked(node, 10) == null);
    }
}