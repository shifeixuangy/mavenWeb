package learn;

import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/11/10.
 */
@Component
public class Teacher  {
    private String job;
    public void work() {

    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
