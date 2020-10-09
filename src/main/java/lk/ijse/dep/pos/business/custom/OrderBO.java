package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.util.CustomerTM;
import lk.ijse.dep.pos.util.SearchOrderTM;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    void saveOrder(String id, Date date, CustomerTM customer) throws Exception;
    void saveOrderDetail(String orderId, String itemCode, int qty, double unitPrice) throws Exception;
    String generateNewOrderId() throws SQLException;
    List<SearchOrderTM> getOrderDetails() throws Exception;
}
