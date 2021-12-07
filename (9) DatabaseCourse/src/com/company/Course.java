package com.company;

public class Course {
    public int id;
    public String name;
    public String skills;
    public String area;
    public double price;

    public Course(int id, String name, String skills, String area, double price){
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.area = area;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skills='" + name + '\'' +
                ", area='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
