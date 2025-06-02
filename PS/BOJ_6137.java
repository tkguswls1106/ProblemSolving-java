import java.util.*;
import java.io.*;

// - 알고리즘: 투 포인터

public class BOJ_6137 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[] arr = new char[n];
        for(int i=0; i<n; i++) {
            char inputCh = br.readLine().charAt(0);
            arr[i] = inputCh;
        }
        
        int start = 0, end = arr.length-1;
        while(start<end && end<arr.length) {
            char ch1 = arr[start], ch2 = arr[end];
            if(ch1 < ch2) {
                stb.append(ch1);
                start++;
            }
            else if(ch1 > ch2) {
                stb.append(ch2);
                end--;
            }
            else {
                int idx1 = start+1, idx2 = end-1;
                boolean isSmallLeft = true;  // (서로 다른지점을 못찾았다해도 기본이동인 start++로 지정.)
                while(idx1 < idx2) {
                    char nextCh1 = arr[idx1], nextCh2 = arr[idx2];
                    if(nextCh1 == nextCh2) {
                        idx1++;
                        idx2--;
                    }
                    else {
                        if(nextCh1 < nextCh2) isSmallLeft = true;
                        else isSmallLeft = false;
                        break;
                    }
                }

                if(isSmallLeft == true) {
                    stb.append(ch1);
                    start++;
                }
                else {
                    stb.append(ch2);
                    end--;
                }
            }

            if(start == end) {  // 후위연산++ 이후
                stb.append(arr[start]);
                break;
            }
        }

        int divCnt = stb.length() / 80;  // 각각의 인덱스 80 위치당 줄바꿈 삽입.
        int insertIdx = 80;
        while(divCnt-- > 0) {
            stb.insert(insertIdx, "\n");
            insertIdx += 81;
        }

        System.out.print(stb.toString());
    }
}
