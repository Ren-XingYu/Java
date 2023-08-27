package x.y.z.service;

import org.springframework.transaction.annotation.Transactional;
import x.y.z.domain.User;

import java.util.List;

@Transactional
public interface UserService {
    List<User> findAll();
}
