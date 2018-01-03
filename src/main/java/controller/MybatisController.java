package controller;

import base_mybatis.MybatisMapper;
import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @RequestMapping("select")
    @ResponseBody
    public Map getData(HashMap map, ServletRequest servletRequest) {
        try {
            SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder();
            String realPath = servletRequest.getServletContext().getRealPath("/");
            String resource = realPath+"mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSession ss =build.build(is).openSession();
            MybatisMapper mm =ss.getMapper(MybatisMapper.class);
            HashMap user1 =mm.getSomeOneByid1();
            List user2 =mm.getSomeOneByid2();
            User user3 =mm.getSomeOneByid3();
            User user4 =mm.getSomeOneByid4();
            mm.insertSome0();
            int x = mm.insertSome1("222222","232323");
             mm.insertSome2("22211221","44222");
            User user =new User();
            user.setLoginName("fffff");
            user.setPassword("ddddd");
              mm.insertSome3(user);
            ss.commit();
            map.put("user",user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    public Map updateData(HashMap map){

        return map;
    }



}
