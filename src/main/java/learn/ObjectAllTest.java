package learn;

/**
 * Created by shifeixuan on 2018/6/26.
 */
public class ObjectAllTest extends ParentMethod implements ParentInterface {

    private String food;
    private String drink;
    public ObjectAllTest(String food) {
        this.food = food;
    }

    @Override
    public void eat(String x) {
        System.out.println("wo xiahjm " + x);
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}


interface  ParentInterface {
    public abstract void eat(String x);

/*    default void drink(){

    }*/
}

class  ParentMethod{
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}