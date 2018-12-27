package RankSortPackage;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.RecursiveAction;

/**
 * @author: yotta
 * @Description:
 * @Date: create in 13:57 2018/12/11
 * @Modified By:
 */
public class ParallelRankSort extends RecursiveAction{

    private int[] elements;

    private int start;

    private int end;

    private int index;

    private int[] result;

    private int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    public ParallelRankSort(int[] elements, int start, int end, int index, int[] result) {
        this.elements = elements;
        this.start = start;
        this.end = end;
        this.index = index;
        this.result = result;
    }


    @Override
    protected void compute() {
        if (index == -1) {
            List<ParallelRankSort> futures = new Vector<>();
            for (int i = start; i <= end; i++) {
                final ParallelRankSort newTask = new ParallelRankSort(elements, start, end, i ,result);
                futures.add(newTask);
            }
            invokeAll(futures);
        }
        else{
            assert(index >= start && index <= end);
            int k = 0;
            for (int j = 0; j < elements.length; j++){
                if (elements[index] > elements[j])
                    k++;
            }
            result[k] = elements[index];
        }
    }



}

