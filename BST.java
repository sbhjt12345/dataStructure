
public class BST {
   class TreeNode{
	   TreeNode left, right;
	   int val;
	   TreeNode(int val){
		   this.val = val;
		   left = right = null;
	   }
   }
   
   TreeNode root;
   
   public void insert(int key){
	   root = insert(root,key);
   }
   
   public TreeNode insert(TreeNode root, int val){
	   if (root==null) {
		   root = new TreeNode(val);
		   return root;
	   }
	   if (root.val<val) root.right = insert(root.right,val);
	   if (root.val>val) root.left = insert(root.left,val);
	   return root;
	   
   }
   
   /**
    * LCA in a non BST binary tree
    * 
    * below commented is a stupid way to accomplish
    * **/
   
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       // search left tree for p 
       if (root==null || root==p || root==q) return root;
       boolean leftp = search(root.left,p);
       boolean rightq = search(root.right,q);
       if (leftp^rightq) {
           if (leftp){
               return lowestCommonAncestor(root.left,p,q);
           }
           return lowestCommonAncestor(root.right,p,q);
       }
       else return root;
   }
   
   public boolean search(TreeNode root, TreeNode node){
       if (root==null) return false;
       if (root==node) return true;
       return search(root.left,node) || search(root.right,node);
   }
   
   public TreeNode LCA(TreeNode root, int v1, int v2){
	   // if v1 smaller than root v2 bigger than root then return root
	   // if both bigger then search right, else search left;
	   // for simplicity assume v1<v2
	   if (v1>root.val) return LCA(root.right,v1,v2);
	   else if (v2<root.val) return LCA(root.left,v1,v2);
	   return root;
   }
   
   public void morrisTraversal(TreeNode root){
	   /**
	    *  Morris Algorithm: 
	    * current = root
	    * while current is not null:
	    *    if (current.left == null):
	    *       printout current;
	    *       current = current.right;
	    *    else:
	    *       find the rightmost node in the left subtree of the current, which is the predecessor of current
	    *       if (pre.right == null):
	    *           pre.right = current;
	    *           current = current.left;
	    *       else:
	    *           pre.right = null;
	    *           printout current;
	    *           current = current.right;   
	    */
	   
	   TreeNode cur = root;
	   while (cur != null){
		   if (cur.left == null){
			   System.out.print(cur.val + " ");
			   cur = cur.right;
		   }
		   else {
			   TreeNode pre = cur.left;
			   while (pre.right != null && pre.right != cur){
				   pre = pre.right;
			   }
			   
			   if (pre.right == null){
				   pre.right = cur;
				   cur = cur.left;
			   }
			   else {
				   pre.right = null;
				   System.out.print(cur.val + " ");
				   cur = cur.right;
			   }
		   }
	   }
   }
   
   /**
    * Add all greater values to every node in a given BST
    *
    * idea : 
    * right subtree all greater than root, so get sum of right tree( while doing the recursion), change the val of root
    * then recursion on left
    * 
    * **/
   
   public void modifyBST(TreeNode root){
	   visit(root,0);
   }
   
   public int visit(TreeNode root, int sum){
	   if (root==null) return sum;
	   root.val = root.val + visit(root.right,sum);
	   return visit(root.left,root.val);
   }
   
   /************************************/
   
   /**
    * Remove nodes in a BST outside a given range
    * **/
   
   public TreeNode removeRange(TreeNode root,int low, int high){
	   if (root==null) return root;
	   if (root.val<low) return removeRange(root.right,low,high);
	   if (root.val>high) return removeRange(root.left,low,high);
	   root.left = removeRange(root.left,low,high);
	   root.right = removeRange(root.right,low,high);
	   return root;
   }
   
   /*********************/
   
   /**
    * Largest bst in binary tree
    * 
    * **/
   class minMax{
	   int min,max;
	   boolean isBST;
	   int size;
	   minMax(){
		   min = Integer.MAX_VALUE;
		   max = Integer.MIN_VALUE;
		   isBST = true;
		   size = 0;
	   }
   }
   
   public minMax largest(TreeNode root){
	   if (root==null) return new minMax();
	   // why we are using postorder(left->right->root)? cuz we need to get to leaf first and gradually get up
	   // we want to count if subtree is BST and deliver the info to root, so needs to start from bottom
	   minMax leftMM = largest(root.left);
	   minMax rightMM = largest(root.right);
	   
	   if (leftMM.isBST==false || rightMM.isBST==false || (leftMM.max>=root.val || rightMM.min<=root.val)){
		   minMax res = new minMax();
		   res.size = Math.max(leftMM.size, rightMM.size);
		   return res;
	   }
	   minMax res = new minMax();
	   res.min = root.left==null?root.val:leftMM.min;
	   res.max = root.right==null?root.val:rightMM.max;
	   res.isBST = true;
	   res.size = 1+leftMM.size+rightMM.size;
	   return res;
	   
   }
   public int largestBST(TreeNode root){
	   return largest(root).size;
   }
   
   
   
   
   
   
   public static void main(String[] args){
	   BST eg = new BST();
	   eg.insert(6);eg.insert(13);eg.insert(14);eg.insert(-8);eg.insert(-13);
	   eg.insert(15);eg.insert(7);
	   TreeNode res = eg.removeRange(eg.root,-10,13);
	   eg.morrisTraversal(res);
	   
   }
}
