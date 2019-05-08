package chapter3;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：定义一个函数，输入一个链表的头节点，反转该链表并输出反
 * 转后链表的头节点。链表节点定义如下：
 *
 * @author flying
 */
public class Q24_ReverseLinked {
    /**
     * 注意边界判断就好了。
     */
    private SimpleLinkedNode reverse(SimpleLinkedNode head) {
        if (head == null) {
            return head;
        }

        SimpleLinkedNode reversed = null;

        while (head != null) {
            SimpleLinkedNode next = head.next;
            head.next = reversed;
            reversed = head;
            head = next;
        }

        return reversed;
    }

    @Test
    public void test() {
        Assert.assertTrue(reverse(null) == null);
        SimpleLinkedNode head = SimpleLinkedNode.build("1 > 2 > 3 > 4 > 5");
        head = SimpleLinkedNode.build("1 > 2 > 3 > 4 > 5");
        Assert.assertTrue(reverse(head).serialize().equals("5 > 4 > 3 > 2 > 1"));
        head = SimpleLinkedNode.build("1 > 2 > 3 > 4 > 5");
        Assert.assertTrue(reverse(reverse(head)).serialize().equals("1 > 2 > 3 > 4 > 5"));
        head = SimpleLinkedNode.build("1 > 2 > 3 > 4 > 5");
        head.next = null;
        Assert.assertTrue(reverse(head) == head);
    }
}