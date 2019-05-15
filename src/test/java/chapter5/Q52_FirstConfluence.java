package chapter5;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 题目:输入两个链表,找出它们的第一个公共节点。链表节点定义如下
 * @author flying
 */
public class Q52_FirstConfluence {
    /**
     * 两个 stack ，避免使用 hash 算法式的比较了。
     */
    private int firstConfluence(SimpleLinkedNode linked1, SimpleLinkedNode linked2) {
        Stack<SimpleLinkedNode> stack1 = new Stack<>();

        while (linked1 != null) {
            stack1.add(linked1);
            linked1 = linked1.next;
        }

        Stack<SimpleLinkedNode> stack2 = new Stack<>();

        while (linked2 != null) {
            stack2.add(linked2);
            linked2 = linked2.next;
        }

        int lastSame = -1;

        while (!stack1.isEmpty() && !stack1.isEmpty() && stack1.peek().value== stack2.peek().value) {
            stack2.pop();
            lastSame = stack1.pop().value;
        }

        return lastSame;
    }

    @Test
    public void test() {
        Assert.assertTrue(
            firstConfluence(SimpleLinkedNode.build("1>3>5>7>9>10>11>12"), SimpleLinkedNode.build("2>4>6>8>10>11>12"))
                == 10);
        Assert.assertTrue(
            firstConfluence(SimpleLinkedNode.build("1>3>5>7>9>10>11>12"), SimpleLinkedNode.build("1>3>5>7>9>10>11>12"))
                == 1);
    }
}