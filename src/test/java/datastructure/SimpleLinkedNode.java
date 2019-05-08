package datastructure;

/**
 * 单向链表
 *
 * @author flying
 */
public class SimpleLinkedNode {
    /**memebers**/
    public SimpleLinkedNode next;
    public int value;

    /**
     * 构建链表, 格式
     * 1 > 2 > 3 > 4 > 5 > 6
     */
    public static SimpleLinkedNode build(String linked) {
        if (linked == null) {
            return null;
        }

        String[] nodes = linked.split(">");

        if (nodes.length == 0) {
            return null;
        }

        SimpleLinkedNode head = new SimpleLinkedNode();
        head.value = Integer.valueOf(nodes[0].trim());
        SimpleLinkedNode node = head;

        for (int i = 1; i < nodes.length; i ++) {
            node.next = new SimpleLinkedNode();
            node.next.value = Integer.valueOf(nodes[i].trim());
            node = node.next;
        }

        return head;
    }

    /**
     * 序列化链表， 输出格式：1 > 2 > 3 > 4 > 5 > 6
     */
    public String serialize() {
        SimpleLinkedNode node = this;
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.value + ((node = node.next) != null ? " > " : ""));
        }

        return sb.toString();
    }
}