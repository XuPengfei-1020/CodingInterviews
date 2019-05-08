package chapter4;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;
import util.Util;

/**
 * 题目一：不分行从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺
 * 序打印。例如，输入图4．6中的二叉树，则依次打印出8，6，10，5，7，9，116
 * 叉树节点的定义如下：
 *
 * @author flying
 */
public class Q32_DFSBinaryTree {
    /**
     * 代码写到 {@link datastructure.BinaryTreeNode}
     */

    @Test
    public void test() {
        BinaryTreeNode head = BinaryTreeNode.build(new Object[] {
            8,
            new Object[] {6, new Object[] {5}, new Object[] {7}},
            new Object[] {10, new Object[] {9}, new Object[] {11}}
        });

        Util.assertArrayEquals(head.depthTraversal(), new int[] {8, 6, 10, 5, 7, 9, 11});
    }
}