package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.controller.PlaceOrderFormController;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO {
    public boolean addOrderDetails(String orderId, List<OrderDetailDTO> orderDetailDTO) throws SQLException, ClassNotFoundException;

    public boolean saveOrderDetails(String orderId,OrderDetailDTO orderDetailDTO) throws SQLException;
}
