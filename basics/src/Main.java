import java.util.*;

public class Main{
    public static void main(String[]args){

        System.out.println("Hello World");
        List<Object> list = new ArrayList<>();

        list.add("Hello");
        list.add(543);
        list.add("World");
        Map<String,Integer> map = new HashMap<>();
        map.put("first", 25);

        for (Object o : list) {
            System.out.println(o);
        }
            map.forEach((k, v) -> {
                System.out.println("key:" + k + " value:" + v);
            });

        Optional<Integer> marks = Optional.ofNullable(map.get("first"));
        System.out.println(marks);
        marks.ifPresent(System.out::println);

    }
}