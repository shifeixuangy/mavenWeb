package learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/11/10.
 */
@Component
public class Student {
    @Autowired
    private Teacher teacher;
    public String teachersJob(){
        return  teacher.getJob();
    }
}
