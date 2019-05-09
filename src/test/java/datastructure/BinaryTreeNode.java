package datastructure;

import javax.management.RuntimeMBeanException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树树结构
 * @author flying
 */
public class BinaryTreeNode {
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public int value;

    /**
     * 中序遍历
     */
    public Integer[] inOrder() {
        BinaryTreeNode node = this;
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.add(node);
        boolean addLeft = true;

        while (!stack.isEmpty()) {
            node = stack.peek();

            if (addLeft && node.left != null) {
                stack.add(node.left);
                continue;
            }

            result.add(stack.pop().value);

            if (addLeft = (node.right != null)) {
                stack.add(node.right);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    /**
     * 先序遍历
     */
    public Integer[] preOrder() {
        BinaryTreeNode node = this;
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.add(node);
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            node = stack.pop();
            result.add(node.value);

            if (node.right !=  null) {
                stack.add(node.right);
            }

            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    public Integer[] depthTraversal() {
        LinkedList<BinaryTreeNode> newLinked = new LinkedList<>();
        LinkedList<BinaryTreeNode> oldLinked = new LinkedList<>();
        oldLinked.add(this);
        ArrayList<Integer> result = new ArrayList<>();

        while (!oldLinked.isEmpty()) {
            newLinked.clear();

            while (!oldLinked.isEmpty()) {
                BinaryTreeNode node = oldLinked.removeFirst();
                result.add(node.value);

                if (node.left != null) {
                    newLinked.add(node.left);
                }

                if (node.right != null) {
                    newLinked.add(node.right);
                }
            }

            LinkedList temp = newLinked;
            newLinked = oldLinked;
            oldLinked = temp;
        }

        return result.toArray(new Integer[result.size()]);
    }

    /**
     * 从一个多维数组中构建 tree
     */
    public static BinaryTreeNode build(Object[] tree) {
        BinaryTreeNode node = new BinaryTreeNode();
        node.value = (int) tree[0];

        if (tree.length > 1 && tree[1] != null) {
            node.left = build((Object[]) tree[1]);
        }

        if (tree.length > 2 && tree[2] != null) {
            node.right = build((Object[]) tree[2]);
        }

        return node;
    }

    /**
     * 序列化, 先序遍历
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(value);
        sb.append(left == null ? right == null ? "" : "[]" : left.serialize());
        sb.append(right == null ? "" : right.serialize());
        sb.append("]");
        return sb.toString();
    }

    /**
     * 反序列化
     */
    public static BinaryTreeNode deserialize(String str) {
        return deserialize(str, 0, str.length() - 1);
    }

    /**
     * 判断两个树是不是一模一样
     */
    public boolean equals(BinaryTreeNode tree) {
        return diff(this, tree);
    }

    /**
     * 做比较
     */
    private boolean diff(BinaryTreeNode tree1, BinaryTreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }

        if (tree1.value == tree2.value) {
            return diff(tree1.left, tree2.left) && diff(tree1.right, tree2.right);
        }

        return false;
    }

    /**
     * 反序列化，私有
     */
    private static BinaryTreeNode deserialize(String str, int start, int end) {
        if (str == null || str.length() == 0 || start == end - 1) {
            return null;
        }

        int value = 0;
        int i = start + 1;

        while (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            value = value * 10 + (str.charAt(i++) - 48);
        }

        BinaryTreeNode node = new BinaryTreeNode();
        node.value = value;

        if (i == end) {
            // 没有 left 和 right 了
            return node;
        }

        if (str.charAt(i) != '[') {
            throw new RuntimeException("compress 序列化内容不正确，在 index :" + i + " 位置期待一个 '['");
        }

        // 寻找与当前 i 位置的 '[' 对应的下一个 ‘]’
        int count = 0;
        int leftEnd = i;

        while (++leftEnd < end) {
            if (str.charAt(leftEnd) == '[') {
                count++;
                continue;
            }

            if (str.charAt(leftEnd) == ']' && count-- == 0) {
                break;
            }
        }

        if (leftEnd == end) {
            throw new RuntimeException("compress 序列化内容不正确， 内容过早的结束");
        }

        node.left = deserialize(str, i, leftEnd);
        node.right = deserialize(str, leftEnd + 1, end - 1);
        return node;
    }
}