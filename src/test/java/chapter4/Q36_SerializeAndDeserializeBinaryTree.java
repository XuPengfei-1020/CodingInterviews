package chapter4;

import datastructure.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 请实现两个函数，分别实现 tree 的序列化和反序列化
 *
 * @author flying
 */
public class Q36_SerializeAndDeserializeBinaryTree {
    //如果树中没有重复的内容，那么使用 approach1 的方式是没有问题的。
    /**
     * 因为不是搜索二叉树，只有一种遍历顺序的序列化结果，是无法反序列化的。因此使用中序和后续遍历的方式，
     * 将两种结果一块儿返回作为最终的序列结果。
     * 遍历的逻辑旧不再实现了，在 {@link BinaryTreeNode} 中有实现。
     * @param root
     * @return
     */
    private int[][] serializeApproach1(BinaryTreeNode root) {
        return null;
    }

    /**
     * 重构的逻辑也不再实现了，在 {@link chapter2.Q7_RebuildBinaryTree} 中实现。
     * @param compress
     * @return
     */
    private BinaryTreeNode deSerializeApproach2(int[][] compress) {
        return null;
    }

    /** 如果树中的数据有重复，那么要使用序列化的内容来体现出它的层次来,
     * 然后用 {@link BinaryTreeNode#build(Object[])} 还原, 具体代码写到 {@link BinaryTreeNode#serialize()} () 中吧}**/

    @Test
    public void test() {
        String compress = "[1[2[4][5]][3[6][7[][123]]]]";
        Assert.assertTrue(BinaryTreeNode.deserialize(compress).serialize().equals(compress));
        compress = "[1]";
        Assert.assertTrue(BinaryTreeNode.deserialize(compress).serialize().equals(compress));
    }
}