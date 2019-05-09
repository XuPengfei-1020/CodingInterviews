package chapter4;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：请实现函数ComplexListNode*Clone(ComplexListNode*pHead)，
 * 复制一个复杂链表。在复杂链表中，每个节点除了有一个m-pNext指针指
 * 向下一个节点，还有一个m-DSibling指针指向链表中的任意节点或者
 * nullptr。节点的C++定义如下：
 */
public class Q35_CopyComplexLinked {
    static class ComplexLinked {
        int value;
        ComplexLinked next;
        ComplexLinked sibling;

        boolean equals(ComplexLinked linked) {
            if (linked == null) {
                return false;
            }

            if (linked.value != this.value) {
                return false;
            }

            if (linked.sibling != this.sibling && (this.sibling == null || linked.sibling == null ||
                linked.sibling.value != this.sibling.value))
            {
                return false;
            }

            if (this.next == null) {
                return linked.next == null;
            }

            return this.next.equals(linked.next);
        }

        /**
         * 构建链表, 格式
         * 1 > 2 > 3 > 4 > 5 > 6
         */
        static ComplexLinked build(int [] nodes) {
            if (nodes == null || nodes.length == 0) {
                return null;
            }
            ComplexLinked head = new ComplexLinked();
            head.value = nodes[0];
            ComplexLinked node = head;

            for (int i = 1; i < nodes.length; i ++) {
                node.next = new ComplexLinked();
                node.next.value = nodes[i];
                node = node.next;
            }

            return head;
        }
    }

    /**
     * 第一种方式，需要额外的空间，存放旧节点到新节点的映射，空间和时间都是 o(n), 不在实现了。
     */
    private ComplexLinked copyApproach1(ComplexLinked linked) {
        return null;
    }

    /**
     * 第二种方式，把新旧节点混编在一起，时间是 o(n)
     */
    private ComplexLinked copyApproach2(ComplexLinked linked) {
        if (linked == null) {
            return null;
        }

        ComplexLinked current = linked;

        // 变形
        while (current != null) {
            ComplexLinked ghost = new ComplexLinked();
            ghost.value = current.value;
            ComplexLinked next = current.next;
            current.next = ghost;
            ghost.next = next;
            current = next;
        }

        current = linked;

        // 复制
        while (current != null) {
            ComplexLinked next = current.next;
            ComplexLinked sibling = current.sibling;

            if (sibling != null) {
                next.sibling = sibling.next;
            }

            current = next.next;
        }

        // 拆分
        ComplexLinked result = linked.next;
        current = linked;

        while (current != null) {
            ComplexLinked mid = current.next;
            current.next = mid.next;
            mid.next = mid.next == null ? null : mid.next.next;
            current = current.next;
        }

        return result;
    }

    @Test
    public void test() {
        ComplexLinked testLinked = ComplexLinked.build(new int[] {1, 2 ,3 ,4 ,5 ,6, 7, 8, 9});
        ComplexLinked next = testLinked;

        while (next.next != null) {
            next.sibling = next.next.next;
            next = next.next;
        }

        Assert.assertTrue(testLinked.equals(testLinked));
        Assert.assertTrue(testLinked.equals(copyApproach2(testLinked)));
        Assert.assertTrue(testLinked.equals(copyApproach2(testLinked)));
        testLinked.sibling = testLinked.next = null;
        Assert.assertTrue(testLinked.equals(copyApproach2(testLinked)));
        Assert.assertTrue(testLinked.equals(copyApproach2(testLinked)));
    }
}