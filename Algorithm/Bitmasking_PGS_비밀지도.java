import java.util.*;

// [ 비트마스킹 (Bitmasking) ]
// - 개념 특징 :
// 정수의 2진수 표현을 자료구조로 쓰는 기법인 비트마스크(Bitmask)를 의미.
// - 추가 Tip :
// # num << n;
// 10진수 숫자 num을 이진수로 나타냈을때, 모든 비트를 왼쪽으로 n만큼 이동 => num *= (2^n)
// # num >> n;
// 10진수 숫자 num을 이진수로 나타냈을때, 모든 비트를 오른쪽으로 n만큼 이동 => num /= (2^n)
// # num |= (1 << n);
// 10진수 숫자 num을 이진수로 나타냈을때, 오른쪽기준 n번째 비트를 1로 설정.
// # num &= ~(1 << n);
// 10진수 숫자 num을 이진수로 나타냈을때, 오른쪽기준 n번째 비트를 0으로 설정.
// # if((num & (1 << n)) != 0){}
// 10진수 숫자 num을 이진수로 나타냈을때, 오른쪽기준 n번째 비트가 1인지 검사.
// ==> 참고로 int가 아닌 long 타입을 사용할 경우, 1 대신 1L을 사용할것.

class Solution {   
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            int rowNum10 = (arr1[i] | arr2[i]);  // 두가지 10진수 숫자를 이진 비교하여, 10진수 숫자를 반환.
            String rowStr2 = Integer.toString(rowNum10, 2);  // 2진수 문자열 (이 경우, 앞의 0들은 제거됨)
            StringBuilder rowStb = new StringBuilder();
            
            // n자릿수를 채우기에 부족한만큼 앞에 공백(0)을 삽입.
            // ex) 6자리인데 1101 => 001101
            int prevLen = n - rowStr2.length();
            while(prevLen-- > 0) {
                rowStb.append(' ');
            }
            
            // 변환하여 삽입.
            for(char ch : rowStr2.toCharArray()) {
                rowStb.append(ch == '1' ? '#' : ' ');
            }
            /*
            // 변환하여 삽입. (다른 방식)
            StringBuilder bitStb = new StringBuilder();
            while(rowNum10 > 0) {
                bitStb.append((rowNum10 & 1) == 1 ? '#' : ' ');  // 오른쪽 끝부분을 비교.
                rowNum10 >>= 1;  // 비교완료한 오른쪽 끝부분을 밀어내서 잘라냄.
            }
            rowStb.append(bitStb.reverse());
            */
            
            answer[i] = rowStb.toString();
        }
        
        return answer;
    }
}

public class Bitmasking_PGS_비밀지도 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] answer1 = solution.solution(5, new int[]{9,20,28,18,11}, new int[]{30,1,21,17,28});
        String[] answer2 = solution.solution(6, new int[]{46,33,33,22,31,50}, new int[]{27,56,19,14,14,10});

        System.out.println(Arrays.toString(answer1));  // => ["#####", "# # #", "### #", "#  ##", "#####"] 출력
        System.out.println(Arrays.toString(answer2));  // => ["######", "###  #", "##  ##", " #### ", " #####", "### # "] 출력
    }
}