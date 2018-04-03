package controller;

import base_mybatis.MybatisMapper;
import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ExportExcel;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    private SqlSession sqlSession;

    @RequestMapping("/select")
    @ResponseBody
    public Map getData(HashMap map, ServletRequest servletRequest) {
        try {
            SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder();
            String realPath = servletRequest.getServletContext().getRealPath("/");
            String resource = realPath + "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSession ss = build.build(is).openSession();
            MybatisMapper mm = ss.getMapper(MybatisMapper.class);
            Random ra = new Random(System.currentTimeMillis());
            //查询
            HashMap select1 = mm.getSomeOneByid1();
            List select2 = mm.getSomeOneByid2();
            User select3 = mm.getSomeOneByid3();
            User select4 = mm.getSomeOneByid4();
            List list = new ArrayList();
            list.add("123");
            list.add("1234");
            List select5 = mm.getSome5(list);
            //插入数据

            mm.insertSome0();
            mm.insertSome1(String.valueOf(ra.nextLong()), String.valueOf(ra.nextLong()));
            mm.insertSome2(String.valueOf(ra.nextLong()), String.valueOf(ra.nextLong()));
            User user = new User();
            user.setLoginName(String.valueOf(ra.nextLong()));
            user.setPassword(String.valueOf(ra.nextLong()));
            mm.insertSome3(user);
            //更新

            User userA = new User();
            User userB = new User();
            userA.setPassword("123");
            userA.setLoginName(String.valueOf(ra.nextLong()));
            userB.setLoginName("1234");
            userB.setPassword(String.valueOf(ra.nextLong()));
            mm.update1(userA);
            mm.update2(userB);
            ss.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map updateData(HashMap map, HttpServletRequest Request, HttpServletResponse response) throws Exception {
        ExportExcel.renderExcel(response, Request, "猜猜.xls", ExportExcel.jsonToObj(ExportExcel.getJsonString()));
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map deleteData(HashMap map) throws Exception {
        map = sqlSession.getMapper(MybatisMapper.class).getSomeOneByid1();
        return map;
    }


}
