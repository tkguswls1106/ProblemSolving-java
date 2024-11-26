import java.util.*;
import java.io.*;

// - 알고리즘: 커스텀 클래스 구현 및 정렬

public class BOJ_10814 {
    public static int order = 1;

    public static class User implements Comparable<User> {
        public int age;  // 나이
        public String name;  // 이름
        public int join;  // 가입순서

        public User(int age, String name) {
            this.age = age;
            this.name = name;
            this.join = order++;
        }

        @Override
        public int compareTo(User b) {
            if(this.age != b.age) {
                return this.age - b.age;  // age 기준 오름차순
            }
            else {
                return this.join - b.join;  // join 기준 오름차순
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        List<User> userList = new ArrayList<>();
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(stt.nextToken());
            String name = stt.nextToken();
            userList.add(new User(age, name));
        }

        Collections.sort(userList);
        for(User user : userList) {
            stb.append(user.age).append(" ").append(user.name).append("\n");
        }

        String answer = stb.toString();
        System.out.print(answer);
    }
}
