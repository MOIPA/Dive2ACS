package com.new_leet_code.graph;

import java.util.*;

public class GraphOP {
    public static void main(String[] args) {
        GraphOP graphOP = new GraphOP();
        Solution s = graphOP.new Solution();

        Graph graph = graphOP.createExample();
        Node root = graph.nodes.get(0);
        // 1. BFS 宽度优先遍历
        System.out.println(s.bfs(root));
        // 2. DFS 深度优先遍历
        System.out.println(s.dfs(root));
    }

    class Solution {
        // 1. BFS 宽度优先遍历 从一个节点开始出发遍历
        public String bfs(Node node) {
            Queue<Node> queue = new LinkedList<>();
            Set<Node> set = new HashSet<>(); // set防止环路
            String res = "";
            queue.add(node);
            set.add(node);
            while (!queue.isEmpty()) {
                Node p = queue.poll();
                res += p.value + " ";
                for (Node n : p.nexts)
                    if (!set.contains(n)) {
                        set.add(n);
                        queue.add(n);
                    }
            }
            return res;
        }
        // 2. DFS 深度优先遍历 用栈和set，栈每次弹出找到一个没访问过的点next，将自己和next入栈，如果next没有子节点了，自己出栈找到next2
        public String dfs(Node node){
            Set<Node> set = new HashSet<>(); // 防止循环
            Stack<Node> stack = new Stack<>();
            String res = "";
            stack.push(node);
            set.add(node);
            res += node.value+" ";
            // 遍历
            while(!stack.isEmpty()){
                Node n = stack.pop(); 
                // 找到这个点的下一个未访问点
                for(Node next : n.nexts){
                    if(!set.contains(next)){
                        res += next.value+" ";
                        set.add(next);
                        stack.push(n);
                        stack.push(next);
                        break; // 不能将所有未访问的点入栈，只加入一个未访问的点，然后从这个未访问的点出发找它的下一个入栈
                    }
                }
            }
            return res;
        }
    }

    public Graph createExample(){
        int[][] matrix = new int[6][3];
        matrix[0] = new int[]{0,1,1};
        matrix[1] = new int[]{0,2,2};
        matrix[2] = new int[]{0,3,2};
        matrix[3] = new int[]{1,4,1};
        matrix[4] = new int[]{1,2,3};
        matrix[5] = new int[]{2,3,1};

        return createGraph(matrix);
    }

    // 通过输入数组，创建图
    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        // 数组格式[from,to,weight]
        for (int[] args : matrix) {
            // 获取或创建点
            Node from, to;
            if (graph.nodes.containsKey(args[0]))
                from = graph.nodes.get(args[0]);
            else {
                from = new Node(args[0]);
                graph.nodes.put(args[0], from);
            }
            if (graph.nodes.containsKey(args[1]))
                to = graph.nodes.get(args[1]);
            else {
                to = new Node(args[1]);
                graph.nodes.put(args[1], to);
            }
            Edge edge = new Edge(args[2], from, to);
            // 加入边集
            if (!graph.edges.contains(edge))
                graph.edges.add(edge);
            // 配置点和边关系
            from.out++;
            to.in++;
            from.nexts.add(to);
            from.edges.add(edge);
        }
        return graph;
    }

    // 图中每个点的数据结构定义
    class Node {
        public int value;
        public int in; // 入度
        public int out; // 出度
        public List<Node> nexts; // 出度方向的直接邻居
        public List<Edge> edges; // 和直接邻居的边，对于nexts

        public Node(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    // 边的定义
    class Edge {
        public int weight; // 边的权重
        public Node from; // 射出边点
        public Node to; // 摄入边点

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    // 图的数据结构定义
    class Graph {
        // 点集 和 边集
        HashMap<Integer, Node> nodes;
        HashSet<Edge> edges;

        public Graph() {
            this.nodes = new HashMap<>();
            this.edges = new HashSet<>();
        }
    }
}
