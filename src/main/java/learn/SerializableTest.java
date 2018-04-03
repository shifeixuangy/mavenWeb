package learn;

import java.io.*;

/**
 * Created by shifeixuan on 2018/4/3.
 */
public class SerializableTest {
    public static void main(String[] args) throws IOException {
        Mouse mouse =new Mouse();
        mouse.setColor("红色");
        Computer computer =new Computer(mouse);
        computer.setKeyBorder("罗技");
        computer.setMonitor("华硕");
/*        monitor用transient进行修饰，表示monitor不进行序列化
                序列化用于两种：远程调用和存于数据库和文件。
                把对象转换 为二进制数据(如网络传输,存储数据库等),必须实现序列化接口 (java.io.Serializable).
                 把对象保存 在介质上(如写文件,读文件不是), 没有接口实现,一般指方法调用. */
        ObjectOutputStream objectOutputStream =new ObjectOutputStream(new FileOutputStream("hehe.txt"));
        objectOutputStream.writeObject(computer);
        objectOutputStream.flush();
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("hehe.txt"));
        try {
            Computer computerIns =(Computer) objectInputStream.readObject();
            System.out.println(computerIns.getMonitor()+"鼠标："+computerIns.getMouse().getColor());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
