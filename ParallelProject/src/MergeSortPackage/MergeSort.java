package MergeSortPackage;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 20:50 2018/12/9
 * @Modified By:
 */
public class MergeSort {

    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        }
        return c;
    }

    public static int[] sort(int[] input) {
        int N = input.length;
        if (N <= 1) return input;
        int[] a = new int[N/2];
        int[] b = new int[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return merge(sort(a), sort(b));
    }

//    public static void sort(int[] array, int left, int right){
//        int[] array2 = new int[array.length];
//        if(left < right){
//            int offset = (left +right)/2;
//            sort(array, left, offset);
//            sort(array, offset+1, right);
//            merge(array,array2, left, offset, right);
//            copy(array,array2,left,right);
//        }
//
//    }
//
//    private static void merge(int[] c, int[] d, int l, int m,int r) {
//        int i = l;
//        int j = m + 1;
//        int k = l;
//        while ((i <= m) && (j <= r))
//            if ((c[i]-c[j]) <= 0)
//                d[k++] = c[i++];
//            else
//                d[k++] = c[j++];
//        if (i > m)
//            for (int q = j; q <= r; q++)
//                d[k++] = c[q];
//        else
//            for (int q = i; q <= m; q++)
//                d[k++] = c[q];
//    }
//
//
//    private static void copy(int[] a, int[] b, int left, int right) {
//        int i = left;
//        int j = left;
//        while (i <= right)
//            a[i++] = b[j++];
//    }

}
