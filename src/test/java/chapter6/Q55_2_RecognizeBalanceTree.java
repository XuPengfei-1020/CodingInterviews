package chapter6;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目二:平衡二又树。输入一棵二又树的根节点,判断该树是不是平衡二叉树。
 * 如果某二又树中任意节点的左、右子树的深度相差不超过1,那么它就是一棵平衡二又树。
 * 例如,图6.2中的二又树就是一棵平衡二叉树。
 *
 * @author flying
 */
public class Q55_2_RecognizeBalanceTree {
    /**
     * 如果一颗树是平衡二叉树，那么它的左子树的深度和右子树的深度，相差不超过一。
     * 和 {@link Q55_1_DepthOfBinaryTree} 异曲同工
     * 如果一个树的左子树是平衡的，并且右子树是平衡的，并且它俩的深度相差不超过一，那么它也是平衡的。
     */
    private int recognizeBalanceTree(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        int left = recognizeBalanceTree(treeNode.left);
        int right = recognizeBalanceTree(treeNode.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }

    @Test
    public void test() {
        Assert.assertTrue(recognizeBalanceTree(BinaryTreeNode.deserialize("[1[2[4][5[7]]][3[][6]]]")) != -1);
        Assert.assertTrue(recognizeBalanceTree(BinaryTreeNode.deserialize("[1[2[4][5[7]]][3]]")) == -1);

    }
}