package chapter6;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目:给定一个数组A[0,1,…,n-1],
 * 请构建一个数组B[,…,n-1],其中B中的元素B[i] = A[0] * A[l] * … * A[i-1] * A[i+1] * ... * A[n-1]。不能使用除法。
 *
 * @author flying
 */
public class Q66_BuildMulArray {
    /**
     * 如果能使用除法，那么将非常简单。mul(1 .. n) /  A[i] 就行了。
     * 但是不能用除法，那么直接算也是可以的，就是存在非常大量的重复计算。
     * 我们找个方式把重复计算的给干掉就好了啊。
     * 设 mul(n -m) 表示从 n * n + 1 * ... * m -1 * m
     * B[i] = mul(A[1] .. A[i-1]) * mul(A[i+1] .. A[n - 1])
     * 用两个数组，C 和 D
     * 其中 C[i] = mul(A[1] ... A[i - 1]); D[i] = mul(A[i + 1] .. A[n - 1])
     * C[i] = i == 0 ? 1 : C[i - 1] * A[i - 1]，
     * D[i] = i == (a.length - 1) ? 1 : C[i + 1] * A[i + 1]
     * B[i] = C[i] * D[i];
     */
    private int[] buildMulArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("param is invalid");
        }

        int[] c = new int[arr.length];
        int[] d = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            c[i] = i == 0 ? 1 : c[i - 1] * arr[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            d[i] = i == (arr.length - 1) ? 1 : d[i + 1] * arr[i + 1];
        }

        int b[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            b[i] = c[i] * d[i];
        }

        return b;
    }

    /**
     * 用笨方法做一个，用来测试。
     */
    private int[] benchMark(int[] arr) {
        int[] b = new int[arr.length];

        for (int i = 0; i < b.length; i++) {
            b[i] = 1;

            for (int j = 0; j < arr.length; j++) {
                b[i] = b[i] * (i == j ? 1 : arr[j]);
            }
        }

        return b;
    }

    @Test
    public void test() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertTrue(Arrays.equals(buildMulArray(arr), benchMark(arr)));
        arr = new int[] {1, 2, 3, 4, 5, 6, 0, 8};
        Assert.assertTrue(Arrays.equals(buildMulArray(arr), benchMark(arr)));
        arr = new int[] {1, 0, 3, 4, 5, 6, 0, 8};
        Assert.assertTrue(Arrays.equals(buildMulArray(arr), benchMark(arr)));
        arr = new int[] {-1, 2, -3, 4, -5, 6, 0, -8};
        Assert.assertTrue(Arrays.equals(buildMulArray(arr), benchMark(arr)));
    }
}