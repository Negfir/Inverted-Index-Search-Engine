/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author applenegin
 */
class LinkedHashEntry 
{
    String key;
    
    LinkedHashEntry next;
    linkList linkedList;
 

    LinkedHashEntry(String key) 
    {
        this.key = key;
        
        this.next = null;
        this.linkedList=null;
    }
}