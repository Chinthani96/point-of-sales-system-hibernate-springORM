package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.util.CustomerTM;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerTM> getAllCustomers() throws Exception;
    void saveCustomer(String id, String name, String address) throws Exception;
    void updateCustomer(String id, String name, String address) throws Exception;
    void deleteCustomer(String id) throws Exception;
    String generateNewCustomerId() throws SQLException;
}
