package chapter3;

/**
 * 题目一：在o(l)时间内删除链表节点。
 * 给定单向链表的头指针和一个节点指针，定义一个函数在o(1)时间内删除该节点。
 * @author flying
 */
public class Q18_DelLinkedNodeInO1Spend {
    /**
     * 这道题目考的是指针，如果是 c 语言，有指针，那么直接把指向 delNode 的指针指向 delNode.next 即可。
     * 在 java 中，我们取个巧吧，把 delNode 中的 value 变成 delNode.next.value，然后 delNode.next = delNode.next.next
     * 然后注意边界问题就好了。
     * */
    private void delNode(Node head, Node delNode) {
        if (head == null || delNode == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        if (delNode.next != null) {
            // 这种情况需要把 delNode 的 parent 节点的 next 置空，java就没办法了。
            throw new IllegalArgumentException("恕 java 无能为力");
        }

        delNode.value = delNode.next.value;

        if (delNode.next.next != null) {
            delNode.next = delNode.next.next;
        }
    }

    private class Node {
        Node next;
        int value;
    }
}
