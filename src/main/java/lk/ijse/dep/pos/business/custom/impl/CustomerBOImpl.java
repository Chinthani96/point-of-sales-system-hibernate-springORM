package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.CustomerBO;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.util.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    public List<CustomerTM> getAllCustomers() {
        List<Customer> allCustomers = null;
        List<CustomerTM> customerTMS = new ArrayList<>();
        try {
            allCustomers = customerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Customer customer : allCustomers) {
            customerTMS.add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerTMS;
    }

    public void saveCustomer(String id, String name, String address) throws SQLException {
        try {
            customerDAO.save(new Customer(id, name, address));
        } catch (Throwable t) {
            throw t;
        }

    }

    public void updateCustomer(String id, String name, String address) throws SQLException {
        try {
            customerDAO.update(new Customer(id, name, address));
        } catch (Throwable t) {
            throw t;
        }
    }

    public void deleteCustomer(String id) throws SQLException {
        try {
            customerDAO.delete(id);
        } catch (Throwable t) {
            throw t;
        }
    }

    public String generateNewCustomerId() {
        try {
            String lastCustomerId = customerDAO.getLastCustomerId();
            int lastNumber = Integer.parseInt(lastCustomerId.substring(1, 4));
            if (lastNumber == 0) {
//                lastNumber++;
                return "C001";
            } else if (lastNumber < 9) {
                lastNumber++;
                return "C00" + lastNumber;
            } else if (lastNumber < 99) {
                lastNumber++;
                return "C0" + lastNumber;
            } else {
                lastNumber++;
                return "C" + lastNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
