package com.root.allInOne.LeetCode.LcHard;
 import java.util.*;
public class SkylineCitylights {
//    Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//    Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (int[] one, int[] another) -> {
                    if(one[2]!=another[2]) {
                        return another[2] - one[2];
                    }
                    return another[1] - one[1];
                });

        int last_height = 0;
        for(int[] build: buildings) {

            if(pq.isEmpty()) {
                addAns(build[0], build[2]);
                pq.add(build);
            } else {
                int[] lastBuild = pq.peek();
                //case : new build is not touching
                if(lastBuild[1]<build[0]) {
                }
                //common
//                if()
//                add left edge of the new building
                //case: new build is touching
            }
        }
        return null;
    }

    void resolve_queue() {

    }

    void addAns(int x, int y) {
        ans.add(Arrays.asList(x,y));
    }

}
