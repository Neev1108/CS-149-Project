import java.util.concurrent.*;

public class MergeSortTask extends RecursiveAction {
    int[] array;
    int start;
    int end;
    int threshold = 100; 

    MergeSortTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.threshold = array.length / 10;
    }

    protected void compute() {
        if (end - start <= threshold) {
            InsertionSort(array);
        } else {
            int middle = (end + start) / 2;

            MergeSortTask subTask1 = new MergeSortTask(array, start, middle);
            MergeSortTask subTask2 = new MergeSortTask(array, middle, end);
            invokeAll(subTask1, subTask2);
            merge(middle);

        }
    }

    private void merge(int middle) {
        if (this.array[middle - 1] < this.array[middle]) {
            return;
        }
        int[] copy = new int[this.end - this.start];
        System.arraycopy(array, this.start, copy, 0, copy.length);
        int copyLow = 0;
        int copyHigh = this.end - this.start;
        int copyMiddle = middle - this.start;

        for (int i = this.start, p = copyLow, q = copyMiddle; i < this.end; i++) {
            if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                array[i] = copy[p++];
            } else {
                array[i] = copy[q++];
            }
        }
    }

    public void InsertionSort(int [] arr){
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    
    }
}




