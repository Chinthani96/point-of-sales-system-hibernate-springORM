package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.util.ItemTM;

import java.util.List;

public interface ItemBO extends SuperBO {
    String generateNewItemId();
    List<ItemTM> getAllItems();
    void saveItem(String code, String description, double unitPrice, int qtyOnHand);
    void updateItem(String code, String description, double unitPrice, int qtyOnHand);
    void deleteItem(String code);

}
