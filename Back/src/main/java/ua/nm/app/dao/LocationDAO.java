package ua.nm.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
