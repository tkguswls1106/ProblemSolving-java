import java.util.*;
import java.io.*;

// [ Sort 커스텀 정렬 ]
// - 만약 'num1 < num2'인 경우 :
// return num1 - num2; ==> 반환값이 음수(int)이므로, 오름차순 정렬.
// return num2 - num1; ==> 반환값이 양수(int)이므로, 내림차순 정렬.
// - 만약 'str1 < str2'인 경우 (사전식 비교) :
// return str1.compareTo(str2); ==> 반환값이 음수(int)이므로, 오름차순 정렬.
// return str2.compareTo(str1); ==> 반환값이 양수(int)이므로, 내림차순 정렬.

public class Sort_BOJ_1431 {
    public static int getSum(String str) {
        int sum = 0;
        for(char ch : str.toCharArray()) {  // str.toCharArray() => String to char[]
            if(Character.isDigit(ch)) {  // if('0' ~ '9')
                sum += (ch - '0');
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();

        List<String> list = new ArrayList<>();
        String inputStr;
        while(n-- > 0 ){
            inputStr = br.readLine();
            list.add(inputStr);
        }

        // List 커스텀 정렬
        Collections.sort(list, new Comparator<String>() {  // !!! 주의사항: Comparable 아니며, ()를 붙여주어야함. !!!
            @Override
            public int compare(String a, String b) {  // !!! 주의사항: compareTo 아니며, 파라미터 변수가 하나 더 들어감. !!!
                if(a.length() != b.length()) {
                    return a.length() - b.length();
                }
                else if(getSum(a) != getSum(b)) {
                    return getSum(a) - getSum(b);
                }
                else {
                    return a.compareTo(b);  // !!! 주의사항: compare 아님. !!!
                }
            }
        });

        for(String str : list) {
            stb.append(str).append("\n");
        }
        System.out.print(stb.toString());
    }
}
