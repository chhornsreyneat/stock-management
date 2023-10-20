package com.neat;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private Integer productID;
    private String productName;
    private Integer productQty;
    private Integer price;
    private LocalDate importproDate;

    public LocalDate getImportDate() {
        return importproDate;
    }

    public void setImportDate(LocalDate importproDate) {
        this.importproDate = importproDate;
    }

    public Product(Integer productID, String productName, Integer productQty, Integer price, LocalDate importproDate) {
        this.productID = productID;
        this.productName = productName;
        this.productQty = productQty;
        this.price = price;
        this.importproDate = importproDate;
    }

    public Integer getproId() {

        return productID;
    }

    public void setproId(Integer productID) {
        this.productID = productID;
    }

    public String getproName() {
        return productName;
    }

    public void setproName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return productQty;
    }

    public void setQty(Integer qty) {
        this.productQty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productID +
                ", productName='" + productName + '\'' +
                ", qty=" + productQty +
                ", price=" + price +
                ", importDate=" + importproDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) && Objects.equals(productName, product.productName) && Objects.equals(productQty, product.productQty) && Objects.equals(price, product.price) && Objects.equals(importproDate, product.importproDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productQty, price, importproDate);
    }
}

