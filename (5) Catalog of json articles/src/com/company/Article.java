package com.company;

public class Article {
    public String author;
    public String rubric;
    public String title;
    public int year;
    public String[] keyWord;

    public Article()
    {
        super();
    }

    public Article(String author, String rubric, String name, int year, String[] keyWord) {
        this.author = author;
        this.rubric = rubric;
        this.title = name;
        this.year = year;
        this.keyWord = keyWord;
    }

    public String toString() {
        return this.author + " " + this.title + " " + this.year + " " + this.rubric;
    }

}
