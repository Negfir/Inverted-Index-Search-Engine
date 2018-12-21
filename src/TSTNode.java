/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class TSTNode
{
    char data;
    boolean isEnd;
    TSTNode left, middle, right;
     linkList linkedList;
 
    public TSTNode(char data)
    {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.linkedList=null;
    }        
}
