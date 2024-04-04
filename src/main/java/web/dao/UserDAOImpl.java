package web.dao;

import web.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    //@Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }


    @Override
    public void add(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }


    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        entityManager.flush();
    }


    @Override
    public void edit(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }


    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

}
