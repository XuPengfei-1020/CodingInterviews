package chapter4;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：请实现一个函数，用来判断一棵二叉树是不是对称的。如果一
 * 棵二叉树和它的镜像一样，那么它是对称的。例如，在如图4．3所示的3棵
 * 二叉树中，第一棵二叉树是对称的，而另外两棵不是。
 *
 * @author flying
 */
public class Q28_IfABinaryTreeIsMirroTree {
    /**
     * 递归就是简洁！
     */
    private boolean isMirro(BinaryTreeNode root) {
        mirror(root.left);
        return diff(root.left, root.right);
    }

    /**把左子树翻转了*/
    private void mirror(BinaryTreeNode root) {
        if (root == null) return;
        BinaryTreeNode left = root.left;
        mirror(root.left = root.right);
        mirror(root.right = left);
    }

    /**判断两个 tree 是否相同**/
    private boolean diff(BinaryTreeNode tree1, BinaryTreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == null && tree2 == null;
        }

        if (tree1.value != tree2.value) {
            return false;
        }

        return diff(tree1.left, tree2.left) && diff(tree1.right, tree2.right);
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

        Assert.assertFalse(isMirro(BinaryTreeNode.build(treeDef)));

        treeDef = new Object[] {
            1,
            new Object[] {
                2,
                new Object[] {3},
                new Object[] {4}
            },
            new Object[] {
                1,
                new Object[] {4},
                new Object[] {3}
            }
        };

        Assert.assertFalse(isMirro(BinaryTreeNode.build(treeDef)));
        treeDef = new Object[] {1};
        Assert.assertTrue(isMirro(BinaryTreeNode.build(treeDef)));
    }
}