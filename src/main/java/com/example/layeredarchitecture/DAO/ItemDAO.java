package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllItemCode() throws SQLException, ClassNotFoundException;

    public  ArrayList<ItemDTO> findItem(String code) throws SQLException, ClassNotFoundException;

    public void saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public void deleteItem(String code) throws SQLException, ClassNotFoundException;

    public Boolean existItem1(String code) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public int updateItem(int qtyOnHand,String code,ItemDTO item) throws SQLException, ClassNotFoundException;

    public ItemDTO findItemCom(String newItemCode) throws SQLException, ClassNotFoundException;
}
