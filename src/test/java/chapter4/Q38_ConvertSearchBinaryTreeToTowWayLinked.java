package chapter4;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一棵二叉搜索树，将该二义搜索树转换成一个排序的双向
 * 链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。比如，
 * 输入图4．巧中左边的二叉搜索树，则输出转换之后的排序双向链表。二叉
 * 树节点的定义如下：
 *
 * @author flying
 */
public class Q38_ConvertSearchBinaryTreeToTowWayLinked {
    /**
     * 简单。对于一个简单的二叉树。左子树是头部，自己是中间，右子树是尾巴。递归重复这个过程就好了。
     * 假设一个节点的 left 是他的上一个，right 是她的下一个。
     */
    private BinaryTreeNode[] convertTreeToTowWayLinked(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        BinaryTreeNode[] left = convertTreeToTowWayLinked(node.left);
        BinaryTreeNode[] right = convertTreeToTowWayLinked(node.right);

        // 合并这三个节点。
        if (left != null) {
            node.left = left[1];
            left[1].right = node;
        }

        if (right != null) {
            node.right = right[0];
            right[0].left = node;
        }

        return new BinaryTreeNode[] {left == null ? node : left[0], right == null ? node : right[1]};
    }

    @Test
    public void test() {
        BinaryTreeNode treeNode = BinaryTreeNode.build(new Object[] {
            10,
            new Object[] {6, new Object[] {4}, new Object[] {8}},
            new Object[] {14, new Object[] {12}, new Object[] {16}}
        });

        Assert.assertTrue(treeNode.equals(treeNode));
        BinaryTreeNode[] headAndTail = convertTreeToTowWayLinked(treeNode);
        BinaryTreeNode head = headAndTail[0];
        BinaryTreeNode tail = headAndTail[1];
        Assert.assertTrue(head.value == 4);
        Assert.assertTrue(tail.value == 16);

        for (int i = 6; i <= 14; i+=2) {
            Assert.assertTrue(head.right.value == i);
            head = head.right;
        }

        for (int i = 14; i <= 6; i-=2) {
            Assert.assertTrue(tail.left.value == i);
            tail = tail.left;
        }
    }
}