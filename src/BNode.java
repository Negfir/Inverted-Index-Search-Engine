/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Negin Firouzian
 */
class BNode{
    
	String data;
	BNode left;
	BNode right;
       public boolean i;
         linkList linkedList;
	public BNode(String data){
		this.data = data;
		left = null;
		right = null;
                linkedList= null;
                i=false;
	}
}
