/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test_service;

import com.werapan.databaseproject.dao.RecieptDetailDao;
import com.werapan.databaseproject.model.RecieptDetail;

/**
 *
 * @author ASUS
 */
public class TestRecieptDetailDao {
    public static void main(String[] args) {
        RecieptDetailDao rdd = new RecieptDetailDao();
        for(RecieptDetail rd :rdd.getAll()){
            System.out.println(rd);
        }
    }
}
