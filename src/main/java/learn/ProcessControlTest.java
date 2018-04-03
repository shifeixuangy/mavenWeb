package learn;

/**
 * Created by shifeixuan on 2018/3/16.
 */
public class ProcessControlTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        String name =new String("haha");
        switch (3) {
            case 1:
                System.out.println("错误");
                break;
            case 2:
                System.out.println("正确");
                break;
            default:
                System.out.println("全部错误");
        }
        System.out.println("----------------  分割线  --------------");
        /*return的作用是结束当前方法，如果在循环或者分支语句中，遇到return，将结束剩下的代码，直接跳出方法外。*/
//        if (a == 1) {
//            System.out.println("测试return的用处");
//            return;
//        }
        /*break 退出循环体*/
        do {
            a++;
            if (a == 6) {
                break;
            }
            System.out.println("do while 循环 a=" + a);

        } while (a < 10);
        System.out.println("----------------  分割线  --------------");
/*continue 结束了当b==6循环时剩下的代码*/
        while (b < 10) {
            b++;
            if (b == 6) {
                continue;
            }
            System.out.println("do while 循环 b=" + b);
        }
        System.out.println("----------------  分割线  --------------");
        outerTest:for(int i=0;i<10;i++){
            System.out.println("for循环的外层 i="+i);
            for (int j = 0; j <10 ; j++) {
                if (j == 6) {
                 break outerTest;
                }
                System.out.println("for循环的内层空间 j="+j);
            }
        }
        System.out.println("----------------  分割线  --------------");
    }
}
