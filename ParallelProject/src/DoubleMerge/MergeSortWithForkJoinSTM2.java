package DoubleMerge;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortWithForkJoinSTM2 extends RecursiveAction {
 
    private int threadID;
    private int start; 
    private int length;
    private long array[];
    private long aux[];
    private int numberOfLeafThreads;
 
    public MergeSortWithForkJoinSTM2(int id, long array[], long aux[], int threads) {
        this.threadID = id;
        this.array = array;
        this.aux = aux;
        this.numberOfLeafThreads = threads;
    }
 

    
    protected void sortSequentially() {
    	int blockSize = array.length/numberOfLeafThreads; // the size of the sub array that will be sorted sequentially
    	int firstLeafNode = numberOfLeafThreads; 
    	int lastNodeID = numberOfLeafThreads*2-1; // last node of the thread tree
    	int treeHeight = (int)(Math.log(lastNodeID)/Math.log(2));
    	int firstNodeOfLastLevel = (int)Math.pow(2, treeHeight);
    	int nodesInLastLevel = lastNodeID - firstNodeOfLastLevel + 1;

    	// if the thread is in the last level
    	if(threadID >= firstNodeOfLastLevel)
    		start = (threadID-firstNodeOfLastLevel)*blockSize;
    	
    	// if the thread is in the previous level
    	else
    		start = (nodesInLastLevel + (threadID-firstLeafNode))*blockSize;
    	
    	length = blockSize;


    	if( MergeSortUtil.checkPowerOfTwo(threadID+1) )
    		length = array.length - start;

    	
        Arrays.sort(array, start, start+length);
    }
 
    @Override
    protected void compute() {
        if (threadID >= numberOfLeafThreads) {
            sortSequentially();
            return;
        }
        MergeSortWithForkJoinSTM2 th1 = new MergeSortWithForkJoinSTM2(2*threadID, array, aux, numberOfLeafThreads);
        MergeSortWithForkJoinSTM2 th2 = new MergeSortWithForkJoinSTM2(2*threadID+1, array, aux, numberOfLeafThreads);
        invokeAll(th1, th2);
        MergeSortUtil.merge(array, aux, th1.start, th2.start, th2.start+th2.length);
        start = th1.start;
        length = th1.length + th2.length;
        

    }
    

    public static void parallelMergeSort(long array[], int numberOfThreads) {
    	
        long aux[] = new long[array.length];
        
        MergeSortWithForkJoinSTM2 fb = new MergeSortWithForkJoinSTM2(1, array, aux, numberOfThreads);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(fb);
    }

}