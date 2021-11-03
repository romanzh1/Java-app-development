package com.company;

public class ProductCategory {
    public int id_product;
    public String category;

    public ProductCategory(int id_product, String category){
        this.id_product = id_product;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id_product=" + id_product +
                ", category='" + category + '\'' +
                '}';
    }
}
