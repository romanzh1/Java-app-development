package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton button2;
    private JTextArea textArea1;
    private JButton addfile;
    private JTabbedPane tabbedPane1;
    private JTextField autor;
    private JTextField nameArt;
    private JTextField yearArt;
    private JComboBox comboBox1;
    private JTextField keyWord;
    private JTextField textField2;
    private JPanel addF;
    private JPanel workFs;
    private JButton sortF;
    private JButton searchF;
    ArrayList<Article> artList;

    ObjectMapper mapper = new ObjectMapper();
    File file = new File("test\\data.json");

    void readJson() {
        CollectionType listType =
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, Article.class);
        try {
            artList = mapper.readValue(file, listType);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    void showList(ArrayList<Article> artListv) {
        String s = "[\n    {";
        for (int i = 0; i < artListv.size(); i++) {
            s += "\n        \"title\": " + artListv.get(i).title;
            s += "\n        \"author\": " + artListv.get(i).author;
            s += "\n        \"year\": " + artListv.get(i).year;
            s += "\n        \"category\": " + artListv.get(i).category;
            s += "\n        \"keyWord\": [";
            for (int j = 0; j < artListv.get(i).keyWord.length; j++) {
                s += "\"" + artListv.get(i).keyWord[j];
                if (i != artListv.get(i).keyWord.length - 1) s += "\", ";
            }
            s += "]";
            s += "\n    }";
        }
        s += "\n]";
        textArea1.setText(s);
    }

    public MyForm() {
        setContentPane(contentPane);
        setModal(true);

        tabbedPane1.setSelectedIndex(1);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readJson();
                showList(artList);
            }
        });
        addfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readJson();
                String[] keyWords = keyWord.getText().split(","); // !!! Ввод ключевых слов через запятую без пробелов
                artList.add(new Article(nameArt.getText(), autor.getText(), Integer.parseInt(yearArt.getText()),
                        (String) comboBox1.getSelectedItem(), keyWords));
                try {
                    mapper.writeValue(new FileOutputStream("test/data.json"), artList);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                showList(artList);
            }
        });
        searchF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readJson();
                ArrayList<Article> searchList = new ArrayList<Article>();
                for (int i = 0; i < artList.size(); i++) {
                    for (int j = 0; j < artList.get(i).keyWord.length; j++) {
                        if (artList.get(i).keyWord[j].equals(textField2.getText())) {
                            searchList.add(artList.get(i));
                        }
                    }
                }
                showList(searchList);
            }
        });
        sortF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readJson();
                ArrayList<String> uniqKey = new ArrayList<>();
                for (int i = 0; i < artList.size(); i++) {
                    for (int j = 0; j < artList.get(i).keyWord.length; j++) {
                        boolean finded = false;
                        for (int k = 0; k < uniqKey.size(); k++) {
                            if (uniqKey.get(k).equals(artList.get(i).keyWord[j]))
                                finded = true;
                        }
                        if (!finded)
                            uniqKey.add(artList.get(i).keyWord[j]);

                    }
                }
                String result = "Уникальные keywords:\n\n";
                for (int k = 0; k < uniqKey.size(); k++) {
                    result += uniqKey.get(k) + "\n";
                }
                textArea1.setText(result);
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
