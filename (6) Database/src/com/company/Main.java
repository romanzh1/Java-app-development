package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static void deleteProductName(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "BEGIN; " +
                        "DELETE FROM product_category " +
                        "WHERE id_product = (" +
                            "SELECT id " +
                            "FROM product " +
                            "WHERE name = ?); " +
                        "DELETE FROM product " +
                        "WHERE name = ?; " +
                    "COMMIT;");
        ps.setString(1, name);
        ps.setString(2, name);
        ps.execute();
        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).name.equals(name)) {
                pr.remove(i);
                pc.remove(i);
            }
        }
    }

    private static void deleteProductCatName(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "WITH prcat AS (" +
                        "DELETE FROM product_category " +
                        "WHERE category = ? " +
                        "RETURNING id_product)" +
                    "DELETE FROM product " +
                    "WHERE id IN (SELECT id_product FROM prcat);");
        ps.setString(1, name);
        ps.execute();
        for (int i = 0; i < pr.size(); i++) {
            if (pc.get(i).category.equals(name)) {
                pr.remove(i);
                pc.remove(i);
            }
        }
    }

    private static void addProduct(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, Product itemP, ProductCategory itemPC) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO product (name, price) VALUES (?, ?) RETURNING id");
        ps.setString(1, itemP.name);
        ps.setDouble(2, itemP.price);
        ps.execute();
        pr.add(itemP);

        ResultSet res = ps.getResultSet();
        res.next();
        int id = res.getInt("id");

        PreparedStatement ps2 = con.prepareStatement("INSERT INTO product_category (id_product, category) VALUES (?, ?)");
        ps2.setInt(1, id);
        ps2.setString(2, itemPC.category);
        ps2.executeUpdate();
        pc.add(itemPC);
    }

    private static void showProduct(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc) {
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(
                    "SELECT id, price, name, category " +
                            "FROM product, product_category " +
                            "WHERE id = id_product");
            show(con, pr, pc, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showProductCategory(Connection con, ArrayList<ProductCategory> pc) {
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("Select * From product_category");
            pc.clear();
            while (res.next()) { // вывод результатов
                pc.add(new ProductCategory(res.getInt("id_product"), res.getString("category")));
            }

            for (int i = 0; i < pc.size(); i++) {
                System.out.println(pc.get(i).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMaxPriceProductByCat(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT MAX(price), category " +
                            "FROM product, product_category " +
                            "WHERE id = id_product " +
                            "GROUP BY category");
            ps.execute();
            ResultSet res = ps.getResultSet();

            while (res.next()) { // вывод результатов
                System.out.println(res.getString("category") + " max = " + res.getDouble("max"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showCountProductByCat(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT COUNT(*), category " +
                            "FROM product, product_category " +
                            "WHERE id = id_product " +
                            "GROUP BY category");
            ps.execute();
            ResultSet res = ps.getResultSet();

            while (res.next()) { // вывод результатов
                System.out.println(res.getString("category") + " count = " + res.getInt(    "count"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showCostProductByCat(Connection con, String name) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT SUM(price), category " +
                        "FROM product, product_category " +
                        "WHERE id = id_product AND category = ? " +
                        "GROUP BY category");
            ps.setString(1, name);
            ps.execute();
            ResultSet res = ps.getResultSet();

            while (res.next()) { // вывод результатов
                System.out.println(res.getString("category") + " sum = " + res.getDouble("sum"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showFilteredPriceProduct(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, Double price, String sign) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, price, name, category " +
                            "FROM product, product_category " +
                            "WHERE id = id_product AND price "+ sign + "?");
            ps.setDouble(1, price);
            ps.execute();
            ResultSet res = ps.getResultSet();

            show(con, pr, pc, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showProductByCat(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, String name) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, price, name, category " +
                            "FROM product, product_category " +
                            "WHERE id = id_product AND category = ?");
            ps.setString(1, name);
            ps.execute();
            ResultSet res = ps.getResultSet();

            show(con, pr, pc, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void show(Connection con, ArrayList<Product> pr, ArrayList<ProductCategory> pc, ResultSet res) {
        try {
            pr.clear();
            pc.clear();
            while (res.next()) { // вывод результатов
                pr.add(new Product(res.getInt("id"), res.getString("name"), res.getFloat("price")));
                pc.add(new ProductCategory(res.getInt("id"), res.getString("category")));
            }

            for (int i = 0; i < pr.size(); i++) {
                System.out.println(pr.get(i).toString() + "  " + pc.get(i).toString());
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(55);
        ArrayList<Product> pr = new ArrayList<>();
        ArrayList<ProductCategory> prCat = new ArrayList<>();
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JavaDB", "postgres",
                    "vjycnh");
            showProduct(con, pr, prCat);
            showProductByCat(con, pr, prCat, "food");
            showFilteredPriceProduct(con, pr, prCat, 3.3, ">=");
            showCostProductByCat(con, "italian");
            showCountProductByCat(con);
            showMaxPriceProductByCat(con);

            // add product
//            Product itemP = new Product(0, "mymymy", 4);
//            ProductCategory itemPC = new ProductCategory(0, "it");
//            addProduct(con, pr, prCat, itemP, itemPC);
//            showProduct(con, pr, prCat);

            // delete product and category
            deleteProductName(con, pr, prCat,"mymymy");
//            deleteProductCatName(con, pr, prCat, "it");
//            showProduct(con, pr, prCat);

            // filter data

            //
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (con != null) {
            con.close();
        }
    }
}
