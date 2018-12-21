

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 *
 * @author Negin Firouzian
 */
public class main {

    public static void main(String[] args) throws FileNotFoundException {
        
            //         try {
//           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//       } catch (Exception ex) {
//           System.err.println(ex.getMessage());
//}


MyFrame frame = new MyFrame();
//frame.buidTree();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(600, 700);
frame.setVisible(true);

//      LList l= new LList();
//      l.add("3");
//      l.add("6");
//      l.add("8");
//      l.add("9");
//      l.add("10");
//      l.delete("10");
//      l.print();


//       BST b=new BST();
//       b.insert("salam");
//       b.insert("hi");
//      b.insert("salam");
//       b.insert("jkjk");
//      b.insert("hiiii");
//      b.insert("sajam");
//     // b.delete("hi");
//      // b.delete("hiiiI");
////    
//       b.addLL("fgsd","hi");
//       b.addLL("kkkk","hiiii");
//       b.addLL("hhh", "hi");
//       //b.find("salam").linkedList.print();
//       //b.find("hi").linkedList.print();
//       ArrayList<String> result = result=b.print();
//      
//      for(String s: result ){
//          System.out.println(s);
//      }


//File f= new File("/Users/applenegin/Documents/Semester3/DS/docs/d1.txt");
//

//    Scanner sc2 = null;
//    try {
//        sc2 = new Scanner(new File("/Users/applenegin/Documents/Semester3/DS/docs/d1.txt"));
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();  
//    }
//    while (sc2.hasNextLine()) {
//            Scanner s2 = new Scanner(sc2.nextLine());
//        while (s2.hasNext()) {
//            String s = s2.next();
//            if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length()<2))
//            System.out.println(s+".");
//        }
//    }
    
//    Scanner scanner = null;
//Pattern pattern=Pattern.compile("[^a-zA-Z0]|[^\']");
//String word;
//
//try{
//    scanner=new Scanner(f);
//}catch(FileNotFoundException e){
//    System.out.println("Can't Find the File in Dictionary class!");
//}
////time=System.nanoTime();
//while(scanner.hasNext()){
//    scanner.useDelimiter(pattern);
//    word=scanner.next();
//    System.out.println(word);
//    if(!word.equals("")){
//        System.out.println(word);
//    }
//}
       
//      Scanner scanInput = new Scanner(new FileReader(f));
//		ArrayList<String> words = new ArrayList<String>();
//		String theWord;
//		String next;
//		int parCount =1;
//		int lineCount =1;
//		while (scanInput.hasNext()){
//			next = scanInput.next();
//			String[] tokens = next.split("[^a-zA-Z0-9\\-\']");
//			for (int i = 0; i < tokens.length; i++){
//				theWord = new String(tokens[i]);
//                                if (!(theWord.contains("'")) && theWord.length()>=2)
//				words.add(theWord);
//			}
//			
//			
//		}
//		
//		for(String w: words){
//			System.out.println(w);
//                        
//		}
            
  
               
//      ArrayList<String> stopWords = new ArrayList<String>();
//        File file = new File("StopWords.txt");
//        try {
//            
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                stopWords.add(line);
//               // System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        
//         for(String w: stopWords){
//			System.out.println(w);
//                       
//		}

//TST t=new TST();
//t.insert("hi");
//t.insert("salam");
//t.insert("hi");
//t.insert("bye");
//t.insert("hey");
//t.insert("hii");
//t.insert("come");
//t.insert("comepeny");
//t.insert("hii");

//t.delete("hi");
//System.out.println(t.search("hi").linkedList.display());
//t.toString();
       // System.out.println(t.toString());
       
//                for(String w: t.print()){
//			System.out.println(w);
//                       
//		}

//Trie e= new Trie();
//e.add("hi");
//e.add("salam");
//e.add("aaaabb");
//e.add("aaaabc");
//e.add("ccccc");
//e.add("alam");
//e.add("baasd");
//e.add("wwwwww");
//e.add("waaaa");
//e.addLL("sssssss", "hi");
//e.addLL("aaaaaa", "hi");
//e.remove("aaaa");
//e.remove("aaaab");
//
//e.remove("waaaa");
//  for(String w: e.print()){
//			System.out.println(w);
//                       
//		}
//
//    

//MyStack s =new MyStack(10);
//s.push("ffff");
//s.push("wkjbfkwqjb");
//s.push("44444");
//s.push("dddddddddd");
//while(!(s.isEmpty())){
//    System.out.println(s.pop()); 
//}

File f= new File("/Users/applenegin/Documents/Semester3/DS/docs/d1.txt");
File f1= new File("/Users/applenegin/Documents/Semester3/DS/docs/d2.txt");
File f3= new File("/Users/applenegin/Documents/Semester3/DS/docs/d100.txt");
File f4= new File("/Users/applenegin/Documents/Semester3/DS/docs/d900.txt");
linkList l=new linkList();
l.insertAtEnd(f4);
l.insertAtEnd(f3);
l.insertAtEnd(f1);
//if(l.searchFile(f1)=)
System.out.println(l.isinList(f1));
l.insertAtEnd(f1);
l.insertAtEnd(f);

        System.out.println(l.size);
//l.delete(f1);
for (int i=1; i<=l.size;i++){
        System.out.println(l.FindAtPos(i).getData().getName());}


//String s = "I want to walk my dog";
//
// String[] arr = s.split(" ");    
//
// for ( String ss : arr) {
//
//       System.out.println(ss);
//  }

 Hash map = new Hash(99);
        map.insert("egggg");
        map.insert("hiiii");
        map.insert("hello");
        map.insert("go");
        map.insert("go");
        map.insert("bye");
        map.addLL(f4, "bye");
        System.out.println(map.get("hello"));
        System.out.println(map.get("go"));
        System.out.println(map.getSize());
        map.remove("pp");
       
                        for(String w: map.printHashTable()){
			System.out.println(w);
                       
		}

   }
    
            
  

}
     

