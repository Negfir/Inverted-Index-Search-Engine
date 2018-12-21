
import java.io.File;


 

class linkList
{
    protected Nodee start;
    protected Nodee end ;
    public int size ;
 
    /*  Constructor  */
    public linkList()
    {
        start = null;
        end = null;
        size = 0;
    }
    /*  Function to check if list is empty  */
    public boolean isEmpty()
    {
        return start == null;
    }
    /*  Function to get size of list  */
    public int getSize()
    {
        return size;
    }    
    /*  Function to insert an element at begining  */
    public void insertAtStart(File val)
    {
        Nodee nptr = new Nodee(val, null);    
        size++ ;    
        if(start == null) 
        {
            start = nptr;
            end = start;
        }
        else 
        {
            nptr.setLink(start);
            start = nptr;
        }
    }
    /*  Function to insert an element at end  */
//    public void insert(File val){
//        Nodee n=start;
//        
//        
//    }
    
    public void insertAtEnd(File val)
    {
       // if (isinList(val)){
        Nodee nptr = new Nodee(val,null);    
           
        if(start == null) 
        {
            start = nptr;
            end = start;
            size++ ; 
        }
        else 
           
        {
            if (!(isinList(val))){
            end.setLink(nptr);
            end = nptr;
            size++ ; }
        }
    }
    /*  Function to insert an element at position  */
    public void insertAtPos(File val , int pos)
    {
        Nodee nptr = new Nodee(val, null);                
        Nodee ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size; i++) 
        {
            if (i == pos) 
            {
                Nodee tmp = ptr.getLink() ;
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++ ;
    }
    /*  Function to delete an element at position  */
    public void deleteAtPos(int pos)
    {        
        if (pos == 1) 
        {
            start = start.getLink();
            size--; 
            return ;
        }
        if (pos == size) 
        {
            Nodee s = start;
            Nodee t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
        }
        Nodee ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == pos) 
            {
                Nodee tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size-- ;
    }    
    /*  Function to display elements  */
    public String display()
    {
        String s = null;
        File f=null;
        //System.out.print("\nSingly Linked List = ");
        if (size == 0) 
        {
           // System.out.print("empty\n");
            
            return null;
        }    
        if (start.getLink() == null) 
        {
            //System.out.print(start.getData() );
            f=start.getData();
            s=f.getName();
            return s;
        }
        Nodee ptr = start;
        //System.out.print(start.getData()+ " ");
       // s=f.getName();
        s=start.getData().getName()+ " ,";
        ptr = start.getLink();
        while (ptr.getLink() != null)
        {
            //System.out.print(ptr.getData()+ " ");
           s= s.concat(ptr.getData().getName()+ " ,");
            ptr = ptr.getLink();
        }
        //System.out.print(ptr.getData()+ " ");
        s=s.concat(ptr.getData().getName()+ " ,");
        
        return s;
    }
    
    public Nodee searchFile(File f){
        Nodee ptr = start;
           if (size>0){
        if (start.getLink() == null) 
        {
            //System.out.print(start.getData() );
          
            return start;
        }
        
          while (!( ptr.getData().equals(f)))
        {           
            ptr = ptr.getLink();
        }
        return ptr;
           }
           return null;
        
    }
    
    public boolean isinList (File f){
        if (start==null){
            return true;
        }
        Nodee n=start;
        while (n!=null){
            if (n.getData().equals(f)){
                return true;
            }
            n=n.link;
            
        }
        return false;
    }
    
//        public boolean searchtrue(File f){
//        Nodee ptr = start;
//           if (size>0){
//       
//        
//          while (!( ptr.getData().equals(f)))
//        {           
//            ptr = ptr.getLink();
//        }
//        return true;
//           }
//           return null;
//        
//    }
    
    public void delete (File f){
          if (size == 0) 
        {
           
            return;
        } 
          if (start.data.equals(f)) 
        {
            start = start.getLink();
            size--; 
           
            return ;
        }
          if(end.data.equals(f)){
              Nodee s = start;
            Nodee t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
          }
          
          Nodee ptr = start;
        
        while (!(ptr.equals(end)))
        {
            if (ptr.getLink().getData().equals(f)) 
            {
                Nodee tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size--;

    
        
    }
    
    
        public Nodee FindAtPos(int pos)
    {    
        int a=size;
        if (pos == 1) 
        {
            
             
            return start;
        }
        if (pos == size) 
        {
           
        
            return end;
        }
        Nodee ptr = start.getLink();
        pos = pos  ;
        for (int i = 2; i < a ; i++) 
        {
            if (i == pos) 
            {
              return ptr;  
            }
            ptr = ptr.getLink();
        }
        return null;
        
    } 
    
}