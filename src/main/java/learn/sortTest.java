package learn;


import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by shifeixuan on 2018/6/26.
 */
public class sortTest {

    public static void main(String[] args) {

        int[] array =new int[]{42,12,56,36,74,5,3,5,6,774,343,5,673,4,67,832,12,45,67};
        System.out.println(array.length);
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j]>array[j+1]){
                 int temp =array[j];
                 array[j]=array[j+1];
                 array[j+1]= temp;
                }
            }
        }
        System.out.println(array.length);
        System.out.println(Arrays.toString(array));
        int[] testA =new int[100];
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i <100 ; i++) {
            testA[i]=random.nextInt(1,1000);
        }
        /*array =testA.clone();*/
        System.out.println(Arrays.toString(testA));
        System.out.println(Arrays.binarySearch(array,36));
    }
}
