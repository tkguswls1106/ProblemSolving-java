import java.util.*;
import java.io.*;

public class BOJ_16496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        List<String> strList = new ArrayList<>();
        boolean isOnlyZero = true;
        while(n-- > 0) {
            String str = stt.nextToken();
            strList.add(str);
            if(isOnlyZero == true && !str.equals("0")) {
                isOnlyZero = false;
            }
        }

        if(isOnlyZero == true) {
            System.out.print("0");
            return;
        }
        
        // - 방법 1 :
        Collections.sort(strList, (strA, strB) -> (strB + strA).compareTo(strA + strB));  // 여기서 앞의 0도 비교되어 뒤로 밀림.

        // - 방법 2 :
        // Collections.sort(strList, (strA, strB) -> {
        //     String ab = strA + strB;
        //     String ba = strB + strA;
        //     return ba.compareTo(ab);  // 여기서 앞의 0도 비교되어 뒤로 밀림.
        // });

        // - 방법 3 :
        // Collections.sort(strList, new Comparator<String>() {
        //     @Override
        //     public int compare(String a, String b) {
        //         String ab = a + b;
        //         String ba = b + a;
        //         return ba.compareTo(ab);  // 여기서 앞의 0도 비교되어 뒤로 밀림.
        //     }
        // });

        for(int i=0; i<strList.size(); i++) {
            stb.append(strList.get(i));  // 문자열 추가 시
        }

        System.out.print(stb.toString());
    }
}
