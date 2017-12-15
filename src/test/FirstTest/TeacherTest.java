package FirstTest;

import learn.Fruits;
import learn.Student;
import learn.Teacher;
import config.ComponentConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by admin on 2017/11/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {ComponentConfig.class, Fruits.class})
public class TeacherTest {

    @Autowired
    Teacher teacher;

    @Autowired
    Student student;

    @Autowired
    Fruits fruits;

    @Test
    public void teacherIsNull(){
        teacher.setJob("haha");
        System.out.println(student.teachersJob());
        fruits.setKind(new Long(200));
        System.out.println(fruits.getKind());
    }


}
