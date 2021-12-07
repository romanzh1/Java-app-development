package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static void deleteCourseName(Connection con, ArrayList<Course> pr, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM course " +
                        "WHERE name = ?;");
        ps.setString(1, name);
        ps.execute();
        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).name.equals(name)) {
                pr.remove(i);
            }
        }
    }

    private static void addCourse(Connection con, ArrayList<Course> co, Course itemP) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO course (name, skills, area, price) " +
                        "VALUES (?, ?, ?, ?) RETURNING id");
        ps.setString(1, itemP.name);
        ps.setString(2, itemP.skills);
        ps.setString(3, itemP.area);
        ps.setDouble(4, itemP.price);
        ps.execute();
        co.add(itemP);

        ResultSet res = ps.getResultSet();
        res.next();
        int id = res.getInt("id");
    }

    private static void showCourse(Connection con, ArrayList<Course> pr) {
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(
                    "SELECT * " +
                            "FROM course");
            show(con, pr, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showFilteredPriceCourse(Connection con, ArrayList<Course> pr, Double price, String sign) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * " +
                            "FROM course " +
                            "WHERE price::integer "+ sign + "?");
            ps.setDouble(1, price);
            ps.execute();
            ResultSet res = ps.getResultSet();

            show(con, pr, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showFilteredNameCourse(Connection con, ArrayList<Course> pr, String name) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * " +
                            "FROM course " +
                            "WHERE name = ?");
            ps.setString(1, name);
            ps.execute();
            ResultSet res = ps.getResultSet();

            show(con, pr, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void show(Connection con, ArrayList<Course> pr, ResultSet res) {
        try {
            pr.clear();
            while (res.next()) { // вывод результатов
                pr.add(new Course(res.getInt("id"), res.getString("name"), res.getString("skills"), res.getString("area"), res.getFloat("price")));
            }

            for (int i = 0; i < pr.size(); i++) {
                System.out.println(pr.get(i).toString());
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(55);
        ArrayList<Course> co = new ArrayList<>();
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "vjycnh");
            showCourse(con, co);
//            showCourseByCat(con, co, prCat, "food");
//            showFilteredPriceCourse(con, co, prCat, 3.3, ">=");
//            showCostCourseByCat(con, "italian");
//            showCountCourseByCat(con);
//            showMaxPriceCourseByCat(con);

            // add course
//            Course itemP = new Course(0, "Fascinating physics", "atomic theory, atomic spectroscopy", "Nuclear physics",300);
//            addCourse(con, co, itemP);
//            showCourse(con, co);

            // delete Course and category
            deleteCourseName(con, co, "Integral");
            showCourse(con, co);

            // filter
            showFilteredPriceCourse(con, co, 350.0, ">=");
            showFilteredNameCourse(con, co, "Integral");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (con != null) {
            con.close();
        }
    }
}
