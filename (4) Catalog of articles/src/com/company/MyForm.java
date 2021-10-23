package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

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
    private JButton выбратьПапкуButton;
    File mydir = null;
    String myfile = "";
    int changeStatus;

    void chooseFile(){
        int offset = textArea1.getCaretPosition();
        System.out.println(offset);
        String[] files = textArea1.getText().split("\n");
        String nameFile = "";
        int countSymbol = 0;
        for (int i = 0; i < files.length; i++) {
            if (offset >= countSymbol && offset <= countSymbol + files[i].length()) {
                nameFile = files[i];
            }
            countSymbol += files[i].length();
        }

        myfile = nameFile;
    }

    void showFiles(){
        if (mydir != null) {
            String s = "";
            int i = 0;
            for (File file : Objects.requireNonNull(mydir.listFiles())) {
                if (file.isFile()) s += file.getName() + "\n";
            }
            textArea1.setText(s);
        }
    }

    public MyForm() {
        ArrayList<Article> artList = new ArrayList<Article>();

        Article a = new Article("pushkin", "poema", "mymy",1956);
        artList.add(a);
        artList.add(new Article("a", "b", "c",1986));
        artList.add(new Article("tt", "yy", "uu",1986));
        artList.add(new Article("tt", "hh", "jj",1989));

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
                    List <String> fileReader = Files.readAllLines(Paths.get(mydir + "\\" + myfile), StandardCharsets.UTF_8);
                    String s = "";
                    int i = 0;
                    for (String raw : fileReader) {
                        s += raw + "\n";
                    }
                    textAreaArt.setText(s);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
                    int offset = textArea1.getCaretPosition();
                    System.out.println(offset);
                    String[] files = textArea1.getText().split("\n");
                    String nameFile = "";
                    int countSymbol = 0;
                    for (int i = 0; i < files.length; i++) {
                        if (offset >= countSymbol && offset <= countSymbol + files[i].length()) {
                            nameFile = files[i];
                        }
                        countSymbol += files[i].length();
                    }

                    String pathForDelete = mydir.getAbsolutePath() + "\\" + nameFile;
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
                System.out.println(0);

                if (res == JFileChooser.APPROVE_OPTION) {
                    System.out.println(1);
                    String folder = fileChooser.getSelectedFile().getAbsolutePath();
                    String pathDir = folder + "\\" + textField1.getText();

                    File file = new File(pathDir);
                    mydir = file;
                    System.out.println(2);

                    if (file.mkdir()) JOptionPane.showMessageDialog(contentPane, "Папка создана");
                    else JOptionPane.showMessageDialog(contentPane, "Папка не создана");
                }

                tabbedPane1.setSelectedIndex(2);
                if (mydir != null) {
                    String s = "";
                    int i = 0;
                    for (File file : mydir.listFiles()) {
                        if (file.isFile()) s += file.getName() + "\n";
                    }
                    textArea1.setText(s);
                }
            }
        });
        addfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artList.add(new Article(autor.getName(), (String) comboBox1.getSelectedItem(),
                        nameArt.getText(), Integer.parseInt(yearArt.getText())));
                File newFile = new File(mydir.getAbsolutePath() + "\\" + nameFile.getText());
                System.out.println(mydir.getAbsolutePath() + "\\" + nameFile.getText());
                boolean createFile;
                try {
                    createFile = newFile.createNewFile();
                    System.out.println(createFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try(FileWriter writer = new FileWriter(mydir.getAbsolutePath() + nameFile, false))
                {
                    Object[] artArr = artList.toArray();
                    for (int i = 0; i < artArr.length; i++){
                        writer.write(artArr[i].toString());
                    }

                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
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
