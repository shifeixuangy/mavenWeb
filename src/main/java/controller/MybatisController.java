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
    public Map getData(HashMap map, ServletRequest servletRequest) throws IOException{
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
        boolean bl1 = mm.insertSome1("12341","123422");
        User user =new User();
        user.setLoginName("144fff");
        user.setPassword("25535fd");
        boolean bl2 = mm.insertSome2(user);
        map.put("user",user);
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    public Map updateData(HashMap map){

        return map;
    }



}
