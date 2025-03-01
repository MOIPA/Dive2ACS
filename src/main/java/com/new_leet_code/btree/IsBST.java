package com.new_leet_code.btree;
// 判断是否是二叉搜索树
import java.util.*;
public class IsBST{
    public static void main(String[] args){
        IsBST bst = new IsBST();
        Solution solution = bst.new Solution();
        TreeNode node1 = bst.new TreeNode(3, bst.new TreeNode(2), bst.new TreeNode(4));
        TreeNode node3 = bst.new TreeNode(7, bst.new TreeNode(6), bst.new TreeNode(8));
        TreeNode root = bst.new TreeNode(5,node1,node3);
        System.out.println(solution.isValidBST(root));

        solution.traverseBiTreeHead(root);
        solution.traverseBiTreeTail(root);
    }
    
    class Solution{
        // 中序遍历，递归的时候，print是按顺序打印的
        private long preValue = Long.MIN_VALUE;
        public boolean isValidBST(TreeNode root) {
            // 终止条件
            if(root==null)return true;
            // 左节点
            if(!isValidBST(root.left))return false;
            // 打印
            if(root.val<=preValue)return false;
            preValue = root.val;
            // 右节点
            return isValidBST(root.right);
        }

        // 用栈的方式遍历二叉树 先序遍历
        public void traverseBiTreeHead(TreeNode root){
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while(!stack.isEmpty()){
                TreeNode r = stack.pop();
                System.out.print(r.val+" || ");
                if(r.right!=null)stack.add(r.right);
                if(r.left!=null)stack.add(r.left);
            }
            System.out.println();
        }
        // 后序遍历
        public void traverseBiTreeTail(TreeNode root){
            //前序遍历 的结果放入另一个栈
            Stack<TreeNode> out = new Stack<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode r = stack.pop();
                out.add(r);
                if(r.left!=null)stack.add(r.left);
                if(r.right!=null)stack.add(r.right);
            }
            while(!out.isEmpty())System.out.print(out.pop().val+" || ");
            System.out.println();
        }
        // 中序遍历
        public void traverseBiTreeSeq(TreeNode root){
            
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

