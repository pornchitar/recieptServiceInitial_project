/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class RecieptDetail {
    private int id;
    private int productId;
    private String productName;
    private float productPrice;
    private int qty;
    private float totalPrice;
    private int recieptId;

    public RecieptDetail(int id, int productId, String productName, float productPrice, int qty, float totalPrice, int reciepId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.recieptId = recieptId;
    }
    
    public RecieptDetail( int productId, String productName, float productPrice, int qty, float totalPrice, int reciepId) {
        this.id = -1;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.recieptId = recieptId;
    }
    
    public RecieptDetail() {
        this.id = -1;
        this.productId = 0;
        this.productName = "";
        this.productPrice = 0;
        this.qty = 0;
        this.totalPrice = 0;
        this.recieptId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRecieptId() {
        return recieptId;
    }

    public void setRecieptId(int reciepId) {
        this.recieptId = recieptId;
    }

    @Override
    public String toString() {
        return "RecieptDetial{" + "id=" + id + ", productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", qty=" + qty + ", totalPrice=" + totalPrice + ", reciepId=" + recieptId + '}';
    }
    
    public static RecieptDetail fromRS(ResultSet rs) {
        RecieptDetail recieptDetail = new RecieptDetail();
        try {
            recieptDetail.setId(rs.getInt("reciept_detail_id"));
            recieptDetail.setProductId(rs.getInt("product_id"));
            recieptDetail.setProductName(rs.getString("product_name"));
            recieptDetail.setProductPrice(rs.getFloat("product_price"));
            recieptDetail.setQty(rs.getInt("qty"));
            recieptDetail.setTotalPrice(rs.getFloat("total_price"));
            recieptDetail.setRecieptId(rs.getInt("reciept_id"));
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return recieptDetail;
    }
}
