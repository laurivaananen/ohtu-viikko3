
package ohtu.data_access;

import java.util.List;
import ohtu.domain.User;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserDao {
    List<User> listAll();
    User findByName(String name);
    void add(User user);
}
