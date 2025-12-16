package com.root.allInOne.LeetCode.graph.hard;

import java.util.*;

class criticalconnections {
    Set<Integer> globalVisit = new HashSet();
    Set<Integer> cycle = new HashSet();
    Set<Integer> tempVisit = new HashSet();
    Integer startOfCycle = null;
    Map<Integer,List<Integer>> graph;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        makeGraph(connections);
        System.out.println("graph size = "+ graph.size());
        dfs(3,3);
        System.out.println("globalVisit size = "+ globalVisit.size());
        System.out.println("cycle size = "+ cycle.size());
        System.out.println("tempVisit size = "+ tempVisit.size());
        System.out.println("startOfCycle  = "+ startOfCycle);
        if(cycle.size()==0) {
            System.out.println("NOOO nodes in cycle are there");
            return new ArrayList();
        }
        System.out.println("nodes in cycle are there");
        cycle.stream().forEach(s -> System.out.println(s));
        //if eddge has both nodes in cycle then...
        return new ArrayList();
    }
    void dfs(int start, int parent) {
        globalVisit.add(start);
        List<Integer> children = graph.get(start);

        for(Integer child: children)  {
            System.out.println("dfs = start="+start+" child="+child);
            if (child == parent) {
                //ignore
                System.out.println("....dfs = start="+start+" parent ignore");
            }
            else if (globalVisit.contains(child)) {
                System.out.println("....dfs = start="+start+" cycle");
                cycle.add(child);
                startOfCycle = child;
                iterateCycle(child, child);
                tempVisit.stream().forEach(s -> System.out.println(s));
                System.out.println("cycle prinitng");
                cycle.stream().forEach(s -> System.out.println(s));
                //remove edges between these now

                startOfCycle = null;
                tempVisit.clear();
            }
            else {
                System.out.println("....dfs = start="+start+" all clear");
                dfs(child, start);
            }
        }
    }
    boolean iterateCycle(int start, int parent) {
        tempVisit.add(start);
        List<Integer> children = graph.get(start);
        for(Integer child: children)  {
            if (child == parent) {
                //ignore
            }  else if (child.equals(startOfCycle)) {
                return true;
            }
            else if (tempVisit.contains(child)) {
                //ignore
            } else {
                if(iterateCycle(child, start)) {
                    cycle.add(child);
                    return true;
                }
            }
        }
        return false;
    }
    void makeGraph(List<List<Integer>> connections) {
        this.graph = new HashMap();
        for(List<Integer> con : connections) {
            int a = con.get(0); int b = con.get(1);
            List<Integer> listA = graph.getOrDefault(a, new LinkedList());
            listA.add(b);
            graph.put(a, listA);
            List<Integer> listB = graph.getOrDefault(b, new LinkedList());
            listB.add(a);
            graph.put(b, listB);
        }
    }

    public static void main(String[] args) {
        criticalconnections c = new criticalconnections();

        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,0),
                Arrays.asList(1,3),
                Arrays.asList(2,4),
                Arrays.asList(4,5),
                Arrays.asList(5,6),
                Arrays.asList(6,4),
                Arrays.asList(0,3)
        );
        c.criticalConnections(4, connections);
    }
}