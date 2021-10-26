package com.company;

public class Article {
    public String title;
    public String author;
    public int year;
    public String category;
    public String[] keyWord;

    public Article() {
        super();
    }

    public Article(String name, String author, int year, String category, String[] keyWord) {
        this.author = author;
        this.category = category;
        this.title = name;
        this.year = year;
        this.keyWord = keyWord;
    }

//    public String toString() {
//        return this.author + " " + this.title + " " + this.year + " " + this.rubric;
//    }

}
