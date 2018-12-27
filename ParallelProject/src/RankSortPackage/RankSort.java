package RankSortPackage;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 20:40 2018/12/9
 * @Modified By:
 */
public class RankSort {

    public static void sort(int[] array1){
        int[] array2 = new int[array1.length];

        long t1 = System.currentTimeMillis();
        rank(array1,array2);
        long t2 = System.currentTimeMillis();
        rearrange(array1,array2);
        long t3 = System.currentTimeMillis();
        System.out.println("Time: "+(t2-t1)+" "+(t3-t1));
    }

    public static void rank(int[] array1, int[] array2){
        if(array2.length != array1.length)
            throw new IllegalArgumentException("数组长度异常");
        for(int i = 0; i< array1.length;i++){
            array2[i] = 0;
        }
        for (int i = 1; i < array1.length; i++)
            for (int j = 0; j < i; j++)
                if (array1[j] <= array1[i])
                    array2[i]++;
                else
                    array2[j]++;
    }

    public static void rearrange(int[] array1, int[] r) {
        int[] u = new int[array1.length];
        for (int i = 0; i < array1.length; i++)
            u[r[i]] = array1[i];
        for (int i = 0; i < array1.length; i++)
            array1[i] = u[i];
    }


}
