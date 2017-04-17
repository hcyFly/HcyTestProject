package com.cn.burus.hcytestproject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 * java测试
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAdd() throws Exception {
        System.out.print("start---" + System.currentTimeMillis());
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum = sum + i;
            System.out.print("\n" + sum);
        }
        System.out.print("\nend---" + System.currentTimeMillis());
    }

    @Test
    public void testStringSipt() throws Exception {

        testStrIndexof("adsfsdfsgl");
        testStrIndexof("sdfsdfsadfdf");
        testStrIndexof("ssdfsdfjsfa");
        testStrIndexof("sdfjsdfkjs");

    }

    private void testStrIndexof(String str) {
        int aa = str.indexOf("a");
        System.out.print("aa---:" + aa + "\n");
        if (aa >= 0) {
            System.out.print("str--中包含有a字母" + "\n");
        } else {
            System.out.print("str--中没有包含有a字母" + "\n");
        }
    }
}