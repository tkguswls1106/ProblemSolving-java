import java.util.*;
import java.io.*;

// [ Sort 커스텀 정렬 ]
// - 만약 'num1 < num2'인 경우 :
// return num1 - num2; ==> 반환값이 음수(int)이므로, 오름차순 정렬.
// return num2 - num1; ==> 반환값이 양수(int)이므로, 내림차순 정렬.
// - 만약 'str1 < str2'인 경우 (사전식 비교) :
// return str1.compareTo(str2); ==> 반환값이 음수(int)이므로, 오름차순 정렬.
// return str2.compareTo(str1); ==> 반환값이 양수(int)이므로, 내림차순 정렬.
// etc) 정렬 조건으로 'return 0;' 반환 시, 해당 if문에서는 정렬없이 삽입 순서 그대로 유지 가능함.

// - 추가 Tip :
/*
// ◦ compareTo 비교 결과 :
// 'str1.compareTo(str2) > 0'인 경우, str1 > str2
// 'str1.compareTo(str2) < 0'인 경우, str1 < str2
// 'str1.compareTo(str2) == 0'인 경우, str1 == str2

// ◦ 커스텀 방법 1 :
Collections.sort(strList, (a, b) -> (b + a).compareTo(a + b));

// ◦ 커스텀 방법 2 :
Collections.sort(strList, (a, b) -> {
    String ab = a + b;
    String ba = b + a;
    return ba.compareTo(ab);
});

// ◦ 커스텀 방법 3 :
Collections.sort(strList, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        String ab = a + b;
        String ba = b + a;
        return ba.compareTo(ab);
    }
});
*/

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

        // - List(리스트) 커스텀 정렬 :
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
        // - Array(배열) 커스텀 정렬 :
        // Collections.sort 대신 Arrays.sort 사용할것.
        // int[] arr : 커스텀 선언 불가능 X
        // Integer[] arr : 커스텀 선언 가능 O
        // int[][] arr : 커스텀 선언 가능 O => 'new Comparator<int[]>()' 사용.

        for(String str : list) {
            stb.append(str).append("\n");
        }
        System.out.print(stb.toString());
    }
}
