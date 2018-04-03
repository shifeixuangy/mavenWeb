package learn;


import java.util.Arrays;
import java.util.Random;

/**
 * Created by shifeixuan on 2018/3/16.
 */
public class ArrayTest {

    public static void main(String[] args) {
        long[] a = new long[1000000];
        for (int i = 0; i < 1000000; i++) {
            a[i] = new Random(System.currentTimeMillis()).nextLong();
        }
        System.out.println("开始排序" + System.currentTimeMillis());
        Arrays.sort(a);
        System.out.println("排序结束" + System.currentTimeMillis());
        System.out.println("开始排序" + System.currentTimeMillis());
/*        Arrays.parallelSort(a);*/
        System.out.println("排序结束" + System.currentTimeMillis());
//        System.out.println(a);
      /*  for (long b :
                a) {
            System.out.println(b);
        }*/
    }
}
