import DoubleMerge.MergeSortWithForkJoinSTM2;
import MergeSortPackage.MergeSort;
import MergeSortPackage.ParallelMergeSort;
import QuickSortPackage.ParallelQuickSort;
import QuickSortPackage.QuickSort;
import RankSortPackage.ParallelRankSort;
import RankSortPackage.RankSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Perform {

    public static final String DATA_PATH = "random.txt";

    public static final int MAX_DATA_NUM = 40000;


    public static int[] readTxt(){

        int[] data = new int[MAX_DATA_NUM];
        int[] raw_data = new int[MAX_DATA_NUM];
        File inputFile = new File(DATA_PATH);
        int index = 0;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(inputFile);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()){
                raw_data[index++] = scanner.nextInt();
            }
            fileInputStream.close();
            System.arraycopy(raw_data, 0, data, 0, raw_data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void outputToTxt(int id, int[] arr){
        try {
            File outputFile = new File("order" + String.valueOf(id) + ".txt");
            if (outputFile.exists())
                outputFile.delete();
            outputFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            PrintStream printStream = new PrintStream(fileOutputStream);
            for (int i = 0; i < arr.length; i++) {
                printStream.print(arr[i] + " ");
            }
            printStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        int[] test = readTxt();

        //QuickSortPackage
//        int[] test0 = test;
//        QuickSort quickSort = new QuickSort();
//        long start = System.currentTimeMillis();
//        quickSort.sort(test0,0, test.length-1);
//        long end = System.currentTimeMillis();
//        System.out.print(end-start+"ms");
//        outputToTxt(1, test0);



        //MergeSortPackage
//        int[] test1 = test;
//        MergeSort mergeSort = new MergeSort();
//        long start1 = System.currentTimeMillis();
//        test = mergeSort.sort(test1);
//        long end1 = System.currentTimeMillis();
//        System.out.print(end1-start1+"ms");
//        outputToTxt(2, test1);



        //RankSortPackage
//        int[] test2 = test;
//        RankSort rankSort = new RankSort();
//        long start2 = System.currentTimeMillis();
//        rankSort.sort(test2);
//        long end2 = System.currentTimeMillis();
//        System.out.print(end2-start2+"ms");
//        outputToTxt(3, test2);



        //System sort
//        int[] test3 = test;
//        long start3 = System.currentTimeMillis();
//        Arrays.sort(test3);
//        long end3 = System.currentTimeMillis();
//        System.out.print(end3-start3+"ms");




        //Parallel MergeSortPackage
//        int[] test4 = test;
//        ArrayList<Integer> list = new ArrayList<>();
//        for(int i: test4){
//            list.add(i);
//        }
//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        long start4 = System.currentTimeMillis();
//        List<Integer> result = forkJoinPool.invoke(new ParallelMergeSort<Integer>(list));
//        long end4 = System.currentTimeMillis();
//        System.out.print(end4-start4+"ms");
//        for(int i = 0;i<result.size(); i++){
//            test4[i] = result.get(i);
//        }
//        outputToTxt(4, test4);


        //Parallel QuickSortPackage
//        int[] test5 = test;
//        final ForkJoinPool forkJoinPoolQsp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
//        long start5 = System.currentTimeMillis();
//        forkJoinPoolQsp.invoke(new ParallelQuickSort(test5, 0, test5.length - 1));
//        long end5 = System.currentTimeMillis();
//        System.out.print(end5-start5+"ms");
//        outputToTxt(5, test5);



        //Parallel RankSortPackage
//        int[] test6 = test;
//        int[] re = new int[test6.length];
//        final ForkJoinPool forkJoinPoolEsp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
//        long start6 = System.currentTimeMillis();
//        forkJoinPoolEsp.invoke(new ParallelRankSort(test6, 0, test.length-1, -1, re));
//        long end6 = System.currentTimeMillis();
//        System.out.print(end6-start6+"ms");
//        outputToTxt(6, re);


        //System Parallel sort
//        int[] test7 = test;
//        long start7 = System.currentTimeMillis();
//        Arrays.parallelSort(test7);
//        long end7 = System.currentTimeMillis();
//        System.out.print(end7-start7+"ms");


        //Parallel MergeSortPackage Double Merge
//        int[] test8 = test;
//        int numberOfLeafThreads = 8;
//        long[] w = new long[test8.length];
//        for(int i =0; i< test8.length; i++){
//            w[i] = test8[i];
//        }
//        long startTime = System.currentTimeMillis();
//        MergeSortWithForkJoinSTM2.parallelMergeSort(w, numberOfLeafThreads);
//        long duration = System.currentTimeMillis() - startTime;
//        System.out.println("sorting took " + duration + " milliseconds.");


    }
}
