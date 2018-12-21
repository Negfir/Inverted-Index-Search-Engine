
   class TrieNode {
        TrieNode[] alphabets;
        char ch;
        String word;
        linkList linkedList;
        int child;
private static final int ASCII = 256;
        public TrieNode() {
            this.alphabets = new TrieNode[ASCII];
             child=0;
             word=null;
            linkedList=null;
            
        }

        public TrieNode (char ch) {
            this.alphabets = new TrieNode[ASCII];
            this.ch = ch;
            word=null;
            linkedList=null;
            child=0;
        }
    }