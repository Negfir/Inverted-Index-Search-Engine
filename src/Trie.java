
import java.io.File;
import java.util.ArrayList;

public class Trie {

    private static final int ASCII = 256;

    TrieNode root;
    static ArrayList<String> result = new ArrayList<String>();

    public Trie() {
        root = new TrieNode();
    }

    public void add(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();

        for (char c : ch) {
            if (node.alphabets[c] == null) {
                node.alphabets[c] = new TrieNode(c);
                node.child = node.child + 1;
            }
            node = node.alphabets[c];
        }
        node.word = word;
        //node.linkedList.insertAtEnd(file);
    }

    public void addLL(File file, String id) {
        if (!(getWord(id) == null)) {

            if (getWord(id).linkedList == null) {
                getWord(id).linkedList = new linkList();
                getWord(id).linkedList.insertAtEnd(file);
            } else {
                getWord(id).linkedList.insertAtEnd(file);
            }
        }
    }

    public TrieNode getWord(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();

        for (char c : word.toCharArray()) {
            node = node.alphabets[c];
            if (node == null) {
                System.out.println("Trie.getWord()");
                return null;
            }
        }

        return node;
    }

    /**
     * Deletes all its children, but does not delete itself.
     */
    public void prune(String string) {
        TrieNode node = root;
        char[] ch = string.toCharArray();

        for (char c : ch) {
            node = node.alphabets[c];
            if (node == null) {
                return;
            }
        }
        node.alphabets = new TrieNode[ASCII];
        return;
    }

    public void remove(String word) {
        if (getWord(word) == null) {
            //System.out.println(word +" does not exist in trie\n");
            return;
        }
        System.out.println("Trie.remove()");
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode childd = current.alphabets[ch];

            if (childd.child == -1) {
                current.alphabets[ch] = null;
            } else {
                current.child -= 1;

            }
            current = childd;
        }

        if (current == getWord(word)) {

            current.word = null;
        }
        return;

        //current.word = null;
    }

    public ArrayList<String> print() {
        result.clear();
        return printWhole(root);
    }

    private ArrayList<String> printWhole(TrieNode node) {
        String s;
        if (node == null) {
            return null;
        }
        if (node.word != null) {
            s = "|" + node.word + "->";
            if (!(node.linkedList == null)) {
                s = s.concat(" " + node.linkedList.display());
            }
            result.add(s);
        }
        for (int i = 0; i < ASCII; i++) {
            printWhole(node.alphabets[i]);
        }
        return result;
    }
    
    public void deleteFile(File file){
        deleteFile(file, root);
    }
    
      private void deleteFile(File file,TrieNode node) {
        
        if (node == null) {
            return;
        }
        
        if (node.word != null) {
            node.linkedList.delete(file);
            // System.out.println(file.getName());
        }
        
       
        for (int i = 0; i < ASCII; i++) {
            if (node.alphabets[i]!=null)
            deleteFile(file,node.alphabets[i]);
        }
        return;
    }
    
}
