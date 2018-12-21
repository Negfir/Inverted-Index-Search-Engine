
import java.io.File;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Negin Firouzian
 */
public class BST {

    int s = 0;
    int cnt=0;
    public static BNode root;
    //linkList d;
    static ArrayList<String> result = new ArrayList<String>();
    //
   String k="BST: \n";
    public BST() {
        this.root = null;
    }

    public BNode find(String id) {
        BNode current = root;
        int s;
        while (current != null) {
            s = id.compareTo(current.data);
            if (s == 0) {
                return current;

            } else if (s < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

//    public LList creatLL (String id){
//        find(id).linkedList=new LList();
//        return find(id).linkedList;
//        
//    }
    public void addLL(File file, String id) {
        if (!(find(id).i)) {
            if (find(id).linkedList == null) {
                find(id).linkedList = new linkList();
                find(id).linkedList.insertAtEnd(file);
                //d.insertAtEnd(file);
            } else {
                find(id).linkedList.insertAtEnd(file);

                // d.insertAtEnd(file);
            }
        }

    }

    public boolean delete(String id) {
        BNode parent = root;
        BNode current = root;
        boolean isLeftChild = false;
        int s;

        while (!(current.data.equalsIgnoreCase(id))) {
            parent = current;
            s = id.compareTo(current.data);
            if (s < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                System.out.println("false");
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } //Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            //now we have found the minimum element in the right sub tree
            BNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        System.out.println("done");
        return true;
    }

    public BNode getSuccessor(BNode deleleNode) {
        BNode successsor = null;
        BNode successsorParent = null;
        BNode current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    private int height(BNode t) {
        if (t==null) return 0;
        return Math.max(height(t.left), height(t.right) + 1);
    }
    
    public int height (){
        return height(root);
    }
    
    public void insert(String s){
       root= insert(s,root);
    }

private BNode insert(String x, BNode t)
     {
         if (t == null)
             t = new BNode(x);
         else if (x.compareToIgnoreCase(t.data)<0)
         {
             t.left = insert( x, t.left );
             if( height( t.left ) - height( t.right ) == 2 )
                 if( x.compareToIgnoreCase(t.left.data) < 0 )
                     t = rotateWithLeftChild( t );
                 else
                     t = doubleWithLeftChild( t );
         }
         else if( x.compareToIgnoreCase(t.data) > 0 )
         {
             t.right = insert( x, t.right );
             if( height( t.right ) - height( t.left ) == 2 )
                 if( x.compareToIgnoreCase(t.right.data) > 0)
                     t = rotateWithRightChild( t );
                 else
                     t = doubleWithRightChild( t );
         }
         else
            return t;
        
         return t;
     }

    private BNode rotateWithLeftChild(BNode k2) {
        BNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        
        return k1;
    }

    /* Rotate binary tree node with right child */
    private BNode rotateWithRightChild(BNode k1) {
        BNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child
     */
    private BNode doubleWithLeftChild(BNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child
     */
    private BNode doubleWithRightChild(BNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public ArrayList<String> display(BNode root) {

        String s;
        if (root != null) {
            display(root.left);
            //System.out.print("|"+root.data+"->"+" ");

            s = "|" + root.data + "->" + " ";

            if (!(root.linkedList == null)) {
                s = s.concat(find(root.data).linkedList.display()+"\n");
                cnt++;
            }
            
            //System.out.println(s);
            result.add(s);
            
            // System.out.println("");
            display(root.right);
        }
//        for(String j: result ){
//          System.out.print(j+"\n");
//      }
        return result;
    }

    public ArrayList<String> print() {
        
        result.clear();
        
        return display(root);

    }

    public void deleteFile(File file) {
        deleteFile(file, root);
    }

    public void deleteFile(File file, BNode r) {

        if (r == null) {
            return;
        }
        deleteFile(file, r.left);

        r.linkedList.delete(file);

        //System.out.println(file.getName()+" "+r.data);
        deleteFile(file, r.right);

    }

    public void isContain(File file) {
        isContain(file, root);
    }

    public void isContain(File file, BNode r) {

        if (r == null) {
            return;
        }
        isContain(file, r.left);
        if ((r.linkedList.isinList(file))) {
            s = s + 1;
        }
        isContain(file, r.right);

    }

//    public ArrayList<String> list() {
//        listfile.clear();
//        listfile.add(">> list –l");
//
//        list(root);
//        return listfile;
//    }
//
//    public void list(BNode r) {
//
//        if (r == null) {
//            return;
//        }
//        if (r.left != null) {
//            list(r.left);
//        }
//        for (int i = 1; i <= r.linkedList.size; i++) {
//
//            if (!(listfile.isEmpty())) {
//                if (!(listfile.contains(r.linkedList.FindAtPos(s).getData().getName()))) {
//                    listfile.add(r.linkedList.FindAtPos(i).data.getName());
//
//                }
//            }
//        }
//        if (r.right != null) {
//            list(r.right);
//        }
//
//    }
}
