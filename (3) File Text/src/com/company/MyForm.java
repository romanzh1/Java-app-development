package com.company;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JButton button2;
    private JTextArea textArea1;
    private JButton удалитьФайлButton;
    private JTextField textField1;
    private JButton createFolder;
    private JButton renamefile;
    private JButton copyFile;
    File mydir = null;

    public MyForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fileChooser.showDialog(null, "Choose dir");
                if (res == JFileChooser.APPROVE_OPTION) {
                    mydir = fileChooser.getSelectedFile();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        renamefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                String pathForRename = mydir.getAbsolutePath() + "\\";

                File newfile = new File(pathForRename + textField1.getText());
                File file = new File(pathForRename + nameFile);
                if (file.renameTo(newfile)) JOptionPane.showMessageDialog(contentPane, "Название файла изменено");
                else JOptionPane.showMessageDialog(contentPane, "Название файла не изменено");
            }
        });
        copyFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileTyCopy = null;

                if (mydir == null) {
                    JFileChooser whereToCopyFrom = new JFileChooser();
                    whereToCopyFrom.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int res = whereToCopyFrom.showDialog(null, "Выберите файл");
                    if (res == JFileChooser.APPROVE_OPTION) {
                        mydir = whereToCopyFrom.getSelectedFile();
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

                    fileTyCopy = new File(mydir.getAbsolutePath() + "\\" + nameFile);
                }

                File newDirectory = null;
                JFileChooser whereToCopy = new JFileChooser();
                whereToCopy.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = whereToCopy.showDialog(null, "Выберите папку");
                if (res == JFileChooser.APPROVE_OPTION) {
                    newDirectory = whereToCopy.getSelectedFile();
                }

                try {
                    if (fileTyCopy == null) FileUtils.copyFileToDirectory(mydir, newDirectory);
                    else FileUtils.copyFileToDirectory(fileTyCopy, newDirectory);
                } catch (IOException exception) {
                    exception.printStackTrace();
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
}
