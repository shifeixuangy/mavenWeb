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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @RequestMapping("select")
    @ResponseBody
    public Map getData(HashMap map, ServletRequest servletRequest) throws Exception{
        SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder();
        String realPath = servletRequest.getServletContext().getRealPath("/");
        String resource = realPath+"WEB_INF\\classes\\mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSession ss =build.build(is).openSession();
        MybatisMapper mm =ss.getMapper(MybatisMapper.class);
        User user =mm.getSomeOneByid();
        map.put("user",user);
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    public Map updateData(HashMap map){

        return map;
    }



}
