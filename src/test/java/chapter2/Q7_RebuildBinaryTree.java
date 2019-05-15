package chapter2;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如，输入
 * 前序遍历序列{1, 2, 4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，则
 * 重建如图2．6所示的二叉树并输出它的头节点。二叉树节点的定义如下：
 *
 * @author flying
 */
public class Q7_RebuildBinaryTree {
    /**
     * 重构二叉树， 使用递归的方式重构，步骤如下：
     * 1. preOrder 的第一个元素就是 root 节点，先构建出 root 节点。
     * 2. 可以推断在 inOrder 中，root 节点前面的元素肯定都是 root 的左子树中的节点，
     *      root 节点后面的元素肯定都是 root 的右子树中的节点。
     * 3. 那么我们就知道 root 的左子树节点的个数，记作numOfLeftChildren，和 root 右子树节点的个数，记作 numOfRightChildren。
     * 4.如果 root 存在左子树(numOfLeftChildren > 0)，那么左子树的最高节点一定是在 preOrder 中，紧挨着 root 的后面的那个元素的值。
     *      如果 root 存在右子树(numOfRightChildren > 0)，那么右子树的最高节点一定是在 preOrder 中，从 root 往后数，
     *      跳过所有左子树元素后（numOfLeftChildren 个），那个元素的值。
     * 5. 找到了左子树和右子树分别的 root 节点的值和他们的 inOrder 内容了，可以按照 1- 4 步骤进行递归了，分别构建出 left 和 right。
     *
     * @param preOrder 前序遍历结果
     * @param i preOrder 的第一个元素
     * @param inOrder 中序遍历结果
     * @param start, end 指定 inOrder 的范围
     * @return 重构后的二叉树
     */
    private BinaryTreeNode rebuildBinaryTree(int[] preOrder, int i, int[] inOrder, int start, int end) {
        if (preOrder == null || inOrder == null || (preOrder.length != inOrder.length) || preOrder.length == 0) {
            throw new IllegalArgumentException("Param is invalid");
        }

        if (start > end) {
            return null;
        }

        int rootValue = preOrder[i];
        int indexOfInOrder = find(inOrder, start, end, rootValue);

        if (indexOfInOrder == -1) {
            throw new RuntimeException("node：" + rootValue + " 在中序遍历中不存在，参数不正确");
        }

        BinaryTreeNode root = new BinaryTreeNode();
        root.value = rootValue;
        root.left = rebuildBinaryTree(preOrder, i + 1, inOrder, start, indexOfInOrder - 1);
        root.right = rebuildBinaryTree(preOrder, i + (indexOfInOrder - start + 1), inOrder, indexOfInOrder + 1, end);
        return root;
    }

    /**
     * 给定一个数字，寻找这个数字在 inorder 中的位置，位置左边的都是它的 left 以及 left 的子孙节点，右边的都是 right
     * 以及 right 的子孙节点。
     */
    private int find(int[] array, int start, int end, int target) {
        for (int i = start; i <= end && i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void test() {
        int[] preOrder = new int[] {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = new int[] {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode node = rebuildBinaryTree(preOrder, 0, inOrder, 0, inOrder.length - 1);
        Assert.assertTrue(Arrays.equals(Arrays.stream(node.preOrder()).mapToInt(i->i).toArray() , preOrder));
        Assert.assertTrue(Arrays.equals(Arrays.stream(node.inOrder()).mapToInt(i->i).toArray() , inOrder));
    }
}
