
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Hash {

    private int TABLE_SIZE;
    private int size;
    private LinkedHashEntry[] table;
    static ArrayList<String> result = new ArrayList<String>();

    /* Constructor */
    public Hash(int ts) {
        size = 0;
        TABLE_SIZE = ts;
        table = new LinkedHashEntry[300];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = null;
        }
    }

    /* Function to get number of key-value pairs */
    public int getSize() {
        return size;
    }

    /* Function to clear hash table */
    public void makeEmpty() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = null;
        }
    }

    /* Function to get value of a key */
    public LinkedHashEntry get(String key) {
        int hash = (hashFunction(key) % TABLE_SIZE);
        if (table[hash] == null) {
            return null;
        } else {
            LinkedHashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            if (entry == null) {
                return null;
            } else {
                return entry;
            }
        }
    }

    /* Function to insert a key value pair */
    public void insert(String key) {
        int a = hashFunction(key);

        int hash = a % TABLE_SIZE;
        hash = Math.abs(hash);
        System.out.println(hash);

        if (table[Math.abs(hash)] == null) {
            table[hash] = new LinkedHashEntry(key);
        } else {
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            if (!entry.key.equals(key)) {
                entry.next = new LinkedHashEntry(key);
                size++;
            }
        }

    }

    public void remove(String key) {
        int hash = (hashFunction(key) % TABLE_SIZE);
        if (table[hash] != null) {
            LinkedHashEntry prevEntry = null;
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) {
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                if (prevEntry == null) {
                    table[hash] = entry.next;
                } else {
                    prevEntry.next = entry.next;
                }
                size--;
            }
        }
    }

    /* Function myhash which gives a hash value for a given string */
    private int hashFunction(String x) {
        int hash = 7;
        for (int i = 0; i < x.length(); i++) {
            hash = hash * 31 + x.charAt(i);

        }
        //System.out.println(hash + "--");
        return Math.abs(hash);

    }

    /* Function to print hash table */
    public ArrayList<String> printHashTable() {
        result.clear();
        String s = null;
        for (int i = 0; i < TABLE_SIZE; i++) {

            LinkedHashEntry entry = table[i];
            while (entry != null) {
                s = "|" + entry.key + "->" + " ";

                if (!(entry.linkedList == null)) {
                    s = s.concat(get(entry.key).linkedList.display());
                }

                result.add(s);
                s = null;
                entry = entry.next;
            }
        }
        return result;
    }

    public void addLL(File file, String id) {
        if (!(get(id) == null)) {

            if (get(id).linkedList == null) {
                get(id).linkedList = new linkList();
                get(id).linkedList.insertAtEnd(file);
            } else {
                get(id).linkedList.insertAtEnd(file);
            }
        }
    }

    public void deleteFile(File file) {

        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                LinkedHashEntry entry = table[i];
                while (entry != null) {
                   entry.linkedList.delete(file);
                   entry=entry.next;
                }
            }
        }
    }
}
