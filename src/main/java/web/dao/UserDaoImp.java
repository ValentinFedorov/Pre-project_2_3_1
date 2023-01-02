package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

//    protected EntityManager getEntityManager() {
//        return entityManager.createEntityManager();
//    }



    public void add(User user) {
        entityManager.persist(user);

    }

    public List<User> getAllUsers() {
        List<User> allUsers;
        allUsers = entityManager.createQuery("select user from User user", User.class).getResultList();
        return allUsers;
    }

    public User getUserById(long id) {
        return  entityManager.find(User.class, id);
    }

    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    public void updateUser(long id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        entityManager.merge(user);
    }
}
