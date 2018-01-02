package base_mybatis;

import entity.User;

import java.util.HashMap;
import java.util.List;

public interface MybatisMapper {
    public abstract HashMap getSomeOneByid1();
    List<User> getSomeOneByid2();
    User getSomeOneByid3();
    User getSomeOneByid4();
    User getSomeOneByid5();
    boolean insertSome1(String login,String pass);
    boolean insertSome2(User user);

}
