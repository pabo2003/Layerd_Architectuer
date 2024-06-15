package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.controller.PlaceOrderFormController;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO{
    Connection connection = DBConnection.getDbConnection().getConnection();

    public OrderDetailsDAOImpl() throws SQLException, ClassNotFoundException {
    }

    @Override
    public boolean addOrderDetails(String orderId, List<OrderDetailDTO> orderDetailDTO) throws SQLException, ClassNotFoundException {
        for(OrderDetailDTO orderDetailDTO1 : orderDetailDTO){
            String itemCode = orderDetailDTO1.getItemCode();
            BigDecimal unitPrice = orderDetailDTO1.getUnitPrice();
            int qtyOnHand = orderDetailDTO1.getQty();
            Boolean added = saveOrderDetails(orderId, new OrderDetailDTO(itemCode,qtyOnHand,unitPrice));

            ItemDTO itemDTO = PlaceOrderFormController.findItem(orderDetailDTO1.getItemCode());
            itemDTO.setQtyOnHand(itemDTO.getQtyOnHand()-orderDetailDTO1.getQty());
            ItemDAOImpl itemDAO = new ItemDAOImpl();
            int done = itemDAO.updateItem(itemDTO.getQtyOnHand(),itemDTO.getCode(),itemDTO);

            if(added && !(done>0)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(String orderId,OrderDetailDTO orderDetailDTO) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1, orderId);
        stm.setString(2, orderDetailDTO.getItemCode());
        stm.setBigDecimal(3, orderDetailDTO.getUnitPrice());
        stm.setInt(4, orderDetailDTO.getQty());
        return stm.executeUpdate() == 1;
    }
}
