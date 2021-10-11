package com.company;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JButton button2;
    private JTextArea textArea1;
    private JButton удалитьФайлButton;
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
                    mydir= fileChooser.getSelectedFile();
                }
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

                int offset = textArea1.getCaretPosition();
                System.out.println(offset);
                String []files = textArea1.getText().split("\n");
                int countSymbol = 0;
                for (int i = 0; i < files.length; i++){
                    if (offset >= countSymbol && offset <= countSymbol + files[i].length()){
                        //TODO delete file
                        System.out.println(files[i]);
                    }
                    countSymbol += files[i].length();
                }
//                int start = Utilities.getRowStart();
//                int end = Utilities.getRowEnd(...);
//                textArea.replaceRange("", start, end);
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
