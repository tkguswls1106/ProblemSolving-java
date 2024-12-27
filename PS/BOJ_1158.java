import java.util.*;

public class BOJ_1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder stb = new StringBuilder("<");

        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> qu = new LinkedList<>();
        for(int i=1; i<=n; i++) qu.offer(i);

        while(!qu.isEmpty()) {
            for(int i=0; i<k-1; i++) {
                qu.offer(qu.poll());
            }
            stb.append(qu.poll());
            if(!qu.isEmpty()) stb.append(", ");
        }
        stb.append(">");

        System.out.print(stb.toString());
    }
}
