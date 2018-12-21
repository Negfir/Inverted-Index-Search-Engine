
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author applenegin
 */
class Nodee
{
     File data;
     Nodee link;
     int count;
 
    /*  Constructor  */
    public Nodee()
    {
        link = null;
        data = null;
    }    
    /*  Constructor  */
    public Nodee(File d,Nodee n)
    {
        data = d;
        link = n;
    }    
    /*  Function to set link to next Node  */
    public void setLink(Nodee n)
    {
        link = n;
    }    
    /*  Function to set data to current Node  */
    public void setData(File d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public Nodee getLink()
    {
        return link;
    }    
    /*  Function to get data from current Node  */
    public File getData()
    {
        return data;
    }
}