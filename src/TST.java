/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

class TST {

    private TSTNode root;
    private ArrayList<String> al;

    /**
     * Constructor *
     */
    public TST() {
        root = null;
    }

    /**
     * function to check if empty *
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * function to clear *
     */
    public void makeEmpty() {
        root = null;
    }

    
    
        private int height(TSTNode t) {
        if (t==null) return 0;
        return Math.max(height(t.left), height(t.right) + 1);
    }
    
    public int height (){
        return height(root);
    }
    
    
    public void insert(String word) {
        if (searchReturn(word.toString()) == false) {
            root = insert(root, word.toCharArray(), 0);
        }
    }

    /**
     * function to insert for a word *
     */
    public TSTNode insert(TSTNode r, char[] word, int ptr) {
        if (r == null) {
            r = new TSTNode(word[ptr]);
        }

        if (word[ptr] < r.data) {
            r.left = insert(r.left, word, ptr);
            if( height( r.left ) - height( r.right ) == 2 )
                 if( word[ptr]<r.left.data)
                     r = rotateWithLeftChild( r );
                 else
                     r = doubleWithLeftChild( r );
        } else if (word[ptr] > r.data) {
            r.right = insert(r.right, word, ptr);
            if( height( r.right ) - height( r.left ) == 2 )
                 if(word[ptr]>r.right.data)
                     r = rotateWithRightChild( r );
                 else
                     r = doubleWithRightChild( r );
            
            
        } else if (ptr + 1 < word.length) {
            r.middle = insert(r.middle, word, ptr + 1);
        } else {
            r.isEnd = true;
        }

        return r;
    }

    
    private TSTNode rotateWithLeftChild(TSTNode k2) {
        TSTNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        
        return k1;
    }

    /* Rotate binary tree node with right child */
    private TSTNode rotateWithRightChild(TSTNode k1) {
        TSTNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child
     */
    private TSTNode doubleWithLeftChild(TSTNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child
     */
    private TSTNode doubleWithRightChild(TSTNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    
    
    /**
     * function to delete a word *
     */
    public void delete(String word) {
        delete(root, word.toCharArray(), 0);
    }

    /**
     * function to delete a word *
     */
    private void delete(TSTNode r, char[] word, int ptr) {
        if (r == null) {
            return;
        }

        if (word[ptr] < r.data) {
            delete(r.left, word, ptr);
        } else if (word[ptr] > r.data) {
            delete(r.right, word, ptr);
        } else /**
         * to delete a word just make isEnd false *
         */
        {
            if (r.isEnd && ptr == word.length - 1) {
                r.isEnd = false;
            } else if (ptr + 1 < word.length) {
                delete(r.middle, word, ptr + 1);
            }
        }
    }

    /**
     * function to search for a word *
     */
    public TSTNode search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    /**
     * function to search for a word *
     */
    private TSTNode search(TSTNode r, char[] word, int ptr) {
        if (r == null) {
            return null;
        }

        if (word[ptr] < r.data) {
            return search(r.left, word, ptr);
        } else if (word[ptr] > r.data) {
            return search(r.right, word, ptr);
        } else if (r.isEnd && ptr == word.length - 1) {
            return r;
        } else if (ptr == word.length - 1) {
            return null;
        } else {
            return search(r.middle, word, ptr + 1);
        }
    }

    public boolean searchReturn(String word) {
        return searchReturn(root, word.toCharArray(), 0);
    }

    /**
     * function to search for a word *
     */
    private boolean searchReturn(TSTNode r, char[] word, int ptr) {
        if (r == null) {
            return false;
        }

        if (word[ptr] < r.data) {
            return searchReturn(r.left, word, ptr);
        } else if (word[ptr] > r.data) {
            return searchReturn(r.right, word, ptr);
        } else if (r.isEnd && ptr == word.length - 1) {
            return true;
        } else if (ptr == word.length - 1) {
            return false;
        } else {
            return searchReturn(r.middle, word, ptr + 1);
        }
    }

    public void addLL(File file, String id) {
        if (!(search(id) == null)) {

            if (search(id).linkedList == null) {
                search(id).linkedList = new linkList();
                search(id).linkedList.insertAtEnd(file);
            } else {
                search(id).linkedList.insertAtEnd(file);
            }
        }
    }

    /**
     * function to print tree *
     */
    public String toString() {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : " + al;
    }

    public ArrayList<String> print() {
        al = new ArrayList<String>();
        traverse(root, "");
        return al;
    }

    /**
     * function to traverse tree *
     */
    private void traverse(TSTNode r, String str) {
        if (r != null) {
            traverse(r.left, str);

            str = str + r.data;
            if (r.isEnd) {
                if (!(search(str) == null)) {
                    if (!(search(str).linkedList == null)) {
                        str = str.concat("-> " + search(str).linkedList.display());
                    }
                }
                al.add(str);
            }

            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(r.right, str);
        }
    }
    
    public void deleteFile(File file){
        deleteFile(file, root);
    }

    private void deleteFile(File file, TSTNode r) {
        if (r != null) {
            deleteFile(file,r.left);
            if (r.isEnd)
            r.linkedList.delete(file);

            deleteFile(file,r.middle);
 if (r.isEnd)
            r.linkedList.delete(file);
            deleteFile(file,r.right);
        }
    }
}

