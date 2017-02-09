
public class LinkedList {
     Node head;
     public class Node{
    	 Node next;
    	 int value;
    	 public Node(int value){
    		 this.value = value;
    		 next = null;
    	 }
     }
     
     void deleteNode(int key){
    	 Node tmp = head,prev=null;
    	 if (tmp !=null && tmp.value==key){
    		 head = tmp.next;
    		 return;
    	 }
    	 while (tmp!=null && tmp.value != key){
    		 prev = tmp;
    		 tmp = tmp.next;
    	 }
    	 if (tmp==null) return;
    	 prev.next =tmp.next;
     }
     
     void deleteNode2(int pos){
    	 Node tmp = head;
    	 if (tmp==null) return;
    	 if (pos==0){
    		 head = tmp.next;
    		 return;
    	 }
    	 
    	 for (int i=0;tmp!=null && i<pos-1;i++){
    		 tmp = tmp.next;
    	 }
    	 if (tmp==null || tmp.next==null) return;
    	 Node next = tmp.next.next;
    	 tmp.next = next;	 
     }
     
     public void swap(int x, int y){
    	 // find the two node, switch the two node with their next, and switch back next
    	 Node prevx = null,curx = head;
    	 while (curx!=null && curx.value!=x){
    		 prevx = curx;
    		 curx = curx.next;
    	 }
    	 
    	 Node prevy = null, cury = head;
    	 while (cury!=null && cury.value != y){
    		 prevy = cury;
    		 cury = cury.next;
    	 }
    	 
    	 if (curx==null || cury==null) return;
    	 if (prevx!=null){
    		 prevx.next = cury;
    	 }
    	 else head = cury;
    	 
    	 if (prevy!=null) prevy.next = curx;
    	 else head = curx;
    	 
    	 Node tmp = curx.next;
    	 curx.next = cury.next;
    	 cury.next = tmp;
     }
     
     public int getNth(int n){
    	 int count = 0;
    	 Node cur = head;
    	 while (cur != null && count!=n){
    		 count++;
    		 cur = cur.next;
    	 }
    	 if (cur==null) return 0;
    	 return cur.value;
     }
     
     public void push(int new_data)
     {
  
         /* 1. alloc the Node and put data*/
         Node new_Node = new Node(new_data);
  
         /* 2. Make next of new Node as head */
         new_Node.next = head;
  
         /* 3. Move the head to point to new Node */
         head = new_Node;
     }
     
     public void printl(Node head){
    	 Node cur = head;
    	 while (cur != null){
    		 System.out.print(cur.value + " , ");
    		 cur = cur.next;
    	 }
    	 System.out.println("");
     }
     
     public void sort(Node head){
    	 if (head==null || head.next==null) return;
    	 Node asc = new Node(0), asc2 = asc;
    	 Node desc = new Node(0), desc2 = desc;
    	 Node cc = head;
    	 while (cc != null){
    		 asc.next = cc;
    		 asc = asc.next;
    		 cc = cc.next;
    		 if (cc != null){
    			 desc.next = cc;
    			 cc = cc.next;
    			 desc = desc.next;
    		 }
    	 }
    	 asc.next =null;
    	 desc.next = null;
    	 asc2 = asc2.next;desc2 =desc2.next;
    	 printl(asc2);
    	 printl(desc2);
    	 
    	 Node prev=null, cur = desc2;
    	 while (cur !=null){
    		 Node next = cur.next;
    		 cur.next = prev;
    		 prev = cur;
    		 cur = next;
    	 }
    	 desc2 = prev;
    	 printl(desc2);
    	 head = merge(asc2,desc2);
     }
     
//     public Node merge(Node h1, Node h2){
//    	 if (h1==null) return h2;
//    	 if (h2==null) return h1;
//    	 Node tmp = null;
//    	 if (h1.value<h2.value){
//    		 tmp = h1;
//    		 tmp.next = merge(h1.next,h2);
//    	 }
//    	 else{
//    		 tmp=h2;
//    		 tmp.next = merge(h1,h2.next);
//    	 }
//    	 return tmp;
//     }
     
     public Node merge(Node h1, Node h2){
    	 Node res = new Node(0), out = res;
    	 while(h1!=null || h2!=null){
    		 if (h1==null) {res.next = h2;break;}
    		 else if (h2==null) {res.next = h1;break;}
    		 else{
    			 if (h1.value<=h2.value){
    				 res.next = h1;
    				 h1 = h1.next;
    			 }
    			 else{
    				 res.next = h2;
    				 h2 = h2.next;
    			 }
    			 res = res.next;
    		 }
    	 }
    	 return out;
     }
  
     /* Drier program to test above functions*/
     public static void main(String[] args)
     {
         /* Start with empty list */
         LinkedList llist = new LinkedList();
  
         /* Use push() to construct below list
            1->12->1->4->1  */
         llist.push(8);
         llist.push(4);
         llist.push(5);
         llist.push(12);
         llist.push(1);
 //        llist.printl(llist.head);
  
         /* Check the count function */
         //System.out.println("Element at index 3 is "+llist.getNth(3));
         llist.sort(llist.head);
         llist.printl(llist.head);
     }
}
