import java.util.*;
import java.io.*;

public class BOJ_7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt;
        Set<String> s = new TreeSet<>();
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            String name = stt.nextToken();
            String str = stt.nextToken();
            if(str.equals("enter")) s.add(name);
            else s.remove(name);
        }

        List<String> nameList = new ArrayList<>(s);
        for(int i=nameList.size()-1; i>=0; i--) {
            stb.append(nameList.get(i)).append("\n");
        }

        System.out.print(stb.toString());
    }
}
