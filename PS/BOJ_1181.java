import java.util.*;
import java.util.stream.*;
import java.io.*;

public class BOJ_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        List<String> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String str =  br.readLine();
            list.add(str);
        }

        list = list.stream()
                    .distinct()  // 중복제거
                    .collect(Collectors.toList());

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if(a.length() != b.length()) {
                    return a.length() - b.length();
                }
                else {
                    return a.compareTo(b);
                }
            }
        });
        
        for(String str : list) {
            stb.append(str).append("\n");
        }
        System.out.print(stb.toString());
    }
}
