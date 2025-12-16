package com.root.allInOne.AllInterviews.Google;

/*
MaxQuestionAssignment
https://leetcode.com/discuss/post/6846591/google-l4-onsite-dsa-interview-questions-vz1t/
Optimally assigning questions to volunteers based on skill tags vs. question tags
I was thinking greedy - sort all volunteers on max skills first and questions with max tags first and try matching
 mera Greedy doesn't work coz
v1: {a, b,c} v2: {a,b}
q1: {a,b} q2: {b,c}
OR
v1: {a,b} v2: {a}
q1: {a} q2: {b}
mere greedy me q1=v1 q2=_
so use dfs based bipartite matching algo
*/


import java.util.*;
//class MaxQuestionAssignment
public class MaxBipartiteMatching {

    public int maxAssignedQuestions(List<Question> questions, List<Volunteer> volunteers) {
        int m = questions.size();
        int n = volunteers.size();
        // Build bipartite graph: question -> list of volunteers who can answer
        for (int i = 0; i < m; i++) graph.add(new ArrayList<>());
        for (int qi = 0; qi < m; qi++) {
            Question q = questions.get(qi);
            for (int vi = 0; vi < n; vi++) {
                Volunteer v = volunteers.get(vi);
                if (v.skills.containsAll(q.requiredSkills)) graph.get(qi).add(vi); // question qi can go to volunteer vi
            }
        }

        mapVtoQ = new Integer[n];
        skipVolunteers = new HashSet<>();
        int res = 0;
        for(int q=0; q<m; q++) {
            skipVolunteers.clear();
            boolean yesWeCan = canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(q);
            res = yesWeCan ? res+1 : res;
        }
        return res;
    }

    List<List<Integer>> graph = new ArrayList<>();
    Integer[] mapVtoQ;
    Set<Integer> skipVolunteers;
    /*
    // DFS to find an augmenting path
    // DFS based bipartite matching
        v1: {a,b} v2: {a}
        q1: {a} q2: {b}
        graph = q1[v1,v2] q2[v1]
        canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(q1)
           -1 tried = {v1}
           -2 assignment = [q1=v1]
           -3 return true
        canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(q2)
            -1 tried = {v1}
            -2 canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(q1)
                -2.1 tried = {v1, v2}
                -2.2 assignment[q1=v2]
                -2.3 return true
            -3 step 2 gets return true so assignment[q2=v1, q1=v2]
            -4 return true
      Basically hindi me zist is, har question ke liye eligible volunteers ko ek ek karke dekho starting from first
        agar vo khali hai, to mil gaya, return
        agar vo kisi question ko assigned hai, to vo question ko kisi aur ko pakad ne bol, iss volunteer ke alawa - ye recursively hote jayega to set banega visited ka
    */

    //canWeAssignAVolunteerToThisQuestionAndTryChangingOtherAssignmentsIfRequiredSoThatThisQuestionGetsOne -> betterName
    boolean canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(int que) {
        for(int v : graph.get(que)) {
            if(skipVolunteers.contains(v)) continue;
            skipVolunteers.add(v);
            Integer alreadyAssignedQueToV = mapVtoQ[v];
            if(alreadyAssignedQueToV == null || canWeReAssignAVolunteerToThisQuestionTryingEachVolunteerOnce(alreadyAssignedQueToV)) {
                mapVtoQ[v] = que;
                return true;
            }
        }
        return false;
    }

  //zist of the above functions is the below. bus ye next 2 lines add kar dena -> if(skipVolunteers[v]==true) continue; skipVolunteers[v] = true;
//    boolean dfs(int q) {
//        for(var v: graph.get(q)) {
//            var preAssignedQ = mapVtoQ[v];
//            if(preAssignedQ==null || dfs(preAssignedQ)) {
//                mapVtoQ[v] = q; return true;
//            }
//        }
//        return false;
//    }

    static class Volunteer {
        int id; Set<String> skills;
        Volunteer(int id, Set<String> skills) {
            this.id = id; this.skills = skills;
        }
    }

    static class Question {
        int id;
        Set<String> requiredSkills;

        Question(int id, Set<String> requiredSkills) {
            this.id = id;
            this.requiredSkills = requiredSkills;
        }
    }
}
