package MergeSortPackage;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 13:57 2018/12/11
 * @Modified By:
 */

public class ParallelMergeSort<N extends Comparable<N>> extends RecursiveTask<List<N>>{

    private List<N> elements;

    public ParallelMergeSort(List<N> elements){
        this.elements = elements;
    }

    @Override
    protected List<N> compute() {
        if(this.elements.size() <= 1){
            return this.elements;
        }
        else{
            final int pivot = this.elements.size() / 2;
            ParallelMergeSort<N> leftTask = new ParallelMergeSort<N>(this.elements.subList(0, pivot));
            ParallelMergeSort<N> rightTask = new ParallelMergeSort<N>(this.elements.subList(pivot, this.elements.size()));

            leftTask.fork();
            rightTask.fork();

            List<N> left = leftTask.join();
            List<N> right = rightTask.join();

            merge(left, right);
            return this.elements;
        }

    }

    private void merge(List<N> left, List<N> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        while(leftIndex < left.size() ) {
            if(rightIndex == 0) {
                if( left.get(leftIndex).compareTo(right.get(rightIndex)) > 0 ) {
                    swap(left, leftIndex++, right, rightIndex++);
                } else {
                    leftIndex++;
                }
            } else {
                if(rightIndex >= right.size()) {
                    if(right.get(0).compareTo(left.get(left.size() - 1)) < 0 )
                        merge(left, right);
                    else
                        return;
                }
                else if( right.get(0).compareTo(right.get(rightIndex)) < 0 ) {
                    swap(left, leftIndex++, right, 0);
                } else {
                    swap(left, leftIndex++, right, rightIndex++);
                }
            }
        }

        if(rightIndex < right.size() && rightIndex != 0)
            merge(right.subList(0, rightIndex), right.subList(rightIndex, right.size()));
    }

    private void swap(List<N> left, int leftIndex, List<N> right, int rightIndex) {
        //N leftElement = left.get(leftIndex);
        left.set(leftIndex, right.set(rightIndex, left.get(leftIndex)));
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> result = forkJoinPool.invoke(new ParallelMergeSort<Integer>(new ArrayList<>(Arrays.asList(5,9,8,7,6,1,2,3,4))));
        System.out.println("result: " + result);
    }
}



