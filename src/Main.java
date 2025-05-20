import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[]args){

        System.out.println("Hello World");
        List<Object> list = new ArrayList<Object>();

        list.add("Hello");
        list.add(543);
        list.add("World");


        for(int i=0; i<list.size(); i++ ){
            System.out.println(list.get(i));
            System.out.println("Main.main");
            System.out.println("args = " + Arrays.toString(args));
            System.out.println("i = " + i);
        }
    }
}