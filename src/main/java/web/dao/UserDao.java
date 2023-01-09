package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    public List<User> getAllUsers();

    public void deleteUser(long id);

    public User getUserById(long id);

    public void updateUser(long id, User updatedUser);
}
