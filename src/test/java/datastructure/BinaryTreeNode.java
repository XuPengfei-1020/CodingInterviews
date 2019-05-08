package datastructure;

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
}