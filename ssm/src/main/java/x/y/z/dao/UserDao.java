package x.y.z.dao;

import org.apache.ibatis.annotations.Select;
import x.y.z.domain.User;

import java.util.List;

public interface UserDao {

    @Select("select * from tb_user")
    List<User> findAll();
}
