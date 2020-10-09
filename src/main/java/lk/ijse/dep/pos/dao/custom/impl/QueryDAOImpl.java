package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public List<CustomEntity> getOrderDetails() throws Exception {
        return session.createNativeQuery("SELECT o.id,o.date,o.customerId,c.name, Sum(OD.qty *OD.unitPrice) as `Total` from `Order` o INNER JOIN Customer C ON o.customerId = C.id INNER JOIN OrderDetail OD on o.id = OD.orderId GROUP BY o.id",CustomEntity.class).list();
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
