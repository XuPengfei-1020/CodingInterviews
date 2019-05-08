package chapter4;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 题目：请完成一个函数，输入一棵二叉树，该函数输出它的镜像。
 */
public class Q27_MirrorBinaryTree {
    private BinaryTreeNode mirro(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        BinaryTreeNode mirro = new BinaryTreeNode();
        mirro.value = node.value;
        mirro.left = mirro(node.right);
        mirro.right = mirro(node.left);
        return mirro;
    }

    @Test
    public void test() {
        Object[] treeDef = new Object[] {
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

        BinaryTreeNode tree = BinaryTreeNode.build(treeDef);
        BinaryTreeNode mirroTree = mirro(tree);
        // 反转一下
        ArrayList<Integer> traverseResultList = new ArrayList<>();
        Collections.addAll(traverseResultList, mirroTree.inOrder());
        Collections.reverse(traverseResultList);
        Integer[] result = traverseResultList.toArray(new Integer[0]);
        Assert.assertTrue(Arrays.equals(result, tree.inOrder()));

        Object[] tree2 = new Object[] {
            1
        };

        BinaryTreeNode root2 = BinaryTreeNode.build(tree2);
        Assert.assertTrue(Arrays.equals(mirro(root2).inOrder(), root2.inOrder()));
    }
}