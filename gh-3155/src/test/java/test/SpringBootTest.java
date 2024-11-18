package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@org.springframework.boot.test.context.SpringBootTest(classes = Application.class)
class SpringBootTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  void shouldGetAUser() {
    User user = userMapper.getUser(1);
    System.out.println("user--->" + user);
  }

  @Test
  void shouldInsertAUser() {
    User user = new User();
    user.setId(1);
    user.setName("User12");
    List<String> imageList = new ArrayList<>();
    imageList.add("https://www.xx.yy/123.png");
    user.setImages(imageList);
    // update error. images can't obtain the definition typeHandle -> JSONArrayTypeHandler
    userMapper.updateUser(user);
    //assertEquals(1, userMapper.insertUser(user));
  }

}
