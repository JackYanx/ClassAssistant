package com.zeromirai.android.util;

/**
 * Created by initialize on 2018/4/14.
 */

public class Math {

    public static int findKthNum(int[] a, int p, int q, int k)
    {
        if (a == null || p < 0 || q < 0 || p > q) {
            //print("不满足条件,快排结束.\n");
            return -1;
        }
	    /*设置基数,默认为所选数组的第p个到第q个元素的第一个*/
        int baseNum = a[p];
        int i = p, j = q;
        while (i < j) {
            while (i < j && a[j] >= baseNum) j--;
            a[i] = a[j];
            while (i < j && a[i] <= baseNum) i++;
            a[j] = a[i];
        }
        a[i] = baseNum;
	    /*递归遍历基数左侧与基数右侧*/
        if (k < i) {
            return findKthNum(a, p, i - 1, k);
        }
        else if (k > i) {
            return findKthNum(a, i + 1, q, k);
        }
        else {
            return a[k];
        }

    }
}
