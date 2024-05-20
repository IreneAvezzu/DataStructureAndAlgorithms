/*
ASSIGNMENT 1 - SortedArrays
Irene Avezzu' - 20142 - IAvezzu@unibz.it
Worked with: Massimo Marcon, Ivana Nworah Bortot and Emmanuel Scopelliti
*/

public class SortedArrays {
    //Ex 2.1 firstPosXSorted
    public static int firstPosXSorted(int[] A, int x) {
        return firstPosXSorted(A, x, 0, A.length - 1);
    }
    public static int firstPosXSorted(int[] A, int x, int l, int r) {
        if (l == r) {
            if (A[l] == x) return l;
            else return -1;
        }
    
        int m = (l + r) / 2;
        if (A[m] >= x) return firstPosXSorted(A, x, l, m);
        else return firstPosXSorted(A, x, m + 1, r);
    }

    //Ex 2.2 lastPosXSorted
    public static int lastPosXSorted(int[] A, int x) {
        return lastPosXSorted(A, x, 0, A.length - 1);
    }
    public static int lastPosXSorted(int[] A, int x, int l, int r) {
        if (l == r) {
            if (A[l] == x) return l;
            else return -1;
        }
        int middle = (l + r + 1) / 2; //+1 to round up
        if (A[middle] > x) 
            return lastPosXSorted(A, x, l, middle - 1); 
        else 
            return lastPosXSorted(A, x, middle, r);
    }
    
    //Ex 2.3
    public static int mostFrequentElementSorted(int[] A){
        if (A.length == 0) return -1; //empty array

        int maxValue = A[0];
        int maxValueOcc = countXSorted(A, maxValue); //how many occourances of maxValue 
        int last = lastPosXSorted(A, maxValue); //last occourance of maxValue
        int value; 
        int count;  //occourances of value
        
        while (last<A.length-1){
            value = A[last+1]; //next element
            count = countXSorted(A, value);
            if (count>maxValueOcc){
                maxValue = value;
                maxValueOcc = count;
            }
            last = lastPosXSorted(A, value);
        }
        return maxValue;
    }
    //support method that caluculate the occourances of x in A 
    public static int countXSorted(int[] A, int x) {
        int first = firstPosXSorted(A, x);
        if (first == -1) return 0;
        int last = lastPosXSorted(A, x);
        return last - first + 1;
    }  
}