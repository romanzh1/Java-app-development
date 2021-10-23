package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

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
    private JTextField countArt;
    private JComboBox comboBox1;
    private JTextField nameFile;
    private JButton выбратьПапкуButton;
    File mydir = null;

    public MyForm() {
        setContentPane(contentPane);
        setModal(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mydir != null){
                    String s = "";
                    int i = 0;
                    for (File file : mydir.listFiles()){
                        if (file.isFile()) s += file.getName() + "\n";
                    }
                    textArea1.setText(s);
                }
            }
        });
        удалитьФайлButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1.getText().isEmpty()){
                    JFileChooser fileChooser = new JFileChooser(mydir);
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt" , "text", "docx");
                    fileChooser.setFileFilter(filter);

                    int res = fileChooser.showDialog(null, "Выберите файл");
                    if (res == JFileChooser.APPROVE_OPTION){
                        File f = fileChooser.getSelectedFile();
                        f.delete();
                    }
                }
                else{
                    int offset = textArea1.getCaretPosition();
                    System.out.println(offset);
                    String []files = textArea1.getText().split("\n");
                    String nameFile = "";
                    int countSymbol = 0;
                    for (int i = 0; i < files.length; i++){
                        if (offset >= countSymbol && offset <= countSymbol + files[i].length()){
                            nameFile = files[i];
                        }
                        countSymbol += files[i].length();
                    }

                    String pathForDelete = mydir.getAbsolutePath() + "\\" + nameFile;
                    File file = new File(pathForDelete);
                    if (file.delete()) JOptionPane.showMessageDialog(contentPane, "Файл успешно удалён");
                    else JOptionPane.showMessageDialog(contentPane, "Файл не был удалён");

                }
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

                if (res == JFileChooser.APPROVE_OPTION){
                    System.out.println(1);
                    String folder = fileChooser.getSelectedFile().getAbsolutePath();
                    String pathDir = folder + "\\" + textField1.getText();

                    File file = new File(pathDir);
                    mydir = file;
                    System.out.println(2);

                    if (file.mkdir()) JOptionPane.showMessageDialog(contentPane, "Папка создана");
                    else JOptionPane.showMessageDialog(contentPane, "Папка не создана");
                }
            }
        });
        addfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int offset = textArea1.getCaretPosition();
                System.out.println(offset);
                String []files = textArea1.getText().split("\n");
                String nameFile = "";
                int countSymbol = 0;
                for (int i = 0; i < files.length; i++){
                    if (offset >= countSymbol && offset <= countSymbol + files[i].length()){
                        nameFile = files[i];
                    }
                    countSymbol += files[i].length();
                }

                String pathForRename = mydir.getAbsolutePath() + "\\" + nameFile;
                System.out.println(pathForRename);
                System.out.println(textField1.getText());

                File newfile = new File(textField1.getText());
                File file = new File(pathForRename);
                if (file.renameTo(newfile)) JOptionPane.showMessageDialog(contentPane, "Название файла изменено");
                else JOptionPane.showMessageDialog(contentPane, "Название файла не изменено");
                // TODO файл почему-то удаляется
            }
        });
        renameFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(mydir);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                File sourceFile = fileChooser.getSelectedFile();
                // TODO куда копировать выбранный в окне файл
            }
        });
//        выбратьПапкуButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fileChooser.showDialog(null, "Choose dir");
                if (res == JFileChooser.APPROVE_OPTION) {
                    mydir= fileChooser.getSelectedFile();
                }
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
