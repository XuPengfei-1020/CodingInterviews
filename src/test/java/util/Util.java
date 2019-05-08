package util;

import org.junit.Assert;

public class Util {
    public static void assertArrayEquals(Integer[] values, int[] expect) {
        if ((values == null && expect != null) || (values != null && expect == null)) {
            Assert.assertTrue(false);
        }

        if (values.length != expect.length) {
            Assert.assertTrue(false);
        }

        for (int i = 0; i < values.length; i++) {
            Assert.assertTrue(values[i] == expect[i]);
        }
    }
}