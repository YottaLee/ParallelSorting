package QuickSortPackage;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 20:51 2018/12/9
 * @Modified By:
 */
public class QuickSort {

    public static void sort(int[] a, int l, int r) {

        if (l < r) {
            int i,j,x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while(i < j && a[j] > x)
                    j--;
                if(i < j)
                    a[i++] = a[j];
                while(i < j && a[i] < x)
                    i++;
                if(i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            sort(a, l, i-1);
            sort(a, i+1, r);
        }
    }
}
