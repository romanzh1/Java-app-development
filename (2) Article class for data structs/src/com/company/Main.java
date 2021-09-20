package com.company;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Article a = new Article("pushkin", "poema", "mymy", 5, 1956);
        Queue<Article> q = new ArrayDeque();
        q.offer(a);
        q.offer(new Article("a", "b", "c", 5, 1986));
        q.offer(new Article("tt", "yy", "uu", 26, 1955));
        q.offer(new Article("gg", "hh", "jj", 15, 1989));
        q.offer(new Article("vv", "bb", "nn", 17, 1945));
        q.offer(new Article("uu", "ii", "oo", 45, 1333));
        for (Article item:q){
            System.out.println((item.toString()));
        }

        System.out.println("-----------------");
        if (q.size() >= 5){
            q.poll();
            q.offer(new Article("oo", "ppi", "ll", 99, 1222));
        }
        else {
            q.offer(new Article("yy", "ttt", "ss", 44, 1000));
        }

        HashMap <String, Integer> m = new HashMap<String, Integer>();

        for (Article item:q){
            if (m.containsKey(item.autor)){
                m.put(item.autor, m.get(item.autor) + 1);
            }
            else{
                m.put(item.autor, 1);
            }
        }

        // перебор элементов
        for(HashMap.Entry item : m.entrySet())
        {
            System.out.println("Key: "+item.getKey()+"; Value: "+item.getValue());
        }
    }
}

class Article {
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
}
