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
        // 3. 拓扑排序算法（只能用于有向图，且有入度为0的点，且没有环）
        List<Node> res = s.topolgy(graph);
        res.forEach(x -> System.out.print(x.value + " "));
        System.out.println();
        // 4. 最小生成树算法，克鲁斯卡尔（每次选最小边看是否有环，无环加入）和普里姆算法 （只能用于无向图，即每个两个点有来回两条边🔁）
        // 克鲁斯卡尔算法里，很重要的是判断环路，每次选择最小边，加入第一次选择了 k边，两端点是A和B，如果A和B不是一个集合，边可以，否则不可以
        // 要实现这个算法，需要实现这样的集合结构和相关操作：给from和to判断是否处于一个集合，合并from和to所在集合
        Set<Edge> edges = s.kruskalMST(graphOP.createExample2());
        edges.forEach(x->System.out.print("weight:"+x.weight+" from:"+x.from.value+" to:"+x.to.value+"\n"));
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
        public String dfs(Node node) {
            Set<Node> set = new HashSet<>(); // 防止循环
            Stack<Node> stack = new Stack<>();
            String res = "";
            stack.push(node);
            set.add(node);
            res += node.value + " ";
            // 遍历
            while (!stack.isEmpty()) {
                Node n = stack.pop();
                // 找到这个点的下一个未访问点
                for (Node next : n.nexts) {
                    if (!set.contains(next)) {
                        res += next.value + " ";
                        set.add(next);
                        stack.push(n);
                        stack.push(next);
                        break; // 不能将所有未访问的点入栈，只加入一个未访问的点，然后从这个未访问的点出发找它的下一个入栈
                    }
                }
            }
            return res;
        }

        // 3. 拓扑排序算法（只能用于有向图，且有入度为0的点，且没有环）,思路是：先找入度为0的点，擦掉点和对应边，如此循环，如果最后还有节点存在，说明有环路
        public List<Node> topolgy(Graph graph) {
            List<Node> res = new ArrayList<>();
            // 存储所有节点和对于的入度，不能操作和破坏原始的图
            HashMap<Node, Integer> inMap = new HashMap<>();
            // 所有入度为0的点都进入队列
            Queue<Node> queue = new LinkedList<>();
            for (Node n : graph.nodes.values()) {
                inMap.put(n, n.in);
                if (n.in == 0)
                    queue.add(n);
            }
            // 访问队列
            while (!queue.isEmpty()) {
                Node n = queue.poll();
                res.add(n);
                for (Node next : n.nexts) {
                    int newIn = inMap.get(next) - 1;
                    inMap.put(next, newIn);
                    if (newIn == 0)
                        queue.add(next);
                }
            }
            return res;
        }

        // 4.1 kruskal最小生成树算法
        public Set<Edge> kruskalMST(Graph graph) {
            List<Node> vals = new ArrayList<>();
            for(Node n:graph.nodes.values()){
                vals.add(n);
            }
            MySets sets = new MySets(vals);
            Set<Edge> res = new HashSet<>();
            // 排序edge并遍历
            Set<Edge> edges = graph.edges;
            PriorityQueue<Edge> sortedEdges = new PriorityQueue<>((x, y) -> x.weight - y.weight);
            for(Edge e:edges){
                sortedEdges.add(e);
            }
            while (!sortedEdges.isEmpty()) {
                Edge e = sortedEdges.poll();
                if (!sets.isSameSet(e.from, e.to)) {
                    res.add(e);
                    sets.merge(e.from, e.to);
                }
            }
            return res;
        }
    }

    public Graph createExample() {
        int[][] matrix = new int[6][3];
        matrix[0] = new int[] { 0, 1, 1 };
        matrix[1] = new int[] { 0, 2, 2 };
        matrix[2] = new int[] { 0, 3, 2 };
        matrix[3] = new int[] { 1, 4, 1 };
        matrix[4] = new int[] { 1, 2, 3 };
        matrix[5] = new int[] { 2, 3, 1 };

        return createGraph(matrix);
    }
    public Graph createExample2() {
        int[][] matrix = new int[12][3];
        matrix[0] = new int[] { 0, 1, 1 };
        matrix[1] = new int[] { 0, 2, 2 };
        matrix[2] = new int[] { 0, 3, 2 };
        matrix[3] = new int[] { 1, 4, 1 };
        matrix[4] = new int[] { 1, 2, 3 };
        matrix[5] = new int[] { 2, 3, 1 };

        matrix[6] = new int[] { 1, 0, 1 };
        matrix[7] = new int[] { 2, 0, 2 };
        matrix[8] = new int[] { 3, 0, 2 };
        matrix[9] = new int[] { 4, 1, 1 };
        matrix[10] = new int[] { 2, 1, 3 };
        matrix[11] = new int[] { 3, 2, 1 };
        return createGraph(matrix);
    }

    class MySets {
        private HashMap<Node, List<Node>> map = new HashMap<>(); // 每个点处于一个list集合中，可以通过点找到所在集合

        public MySets(List<Node> nodes) {
            // 初始化 map，每个点对应存在一个集合
            for (Node n : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(n);
                map.put(n, set);
            }
        }

        // 是否两个点处于一个集合
        public boolean isSameSet(Node from, Node to) {
            List<Node> set1 = this.map.get(from);
            List<Node> set2 = this.map.get(to);
            return set1 == set2;
        }

        // 合并两个点所在集合
        public List<Node> merge(Node from, Node to) {
            List<Node> set1 = this.map.get(from);
            List<Node> set2 = this.map.get(to);
            if (set1 == set2)
                return set1;
            // 不能使用addall，如果使用addall，还得单独循环set2，给每个点设置map中的新集合地址
            for (Node n : set2) {
                set1.add(n);
                this.map.put(n, set1);
            }
            return set1;
        }

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
