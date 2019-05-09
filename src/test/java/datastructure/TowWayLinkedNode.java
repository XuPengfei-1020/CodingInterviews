package datastructure;

/**
 * 双向链表
 */
public class TowWayLinkedNode {
    public int value;
    public TowWayLinkedNode next;
    public TowWayLinkedNode prev;

    boolean equals(TowWayLinkedNode linked) {
        if (linked == null) {
            return false;
        }

        if (linked.value != this.value) {
            return false;
        }

        if (this.next == null) {
            return linked.next == null;
        }

        if (this.prev != null && this.prev.next != this) {
            throw new IllegalArgumentException("prev.next != this");
        }

        if (linked.prev != null && linked.prev.next != linked) {
            throw new IllegalArgumentException("prev.next != this");
        }

        return this.next.equals(linked.next);
    }

    /**
     * 构建链表, 格式
     * 1 > 2 > 3 > 4 > 5 > 6
     */
    static TowWayLinkedNode build(int [] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        TowWayLinkedNode head = new TowWayLinkedNode();
        head.value = nodes[0];
        TowWayLinkedNode node = head;

        for (int i = 1; i < nodes.length; i ++) {
            node.next = new TowWayLinkedNode();
            node.next.value = nodes[i];
            node.next.prev = node;
            node = node.next;
        }

        return head;
    }
}