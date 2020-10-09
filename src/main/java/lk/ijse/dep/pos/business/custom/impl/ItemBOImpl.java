package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.ItemBO;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;
import lk.ijse.dep.pos.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ItemBOImpl implements ItemBO {
    @Autowired
    private ItemDAO itemDAO;

    public List<ItemTM> getAllItems() throws Exception {

        List<Item> allItems = null;
        List<ItemTM> items = new ArrayList<>();
        allItems = itemDAO.findAll();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getCode(), item.getDescription(), item.getUnitPrice().doubleValue(), item.getQtyOnHand()));
        }
        return items;
    }

    public void saveItem(String code, String description, double unitPrice, int qtyOnHand) throws Exception {
        itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public void updateItem(String code, String description, double unitPrice, int qtyOnHand) throws Exception {
        itemDAO.update(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public void deleteItem(String code) throws Exception {
        itemDAO.delete(code);
    }

    public String generateNewItemId() throws SQLException {
        String lastItemId = itemDAO.getLastItemId();
        int lastNumber = Integer.parseInt(lastItemId.substring(1, 4));
        if (lastNumber == 0) {
            lastNumber++;
            return "I001";
        } else if (lastNumber < 9) {
            lastNumber++;
            return "I00" + lastNumber;
        } else if (lastNumber < 99) {
            lastNumber++;
            return "I0" + lastNumber;
        } else {
            lastNumber++;
            return "I" + lastNumber;
        }
    }
}
