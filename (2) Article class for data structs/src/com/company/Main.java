package com.company;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
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
        q.offer(new Article("a", "b", "c", 5, 1986));
        q.offer(new Article("tt", "yy", "uu", 26, 1955));
        q.offer(new Article("gg", "hh", "jj", 15, 1989));
        q.offer(new Article("vv", "bb", "nn", 17, 1945));
        q.offer(new Article("vv", "bb", "nn", 18, 1945));


        for (Article item:q){
            System.out.println((item.toString()));
        }

        System.out.println("-----------------");

        if (q.size() >= 10){
            q.poll();
            q.offer(new Article("oo", "ppi", "ll", 99, 1222));
        }
        else {
            q.offer(new Article("yy", "ttt", "ss", 44, 1000));
        }
        for (Article item:q){
            System.out.println((item.toString()));
        }
        System.out.println("-----------------");

        HashMap <String, Integer> countArticle = new HashMap<String, Integer>();

        for (Article item:q){
            if (countArticle.containsKey(item.autor)){
                countArticle.put(item.autor, countArticle.get(item.autor) + 1);
            }
            else{
                countArticle.put(item.autor, 1);
            }
        }
        // перебор элементов
        for(HashMap.Entry item : countArticle.entrySet())
        {
            System.out.println("Autor: "+item.getKey()+"; Count articles: "+item.getValue());
        }
        System.out.println("+++++++++++++++");

        HashMap <String, Integer> countViews = new HashMap<String, Integer>();
        for (Article item:q){
            if (countViews.containsKey(item.autor)){

                countViews.put(item.autor, countViews.get(item.autor) + item.count);
            }
            else{
                countViews.put(item.autor, item.count);
            }
        }
        for(HashMap.Entry item : countViews.entrySet())
        {
            System.out.println("Autor: "+item.getKey()+"; Count Views: "+item.getValue());
        }
        System.out.println("=================");

        HashMap <Integer, Integer> articlesByYear = new HashMap<Integer, Integer>();
        for (Article item:q){
            if (articlesByYear.containsKey(item.year)){
                articlesByYear.put(item.year, articlesByYear.get(item.year) + item.count);
            }
            else{
                articlesByYear.put(item.year, item.count);
            }
        }
        for(HashMap.Entry item : articlesByYear.entrySet())
        {
            System.out.println("Year: "+item.getKey()+"; Count Articles: "+item.getValue());
        }

        System.out.println();
        countViews = a.removeLess40(countViews);
        for(HashMap.Entry item : countViews.entrySet())
        {
            System.out.println("Autor: "+item.getKey()+"; Count Views: "+item.getValue());
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

    public HashMap <String, Integer> removeLess40(HashMap <String, Integer> countViews){
        HashMap <String, Integer> countViews2 = new HashMap <String, Integer>(countViews);
        for (HashMap.Entry item : countViews.entrySet()){
            if ((int)item.getValue() < 40){
                countViews2.remove(item.getKey());
            }
        }
        return countViews2;
    }
}
