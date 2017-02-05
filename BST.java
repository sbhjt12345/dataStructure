
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
   
   public TreeNode LCA(TreeNode root, int v1, int v2){
	   // if v1 smaller than root v2 bigger than root then return root
	   // if both bigger then search right, else search left;
	   // for simplicity assume v1<v2
	   if (v1>root.val) return LCA(root.right,v1,v2);
	   else if (v2<root.val) return LCA(root.left,v1,v2);
	   return root;
   }
}
