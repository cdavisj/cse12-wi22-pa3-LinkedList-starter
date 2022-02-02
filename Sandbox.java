import java.util.*;

public class Sandbox {
    public static void print(MyLinkedList<Integer> list) {
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        Collection<Integer> test = list;

        System.out.println(test);
    }
}
