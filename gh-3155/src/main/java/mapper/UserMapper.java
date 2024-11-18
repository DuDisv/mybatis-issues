package mapper;

import org.apache.ibatis.annotations.Param;
import test.User;

public interface UserMapper {
  User getUser(Integer id);

  int insertUser(@Param("user") User user);

  int updateUser(@Param("user") User user);
}
