package learn;

/**
 * Created by admin on 2017/11/10.
 */
public class Computer extends BaseEntity{
    private String keyBorder;
    private Mouse mouse;
      private  transient String monitor;

    public Computer(Mouse mouse){
        this.mouse =mouse;
    }

    public String getKeyBorder() {
        return keyBorder;
    }

    public void setKeyBorder(String keyBorder) {
        this.keyBorder = keyBorder;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
}
