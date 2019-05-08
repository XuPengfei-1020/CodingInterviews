package chapter3;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 题目：输入两棵二叉树A和B，判断B是不是A的子结构。二叉树节
 * 点的定义如下：
 *
 *  其实我还有个算法，如果这些 tree 中没有重复节点的话，可以把他们按照先序和中序遍历输出出来。
 *  然后利用 KMP 算法比较两个输出结果，如果在两种遍历顺序中，后者结果总是是前者结果的 subset 的话，
 *  那么就可以判定后者是前者的 sub-tree 这个时间效率高不少。有一些空间开销。
 *
 * @author
 */
public class Q26_RecognizeSubTree {
    /**
     * 遍历 tree1，对 tree1 中每一个 subtree
     * 找到 tree1 中和 tree2 节点一样的节点，然后比较他们以及他们的子节点。
     * @return
     */
    private boolean isSubTree(BinaryTreeNode tree1, BinaryTreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.add(tree1);

        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();

            if(diff(node, tree2)) {
                return true;
            }

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return false;
    }

    /**
     * 判断两个树是不是一模一样
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

    @Test
    public void test() {
        Object[] tree = new Object[] {
            1,
            new Object[] {
                2,
                new Object[] {3},
                new Object[] {4}
            },
            new Object[] {
                5,
                new Object[] {6},
                new Object[] {7}
            }
        };

        BinaryTreeNode root1 = BinaryTreeNode.build(tree);
        Assert.assertTrue(isSubTree(root1, root1));

        Object[] tree2 = new Object[] {
            1,
            new Object[] {
                2,
                new Object[] {5},
                new Object[] {4}
            },
            new Object[] {
                5,
                new Object[] {6},
                new Object[] {7}
            }
        };

        BinaryTreeNode root2 = BinaryTreeNode.build(tree2);
        Assert.assertFalse(isSubTree(root1, root2));
        Assert.assertFalse(isSubTree(root2, root1));
        root1.left = root2;
        Assert.assertTrue(isSubTree(root1, root2));
    }
}