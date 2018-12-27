package DoubleMerge;



public class MergeSortUtil {

    public static void merge(long d1[], long aux[], int start1, int start2, int last){
        int index1 = start1;
        int index2 = start2;
        int index3 = start1;
        while(index1<start2 && index2<last){
            if(d1[index1] < d1[index2]){
                aux[index3] = d1[index1];
                index1++;
                index3++;
            }else{
                aux[index3] = d1[index2];
                index2++;
                index3++;
            }
        }
        

        while(index1<start2){
            aux[index3++] = d1[index1++];
        }
        

        while(index2<last){
            aux[index3++] = d1[index2++];
        }
        
        System.arraycopy(aux, start1, d1, start1, last-start1);
    }

    public static int mergeMins(long d1[], long aux[], int start1, int start2, int last){
        int index1 = start1;
        int index2 = start2;
        int index3 = start1;
        int elementsToMerge = (last - start1)/2;
        int counter = 0;
        
        while(index1<start2 && index2<last  && counter<elementsToMerge){
            if(d1[index1] < d1[index2]){
                aux[index3] = d1[index1];
                index1++;
                index3++;
            }else{
                aux[index3] = d1[index2];
                index2++;
                index3++;
            }
            counter++;
        }
        

        while(index1<start2  && counter<elementsToMerge){
            aux[index3++] = d1[index1++];
            counter++;
        }
        

        while(index2<last  && counter<elementsToMerge){
            aux[index3++] = d1[index2++];
            counter++;
        }
        
        return (index3-start1);
    }


    public static int mergeMaxes(long d1[], long aux[], int start1, int start2, int last){
        int index1 = start2-1;
        int index2 = last-1;
        int index3 = last-1;
        int elementsToMerge = (int)Math.ceil( (last - start1)/2.0 );
        int counter = 0;
        
        while(index1>=start1 && index2>=start2  && counter<elementsToMerge){
            if(d1[index1] > d1[index2]){
                aux[index3] = d1[index1];
                index1--;
                index3--;
            }else{
                aux[index3] = d1[index2];
                index2--;
                index3--;
            }
            counter++;
        }

        while(index1>=start1  && counter<elementsToMerge){
            aux[index3--] = d1[index1--];
            counter++;
        }

        while(index2>=start2  && counter<elementsToMerge){
            aux[index3--] = d1[index2--];
            counter++;
        }
        
        return (last - index3 -1);
    }


    public static void isSorted(long array[]) {
    	
    	boolean sorted = true;
        for (int i = 0; i < array.length-1; i++) {
            if(array[i] > array[i+1]){
                System.out.println("not sorted");
                System.out.println(i+": "+array[i]);
                System.out.println((i+1)+": "+array[i+1]);
                sorted = false;
            }
        }
        
        if(sorted)
        	System.out.println("array is sorted.");
    }    

    public static void arrayInit(long array[], int seed) {
        java.util.Random r = new java.util.Random(seed);
        for (int j = 0; j < array.length; j++) {
            array[j] = r.nextLong();
        }
    }

    public static boolean checkPowerOfTwo(int number) {
    	    return ((number & (number - 1)) == 0);
  	}
    

    public static void checkInput(int arraySize, int numberOfThreads) {
    	if(!MergeSortUtil.checkPowerOfTwo(numberOfThreads)) {
    		System.out.println("Number of threads must be a power of two.");
    		System.out.println(numberOfThreads + " is not a power of two.");
        	System.exit(0);
    	}
    	
    	if((arraySize % numberOfThreads) != 0) {
    		System.out.println("number of elements must be divisible by the number of threads.");
    		System.out.println("number of elements: " + arraySize);
    		System.out.println("number of threads: " + numberOfThreads);
        	System.exit(0);
    	}
    }    

}
