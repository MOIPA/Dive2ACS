package com.new_leet_code.btree;

import java.util.*;

public class TreeOp {
    public static void main(String[] args) {
        TreeOp op = new TreeOp();
        TreeNode nodeX = op.new TreeNode(2);
        TreeNode nodeY = op.new TreeNode(4);
        TreeNode node1 = op.new TreeNode(3, nodeX, nodeY);
        TreeNode node3 = op.new TreeNode(7,op.new TreeNode(6),op.new TreeNode(8));
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
        // 6. 套路化的方式 来做 完全二叉树和平衡二叉树判断  不能！！！！
        // System.out.println("is complete pro:"+s.doIsCompletePro(root).isComplete);
        // 7. 套路化的方式来做是否是搜索二叉树的判断
        System.out.println("is bst pro:"+s.isBSTPro(root));
        // 8. 套路化方式判断是否是满二叉树，满二叉树满足 节点数 = 2^height
        System.out.println(s.isFull(root));
        // 9. 两个节点的最低公共节点
        System.out.println(s.findLowestPublicNode(root, nodeX, nodeY).val);
        // 9. 两个节点的最低公共节点 高级版本  思路：每个节点看左右子树是否包含目标节点，包含了就返回自身，不包含就返回包含的左子树，或者右子树返回的点，如果自己本节点就是目标节点返回自身
        System.out.println(s.findLPN(root, nodeX, nodeY).val);
        // 10. 序列化树 和 反序列化树
        String info = s.serializeTree(root);
        System.out.println(info);
        TreeNode parsedRoot = s.deserializeTree(info);
        s.printInOrder(parsedRoot);
        System.out.println();
        // 11.0 纸张折痕问题
        System.out.println(s.foldPaper(7));
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
    
        // 6. 套路化的方式 来做 完全二叉树
        public IsCompleteResult doIsCompletePro(TreeNode root){
            // // 需要获得左子树是否是完全二叉树，右子树是否是完全二叉，所以需要一个boolean
            // // 是完全二叉树，是哪种，左右都没 叶节点：0  有左：1  有右：2 都有 3
            // if (root==null) return null;
            
            // IsCompleteResult leftResult = doIsCompletePro(root.left);
            // IsCompleteResult rightResult = doIsCompletePro(root.right);
            // if(leftResult==null && rightResult==null)return new IsCompleteResult(true, 0);
            // // 判断左右情况
            // // 不完全： 1. 没有左子树，有右子树 2. 左右子树有一个不完全  3. 左子树
            // boolean flag = true;
            // if(leftResult==null && rightResult !=null) flag = false;
            // else if(leftResult!=null && rightResult ==null) flag = true;
            // else if(!leftResult.isComplete||!rightResult.isComplete) flag = false;
            // else if((leftResult.type==0||leftResult.type==1) && rightResult.type!=0) flag = false;
            // // 本节点判断
            // int type =0;
            // if(leftResult!=null && rightResult==null)type = 1;
            // if(leftResult!=null && rightResult!=null)type = 3;
            // return new IsCompleteResult(flag, type); 
            return null;
            // 套路化不适合所有题目！！！！
        }

        // 7. 套路化的方式来做是否是搜索二叉树的判断
        public boolean isBSTPro(TreeNode root){
            return doIsBstPro(root).isBst;

        }
        public IsBstResult doIsBstPro(TreeNode root){
            // 左子树是否搜索，右子树是否，要左子树最大值，右子树最小值
            // 所以递归结构应该返回最大最小，是否搜索
            if(root==null) return new IsBstResult(true, null, null);
            IsBstResult leftResult = doIsBstPro(root.left);
            IsBstResult rightResult = doIsBstPro(root.right);
            // 左子树和右子树都搜索，且自己的值大于左的最大，小于右的最小才可以
            if(leftResult.isBst && rightResult.isBst && root.val>leftResult.max && root.val<rightResult.min)
                return new IsBstResult(true, Math.max(root.val, rightResult.max), Math.min(root.val, leftResult.min));
            // 否则不行
            return new IsBstResult(false, null, null);
        }
    
        // 8. 套路化方式判断是否是满二叉树，满二叉树满足 节点数 = 2^height
        public boolean isFull(TreeNode root){
            IsFullResult result = doGetTreeInfo(root);
            if (result.nodes+1==Math.pow(2, result.height)) return true;
            return false;
        }
        private IsFullResult doGetTreeInfo(TreeNode root){
            if(root==null)return new IsFullResult(0, 0);
            IsFullResult leftResult = doGetTreeInfo(root.left);
            IsFullResult rightResult = doGetTreeInfo(root.right);
            return new IsFullResult(leftResult.nodes+rightResult.nodes+1, Math.max(leftResult.height,rightResult.height)+1);
        }

        // 9. 两个节点的最低公共节点
        public TreeNode findLowestPublicNode(TreeNode root,TreeNode node1,TreeNode node2){
            // 两个节点，将两个节点包括自己的本身节点生成一个链表，判断两个链表
            List<TreeNode> path1 = new ArrayList<>();
            List<TreeNode> path2 = new ArrayList<>();
            containTarget(root, path1, node1);
            containTarget(root, path2, node2);
            // path1和path2找公共点
            int i=path1.size()>path2.size()?path1.size()-path2.size():0;
            int j=path2.size()>path1.size()?path2.size()-path1.size():0;
            while(i<path1.size()){
                if(path1.get(i)==path2.get(j))return path1.get(i);
                i++;j++;
            }
            return null;
        }
        private boolean containTarget(TreeNode node,List<TreeNode> list,TreeNode target){
            if(node==null)return false;
            // 通过判断左右节点是否包含目标节点，或者自己是否是目标节点，来判断是否将自己加入path
            // 左右不包含目标，且自己不是目标，整棵树不含目标
            if(!containTarget(node.left, list,target) && !containTarget(node.right, list,target) && node!=target) return false;
            // 否则将自己加入path
            list.add(node);
            return true;
        }

        // 9. 两个节点的最低公共节点 高级版本
        public TreeNode findLPN(TreeNode root,TreeNode node1,TreeNode node2){
            if(root==null||root==node1||root==node2) return root;
            TreeNode left = findLPN(root.left, node1, node2);
            TreeNode right = findLPN(root.right, node1, node2);
            if(left!=null && right!=null)return root;   // 左右子树有目标节点，本节点就是最低祖先，往上返回，因为递归是从下往上返回的
            if(left!=null && right == null)return left; // 左子树有目标节点，右没有，说明左子树返回的目标节点是另一个节点的祖先
            return right;                               // 左子树没有目标节点，返回右（右可能为空，可能为祖先节点
        }
        // 10. 序列化树 和 反序列化树
        public String serializeTree(TreeNode root){
            // 序列化，采用先序方式
            StringBuilder builder =new StringBuilder();
            doSerializeTree(root, builder);
            return builder.toString().substring(1);
        }
        private void doSerializeTree(TreeNode root,StringBuilder builder){
            if(root==null){
                builder.append("_#");
                return;
            }
            builder.append("_"+root.val);
            doSerializeTree(root.left, builder);
            doSerializeTree(root.right, builder);
        }

        // 10.1 反序列化
        public TreeNode deserializeTree(String info){
            String[] list=info.split("_");
            Queue<String> queue = new LinkedList<>();
            for (String itemString : list) {
                queue.add(itemString);
            }
            return doDeserialize(queue);
        }
        // i 位置建立节点，并且左右孩子也建立节点
        private TreeNode doDeserialize(Queue<String> queue){
            String top = queue.poll();
            if(top.equals("#")) return null;
            TreeNode parent = new TreeNode(Integer.parseInt(top));
            parent.left = doDeserialize(queue);
            parent.right = doDeserialize(queue);
            return parent;
        }
        
        // 11.0 纸张折痕问题 打印多次折叠的折痕，凹：1 凸：0，本质就是中序打印一颗二叉树，这个树的根节点1，左1，右0，所有节点都是左1右0
        public String foldPaper(int n){
            return doFoldPaper(1, n, 1); // 根节点为1
        }
        private String doFoldPaper(int current,int n,int type){ // type是这个节点值是0还是1
            if(current>n)return ""; // 如果当前层数大于n返回空
            String res = "";
            res += doFoldPaper(current+1, n,1); // 左节点值都是1
            res += type;                            // 访问自己本身节点值
            res += doFoldPaper(current+1, n,0); // 右节点值都是0
            return res;
        }
    }

    class IsFullResult{
        int nodes;
        int height;
        public IsFullResult(int nodes,int height){
            this.nodes = nodes;
            this.height = height;
        }
    }

    class IsCompleteResult{
        boolean isComplete;
        int type;
        public IsCompleteResult(boolean isComplete,int type){
            this.isComplete = isComplete;
            this.type = type;
        }
    }
    
    class IsBstResult{
        public boolean isBst;
        public Integer max;
        public Integer min;
        public IsBstResult(boolean isBst,Integer max,Integer min){
            this.max = max;
            this.min = min;
            this.isBst = isBst;
            if (this.max==null) {
                this.max = Integer.MIN_VALUE;
            }
            if(this.min== null) this.min = Integer.MAX_VALUE;
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
