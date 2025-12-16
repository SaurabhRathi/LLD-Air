package com.root.allInOne.AllInterviews;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AppleQuestion {

//    https://leetcode.com/problems/basic-calculator-iii/
//    this is supposed to be done by stack bro
//    mai jo niche kiya hu, that is very lengthy
/*
class Solution:
    def calculate(self, s):
        def calc(it):
            def update(op, v):
                if op == "+": stack.append(v)
                if op == "-": stack.append(-v)
                if op == "*": stack.append(stack.pop() * v)
                if op == "/": stack.append(int(stack.pop() / v))

            num, stack, sign = 0, [], "+"

            while it < len(s):
                if s[it].isdigit():
                    num = num * 10 + int(s[it])
                elif s[it] in +-*division (can't use the symbol here actually)
    update(sign, num)
    num, sign = 0, s[it]
    elif s[it] == "(":
    num, j = calc(it + 1)
    it = j - 1
    elif s[it] == ")":
    update(sign, num)
                    return sum(stack), it + 1
    it += 1
    update(sign, num)
            return sum(stack)

        return calc(0)
*/
    static String allOperatorsMatchRegex = "[/x+-]";
    //given an expression, parse it and return a value

    // 3+4-5
    // 3*5/7+4-2
    //3*14/7/2*5*3
    public static void main(String[] args) {
        String exp = "3x14/7-2/2x5x3+4/2-9+7-8";
        exp = process(exp, "/");
        exp = process(exp,"x");
        exp = process(exp, "\\+");
//        exp = processSub(exp);
        System.out.println(exp);
    }
    static String process(String exp, String regex) {
        String[] splits = exp.split(regex);
        if(splits.length==0) return exp;

        for(int i=1; i<splits.length; i++) {
            String rightExp = splits[i];
            int leftVal = getLastOfAndRemoveIt(splits, i-1);
            int rightVal = getFirstOf(rightExp);
            int ans = regex.equals("/") ? leftVal/rightVal : (regex.equals("x") ? leftVal*rightVal : leftVal+rightVal);
            updateFirstOf(ans, splits, i);
        }
        String processedExp = Arrays.stream(splits).collect(Collectors.joining(""));
        return processedExp;
    }

    private static int getLastOfAndRemoveIt(String[] splitsParent, int index) {
        String leftExp = splitsParent[index];
        String [] splits = leftExp.split(allOperatorsMatchRegex);
        int n = splits.length;
        String last = splits[n-1];
        splitsParent[index] = leftExp.substring(0,leftExp.length()-last.length());
        //please check if last ka value also got changed, no it won't as string are immutable but yes if other objects
        return Integer.parseInt(last);
    }

    private static int getFirstOf(String s) {
        String [] splits = s.split(allOperatorsMatchRegex);
        String first = splits[0];
        int val = Integer.parseInt(first);
        return val;
    }

    private static void updateFirstOf(int val, String[] splitsParent, int index) {
        String rightExp = splitsParent[index];
        String [] splits = rightExp.split(allOperatorsMatchRegex); //if no exp?
        String first = splits[0];
        rightExp = rightExp.substring(first.length(), rightExp.length());
        splitsParent[index] = ""  +val + rightExp;
    }
}

//learnings:
//copy and pen leke baitha and try karta to ho jaata jaldi se
//basically of all the steps you visualize you have to run through them