/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.werapan.databaseproject.dao;

import com.werapan.databaseproject.helper.DatabaseHelper;
import com.werapan.databaseproject.model.RecieptDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author werapan
 */
public class RecieptDetailDao implements Dao<RecieptDetail> {

    @Override
    public RecieptDetail get(int id) {
        RecieptDetail recieptDetail = null;
        String sql = "SELECT * FROM recieptDetail WHERE recieptDetail_id=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recieptDetail = RecieptDetail.fromRS(rs);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return recieptDetail;
    }

    public List<RecieptDetail> getAll() {
        ArrayList<RecieptDetail> list = new ArrayList();
        String sql = "SELECT * FROM recieptDetail";
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                RecieptDetail recieptDetail = RecieptDetail.fromRS(rs);
                list.add(recieptDetail);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<RecieptDetail> getAll(String where, String order) {
        ArrayList<RecieptDetail> list = new ArrayList();
        String sql = "SELECT * FROM recieptDetail where " + where + " ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                RecieptDetail recieptDetail = RecieptDetail.fromRS(rs);
                list.add(recieptDetail);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    

    public List<RecieptDetail> getAll(String order) {
        ArrayList<RecieptDetail> list = new ArrayList();
        String sql = "SELECT * FROM recieptDetail  ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                RecieptDetail recieptDetail = RecieptDetail.fromRS(rs);
                list.add(recieptDetail);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public RecieptDetail save(RecieptDetail obj) {

        String sql = "INSERT INTO recieptDetail (total, cash, total_qty, user_id, customer_id)"
                + "VALUES(?, ?, ?, ?, ?)";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, obj.getTotal());
            stmt.setFloat(2, obj.getCash());
            stmt.setInt(3, obj.getTotalQty());
            stmt.setInt(4, obj.getUserId());
            stmt.setInt(5, obj.getCustomerId());
//            System.out.println(stmt);
            stmt.executeUpdate();
            int id = DatabaseHelper.getInsertedId(stmt);
            obj.setId(id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return obj;
    }

    @Override
    public RecieptDetail update(RecieptDetail obj) {
        String sql = "UPDATE recieptDetail"
                + " SET total = ?, cash = ?, total_qty = ?, user_id = ?, customer_id = ?"
                + " WHERE recieptDetail_id = ?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, obj.getTotal());
            stmt.setFloat(2, obj.getCash());
            stmt.setInt(3, obj.getTotalQty());
            stmt.setInt(4, obj.getUserId());
            stmt.setInt(5, obj.getCustomerId());
            stmt.setInt(6, obj.getId());
//          System.out.println(stmt);
            int ret = stmt.executeUpdate();
            System.out.println(ret);
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int delete(RecieptDetail obj) {
        String sql = "DELETE FROM recieptDetail WHERE recieptDetail_id=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            int ret = stmt.executeUpdate();
            return ret;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;        
    }

}
