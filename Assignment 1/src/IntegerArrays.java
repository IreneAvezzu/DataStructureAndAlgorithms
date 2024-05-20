/*
ASSIGNMENT 1 - IntegerArrays
Irene Avezzu' - 20142 - IAvezzu@unibz.it
Worked with: Massimo Marcon, Ivana Nworah Bortot and Emmanuel Scopelliti
*/

public class IntegerArrays {
    //Ex 1.1 swap
    public static void swap(int[] A, int i, int j){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    //Ex 1.2 sorted
    public static boolean sorted(int[] A) {
        return sorted(A, 0);
    }
    public static boolean sorted(int[] A, int l) {
        if (A.length==0 || l == (A.length - 1)) 
            return true;
        if (A[l] <= A[l+1]) 
            return sorted(A, l+1);
        else return false;
    }

    //Ex 1.3 MaxPos
    public static int maxPos(int[] A, int l, int r){
        int index = l;
        for (int i=(l+1); i<(r+1); i++){
            if (A[i]>A[index])
                index = i;
        }
        return index;
    }

    //Ex 1.4 reversedCopy
    public static int[] reversedCopy (int[] A){
        int offSet = A.length-1;
        int [] newA = new int [A.length];
        for (int i=0; i<=offSet; i++){
            newA[offSet-i]=A[i];
        }
        return newA;
    }
	
    //Ex 1.5 reverse
    public static void reverse (int[] A){
        int offset = A.length-1;
        for (int i=0; i<(A.length/2); i++){
            swap(A, i, offset-i);
        }
    }

    //Ex 1.6 localMax
    public static int localMax (int[] A){
        return localMax(A, 0, A.length-1);
    }
    public static int localMax (int[] A, int l, int r){
        if (r==0) 
            return 0; //my first idea was to return -1 since there is no localMax but with the test it requires to return 0
        if (l==r) 
            return l;
        
        int m = (l+r) / 2;
        if (A[m]>A[m+1])
            return localMax (A, l, m);
        else
            return localMax (A, m+1, r);
    }

    //Ex 1.7 selectionSortMax
    public static void selectionSortMax(int[] A) {
        selectionSortMax (A, 0, A.length-1);
    }
    public static void selectionSortMax(int[] A, int l, int r){
        if (l >= r) return;
        int maxPosition = maxPos(A, l, r);
        swap(A, maxPosition, r);
        selectionSortMax(A, l, r-1);
    }

    //Ex 1.8 mostFrequentElement option without sorting
    public static int mostFrequentElement(int[] A){
        int maxValue = A[0]; 
        int maxCount = countOccurrences(A, maxValue);
        int value = A[0];
        int count;
        for (int i = 1; i < A.length; i++) {
            if (value!=A[i]) //new walue is different from the previous one
                value = A[i];
                count = countOccurrences(A, value);
                if (count > maxCount) {
                    maxCount = count;
                    maxValue = value;
                }
        }
        return maxValue;
    }
    //support method that caluculate the occourances of x in A 
    public static int countOccurrences(int[] A, int x){
        int count=0;
        for (int i=0; i<A.length; i++){
            if (A[i]==x)
                count++;
        }
        return count;
    }

    //Ex 1.8 mostFrequentElement option with sorting
    public static int sortedMostFrequentElement(int[] A){
        selectionSortMax(A);
        return SortedArrays.mostFrequentElementSorted(A);
    }
}
