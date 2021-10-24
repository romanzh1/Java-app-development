package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton button1;
    private JButton button2;
    private JTextArea textArea1;
    private JButton удалитьФайлButton;
    private JTextField textField1;
    private JButton createFolder;
    private JButton addfile;
    private JButton renameFile;
    private JTabbedPane tabbedPane1;
    private JTextField autor;
    private JTextField nameArt;
    private JTextField yearArt;
    private JComboBox comboBox1;
    private JTextField nameFile;
    private JPanel setD;
    private JPanel addF;
    private JPanel workFs;
    private JPanel workF;
    private JButton exitF;
    private JButton delArt;
    private JButton chanArt;
    private JTextArea textAreaArt;
    private JTextField textField2;
    private JButton sortF;
    private JButton searchF;
    private JLabel nameFa;
    private JButton выбратьПапкуButton;
    File mydir = null;
    String myfile = "";
    String myart = "";
    int changeStatus;
    int numWord;
    ArrayList<Article> artList = new ArrayList<Article>();


    String chooseWord(String[] word, int offset) {
        String nameWord = "";
        int countSymbol = 0;
        for (int i = 0; i < word.length; i++) {
            if (offset >= countSymbol && offset <= countSymbol + word[i].length()) {
                nameWord = word[i];
                numWord = i;
            }
            countSymbol += word[i].length();
        }

        return nameWord;
    }

    void chooseFile() {
        int offset = textArea1.getCaretPosition();
        String[] files = textArea1.getText().split("\n");

        myfile = chooseWord(files, offset);
    }

    void chooseArt() {
        int offset = textAreaArt.getCaretPosition();
        String[] article = textAreaArt.getText().split("\n");

        myart = chooseWord(article, offset);
    }

    void showFiles() {
        if (mydir != null) {
            String s = "";
            int i = 0;
            for (File file : Objects.requireNonNull(mydir.listFiles())) {
                if (file.isFile()) s += file.getName() + "\n";
            }
            textArea1.setText(s);
        }
    }

    void showArticles() throws IOException {
        List<String> fileReader = Files.readAllLines(Paths.get(mydir + "\\" + myfile), StandardCharsets.UTF_8);
        String s = "";
        int i = 0;
        for (String raw : fileReader) {
            s += raw + "\n";
        }
        textAreaArt.setText(s);
    }

    void overwriteFile() {
        try {
            PrintWriter writer = new PrintWriter(mydir + "\\" + myfile);
            writer.print("");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(mydir + "\\" + myfile, false);
            Object[] artArr = artList.toArray();
            for (int i = 0; i < artArr.length; i++) {
                writer.write(artArr[i].toString() + "\n");
            }
            writer.flush();
            showArticles();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void overwriteArtList() throws IOException {
        List<String> fileReader = Files.readAllLines(Paths.get(mydir + "\\" + myfile), StandardCharsets.UTF_8);
        int i = 0;
        artList.clear();
        for (String raw : fileReader) {
            String[] setWord = raw.split(" ");
            artList.add(new Article(setWord[0], setWord[3], setWord[1], Integer.parseInt(setWord[2])));
        }
    }

    public MyForm() {
        changeStatus = 0;

        setContentPane(contentPane);
        setModal(true);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(3);

                chooseFile();
                System.out.println(myfile);
                try {
                    showArticles();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    overwriteArtList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                nameFa.setText(myfile);
            }
        });
        удалитьФайлButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1.getText().isEmpty()) {
                    JFileChooser fileChooser = new JFileChooser(mydir);
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text", "docx");
                    fileChooser.setFileFilter(filter);

                    int res = fileChooser.showDialog(null, "Выберите файл");
                    if (res == JFileChooser.APPROVE_OPTION) {
                        File f = fileChooser.getSelectedFile();
                        f.delete();
                    }
                } else {
                    chooseFile();

                    String pathForDelete = mydir.getAbsolutePath() + "\\" + myfile;
                    File file = new File(pathForDelete);
                    if (file.delete()) JOptionPane.showMessageDialog(contentPane, "Файл успешно удалён");
                    else JOptionPane.showMessageDialog(contentPane, "Файл не был удалён");
                }
                showFiles();
            }
        });
        createFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()) return;

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fileChooser.showDialog(null, "Выберите папку");

                if (res == JFileChooser.APPROVE_OPTION) {
                    System.out.println(1);
                    String folder = fileChooser.getSelectedFile().getAbsolutePath();
                    String pathDir = folder + "\\" + textField1.getText();

                    File file = new File(pathDir);
                    mydir = file;

                    if (file.mkdir()) JOptionPane.showMessageDialog(contentPane, "Папка создана");
                    else JOptionPane.showMessageDialog(contentPane, "Папка не создана");
                }

                tabbedPane1.setSelectedIndex(2);
                showFiles();
            }
        });
        addfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artList.add(new Article(autor.getText(), (String) comboBox1.getSelectedItem(),
                        nameArt.getText(), Integer.parseInt(yearArt.getText())));
                File newFile = new File(mydir.getAbsolutePath() + "\\" + nameFile.getText());
                myfile = nameFile.getText();
                try {
                    newFile.createNewFile();
                    FileWriter writer = new FileWriter(newFile.getAbsolutePath(), false);
                    Object[] artArr = artList.toArray();
                    for (int i = 0; i < artArr.length; i++) {
                        writer.write(artArr[i].toString() + "\n");
                    }

                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                showFiles();
            }
        });

        renameFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
                String pathForRename = mydir.getAbsolutePath() + "\\";

                File newfile = new File(pathForRename + textField2.getText());
                File file = new File(pathForRename + myfile);
                if (file.renameTo(newfile)) JOptionPane.showMessageDialog(contentPane, "Название файла изменено");
                else JOptionPane.showMessageDialog(contentPane, "Название файла не изменено");
                showFiles();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fileChooser.showDialog(null, "Choose dir");
                if (res == JFileChooser.APPROVE_OPTION) {
                    mydir = fileChooser.getSelectedFile();
                }

                tabbedPane1.setSelectedIndex(2);
                showFiles();
            }
        });
        exitF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
            }
        });
        delArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseArt();
                artList.remove(numWord);

                overwriteFile();
            }
        });
        chanArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseArt();
                String[] chanWord = myart.split(" ");
                artList.set(numWord, new Article(chanWord[0], chanWord[3], chanWord[1], Integer.parseInt(chanWord[2])));
                overwriteFile();
            }
        });
        sortF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList sortList = new ArrayList();
                int offset = textArea1.getCaretPosition();
                String[] files = textArea1.getText().split("\n");
                for (int i = 0; i < files.length; i++) {
                    sortList.add(files[i]);
                }
                Collections.sort(sortList, new Comparator<String>() {
                    public int compare(String a, String b) {
                        return a.compareTo(b);
                    }
                });

                String s = "";
                for (Object str : sortList) {
                    s += str + "\n";
                }
                textArea1.setText(s);

            }
        });
        searchF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();

                try {
                    showArticles();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    overwriteArtList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                String art = "";
                for (int i = 0;i<artList.size();i++){
                    System.out.println(artList.get(i).name);
                    if (artList.get(i).name.equals(textField2.getText())){
                        art = artList.get(i).toString();
                    }
                }
                tabbedPane1.setSelectedIndex(3);
                textAreaArt.setText(art);
                nameFa.setText(myfile);
            }
        });
    }

    public static void main(String[] args) {
        MyForm dialog = new MyForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
