package chapter6;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目一:二又树的深度输入一棵二叉树的根节点,求该树的深度。
 * 从根节点到叶节点依次经过的节点(含根、叶节点)形成树的一条路径,最长路径的长度为树的深度。
 *
 * @author flying
 */
public class Q55_1_DepthOfBinaryTree {
    // 深度遍历可以解决这个问题，但是书上给了另外一种方式，可谓之妙！
    private int depthOfBinaryTree(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(depthOfBinaryTree(node.left), depthOfBinaryTree(node.right));
    }

    @Test
    public void test() {
        Assert.assertTrue(depthOfBinaryTree(BinaryTreeNode.deserialize("[1[2[4][5[7]]][3[][6]]]")) == 4);
    }
}