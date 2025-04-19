import java.util.*;
import java.io.*;

// - 알고리즘: 수학

public class BOJ_2869 {
    public static void main(String[] args) throws IOException {
        // 속도 측정 결과, 1줄만 입력함에도 불구하고
        // 'Scanner의 nextLine()'이 'BufferedReader의 readLine()'보다 느림을 확인할 수 있었음.
        // - Scanner의 nextLine() : 평균 100ms 이상
        // - BufferedReader의 readLine() : 평균 100ms 이하
        // ==> BufferedReader 사용을 채택하여 리팩토링.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(stt.nextToken());
        int b = Integer.parseInt(stt.nextToken());
        int v = Integer.parseInt(stt.nextToken());
        v -= b;  // 최종적으로 정상에 도달하면 밤에 미끄러지지않을것을 고려하기위함.

        int day = v / (a-b);
        if(v % (a-b) != 0) day++;  // 또는 'Math.ceil() 방식'을 사용할것.

        System.out.print((int) day);

        // !!! Math.ceil() 방식 !!!
        /*
        double day = (double) v / (a-b);
        day = Math.ceil(day);

        System.out.print((int) day);
        */
    }
}
