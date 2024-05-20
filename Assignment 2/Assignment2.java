/*
ASSIGNMENT 2
Irene Avezzu' - 20142 - IAvezzu@unibz.it
Worked with: Massimo Marcon, Ivana Nworah Bortot and Emmanuel Scopelliti
*/
public class Assignment2 {
    //Ex 1 isPalindrome
    public static boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length()-1);
    }
    public static boolean isPalindrome(String s, int l, int r) {
        //base case
        if (l>=r) //empty array or left bound is bigger than right bound
            return true;
        if (s.charAt(l) != s.charAt(r)) 
            return false;

        //Recursive call
        return isPalindrome(s, l+1, r-1);        
    }
    
    //Ex 2: maxAscendentLength
    public static int maxAscentLength(int[] A) {
        int maxAscentLength = 1;
        int currAscent = 1;
        for (int i=0; i<A.length-1; i++){
            if(A[i] <= A[i+1]){
                currAscent++;
                maxAscentLength = (currAscent>maxAscentLength)? currAscent : maxAscentLength;
            }
            else{
                currAscent = 1;
            }
        }
        return maxAscentLength;
    }

    //Ex 3: maxMissedGain
    //Brute force method, works on array of minimum size 2
    public static int maxMissedGainBF(int[] A) {
        int buy = 0;
        int sell = A.length-1;
        int maxGain = A[sell]-A[buy];
        for (int i=A.length-1; i>0; i--) { //hypothetical  sell
            for (int j=i-1; j>=0; j--) { //hypothetical  buy
                if (A[i]>=A[j] && A[i]-A[j] > maxGain) { //hypothetical  maxGain
                    buy = j;
                    sell = i;
                    maxGain = A[i]-A[j];
                }
            }
        }
        return (maxGain>=0)?maxGain:0;
    }
    //Improved solution
    public static int maxMissedGain(int[] A) {
        int minSoFar = A[0];
        int maxGain = 0; //A[0] - minSoFar 
        for (int i=1; i<A.length; i++) {
            if (A[i] < minSoFar)
                minSoFar = A[i];
            if (A[i] - minSoFar > maxGain)
                maxGain = A[i] - minSoFar;
        }
        return maxGain;
    }
   
    //Ex 4: matrixOfAverages
    public static double[][] matrixOfAverages(double[] A){
        double[][] averageMatrix = new double[A.length][A.length];
        double sum, count;
        for (int i=A.length-1; i>=0; i--) { //start from the last row and moves upward
            sum = A[i];
            count = 1;
            for (int j=0; j<A.length; j++) { //start from the most left column and moves right
                if (i==j)
                    averageMatrix[i][j] = A[i];
                else if (i>j)
                    averageMatrix[i][j] = 0;
                else{
                    sum += A[j];
                    count++;
                    averageMatrix[i][j] = sum/count;
                }
            }
        }
        return averageMatrix;
    }
}