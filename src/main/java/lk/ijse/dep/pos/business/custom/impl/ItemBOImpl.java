package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.ItemBO;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.db.HibernateUtil;
import lk.ijse.dep.pos.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.dep.pos.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBOImpl implements ItemBO {
    @Autowired
    private ItemDAO itemDAO;
    public List<ItemTM> getAllItems(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;

        List<Item> allItems = null;
        List<ItemTM> items = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            allItems = itemDAO.findAll();
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
        for (Item item : allItems) {
            items.add(new ItemTM(item.getCode(),item.getDescription(),item.getUnitPrice().doubleValue(),item.getQtyOnHand()));
        }
        return items;
    }

    public void saveItem(String code, String description, double unitPrice, int qtyOnHand){
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            itemDAO.save(new Item(code,description,BigDecimal.valueOf(unitPrice),qtyOnHand));
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateItem(String code, String description, double unitPrice, int qtyOnHand){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        itemDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            itemDAO.update(new Item(code,description,BigDecimal.valueOf(unitPrice),qtyOnHand));
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteItem(String code){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        itemDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            itemDAO.delete(code);
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public String generateNewItemId(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String lastItemId= itemDAO.getLastItemId();
            tx.commit();
            int lastNumber = Integer.parseInt(lastItemId.substring(1, 4));
            if (lastNumber==0) {
                lastNumber++;
                return "I001";
            } else if (lastNumber<9) {
                lastNumber++;
                return "I00" +lastNumber;
            } else if (lastNumber<99) {
                lastNumber++;
                return "I0" +lastNumber;
            }
            else{
                lastNumber++;
                return "I" +lastNumber;
            }
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
}
