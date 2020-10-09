package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.util.CustomerTM;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerTM> getAllCustomers();
    void saveCustomer(String id, String name, String address) throws SQLException;
    void updateCustomer(String id, String name, String address) throws SQLException;
    void deleteCustomer(String id) throws SQLException;
    String generateNewCustomerId();
}
