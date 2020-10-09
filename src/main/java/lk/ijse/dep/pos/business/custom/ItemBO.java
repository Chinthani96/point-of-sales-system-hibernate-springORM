package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.util.ItemTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    String generateNewItemId() throws SQLException;
    List<ItemTM> getAllItems() throws Exception;
    void saveItem(String code, String description, double unitPrice, int qtyOnHand) throws Exception;
    void updateItem(String code, String description, double unitPrice, int qtyOnHand) throws Exception;
    void deleteItem(String code) throws Exception;

}
