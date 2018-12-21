
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Negin Firouzian
 */
public class MyFrame extends JFrame {

    public static ArrayList<String> listfile;
    MyStack stack = new MyStack(80);

    BST bst;
    TST tst;
    Trie trie;
    Hash hash;

    // linkList l;
    JRadioButton tstbutton;
    JRadioButton bstbutton;
    JRadioButton triebutton;
    JRadioButton hashbutton;

    File file;
    JTextArea display = new JTextArea(20, 40);
    JTextField text;
    JTextField command;
    JButton enter;
    JButton build;
    JButton reset;
    JButton history;
    JButton exit;
    JButton add;
    JButton delete;
    JButton update;
    JButton listw;
    JButton listl;
    JButton listf;
    JButton searchs;
    JButton searchw;
    ArrayList<String> stopWords = new ArrayList<String>();
    private final JPanel buttonJPanel1;

    private final JButton browse;

    public MyFrame() {
        super("Inverted Index");

        text = new JTextField(20);
        text.setText("/Users/applenegin/Documents/Semester3/DS/docs");
        browse = new JButton("browse");
        text.setEditable(true);
        text.setBounds(100, 200, 300, 80);
        text.setVisible(true);
        setLayout(null);
        buttonJPanel1 = new JPanel();
        buttonJPanel1.add(text);
        buttonJPanel1.add(browse);
        buttonJPanel1.setBounds(0, 10, 500, 40);
        add(buttonJPanel1);

        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));
        display.setEditable(true);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        middlePanel.setBounds(10, 80, 530, 360);
        middlePanel.add(scroll);
        //middlePanel.add(display1);
        add(middlePanel);
        pack();

        //
        //radio buttons 
        //
        tstbutton = new JRadioButton("TST");
        bstbutton = new JRadioButton("BST");
        triebutton = new JRadioButton("Trie");
        hashbutton = new JRadioButton("Hash");
        JPanel radioPanel = new JPanel();
        radioPanel.setBounds(10, 440, 300, 30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(tstbutton);
        buttonGroup.add(bstbutton);
        buttonGroup.add(triebutton);
        buttonGroup.add(hashbutton);
        
        radioPanel.add(bstbutton);
        radioPanel.add(triebutton);
        radioPanel.add(tstbutton);
        radioPanel.add(hashbutton);
        add(radioPanel);

        //
        //command 
        //
        command = new JTextField(40);
        JPanel cmdPanel = new JPanel();
        cmdPanel.setBounds(10, 480, 500, 30);
        cmdPanel.add(command);
        add(cmdPanel);

        //
        //bottom buttons   
        //
        build = new JButton("Build");
        enter = new JButton("enter");
        reset = new JButton("Reset");
        history = new JButton("History");
        exit = new JButton("Exit");
        JPanel bPanel = new JPanel();
        bPanel.setBounds(10, 520, 500, 30);
        bPanel.add(build);
        bPanel.add(reset);
        bPanel.add(history);
        bPanel.add(exit);
        add(bPanel);

        //
        //command buttons
        //
        add = new JButton("Add");
        delete = new JButton("Delete");
        update = new JButton("Update");
        listf = new JButton("List -f");
        listl = new JButton("List -l");
        listw = new JButton("List -w");
        searchs = new JButton("Search -s");
        searchw = new JButton("Search -w");
        JPanel cmdbPanel = new JPanel();
        cmdbPanel.setBounds(10, 550, 500, 30);
        JPanel cmdb2Panel = new JPanel();
        cmdb2Panel.setBounds(10, 610, 500, 30);
        JPanel cmdb3Panel = new JPanel();
        cmdb3Panel.setBounds(10, 580, 500, 30);
        cmdbPanel.add(add);
        cmdbPanel.add(delete);
        cmdbPanel.add(update);
        cmdb2Panel.add(searchs);
        cmdb2Panel.add(searchw);
        cmdb3Panel.add(listf);
        cmdb3Panel.add(listl);
        cmdb3Panel.add(listw);
        add(cmdbPanel);
        add(cmdb2Panel);
        add(cmdb3Panel);

        //
        //action listeners 
        //
        browse.addActionListener(new BrowseHandler());
        build.addActionListener(new BuildHandler());
        reset.addActionListener(new ResetHandler());
        history.addActionListener(new HistoryHandler());
        exit.addActionListener(new ExitHandler());
        add.addActionListener(new AddHandler());
        delete.addActionListener(new DeleteHandler());
        update.addActionListener(new UpdateHandler());
        listf.addActionListener(new ListFHandler());
        listl.addActionListener(new listLHandler());
        listw.addActionListener(new listWHandler());
        searchw.addActionListener(new searchWHandler());
        searchs.addActionListener(new searchSHandler());
    }

    void buildBST() throws FileNotFoundException {
        TST b = new TST();
        listfile = new ArrayList<String>();
        listfile.add(">> list –l " + "\n");
        b = TstopWordCreat();
//        //stopWordCreat();
        File folder = new File(text.getText());
        File[] listOfFiles = folder.listFiles();
        bst = new BST();
        int a = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                int m = 0;
                Scanner sc2 = null;
                try {
                    sc2 = new Scanner(new File(file.getAbsolutePath())).useDelimiter(("[^A-Za-z]"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    //s2=s2
                    //s2.skip("[^a-zA-Z0-9']+");
                    while (s2.hasNext()) {

                      

                        String ss = s2.next();
                        ss = ss.toLowerCase();
                        String[] parts=ss.split("[^A-Za-z]");
                        for(String s: parts ){
                        if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s == null || s.equals("--") || s.equals(""))) {

//
                            if (b.search(s) == null) {
//
                                bst.insert(s);
//
                                bst.addLL(file, s);
                                if (!(listfile.contains(file.getName()))) {
                                    listfile.add(file.getName());
                                }
                                System.out.println("1.");
//
//                           

                                // System.out.println(s+a++);
                            }
                        }}
                    }
                    //}

                }

            }

        }

        //bst.delete("ABOUT");
        //bst.print();
    }

    void buildTST() throws FileNotFoundException {
        TST b = new TST();
        listfile = new ArrayList<String>();
        listfile.add(">> list –l " + "\n");

        b = TstopWordCreat();
        stopWordCreat();
        File folder = new File(text.getText());
        File[] listOfFiles = folder.listFiles();
        tst = new TST();
        int a;
        for (File file : listOfFiles) {
            if (file.isFile()) {

                Scanner sc2 = null;
                try {
                    sc2 = new Scanner(new File(file.getAbsolutePath())).useDelimiter(("[^A-Za-z]"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while (s2.hasNext()) {

                        String ss = s2.next();
                        ss = ss.toLowerCase();
                        String[] parts=ss.split("[^A-Za-z]");
                        for(String s: parts ){
                        a = 0;
                        if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s == null || s == "--" || s == " ")) {

                            if (b.search(s) == null) {
                                tst.insert(s);
                                tst.addLL(file, s);
                                if (!(listfile.contains(file.getName()))) {
                                    listfile.add(file.getName());
                                }

                                System.out.println("1.");

                            }
                        }
                        }
                    }

                }

            }

        }

//          for (String w : stopWords) {
//            bst.delete(w);
//            System.out.println("5");
//
//        }
        tst.delete("--");
        //bst.delete("ABOUT");
//     
        // System.out.println(tst.toString());

    }

    void buildTrie() throws FileNotFoundException {
        TST b = new TST();
        listfile = new ArrayList<String>();
        listfile.add(">> list –l " + "\n");

        b = TstopWordCreat();
        File folder = new File(text.getText());
        File[] listOfFiles = folder.listFiles();
        trie = new Trie();
        int a;
        for (File file : listOfFiles) {
            if (file.isFile()) {

                Scanner sc2 = null;
                try {
                    sc2 = new Scanner(new File(file.getAbsolutePath())).useDelimiter(("[^A-Za-z]"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while (s2.hasNext()) {

                        String ss = s2.next();
                        ss = ss.toLowerCase();
                        String[] parts=ss.split("[^A-Za-z]");
                        for(String s: parts ){
                        if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s.isEmpty() || s.equals("--") || s == " ")) {
                            if (b.search(s) == null) {
                                trie.add(s);
                                //trie.add("aaaaaaa");
                                trie.addLL(file, s);
                                if (!(listfile.contains(file.getName()))) {
                                    listfile.add(file.getName());
                                }
                                System.out.println("1.");
                            }
                            // }
                        }}

                    }

                }

            }

        }

        trie.remove("--");
        //bst.delete("ABOUT");
//        for (String w : stopWords) {
//            trie.remove(w);
//            System.out.println("5");
//
//        }

    }

    void buildHash() throws FileNotFoundException {
        TST b = new TST();
        listfile = new ArrayList<String>();
        listfile.add(">> list –l " + "\n");

        b = TstopWordCreat();
        File folder = new File(text.getText());
        File[] listOfFiles = folder.listFiles();
        hash = new Hash(200);
        int a;
        for (File file : listOfFiles) {
            if (file.isFile()) {

                Scanner sc2 = null;
                try {
                    sc2 = new Scanner(new File(file.getAbsolutePath())).useDelimiter(("[^A-Za-z]"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while (s2.hasNext()) {

                        String ss = s2.next();
                        ss = ss.toLowerCase();
                        String[] parts=ss.split("[^A-Za-z]");
                        for(String s: parts ){
                        if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s == null || s == "--" || s == " ")) {
                            if (b.search(s) == null) {
                                hash.insert(s);
                                //trie.add("aaaaaaa");
                                hash.addLL(file, s);
                                if (!(listfile.contains(file.getName()))) {
                                    listfile.add(file.getName());
                                }
                                System.out.println("1.");
                            }
                            // }
                        }

                    }
                    }
                }

            }

        }

        hash.remove("--");
        //bst.delete("ABOUT");
//        for (String w : stopWords) {
//            trie.remove(w);
//            System.out.println("5");
//
//        }

    }

    BST stopWordCreat() {
        BST b = new BST();

        File file = new File("StopWords.txt");
        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.next();
                stopWords.add(line);
                b.insert(line);
                // System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//         for (String w : stopWords) {
//          
//             System.out.println(w+",");
//
//        }
        return b;
    }

    TST TstopWordCreat() {
        TST b = new TST();

        File file = new File("StopWords.txt");
        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.next();
                stopWords.add(line);
                b.insert(line);
                // System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//         for (String w : stopWords) {
//          
//             System.out.println(w+",");
//
//        }
        return b;
    }

    private class BrowseHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

//           JPanel p=new JPanel();
//           GridLayout.add(p);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnVal = fileChooser.showOpenDialog(MyFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) //System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
            {
                text.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }

        }
    }

    private class BuildHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            if (bstbutton.isSelected()) {

                display.removeAll();
                try {
                    long startTime = System.currentTimeMillis();
                    buildBST();

                    long estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println(estimatedTime);
                    //bst.delete("--");

                } catch (FileNotFoundException | NullPointerException ex) {
                    System.out.println("err");
                }
                System.out.println("|||||||" + bst.height());
                //ArrayList<String> result = result = bst.print();
                display.setText("BST:" + "\n");

                for (String s : bst.print()) {
                    display.setText(display.getText() + s + "\n");

                }

                // System.out.println(bst.print().size());
                display.setText(display.getText() + bst.print().size() + "\n");
                display.setText(display.getText() + "Depth= " + bst.height() + "\n");
                stack.push(display.getText());

            } else if (tstbutton.isSelected()) {
                display.removeAll();
                try {
                    long startTime = System.currentTimeMillis();
                    buildTST();
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println(estimatedTime);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("|||" + tst.height());
                display.setText("TST:" + "\n");
                for (String s : tst.print()) {
                    display.setText(display.getText() + s + "\n");
                }
                System.out.println(tst.print().size());
                display.setText(display.getText() + tst.print().size() + "\n");
                display.setText(display.getText() + "Depth= " + tst.height() + "\n");
                stack.push(display.getText());

            } else if (triebutton.isSelected()) {
                display.removeAll();
                try {

                    long startTime = System.currentTimeMillis();
                    buildTrie();
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println(estimatedTime);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                display.setText("Trie:" + "\n");
                for (String s : trie.print()) {
                    display.setText(display.getText() + s + "\n");

                }
                stack.push(display.getText());
                //display.getText()

                System.out.println(trie.print().size());
                display.setText(display.getText() + (trie.print().size()) + "\n");

            } else if (hashbutton.isSelected()) {
                display.removeAll();
                try {

                    long startTime = System.currentTimeMillis();
                    buildHash();
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println(estimatedTime);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                display.setText("Hash:" + "\n");
                for (String s : hash.printHashTable()) {
                    display.setText(display.getText() + s + "\n");

                }
                stack.push(display.getText());
                //display.getText()

                System.out.println(hash.printHashTable().size());
                display.setText(display.getText() + (hash.printHashTable().size()) + "\n");

            }

        }

    }

    private class ResetHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            bst = null;
            tst = null;
            trie = null;
            hash = null;
            listfile.clear();
            listfile = new ArrayList<String>();
            listfile.add(">> list –l " + "\n");
            display.removeAll();

        }
    }

    private class HistoryHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            display.removeAll();
            while (!(stack.isEmpty())) {
                display.setText(display.getText() + stack.pop() + "\n");
            }

        }
    }

    private class ExitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);

        }
    }

    private class AddHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int a = 0;
            TST b = new TST();
            b = TstopWordCreat();
            File add;
            add = new File(text.getText() + "/" + command.getText() + ".txt");
            if (add.isFile()) {

                Scanner sc2 = null;
                try {
                    sc2 = new Scanner(new File(add.getAbsolutePath()));
                } catch (FileNotFoundException r) {
                    r.printStackTrace();
                    a = 0;
                }

                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while (s2.hasNext()) {

                        String s = s2.next();
                        s = s.toLowerCase();

                        if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s == null || s == "--" || s == " ")) {

                            if (b.search(s) == null) {
                                a = 1;

                                if (bstbutton.isSelected()) {
                                    if (!(bst.find(s).linkedList.isinList(add))) {
                                        a = 2;
                                    }
                                    long startTime = System.nanoTime();
                                    bst.insert(s);
                                    bst.addLL(add, s);
                                    long estimatedTime = System.nanoTime() - startTime;
                                    System.out.println(estimatedTime);
                                } else if (tstbutton.isSelected()) {
                                    if (!(tst.search(s).linkedList.isinList(add))) {
                                        a = 2;
                                    }
                                    long startTime = System.nanoTime();
                                    tst.insert(s);
                                    tst.addLL(add, s);
                                    long estimatedTime = System.nanoTime() - startTime;
                                    System.out.println(estimatedTime);

                                } else if (triebutton.isSelected()) {
                                    if (!(trie.getWord(s).linkedList.isinList(add))) {
                                        a = 2;
                                    }
                                    long startTime = System.nanoTime();
                                    trie.add(s);
                                    trie.addLL(add, s);
                                    long estimatedTime = System.nanoTime() - startTime;
                                    System.out.println(estimatedTime);

                                } else if (hashbutton.isSelected()) {
                                    if (!(hash.get(s).linkedList.isinList(add))) {
                                        a = 2;
                                    }
                                    long startTime = System.nanoTime();
                                    hash.insert(s);
                                    hash.addLL(add, s);
                                    long estimatedTime = System.nanoTime() - startTime;
                                    System.out.println(estimatedTime);

                                }
                                if (!(listfile.contains(add.getName()))) {
                                    listfile.add(add.getName());
                                }
                                System.out.println("1.");

                            }
                        }

                    }

                }

            }
            display.removeAll();
            display.setText(">> add " + add.getName() + "\n");
            if (a == 0) {
                display.setText(display.getText() + "err: document not found." + "\n");
            } else if (a == 1) {
                display.setText(display.getText() + "err: already exists, you may want to update." + "\n");
            } else {
                display.setText(display.getText() + add.getName() + " successfully added." + "\n");
            }
            stack.push(display.getText());
        }

    }

    private class DeleteHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            File f = new File(text.getText() + "/" + command.getText() + ".txt");
            display.removeAll();
            display.setText(">> del " + f.getName() + "\n");

            if (bstbutton.isSelected()) {
                long startTime = System.nanoTime();
                bst.deleteFile(f);
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
                System.out.println(bst.find("come").linkedList.display());
            } else if (triebutton.isSelected()) {
                long startTime = System.nanoTime();
                tst.deleteFile(f);
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
                System.out.println(trie.getWord("come").linkedList.display());
            } else if (tstbutton.isSelected()) {
                long startTime = System.nanoTime();
                trie.deleteFile(f);
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
                System.out.println(tst.search("come").linkedList.display());
            } else if (hashbutton.isSelected()) {
                long startTime = System.nanoTime();
                hash.deleteFile(f);
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
                System.out.println(hash.get("come").linkedList.display());
            }

            if (listfile.contains(f.getName())) {
                listfile.remove(f.getName());
                display.setText(display.getText() + f.getName() + " successfully removed from lists." + "\n");
            } else {
                display.setText(display.getText() + "err: document not found." + "\n");
            }
            stack.push(display.getText());
        }
    }

    private class UpdateHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            int a = 0;
            int n = 0;
            File f = new File(text.getText() + "/" + command.getText() + ".txt");
            display.removeAll();
            display.setText(">> update " + f.getName() + "\n");

            if (bstbutton.isSelected()) {
                bst.deleteFile(f);
                System.out.println(bst.find("come").linkedList.display());
            } else if (triebutton.isSelected()) {
                trie.deleteFile(f);
                System.out.println(trie.getWord("come").linkedList.display());
            } else if (tstbutton.isSelected()) {
                tst.deleteFile(f);
                System.out.println(tst.search("come").linkedList.display());
            } else if (hashbutton.isSelected()) {
                hash.deleteFile(f);
                System.out.println(hash.get("come").linkedList.display());
            }

            if (listfile.contains(f.getName())) {
                //listfile.remove(f.getName());
                display.setText(display.getText() + f.getName() + " successfully updated." + "\n");
            } else {
                display.setText(display.getText() + "err: document not found." + "\n");
            }

            System.out.println(bst.find("come").linkedList.display());

            TST b = new TST();
            b = TstopWordCreat();
            if (n == 0) {
                if (f.isFile()) {

                    Scanner sc2 = null;
                    try {
                        sc2 = new Scanner(new File(f.getAbsolutePath()));
                    } catch (FileNotFoundException r) {
                        r.printStackTrace();
                        a = 0;
                    }

                    while (sc2.hasNextLine()) {
                        Scanner s2 = new Scanner(sc2.nextLine());
                        while (s2.hasNext()) {

                            String s = s2.next();
                            s = s.toLowerCase();

                            if (!(s.equals(",") || s.equals("?") || s.equals(".") || s.contains("'") || s.length() < 2 || s == null || s == "--" || s == " ")) {

                                if (b.search(s) == null) {

                                    bst.insert(s);
                                    bst.addLL(f, s);
                                    tst.insert(s);
                                    tst.addLL(f, s);
                                    trie.add(s);
                                    trie.addLL(f, s);

                                    System.out.println("1.");

                                }
                            }

                        }

                    }
                }

            }

            stack.push(display.getText());

        }
    }

    private class listWHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            int a = 0;
            if (bstbutton.isSelected()) {

                display.removeAll();

                display.setText("BST:" + "\n");
                long startTime = System.currentTimeMillis();
                for (String s : bst.print()) {
                    display.setText(display.getText() + s + "\n");

                    //System.out.println(s);
                }
                long estimatedTime = System.currentTimeMillis() - startTime;
                System.out.println(estimatedTime);
                System.out.println(bst.print().size());
                display.setText(display.getText() + bst.print().size() + "\n");
                display.setText(display.getText() + "Depth= " + bst.height() + "\n");
                stack.push(display.getText());

            } else if (tstbutton.isSelected()) {

                display.removeAll();

                display.setText("TST:" + "\n");
                long startTime = System.currentTimeMillis();
                for (String s : tst.print()) {
                    display.setText(display.getText() + s + "\n");
                    a = a + 1;
                }
                long estimatedTime = System.currentTimeMillis() - startTime;
                System.out.println(estimatedTime);
                display.setText(display.getText() + "Number of words = " + tst.print().size() + "\n");
                stack.push(display.getText());

            } else if (triebutton.isSelected()) {

                display.removeAll();

                display.setText("Trie:" + "\n");
                long startTime = System.currentTimeMillis();
                for (String s : trie.print()) {
                    display.setText(display.getText() + s + "\n");

                }
                long estimatedTime = System.currentTimeMillis() - startTime;
                System.out.println(estimatedTime);
                display.setText(display.getText() + "Number of words = " + trie.print().size() + "\n");
                stack.push(display.getText());

            } else if (hashbutton.isSelected()) {

                display.removeAll();

                display.setText("Hash:" + "\n");

                long startTime = System.currentTimeMillis();
                for (String s : hash.printHashTable()) {
                    display.setText(display.getText() + s + "\n");

                }
                long estimatedTime = System.currentTimeMillis() - startTime;
                System.out.println(estimatedTime);
                stack.push(display.getText());
                //display.getText()

                System.out.println(hash.printHashTable().size());
                display.setText(display.getText() + (hash.printHashTable().size()) + "\n");

            }

        }
    }

    private class listLHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            display.removeAll();
            for (String s : listfile) {
                display.setText(display.getText() + s + "\n");

            }

            display.setText(display.getText() + (listfile.size() - 1) + "\n");
            stack.push(display.getText());
        }
    }

    private class ListFHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            int c = 0;
            display.removeAll();
            display.setText(">> list –f" + "\n");
            File folder = new File(text.getText());
            File[] listOfFiles = folder.listFiles();

            int a;
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    c = c + 1;

                    display.setText(display.getText() + file.getName() + "\n");

                }

            }
            display.setText(display.getText() + "Number of all docs = " + c + "\n");
            stack.push(display.getText());

        }
    }

    private class searchWHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            display.setText(">> search -w “" + command.getText() + "”" + "\n");
            if (bstbutton.isSelected()) {
                long startTime = System.nanoTime();
                display.setText(display.getText() + bst.find(command.getText().toLowerCase()).linkedList.display() + "\n");
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            } else if (tstbutton.isSelected()) {
                long startTime = System.nanoTime();
                display.setText(display.getText() + tst.search(command.getText().toLowerCase()).linkedList.display() + "\n");
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            } else if (triebutton.isSelected()) {
                long startTime = System.nanoTime();
                display.setText(display.getText() + trie.getWord(command.getText().toLowerCase()).linkedList.display() + "\n");
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            } else if (hashbutton.isSelected()) {
                long startTime = System.nanoTime();
                display.setText(display.getText() + hash.get(command.getText().toLowerCase()).linkedList.display() + "\n");
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            }

            stack.push(display.getText());

        }
    }

    private class searchSHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            TST t;
            t = TstopWordCreat();
            linkList l = new linkList();
            display.setText(">> search -s “" + command.getText() + "”" + "\n");
            display.setText(display.getText() + "Appears in:" + "\n");
            String s = command.getText();
            String[] arr = s.split(" ");

            if (bstbutton.isSelected()) {
                long startTime = System.nanoTime();
                for (String ss : arr) {
                    ss = ss.toLowerCase();
                    if (bst.find(ss) != null) {
                        if (!(ss.equals(",") || ss.equals("?") || ss.equals(".") || ss.contains("'") || ss.length() < 2 || ss == null || ss == "--" || ss == " ")) {
                            if (t.search(ss) == null) {
                                l = bst.find(ss).linkedList;
                                System.out.println("MyFrame.searchSHandler.actionPerformed()");
                                System.out.println(l.display());
                                break;
                            }
                        }
                    }
                }
                for (String e : arr) {

                    for (String a : arr) {

                        for (String f : arr) {

                            if (t.search(f) == null) {
                                f = f.toLowerCase();

                                if (bst.find(f) != null) {
                                    System.out.println(f);
                                    for (int i = 1; i <= l.size; i++) {
                                        if (!(bst.find(f).linkedList.isinList(l.FindAtPos(i).data))) {
                                            //bst.find("american").linkedList.isinList
                                            System.out.println(l.FindAtPos(i).data.getName());
                                            l.deleteAtPos(i);

                                        }

                                    }
                                }
                            }

                        }
                    }
                }
                String k;
                for (int i = 1; i <= l.size; i++) {

                    k = ("|" + l.FindAtPos(i).getData().getName() + " -> ");
                    for (String p : arr) {
                        p = p.toLowerCase();
                        if (t.search(p) == null) {
                            Scanner in = null;
                            try {
                                in = new Scanner(l.FindAtPos(i).getData());
                                while (in.hasNext()) {
                                    String line = in.nextLine();
                                    System.out.println(p);
                                    if (line.contains(p)) {
                                        System.out.println(p + ".");
                                        k = k.concat("(..." + line + "...) ");
                                        System.out.println(line);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    display.setText(display.getText() + k + "\n");
                }
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            } else if (tstbutton.isSelected()) {
                long startTime = System.nanoTime();
                for (String ss : arr) {
                    ss = ss.toLowerCase();
                    if (tst.search(ss) != null) {
                        if (!(ss.equals(",") || ss.equals("?") || ss.equals(".") || ss.contains("'") || ss.length() < 2 || ss == null || ss == "--" || ss == " ")) {

                            if (t.search(ss) == null) {
                                l = tst.search(ss).linkedList;
                                System.out.println("MyFrame.searchSHandler.actionPerformed()");
                                System.out.println(l.display());
                                break;
                            }
                        }
                    }
                }
                for (String e : arr) {

                    for (String a : arr) {

                        for (String f : arr) {

                            if (t.search(f) == null) {
                                f = f.toLowerCase();

                                if (tst.search(f) != null) {
                                    System.out.println(f);
                                    for (int i = 1; i <= l.size; i++) {
                                        if (!(tst.search(f).linkedList.isinList(l.FindAtPos(i).data))) {
                                            //bst.find("american").linkedList.isinList
                                            System.out.println(l.FindAtPos(i).data.getName());
                                            l.deleteAtPos(i);

                                        }

                                    }
                                }
                            }

                        }
                    }
                }
                String k;
                for (int i = 1; i <= l.size; i++) {

                    k = ("|" + l.FindAtPos(i).getData().getName() + " -> ");
                    for (String p : arr) {
                        p = p.toLowerCase();
                        if (t.search(p) == null) {
                            Scanner in = null;
                            try {
                                in = new Scanner(l.FindAtPos(i).getData());
                                while (in.hasNext()) {
                                    String line = in.nextLine();
                                    System.out.println(p);
                                    if (line.contains(p)) {
                                        System.out.println(p + ".");
                                        k = k.concat("(..." + line + "...) ");
                                        System.out.println(line);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    display.setText(display.getText() + k + "\n");
                }
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);

            } else if (triebutton.isSelected()) {
                long startTime = System.nanoTime();
                for (String ss : arr) {
                    ss = ss.toLowerCase();
                    if (trie.getWord(ss) != null) {
                        if (!(ss.equals(",") || ss.equals("?") || ss.equals(".") || ss.contains("'") || ss.length() < 2 || ss == null || ss == "--" || ss == " ")) {

                            if (t.search(ss) == null) {

                                l = trie.getWord(ss).linkedList;
                                System.out.println("MyFrame.searchSHandler.actionPerformed()");
                                System.out.println(l.display());
                                break;
                            }
                        }
                    }
                }
                for (String e : arr) {

                    for (String a : arr) {

                        for (String f : arr) {

                            if (t.search(f) == null) {
                                f = f.toLowerCase();

                                if (trie.getWord(f) != null) {
                                    System.out.println(f);
                                    for (int i = 1; i <= l.size; i++) {
                                        if (!(trie.getWord(f).linkedList.isinList(l.FindAtPos(i).data))) {
                                            //bst.find("american").linkedList.isinList
                                            System.out.println(l.FindAtPos(i).data.getName());
                                            l.deleteAtPos(i);

                                        }

                                    }
                                }
                            }

                        }
                    }
                }
                String k;
                for (int i = 1; i <= l.size; i++) {

                    k = ("|" + l.FindAtPos(i).getData().getName() + " -> ");
                    for (String p : arr) {
                        p = p.toLowerCase();
                        if (t.search(p) == null) {
                            Scanner in = null;
                            try {
                                in = new Scanner(l.FindAtPos(i).getData());
                                while (in.hasNext()) {
                                    String line = in.nextLine();
                                    System.out.println(p);
                                    if (line.contains(p)) {
                                        System.out.println(p + ".");
                                        k = k.concat("(..." + line + "...) ");
                                        System.out.println(line);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    display.setText(display.getText() + k + "\n");
                }
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            } else if (hashbutton.isSelected()) {
                long startTime = System.nanoTime();
                for (String ss : arr) {
                    ss = ss.toLowerCase();
                    if (hash.get(ss) != null) {
                        if (!(ss.equals(",") || ss.equals("?") || ss.equals(".") || ss.contains("'") || ss.length() < 2 || ss == null || ss == "--" || ss == " ")) {

                            if (t.search(ss) == null) {
                                l = hash.get(ss).linkedList;
                                System.out.println("MyFrame.searchSHandler.actionPerformed()");
                                System.out.println(l.display());
                                break;
                            }
                        }
                    }
                }
                for (String e : arr) {

                    for (String a : arr) {

                        for (String f : arr) {

                            if (t.search(f) == null) {
                                f = f.toLowerCase();

                                if (hash.get(f) != null) {
                                    System.out.println(f);
                                    for (int i = 1; i <= l.size; i++) {
                                        if (!(hash.get(f).linkedList.isinList(l.FindAtPos(i).data))) {
                                            //bst.find("american").linkedList.isinList
                                            System.out.println(l.FindAtPos(i).data.getName());
                                            l.deleteAtPos(i);

                                        }

                                    }
                                }
                            }

                        }
                    }
                }
                String k;
                for (int i = 1; i <= l.size; i++) {

                    k = ("|" + l.FindAtPos(i).getData().getName() + " -> ");
                    for (String p : arr) {
                        p = p.toLowerCase();
                        if (t.search(p) == null) {
                            Scanner in = null;
                            try {
                                in = new Scanner(l.FindAtPos(i).getData());
                                while (in.hasNext()) {
                                    String line = in.nextLine();
                                    System.out.println(p);
                                    if (line.contains(p)) {
                                        System.out.println(p + ".");
                                        k = k.concat("(..." + line + "...) ");
                                        System.out.println(line);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    display.setText(display.getText() + k + "\n");
                }
                long estimatedTime = System.nanoTime() - startTime;
                System.out.println(estimatedTime);
            }

            display.setText(display.getText() + l.display() + "\n");
            System.out.println("..." + l.display());
            stack.push(display.getText());

        }

    }
}
