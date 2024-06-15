package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    Connection connection = DBConnection.getDbConnection().getConnection();

    public OrderDAOImpl() throws SQLException, ClassNotFoundException {
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean CheckOrderExist(String orderId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        return stm.executeQuery().next();
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTO) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderDTO.getOrderId());
        stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
        stm.setString(3, orderDTO.getCustomerId());

        if (stm.executeUpdate() == 1) {

            if (OrderDetailsDAOImpl.add(orderDTO.getOrderId(),orderDetailDTO)) {
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            }
            connection.rollback();
            connection.setAutoCommit(true);
        }connection.rollback();
        connection.setAutoCommit(true);
        return false;
    }
}
