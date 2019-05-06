package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的
 * 下一个节点？树中的节点除了有两个分别指向左、右子节点的指针，还有
 * 一个指向父节点的指针。
 *
 * @author flying
 */
public class Q8_FindNextNodeInTree {
    /**
     * 思路如下：
     * 1. 如果当前节点有右子树，那么下一个节点就是右子树最左侧的节点，一直找下去就行了。
     * 2. 如果 1 条件不成立，那么要判断当前节点是父节点的左子树还是右子树。如果当前节点是父节点的左子树，
     * 那么下一个节点肯定就是父节点。如果当前节点是父节点的右子树，那么把当前节点设置为当前节点的父节点，递归 2 过程。
     * @param node
     */
    private BinaryTreeNode findNextNodeOfInOrder(BinaryTreeNode node) {
        if (node.right != null) {
            node = node.right;

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        while (node.parent != null) {
            if (node.parent.left == node) {
                return node.parent;
            }

            node = node.parent;
        }

        return null;
    }

    /**
     * 使用多维数组表示二叉树，然后构建出 java 对象
     */
    private BinaryTreeNode buildTree(Object[] tree) {
        BinaryTreeNode node = new BinaryTreeNode();
        node.value = (char) tree[0];

        if (tree.length == 1) {
            return node;
        }

        if (tree[1] != null) {
            node.left = buildTree((Object[]) tree[1]);
            node.left.parent = node;
        }

        if (tree.length == 2) {
            return node;
        }

        if (tree[2] != null) {
            node.right = buildTree((Object[]) tree[2]);
            node.right.parent = node;
        }

        return node;
    }

    @Test
    public void test() {
        Object[] tree = new Object[] {
            'a',
            new Object[] {
                'b', new Object[] {'d'}, new Object[] {'e', new Object[] {'h'}, new Object[] {'i'}
                }
            },
            new Object[] {
                'e',
                new Object[] {'f'},
                new Object[] {'g'}
            }
        };

        BinaryTreeNode node = buildTree(tree);
        Assert.assertTrue(findNextNodeOfInOrder(node).right == null);
        Assert.assertTrue(findNextNodeOfInOrder(node.left).value == 'h');
        Assert.assertTrue(findNextNodeOfInOrder(node).value == 'f');
        Assert.assertTrue(findNextNodeOfInOrder(node.left.right.right).value == 'a');
        Assert.assertTrue(findNextNodeOfInOrder(node.right.right) == null);
    }

    /**
     * 二叉树节点, 双向
     */
    private static class BinaryTreeNode {
        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode parent;
    }
}