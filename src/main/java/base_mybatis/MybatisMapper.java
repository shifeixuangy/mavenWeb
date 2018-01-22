package base_mybatis;

import entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MybatisMapper {
    public abstract HashMap getSomeOneByid1();
    List<User> getSomeOneByid2();
    User getSomeOneByid3();
    User getSomeOneByid4();
    List<User> getSome5(@Param("list") List list);

    @Insert("insert into dm_user (login_name, password) VALUES (\"11123124dff\",\"1112fhhhh\")")
    void insertSome0();
    int insertSome1(String login, String pass);
    void insertSome2(@Param("arg1")String login, @Param("arg2") String pass);
    void insertSome3(User user);
    void update1(User user);
    void update2(User user);


}
