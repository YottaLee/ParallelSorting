package QuickSortPackage;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 13:57 2018/12/11
 * @Modified By:
 */
public class ParallelQuickSort extends RecursiveAction{

    private int[] elements;

    private int start;

    private int end;

    private static int processorNum = Runtime.getRuntime().availableProcessors();

    private static int count = 0;

    public ParallelQuickSort(int[] elements, int start, int end){
        this.elements = elements;
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        quickSort(elements, start, end);
    }




    private void quickSort(int[] elements, int start, int end){
        List<ParallelQuickSort> future = new Vector<>();
        int mid = partition(elements, start, end);

        if(mid - start > 1){
            if(count < processorNum){
                count++;
                ParallelQuickSort leftTask = new ParallelQuickSort(elements, start, mid-1);
                future.add(leftTask);
            }
            else{
                sort(elements, start, mid - 1);
            }
        }

        if(end - mid > 1){
            if(count < processorNum){
                count++;
                ParallelQuickSort rightTask = new ParallelQuickSort(elements, mid + 1, end);
                future.add(rightTask);
            }
            else{
                sort(elements, mid + 1, end);
            }
        }

        if(!future.isEmpty()){
            invokeAll(future);
        }
    }


    private int partition(int[] elements, int start, int end){
        if(start >= end){
            return -1;
        }

        int pivot = start;
        int key = elements[pivot];
        int l = start;
        int r = end;
        while (l < r){
            while (l < r && elements[r] >= key)
                r--;
            if(l < r) {
                elements[l] = elements[r];
                l++;
                while(elements[l] < key && l < r)
                    l++;
                if(l < r) {
                    elements[r] = elements[l];
                    r--;
                }
            }
        }
        elements[l] = key;
        return l;


    }


    private void sort(int[] arr, int left, int right){
        if (left < right){
            int p = partition(arr, left, right);
            sort(arr, left, p - 1);
            sort(arr, p + 1, right);
        }
    }

}
