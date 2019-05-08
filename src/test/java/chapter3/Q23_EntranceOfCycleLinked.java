package chapter3;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;
import sun.nio.ch.SelectorImpl;

import java.util.HashSet;

/**
 * 题目：如果一个链表中包含环，如何找出环的入口节点？例如，在如
 * 图3．8所示的链表中，环的入口节点是节点 3.
 */
public class Q23_EntranceOfCycleLinked {
    /**
     * 简单但是需要 o(n) 空间的方法。把遍历过的结点存放到 hash 中
     */
    private SimpleLinkedNode entranceOfCycleApproach1(SimpleLinkedNode head) {
        if (head == null) {
            return null;
        }

        HashSet<SimpleLinkedNode> passed = new HashSet<>();

        while (head != null) {
            if (passed.contains(head)) {
                return head;
            }

            passed.add(head);
            head = head.next;
        }

        return null;
    }

    /**
     * 高效的方法，拿到 offer 就靠它了。
     * 两个指针，一个跑得快（一次两步），一个跑的慢(一次一步)。如果有环，他俩会相遇。
     * 相遇的时候，快的指针走了 m 个位置，满指针走了 n 个指针。假如 cl 是环的长度，那么 m - n = k * cl
     *  k 还不知道是多少。但是我们可以让慢指针再走一圈，算出来圈的长度 cl。这样 k 的值就算出来了。
     * 知道环的数目了，那么把两个指针重置，快的比慢的提前走 cl 个，然后同样的速度移动，然后它俩会在环口处相遇。神奇吧！
     */
    private SimpleLinkedNode entranceOfCycleApproach2(SimpleLinkedNode head) {
        SimpleLinkedNode slowly = head;
        SimpleLinkedNode quickly = head;

        while (quickly != null && quickly.next != null) {
            slowly = slowly.next;
            quickly = quickly.next.next;

            if (slowly == quickly) {
                SimpleLinkedNode node = slowly.next;
                int cl = 1;

                while (node != slowly) {
                    node = node.next;
                    cl ++;
                }

                slowly = head;
                quickly = head;

                while (cl-- > 0) {
                    quickly = quickly.next;
                }

                while (quickly != slowly) {
                    quickly = quickly.next;
                    slowly = slowly.next;
                }

                // 相遇的那个点就是入口！
                return quickly;
            }
        }

        return null;
    }

    @Test
    public void test() {
        SimpleLinkedNode linked = SimpleLinkedNode.build("1 > 2 > 3 > 4 > 5 > 6 > 7 > 8");
        SimpleLinkedNode tail = linked;

        while (tail.next != null) {
            tail = tail.next;
        }

        for (int i = 1 ; i <= 8; i++) {
            SimpleLinkedNode entrance = linked;
            int entranceIdx = i;

            while (entranceIdx-- > 1) {
                entrance = entrance.next;
            }

            tail.next = entrance;

            Assert.assertTrue(entranceOfCycleApproach1(linked).value == i);
            Assert.assertTrue(entranceOfCycleApproach2(linked).value == i);
        }

        tail.next = null;
        Assert.assertTrue(entranceOfCycleApproach1(linked) == null);
        linked.next = linked;
        Assert.assertTrue(entranceOfCycleApproach1(linked) == linked);
        Assert.assertTrue(entranceOfCycleApproach2(linked) == linked);
    }
}