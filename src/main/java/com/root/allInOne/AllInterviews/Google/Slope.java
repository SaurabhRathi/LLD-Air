package com.root.allInOne.AllInterviews.Google;

public class Slope {

    int[] findLongestNonDecreasingSubArray(int[] arr) {
        int len = arr.length;
        if(len == 0) return new int[0];
        int resStartIndex = 0, maxLen = 1, currStartIndex = 0, currLen = 1;

        for(int i=1; i<len; i++) {
            if(arr[i-1] <= arr[i]) {
                currLen++;
            } else {
                currLen = 1;
                currStartIndex = i;
            }
            if(currLen > maxLen) {
                resStartIndex = currStartIndex;
                maxLen = currLen;
            }
        }

        //copyArray
        int[] res = new int[maxLen];
        int index = 0;
        for(int i=resStartIndex; i < (resStartIndex + maxLen); i++) {
            res[index++] = arr[i];
        }
        return res;
    }

    //now you can change exactly one element

//    [ 1,2,5, 3, 1,2,4,5]
//    [1,2,5, 1,2,3]
//    [1,2,5, 4,7,8,9]
//
//    for each index -> left <= right

    //the answer for question  exactly one and at most one is same ... coz you can always change one element kuch nahi to start vala hi 1 se reduce kar do
    int[] findLongestNonDecreasingSubArrayByChangingExactlyOneElement(int[] arr) {
        //maxEndingAt[] maxStartingAt[]
        //https://chatgpt.com/c/685639ce-cc38-8009-8c3c-538f66240169
        return new int[0];
    }
}
