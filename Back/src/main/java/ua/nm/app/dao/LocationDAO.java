package ua.nm.app.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.nm.app.models.LocationModel;
import org.hibernate.SessionFactory;


@Component
public class LocationDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public LocationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveLocation(LocationModel location) {
        Session session = sessionFactory.getCurrentSession();
        session.save(location);
    }

    @Transactional
    public LocationModel getLocation(String name) {
        LocationModel location;

        Session session = sessionFactory.getCurrentSession();
        location = session.get(LocationModel.class, name);

        return location;
    }
}
