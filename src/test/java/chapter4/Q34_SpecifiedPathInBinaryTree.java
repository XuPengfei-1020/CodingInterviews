package chapter4;

import datastructure.BinaryTreeNode;
import javafx.beans.binding.ObjectExpression;
import org.junit.Assert;
import org.junit.Test;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 题目：输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输
 * 入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形
 * 成一条路径。二叉树节点的定义如下：
 */
public class Q34_SpecifiedPathInBinaryTree {
    /**
     * 简单，使用后遍历，栈中的元素就是路径。
     */
    public BinaryTreeNode[][] specifiedPathInBinaryTree(BinaryTreeNode root, int expectPath) {
        if (root == null) {
            return null;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.add(root);
        BinaryTreeNode last = null;
        ArrayList<BinaryTreeNode[]> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            BinaryTreeNode cnode = stack.peek();

            if (last == null || last != cnode.right) {
                if (last != cnode.left && cnode.left != null) {
                    stack.add(cnode.left);
                    continue;
                }

                if (cnode.right != null) {
                    stack.add(cnode.right);
                    continue;
                }
            }

            if (cnode.left == null && cnode.right == null) {
                BinaryTreeNode[] trace = stack.toArray(new BinaryTreeNode[0]);
                int sum = 0;

                for (BinaryTreeNode node : trace) {
                    sum += node.value;
                }

                if (sum == expectPath) {
                    result.add(trace);
                }
            }

            last = stack.pop();
        }

        return result.toArray(new BinaryTreeNode[0][]);
    }

    @Test
    public void test() {
        BinaryTreeNode tree = BinaryTreeNode.build(new Object[] {
            10,
            new Object[] {
                5,new Object[] {4}, new Object[] {7}
            },
            new Object[] {12},
        });

        BinaryTreeNode[][] result = specifiedPathInBinaryTree(tree, 22);
        Assert.assertTrue(Arrays.equals(Arrays.stream(result[0]).mapToInt(node -> node.value).toArray(), new int[] {10, 5, 7}));
        Assert.assertTrue(Arrays.equals(Arrays.stream(result[1]).mapToInt(node -> node.value).toArray(), new int[] {10, 12}));
    }
}