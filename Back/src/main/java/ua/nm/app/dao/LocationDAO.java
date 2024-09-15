package ua.nm.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nm.app.models.LocationModel;


@Service
public class LocationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveLocation(LocationModel location) {
        entityManager.persist(location);
    }

    @Transactional
    public LocationModel getLocation(String name) {
        LocationModel location;

        location = entityManager.find(LocationModel.class, name);

        return location;
    }
}
