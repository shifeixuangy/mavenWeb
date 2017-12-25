package controller;
import entity.User;
import jdbc.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by shifeixuan on 2017/11/24.
 */
@Component
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/loginView")
    public ModelAndView LoginInView() {
        ModelAndView mav = new ModelAndView("login");

        return mav;
    }

    @RequestMapping("/registerView")
    public String registerView() {
        return "register";
    }
    /*下述方法给以有多重方式写，根据 返回值注解等等*
    在仅有@RequestMapping注解时，返回值为void时，解析器会默认解析到当前url的页面
    即localhost:8080/user/register.jsp
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public void register(User user, HttpServletRequest request, Map model) throws SQLException {
      /*  out.println(user);
        model.put("ha", user);
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into dm_user (login_name,password,sex)value(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getLoginName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getSex());
        int row = ps.executeUpdate();
        if (row > 0) {
            out.println("成功添加里" + row + "条数据");
            ps.close();
            conn.close();
        } else {
            ps.close();
            conn.close();
        }*/
        model.put("ha","ha");
        logger.info("----测试");
        jdbcTemplate.update("insert into dm_user (login_name,password,sex)value(?,?,?)",user.getLoginName(),user.getPassword(),user.getSex());
    }

    @RequestMapping(value = "/login")
    public String loginIn(@Valid User user, HttpServletRequest request, Errors errors) throws SQLException {
        if (errors.hasErrors()) {
            return "login";
        }
        out.println(user);
        Map map = new HashMap();
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT * from dm_user where login_name =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getLoginName());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ps.close();
            conn.close();
            return "homePage";
        } else {
            return "redirect:/user/registerView";
        }

    }
}
