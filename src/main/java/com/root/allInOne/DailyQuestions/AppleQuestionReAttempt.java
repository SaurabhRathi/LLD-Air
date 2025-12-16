package com.root.allInOne.DailyQuestions;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AppleQuestionReAttempt {

    static String matchAllOperatorsRegex = "[+x-]";

    public static void main(String[] args) {
        String s = "abab";
        int i=1;
        int size=1;
        String nextString = s.substring(i, 6);
        String exp = "3x14/7-2/2x5x3+4/2";
        exp = process(exp, "/");
        System.out.println(exp);
        exp = process(exp, "x");
        System.out.println(exp);
    }

    static String process(String exp, String op) {
        String[] arr = exp.split(op);
        for(int iright=1; iright< arr.length; iright++) {
            int ileft = iright - 1;
            int leftVal = reduceLeftAndExtract(arr, ileft);
            int rightVal = cutRightAndExtract(arr, iright);
            int newVal = op.equals("/") ? leftVal / rightVal : leftVal*rightVal;
            arr[iright] = newVal + "" + arr[iright];
        }
        return String.join("", arr);
    }

    static int reduceLeftAndExtract(String[] arr, int ileft) {
        String leftExp = arr[ileft];
        String[] leftArr = leftExp.split(matchAllOperatorsRegex);
        int len = leftArr.length;
        String leftArrRightValue = leftArr[len-1];
        int retValue = Integer.parseInt(leftArrRightValue);

        StringBuilder sb = new StringBuilder(leftExp);
        sb.setLength(leftExp.length() - leftArrRightValue.length());
        arr[ileft] = sb.toString();

        return retValue;
    }
    static int cutRightAndExtract(String[] arr, int iright) {
        String rightExp = arr[iright];
        String[] rightArr = rightExp.split(matchAllOperatorsRegex);
        String rightArrLeftValue = rightArr[0];
        int retValue = Integer.parseInt(rightArrLeftValue);

        arr[iright] = rightExp.substring(rightArrLeftValue.length());

        return retValue;
    }

}

