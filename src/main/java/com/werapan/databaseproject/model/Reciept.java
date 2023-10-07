/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.model;

import com.werapan.databaseproject.dao.CustomerDao;
import com.werapan.databaseproject.dao.UserDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werapan
 */
public class Reciept {
    private int id;
    private Date createdDate;
    private float total;
    private float cash;
    private int totalQty;
    private int userId;
    private int customerId;
    private User user;
    private Customer customer;
    private ArrayList<RecieptDetail> recieptDetails = new ArrayList<RecieptDetail>();

    public Reciept(int id, Date createdDate, float total, float cash, int totalQty, int userId, int customerId) {
        this.id = id;
        this.createdDate = createdDate;
        this.total = total;
        this.cash = cash;
        this.totalQty = totalQty;
        this.userId = userId;
        this.customerId = customerId;
    }

    public Reciept(Date createdDate, float total, float cash, int totalQty, int userId, int customerId) {
        this.id = -1;
        this.createdDate = createdDate;
        this.total = total;
        this.cash = cash;
        this.totalQty = totalQty;
        this.userId = userId;
        this.customerId = customerId;
    }
    
    public Reciept(float total, float cash, int totalQty, int userId, int customerId) {
        this.id = -1;
        this.createdDate = null;
        this.total = total;
        this.cash = cash;
        this.totalQty = totalQty;
        this.userId = userId;
        this.customerId = customerId;
    }
    
    public Reciept(float cash, int userId, int customerId) {
        this.id = -1;
        this.createdDate = null;
        this.total = 0;
        this.cash = cash;
        this.totalQty = 0;
        this.userId = userId;
        this.customerId = customerId;
    }
    
    public Reciept() {
        this.id = -1;
        this.createdDate = null;
        this.total = 0;
        this.cash = 0;
        this.totalQty = 0;
        this.userId = 0;
        this.customerId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public User getUser(){
        return user;
        
    }
    
    public void setUser(User user){
        this.user = user;
        this.userId = user.getId();
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
        this.customerId = customer.getId();
    }

    public ArrayList<RecieptDetail> getRecieptDetails() {
        return recieptDetails;
    }
    
    public void setRecieptDetails(ArrayList recieptDetails) {
        this.recieptDetails = recieptDetails;
    }   

    @Override
    public String toString() {
        return "Reciept{" + "id=" + id + ", createdDate=" + createdDate + ", total=" + total + ", cash=" + cash + ", totalQty=" + totalQty + ", userId=" + userId + ", customerId=" + customerId + ", user=" + user + ", customer=" + customer + ", recieptDetails=" + recieptDetails + '}';
    }
    
    public void addRecieptDetail(RecieptDetail recieptDetail){
        recieptDetails.add(recieptDetail);
        calculateTotal();
    }
    
    public void delRecieptDetail(RecieptDetail recieptDetail){
        recieptDetails.remove(recieptDetail);
        calculateTotal();
    }
    
    public void calculateTotal(){
        int totalQty = 0;
        float total = 0.0f;
        for(RecieptDetail rd: recieptDetails){
            total += rd.getTotalPrice();
            totalQty += rd.getQty();
        }
        this.totalQty = totalQty;
        this.total = total;
    }
    
    public static Reciept fromRS(ResultSet rs) {
        Reciept reciept = new Reciept();
        try {
            reciept.setId(rs.getInt("reciept_id"));
            reciept.setCreatedDate(rs.getTimestamp("created_date"));
            reciept.setTotal(rs.getFloat("total"));
            reciept.setCash(rs.getFloat("cash"));
            reciept.setTotal(rs.getInt("total_qty"));
            reciept.setUserId(rs.getInt("user_id"));
            reciept.setCustomerId(rs.getInt("customer_id"));
            // Population
            CustomerDao customerDao = new CustomerDao();
            UserDao userDao = new UserDao();
            Customer customer = customerDao.get(reciept.getCustomerId());
            User user = userDao.get(reciept.getUserId());
            reciept.setCustomer(customer);
            reciept.setUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(Reciept.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return reciept;
    }
}
