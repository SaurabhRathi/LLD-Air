package com.root.allInOne.AllInterviews;

public class NQueens {

    static int n; //noOfQueens //5 queens 5*5
    static int[] rowPos;
    static int[] colPos;
    static int[] rightDiagonal;
    static int[] leftDiagonal;

    public static void main(String[] args) {
        n = 9;
        rowPos = new int[n];
        colPos = new int[n];
        rightDiagonal =  new int[2*n];
        leftDiagonal =  new int[2*n];
        System.out.println(placeQueens(0));
    }

    static boolean placeQueens(int row) {
        if(row>=n) return true;
        boolean ans = false;
        for(int col=0; col<n; col++) {
            if (isQueenConflicting(row, col)) {
                continue;
            }
            rowPos[row] = 1;
            colPos[col] = 1;
            rightDiagonal[row + col] = 1;
            leftDiagonal[n-1-(col-row)] = 1;
            ans = placeQueens(row + 1);
            if (ans) return true;
            rowPos[row] = 0;
            colPos[col] = 0;
            rightDiagonal[row + col] = 0;
            leftDiagonal[n-1-(col - row)] = 0;
        }
        return ans;
    }

    static  boolean isQueenConflicting(int x, int y) {
        if(rowPos[x]==1) return true;
        if(colPos[y]==1) return true;
        if(rightDiagonal[x+y]==1) return true;
        if(leftDiagonal[n-1-(y-x)]==1) return true;
        return false;
    }


}
