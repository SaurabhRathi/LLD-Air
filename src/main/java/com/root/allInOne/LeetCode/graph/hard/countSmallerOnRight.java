package com.root.allInOne.LeetCode.graph.hard;


import java.util.Arrays;
class Record {
    public int in;
    public int ans;
    public int num;

    Record(int in, int ans, int num) {this.in=in; this.ans=ans; this.num=num;}
    static Record of (int in, int ans, int num) {
        return new Record(in, ans, num);
    }
}
public class countSmallerOnRight {
    static int[] nums = new int[]{5,1,7,3,2,4,10};  //new int[]{5,1,7,3,10,4,2};
    static Record[]records = new Record[0];
    public static void main(String[] args) {
        int len = nums.length;
        records = new Record[len];
        int i=0;
        for (int num: nums) {
            records[i] = Record.of(i,0,num);
            i++;
        }
        mergeSort(0,nums.length-1);
        //System.out.println();
        //Arrays.stream(records).forEach(j -> System.out.println( j.in+ " " + j.num + " " + j.ans));
       // System.out.println();

    }

    static void mergeSort(int low, int high) {
        if(low==high) return;
        int mid = (low+high)/2;
        mergeSort(low, mid);
        mergeSort(mid+1, high);

        int cSize = high-low+1;
        Record[] c = new Record[cSize];

        int i=low,j=mid+1,k=0,elementsCameFromRight=0;

        while(i<=mid && j<=high) {
            if(records[i].num <= records[j].num) {
                records[i].ans += elementsCameFromRight;
                c[k++] = records[i++];
            } else {
                c[k++] = records[j++];
                elementsCameFromRight++;
            }
        }

        while(i<=mid) {
            records[i].ans += elementsCameFromRight;
            c[k++] = records[i];
            i++;
        }
        while(j<=high) {
            c[k++] = records[j++];
        }
        k=0;
        for(int l=low; l<=high; l++) {
            records[l] = c[k++];
        }

        //System.out.println("low="+low+" high="+high+" elementsCameFromRight="+elementsCameFromRight);
        //Arrays.stream(records).forEach(t -> System.out.println( t.in+ " " + t.num + " " + t.ans));
        //create new array
        //int elementsCameFromRight;
        //merge by putting in new array
        //copy in this array


    }
}
