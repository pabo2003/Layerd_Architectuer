package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.List;

public interface OrderDAO {
    public String generateNewId() throws SQLException, ClassNotFoundException;

    public boolean CheckOrderExist(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTO) throws SQLException, ClassNotFoundException ;
}
