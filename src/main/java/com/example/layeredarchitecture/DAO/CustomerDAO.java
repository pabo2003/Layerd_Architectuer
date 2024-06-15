package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    public void saveCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException;

    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer1(String id) throws SQLException, ClassNotFoundException;

    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public CustomerDTO SearchCustomer(String newValue) throws SQLException, ClassNotFoundException;

    public CustomerDTO findCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
