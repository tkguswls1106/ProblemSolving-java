import java.util.*;
import java.io.*;

public class BOJ_5635 {
    public static class Person implements Comparable<Person> {
        public String name;
        public int year;
        public int month;
        public int day;

        public Person(String name, int year, int month, int day) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Person b) {
            if(this.year != b.year) {
                return b.year - this.year;
            }
            else {
                if(this.month != b.month) {
                    return b.month - this.month;
                }
                else {
                    return b.day - this.day;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Person> pList = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String[] inputArr = br.readLine().split(" ");
            Person p = new Person(inputArr[0], Integer.parseInt(inputArr[3]), Integer.parseInt(inputArr[2]), Integer.parseInt(inputArr[1]));
            pList.add(p);
        }

        Collections.sort(pList);
        System.out.println(pList.get(0).name);
        System.out.println(pList.get(pList.size()-1).name);
    }
}
