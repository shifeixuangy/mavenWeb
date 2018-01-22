package entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by shifeixuan on 2017/11/24.
 */
public class User implements Serializable {
    @NotNull
    private  String loginName;
    @NotNull
    private String password;
    private String userName;
    private String sex;
    private Integer age;
    private String createTime;

    public User(){

    }
    public User(String loginName, String password, String userName, String sex, Integer age, String createTime) {
        this.loginName = loginName;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.createTime = createTime;
    }
    public User(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
