package com.new_leet_code.btree;

import java.util.*;

import com.ds.list.array.ArrayList;

public class TreeOp {
    public static void main(String[] args) {
        TreeOp op = new TreeOp();
        TreeNode node1 = op.new TreeNode(3, op.new TreeNode(2,op.new TreeNode(11),op.new TreeNode(12)), op.new TreeNode(4));
        TreeNode node3 = op.new TreeNode(7);
        TreeNode root = op.new TreeNode(5,node1,node3);
        Solution s = op.new Solution();

        // 1. 二叉树中序遍历
        s.printInOrder(root);
        System.out.println();
        s.printInOrderWithStack(root);
        System.out.println();
        // 2. 二叉树最大宽度
        System.out.println(s.getMaxLength(root));
        // 3. 判断一棵树是否是二叉搜索树
        System.out.println(s.isBST(root));
        // 4. 判断一棵树是否是完全二叉树  层次，出队列的时候只要右节点不存在，左节点存在，返回false，或者左右都不存在，后续出队节点如果有孩子 false
        System.out.println(s.isComplete(root));
        // 5. 判断是否是平衡二叉树
        System.out.println(s.getTreeDepth(root, 0));
        System.out.println(s.isBanlance(root));
    }
    
    public class Solution{

        // 1. 二叉树的中序遍历 栈
        public void printInOrderWithStack(TreeNode root){
            // 1. 先将根及其所有左孩子和左孩子的左孩子入栈
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            while(p!=null){
                stack.add(p);
                p = p.left;
            }
            // 2. 所有出栈节点，访问，并且将右孩子和右孩子的所有左孩子放入栈
            while(!stack.isEmpty()){
                TreeNode ele = stack.pop();
                System.out.print("|"+ele.val);
                ele = ele.right;
                while(ele!=null){
                    stack.add(ele);
                    ele = ele.left;
                }
            }
        }

        // 1.1 二叉树的中序遍历，递归
        public void printInOrder(TreeNode root){
            if(root==null)return;
            printInOrder(root.left);
            System.out.print("|"+root.val);
            printInOrder(root.right);
        }
    
        // 2. 求二叉树的最大宽度  层次遍历。计算每一层的长度，简单方法：两个队列
        public int getMaxLength(TreeNode root){
            // 两个队列，a队列所有元素出队遍历，左右孩子加入右队列，右也这样
            int maxLength = 0;
            Queue<TreeNode> queue1 = new LinkedList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();
            queue1.add(root);
            while(!queue1.isEmpty() || !queue2.isEmpty()){
                // 队列1不为空
                int len = 0;
                Queue<TreeNode> q;
                Queue<TreeNode> anoq;
                if(!queue1.isEmpty())q=queue1;
                else q = queue2;
                anoq = q==queue1?queue2:queue1;

                while(!q.isEmpty()){
                    TreeNode p = q.poll();
                    len++;
                    if(p.left!=null)anoq.add(p.left);
                    if(p.right!=null)anoq.add(p.right);
                }
                if(len>maxLength)maxLength = len;
                
            }
            return maxLength;
        }
    
        // 3. 判断一棵树是否是二叉搜索树
        private TreeNode preValue = null; 
        public boolean isBST(TreeNode root){
            if(root==null)return true;
            if(!isBST(root.left))return false;
            // 访问节点
            if(preValue!=null && root.val<=preValue.val)return false;
            else preValue = root;
            if(!isBST(root.right))return false;
            return true;
        }

        // 4. 判断一棵树是否是完全二叉树  层次，出队列的时候只要右节点不存在，左节点存在，返回false，或者左右都不存在，后续出队节点如果有孩子 false
        public boolean isComplete(TreeNode root){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean flag = true; // 后续是否可以有孩子
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node.left==null && node.right!=null)return false;
                if(node.left==null && node.right == null){
                    flag = false;
                }
                if(node.left!=null && node.right == null){
                    flag = false;
                    queue.add(node.left);
                }
                if(node.left!=null && node.right != null){
                    if(!flag) return false;
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            return true;
        }
        
        // 5. 判断是否是平衡二叉树
        public boolean isBanlance(TreeNode root){
            // 左右树是否平衡
            if(root==null)return true;
            if(!isBanlance(root.left))return false;
            if(!isBanlance(root.right))return false;
            // 左右树高度差距是否大于=2
            if(Math.abs(getTreeDepth(root.left, 0)-getTreeDepth(root.right, 0))>=2) return false;
            return true;
        }
        public int getTreeDepth(TreeNode node,int dep){
            if(node==null)return dep;
            int leftDep = getTreeDepth(node.left, dep+1);
            int rightDep = getTreeDepth(node.right, dep+1);
            return Math.max(leftDep, rightDep);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
