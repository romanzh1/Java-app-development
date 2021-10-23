package com.company;

import java.util.HashMap;

public class Article {
    public String autor;
    public String rubric;
    public String name;
    public int count;
    public int year;

    public Article(String autor, String rubric, String name, int count, int year) {
        this.autor = autor;
        this.rubric = rubric;
        this.name = name;
        this.year = year;
        this.count = count;
    }

    public String toString() {
        return this.autor + " " + this.name + " " + this.count;
    }

    public HashMap<String, Integer> removeLess40(HashMap<String, Integer> countViews) {
        HashMap<String, Integer> countViews2 = new HashMap<String, Integer>(countViews);
        for (HashMap.Entry item : countViews.entrySet()) {
            if ((int) item.getValue() < 40) {
                countViews2.remove(item.getKey());
            }
        }
        return countViews2;
    }
}
