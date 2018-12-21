//public class Trie1 {
//	private TrieNode root; //The root node of the trie. It has no letter value; its child holds the first letter.	
//	private TrieNode cursor = null;
//	
//	/**
//	 * Class constructor. Creates a new trienode and stores it in root.
//	 */
//	public Trie1()
//	{
//		root = new TrieNode(); //Set root to a new TrieNode		
//	}
//	
//	/**
//	 * Setter method for cursor data member.
//	 *  
//	 * @param cursor 	the new value of the cursor data member.
//	 */
//	public void setCursor(TrieNode cursor){this.cursor = cursor;}	
//	
//	/**
//	 * Getter method for the cursor data member.
//	 *  
//	 * @return	the value of the cursor data member.
//	 */
//	public TrieNode getCursor() {return this.cursor;}
//		
//	/**
//	 * Starting at 'node', searches 'node' and its siblings for one whose letter matches 'letter.' 
//	 * If letter is found, returns the node whose letter is 'letter.' Otherwise returns null.
//	 * 
//	 * @param node		the TrieNode to start the search. If null, method returns null. 
//	 * @param letter 	the letter being searched for.
//	 * @return 			the TrieNode whose letter matches the letter param.
//	 */
//	private TrieNode findLetter(TrieNode node, char letter)
//	{
//		TrieNode result = null;							//Initialize return variable.
//		if (node == null) return null;					//Check to make sure node is not null 
//		if (letter < node.getLetter()) return null;		//If letter is < node.getLetter it means it won't be found.
//		if (letter == node.getLetter()) return node;	//Letter is found, so return node.		
//		//if (node.getSibling() != null) 					 
//		result = findLetter(node.getSibling(), letter);	//Recursive call to find the letter using the sibling of node.
//		
//		return result;									//Return the node.
//	}
//	
//	/**
//	 * Inserts 'word' into the trie.
//	 * 
//	 * @param word	the word to be inserted into the trie.
//	 */
//	public void insert(String word)
//	{
//		TrieNode cursor = root;													//Starting node
//		for(int i = 0; i < word.length(); i++)									//Loop through all the letters of word
//		{
//			TrieNode newLetter = findLetter(cursor.getChild(), word.charAt(i));	//Look for the character in the child of the cursor
//			if(newLetter != null)												//Letter was found so move cursor to the found node.
//				cursor = newLetter;
//			else																//Letter was not found. Several cases to examine.
//			{
//				if(cursor.getChild() != null) 									//If child is null then the new letter can simply be added. 
//				{         					  									//Otherwise, the letter needs to be inserted into the child's list.
//					if(word.charAt(i) > cursor.getChild().getLetter())			//If letter is > then cursor.child's letter, call inserAfter to add the new letter somewhere after the first letter of the list
//						cursor = insertAfter(cursor.getChild(), word.charAt(i));//Add new node and advance cursor.
//					else if (word.charAt(i) < cursor.getChild().getLetter())	//If letter is < cursor.child's letter then call insertFirst, as it should be the new first node in the list.
//						cursor = insertFirst(cursor, word.charAt(i));			//Add new node and advance cursor.
//				}
//				else															//Add a new node with the new letter, and then advance cursor.
//				{
//					TrieNode newNode = new TrieNode(word.charAt(i), null, new TrieNode(cursor, null));;
//					cursor.setChild(newNode);
//					cursor = cursor.getChild();
//				}
//			}
//			if (i == word.length() - 1) cursor.setStartingPosition(true);		//Check to see if adding the first letter of the word. If so, set the starting position to be true.
//		}
//	}
//	
//	/**
//	 * Deletes theWord from the trie. If all nodes (of theWord) can be removed, all nodes are removed. If not, those
//	 * that can are, and those that can't (because they are parts of other words) remain. If no nodes can be removed
//	 * then the startingPosition value is changed from true to false.
//	 * 
//	 * @param theWord	the word to be deleted from the trie. Unlike other methods, theWord is the actual word and 
//	 * 					not theWord reversed.
//	 */
//	public void delete(String theWord)
//	{					
//		String word = theWord;
//		if(find(reverseString(word)))
//		{
//			TrieNode node = findString(reverseString(word));						//Reverse the string
//			while(node != root)
//			{
//				if(node.getChild() != null)											//If node has a child
//				{
//					if(theWord.compareTo(word) == 0)								//If theWord and word match, then this is the first iteration and theWord is a 
//						node.setStartingPosition(false);							//substring of another word in the trie, so just set the startingPosition to be false.
//					return;
//				}
//				else																//theWord was found and is doesn't have a child, so it is not the substring of another word.
//				{					
//					if(node.isStartingPosition() && theWord.compareTo(word) != 0)	//If a letter has already been removed and its remaining letters
//						return;														//are a valid word then nothing should be done so return.
//					TrieNode parent = getParent(node);								//Get parent of node.
//					if(parent.getChild() == node)									//Is node the first child of parent?
//						removeFirstLetter(parent);									//If so, delete the first child by calling method deleteFirstLetter
//					else
//						removeLetter(parent.getChild(), word.charAt(0));			//Otherwise, letter is somewhere else in the child list of parent, so call removeLetter
//				}
//				word = word.substring(1);											//Decrease word length by one.
//				if(word.length() > 0)												//Make sure word still has letters.
//					node = findLetter(getParent(node), word.charAt(0));				//Find the next letter.
//				else
//					node = getParent(node);											//Otherwise set node to be parent, which will eventually set node to root which will cancel the while loop 
//			}
//		}				
//	}
//	
//	/**
//	 * Returns the parent of node.
//	 * 
//	 * @param node	the node whose parent is desired.
//	 * @return	the parent of node.
//	 */
//	private TrieNode getParent(TrieNode node)
//	{
//		if(node == root) return null;
//		while(node.getSibling() != null)	//Null sibling indicates the last node.
//			node = node.getSibling();
//		return node.getChild();				//The last sibling node's child is the parent of that node (and its siblings.)
//	}
//	
//	/**
//	 * Removes the child node of parent by replacing it with child's sibling. If the sibling is the last node marker
//	 * then the parent's child is set to null.
//	 * 
//	 * @param parent	the parent whose child is to be removed.
//	 */
//	private void removeFirstLetter(TrieNode parent)
//	{
//		if(parent.getChild().getSibling().getSibling() == null)	//Sibling is last node marker, so set child to null.
//			parent.setChild(null);
//		else
//			parent.setChild(parent.getChild().getSibling());	//Sibling exists, so set child to be the sibling of the current child.
//	}
//	
//	/**
//	 * Removes a node whose letter data member matches the letter parameter. Starts search at node's sibling and 
//	 * procedes to traverse the siblings in pursuit of the desired node. The node with the target letter is removed
//	 * by excluding it from the list; the node before target node has its sibling point to target nodes sibling, which
//	 * removes it from the list/trie. 
//	 * 
//	 * @param node		the node to start searching from.
//	 * @param letter	the letter to be deleted.
//	 */
//	private void removeLetter(TrieNode node, char letter)
//	{
//		while(node.getSibling() != null && letter > node.getSibling().getLetter())	//Search for letter, but stop at node before target node.
//			node = node.getSibling();		
//		node.setSibling(node.getSibling().getSibling());							//reset sibling to exclude the target node.
//	}
//	
//	/**
//	 * Inserts a node into sibling's list in the appropriate (alphabetical) order. The new letter must be
//	 * greater than sibling's letter, if it isn't then the method insertFirst should be used.
//	 * 
//	 * @param sibling	the node to start the search for the correct spot to add the new node.
//	 * @param letter	the new letter to be inserted.
//	 * @return			the newly created node.
//	 */
//	private TrieNode insertAfter(TrieNode sibling, char letter)
//	{
//		while(sibling.getSibling() != null && letter > sibling.getSibling().getLetter())	//Locate the node right before the desired position.
//			sibling = sibling.getSibling();
//		TrieNode newNode = new TrieNode(letter, null, sibling.getSibling());				//Create the new node whose sibling is the sibling of the "cursor" node.
//		sibling.setSibling(newNode);														//Insert the new node.
//		return newNode;		
//	}
//	
//	/**
//	 * Inserts a new node whose letter data member is the letter parameter as the child of the parent parameter.
//	 * If parent already has a child, this child is set to be the sibling of the new node.
//	 * 
//	 * @param parent	the parent of the new node.
//	 * @param letter	the new letter to be inserted.
//	 * @return			the newly created node.
//	 */
//	private TrieNode insertFirst(TrieNode parent, char letter)
//	{
//		TrieNode newNode = new TrieNode(letter, null, parent.getChild());	//Create a new node whose sibling is the child of parent.
//		parent.setChild(newNode);											//Set the child of parent to be the new node.
//		return newNode;														//Return the new node.
//	}
//	
//	/**
//	 * Reverses theString: input = "tree"; output = "eert"
//	 * 
//	 * @param theString		the string to be reversed.
//	 * @return				the reversed string.
//	 */
//	public String reverseString(String theString)
//	{
//		String temp = "";
//		for(int i = theString.length() - 1; i >= 0; i--)
//			temp += String.valueOf(theString.charAt(i));
//		
//		return temp;
//	}
//	
//	/**
//	 * Finds a string. If the string is not found null is returned, otherwise the node whose letter is the first letter of 
//	 * the the string is returned.  
//	 * 
//	 * @param word	the word to find.
//	 * @return		the node whose letter is the first letter of the word.
//	 */
//	public TrieNode findString(String theString)
//	{
//		TrieNode cursor = root;															//Start at root
//		
//		for(int i = 0; i < theString.length(); i++)										//Search for all letters of word
//		{
//			if((cursor = findLetter(cursor.getChild(), theString.charAt(i))) == null) 	//Find the current letter. If it's not there, return null.
//				return null;			
//		}	
//		return cursor;
//	}
//	
//	/**
//	 * Finds a word in the trie. 
//	 * 
//	 * @param word	the word to search for.
//	 * @return		true if the word exists in the trie, false otherwise.
//	 */
//	public boolean find(String word)
//	{
//		TrieNode temp = findString(word);
//		if (temp != null && temp.isStartingPosition())
//				return true;
//		return false;
//	}
//	
//	/**
//	 * Driver method for recursive printWord method.
//	 * 
//	 * @param numWordsToPrint	the number of words to print.
//	 */
//	public void printWords(int numWordsToPrint)
//	{	
//		printWord(numWordsToPrint, this.cursor);
//	}
//	
//	/**
//	 * Prints numWordsToPrint words starting from cursor. Method checks for children, then siblings.
//	 * 
//	 * @param numWordsToPrint	the number of words to print.
//	 * @param node				the node to start from.
//	 */
//	public void printWord(int numWordsToPrint, TrieNode node)
//	{		
//		if (numWordsToPrint > 0 && node != null)									//Make sure node != null and numWordsToPrint is > 0
//		{			
//			if(node.isStartingPosition())											//Check to see if the node is the beginning of a word
//			{
//				printWord(node);													//If so, print the word and...
//				numWordsToPrint--;													//decrement numWordsToPrint.				
//			}
//			
//			if(node.getChild() != null)												//Check for a child first.
//				printWord(numWordsToPrint, node.getChild());						//If child exists, call printWord with the child.
//			else if(node.getSibling().getLetter() != '~')							//Check the sibling	exists and isn't the end of list marker.
//				printWord(numWordsToPrint, node.getSibling());						//If sibling exists, call printWord with the sibling.
//			else																	//No child or sibling existed, what to do?
//			{
//				TrieNode parent = getParent(node);									//Get parent.
//				while(parent != root && parent.getSibling().getLetter() == '~')		//Make sure parent isn't root and parent's sibling isn't the EOL marker.
//					parent = getParent(parent);										
//				printWord(numWordsToPrint, parent.getSibling());					//Call printWord with parent's sibling.				
//			}
//		}
//		else
//		{
//			if(node == null && numWordsToPrint > 0)	//More words specified than exist. Since all
//			{
//				this.cursor = null;					//words were printed, reset the cursor.
//				System.out.println("No more words to print.\r");
//			}
//			else
//				this.cursor = node;
//		}
//	}
//	
//	/**
//	 * This method prints out a word whose first letter starts at node and traverses up 
//	 * the trie until it reaches root, printing out each visited nodes letter along the way.
//	 * 
//	 * @param node	the node whose letter is the first letter of the word to be printed.
//	 */
//	private void printWord(TrieNode node)
//	{
//		while(node != this.root)
//		{
//			System.out.print(node.getLetter());
//			node = getParent(node);			
//		}
//		System.out.println();
//	}
//}