# Java 정리

<details open>
  <summary><strong>&nbsp;📖&nbsp;&nbsp;목차</strong></summary>

1. &nbsp;&nbsp;[기본](#기본)
2. &nbsp;&nbsp;[자료형](#자료형)
3. &nbsp;&nbsp;[char](#char)
4. &nbsp;&nbsp;[String](#string)
5. &nbsp;&nbsp;[StringBuilder](#stringbuilder)
6. &nbsp;&nbsp;[StringTokenizer](#stringtokenizer)
7. &nbsp;&nbsp;[Array & List](#array--list)
8. &nbsp;&nbsp;[Set](#set)
9. &nbsp;&nbsp;[Map](#map)
10. &nbsp;&nbsp;[기타](#기타-1)
</details>

<br>

## 기본

### import

```java
import java.util.*;  // Scanner, ArrayList, StringTokenizer
import java.util.stream.*;  // Collectors
import java.io.*;  // BufferedReader
import java.awt.*;  // Point ('util.List'와 중복 주의 -> 'awt.Point'로 선언할것.)
import java.math.*;  // BigInteger
```

### 길이

- 문자열(String) : `length()`
- 배열(int[], Integer[], String[]..) : `length`
- 컬렉션(List..) : `size()`
- 주의 : String은 `length()`, String[]은 `length`

### 입출력

- 데이터 양이 적은 경우
    - 입력 : `Scanner`
    - 출력 : `System.out.print`

```java
// Scanner
Scanner sc = new Scanner(System.in);
String strLine = nextLine();  // 줄바꿈 기준 (띄어쓰기 X) - 한줄씩 문자열 입력
String str = sc.next();  // 띄어쓰기 및 줄바꿈 기준 - 문자열 입력
int num1 = sc.nextInt();  // 띄어쓰기 및 줄바꿈 기준 - 정수 입력
double num2 = sc.nextDouble();  // 띄어쓰기 및 줄바꿈 기준 - 실수 입력
boolean isTrue = nextBoolean();  // 띄어쓰기 및 줄바꿈 기준 - 참거짓 입력

// System.out.print
System.out.print("Hello");
System.out.printf("%s", "Hello");
System.out.println("Hello");
System.out.println(String.format("%d", 3));
```

- 데이터 양이 많은 경우
    - 입력 : `BufferedReader`
    - 출력 : `BufferedWriter` 또는 `StringBuilder`
    - Buffered 입출력문 사용 시, throws IOException 작성할것.
    - BufferedReader로 공백 기준 입력 시, StringTokenizer 또는 split 을 함께 운용할것.

```java
// BufferedReader & BufferedWriter (+ throws IOException)
public class Example {
    public static void main(String[] args) throws IOException {  // IOException 꼭 작성할것.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 문자열로 읽고 출력하기
        String inputLine = br.readLine();
        bw.write("입력 받은 문자열: " + inputLine);  // write의 파라미터는 반드시 String일것.

        // 숫자로 읽고 출력할것 버퍼에 담기 (아직 출력 X)
        int inputNum = Integer.parseInt(br.readLine());
        bw.write("입력 받은 숫자: " + inputNum);  // write의 파라미터는 반드시 String일것.

        // 버퍼 비우기 (버퍼에 쌓인것들 출력)
        bw.flush();

        // 리소스 닫기
        br.close();
        bw.close();  // flush 포함됨.
    }
}
```

- `bw.write()`: 버퍼에 출력할 값을 쭉쭉 담아감 (출력되지 않음)
- `bw.flush()`: 중간출력. 버퍼에 남아있는 데이터를 비운 후 출력
- `bw.close()`: 최종출력. 버퍼에 남아있는 데이터를 비운 후 출력하고 스트림 닫음

### 기타

- max 초기값 선언
  - `int maxValue = (int) 1e9;` (+10억)
  - `int maxValue = Integer.MAX_VALUE;` (약 +21억)
- min 초기값 선언
  - `int minValue = -1;` (-1 : 양수 max값 갱신 용도)
  - `int minValue = Integer.MIN_VALUE;` (약 -21억)
- 삼각형의 두 변 길이의 합은 항상 나머지 변보다 큼 : `side1 + side2 > side3`
- 두 점을 이은 선분의 기울기 공식 : `double (y1-y2)/(x1-x2)`
- 순열,조합 공식
  - 순열 개수
    - nPr = n! / (n-r)! = `n x (n-1) x (n-2) .. 총 r번 곱셈`
    - ex)&nbsp;&nbsp;₇P₃ = 7 x 6 x 5&nbsp;&nbsp;..&nbsp;&nbsp;7부터 시작해서 총 3번 곱셈
  - 조합 개수
    - nCr = n! / {(n-r)! x r!} = `nPr / r!`
    - ex)&nbsp;&nbsp;₇C₃ = ₇P₃ / 3! = (7 x 6 x 5) / (3 x 2 x 1)
- 소수,짝수,홀수 공식
  - 소수의 합
    - 가능 : `4이상의 짝수 = 소수 + 소수` (골드바흐의 추측)
    - 가능 : `6이상의 짝수 = 홀수 소수 + 홀수 소수`
    - 불가능 : `3이하의 수 = 소수 + 소수`
  - 짝수,홀수의 합
    - 반드시 : `3이상의 홀수 = 짝수 + 홀수`
    - 반드시 : `짝수 = 홀수 + 홀수`
  - 단일
    - 짝수 소수는 2만 존재함.
    - 2 이외의, 모든 소수는 홀수임.
    - 홀수가 반드시 소수인것은 아님. (ex. 1,9,15)

### Tip

- 여러 변수의 동시 할당 주의사항
    - 이미 선언되어있는 변수들에 대하여, 한줄로 동시 재할당은 불가능.
    - 가능 (선언 O) : `int x = 0, y = 0;`
    - 불가능 (선언 X) : `x = 0, y = 0;` → 이는 줄바꿈하여 개별 할당해야함.
- 파라미터 순서는 보통 ‘인덱스,개수,찾을값’ 순임.
    - 보통 : `stb.setCharAt(idx, 'a');`
    - 예외 : `str = String.join("", list);` 또는 `index = str.indexOf("welcome",10);`
- Pair 주의사항
    - 자바에서 pair 및 tuple은 직접 구현하여 사용할것.
    - BFS의 큐에는 `pair` 없이, 대신 `int[]`를 넣어서도 구현 가능.
    - Pair 클래스 내 관련 메소드를 오버라이드하지 않으면, `HashSet<Pair>` 또는 `HashSet<int[]>`를 선언해도, 중복제거 및 자동정렬이 제대로 이루어지지 않음.
- 코딩테스트 주의사항
    - 시간 초과
        - 코딩테스트에서 문제의 제한시간은 보통 `1~5초` 정도임.
        일반적으로 `1초`에 실행 가능한 최대 연산 횟수는 약 `1억번`
        - 시간복잡도의 log는 밑이 10이 아닌 2인 `log₂N`를 의미
        - ex)&nbsp;&nbsp;n <= 최대 15000, 시간복잡도 : 최악 O(n^2) 일때
          ```
          시간복잡도 O(15000^2)
          = 15000x15000 = 2억2500만번 횟수
          >= 2억번 횟수 (2초 이상 소요)
          ```
          만약 문제의 시간 제한이 1초 또는 2초라면, 시간 초과로 틀리게 됨.
    - 메모리 초과
        - ex)&nbsp;&nbsp;n <= 최대 1500, 공간복잡도 : 2차원 배열 O(n^2) 일때
          ```
          int형은 4바이트이므로, 공간복잡도 O(int_1500^2)
          = 1500x1500x4 B = 900만 B (900만 바이트)
          = 900만/1024 KB = 약 900만/1000 KB = 약 9000 KB (9000 킬로바이트)
          = 9000/1024 MB = 약 9000/1000 MB = 약 9 MB (9 메가바이트)
          ```
          만약 문제의 메모리 제한이 9MB 미만이라면, 메모리 초과로 틀리게 됨.
    - 메모리 ↔ int 개수
        - 1 MB = 100만 B (25만개 x 4B) = 25만개 int
        - 메모리 MB : int 개수 = 1 : 25만
        - 메모리 제한 `a MB` = 최대 길이 `int[25만 x a]`
        - 메모리 제한 `a GB` = 최대 길이 `int[25만 x a x 1024]`
        - ex)&nbsp;&nbsp;메모리 제한이 128MB 일때
          ```
          메모리(공간복잡도) 128MB
          = int[] 배열의 최대 길이는 '25만 x 128'
          = 최대 int[3200만] 배열
          ```
          단, 코드 내 다른 변수의 메모리도 고려해야 함.

<br>

## 자료형

### 기본형 & 참조형

- 기본형 : int, double, char, …
- 참조형 : Integer, Double, Character, String, …

### 배열 & 리스트

- 기본형 배열 : int[], double[], char[], …
- 참조형 배열 : Integer[], Double[], Character[], String[], …
- 참조형 리스트 : List&lt;Integer>, List&lt;Double>, List&lt;Character>, List&lt;String>, …

### 변환

- 배열 ↔ 리스트 (with Stream)
    - 배열 (int[], Integer[]) → 리스트 (List&lt;Integer>)
        - `list = Arrays.stream(arr) ~`
        - ? → List&lt;Integer> : `~ .collect(Collectors.toList());`
    - 리스트 (List&lt;Integer>) → 배열 (int[], Integer[])
        - `arr = list.stream() ~`
        - ? → int[] : `~ .toArray();`
        - ? → Integer[] : `~ .toArray(Integer[]::new);`
- 기본형 배열 ↔ 참조형 리스트
    - 기본형 배열 (int[]) → 참조형 리스트 (List&lt;Integer>)
        - `Integer::valueOf`
        - `mapToObj(Integer::valueOf)` 또는 `boxed()`
        - mapToObj 가능 / mapToInt 불가능
    - 참조형 리스트 (List&lt;Integer>) → 기본형 배열 (int[])
        - `Integer::intValue`
        - `mapToInt(Integer::intValue)` 또는 `mapToInt(i→i)`
        - mapToObj 불가능 / mapToInt 가능
- 기타 변환
    - 기본형 배열 (int[]) → 참조형 배열 (Integer[])
        - mapToObj 불가능 / mapToInt 불가능 / boxed 가능
    - 기본형 배열 (int[]) → 참조형 리스트 (List&lt;Integer>)
        - Arrays.asList(arr) 불가능
            
            ```java
            // 불가능 예시 X
            int[] intArr = {1,2,3};
            List<Integer> integerList = new ArrayList<>(Arrays.asList(intArr));  // 에러 발생.
            ```
            
    - 참조형 배열 (Integer[]) → 참조형 리스트 (List&lt;Integer>)
        - Arrays.asList(arr) 가능
            
            ```java
            // 가능 예시 O
            Integer[] integerArr = {1,2,3};
            List<Integer> integerList = new ArrayList<>(Arrays.asList(integerArr));  // 정상 작동.
            ```

<br>

## char

### 메소드

- charAt

```java
// String에서 char 가져오기 (반환타입: char)
char ch = str.charAt(idx);
```

- isDigit

```java
// 문자의 숫자 확인 (반환타입: boolean)
Character.isDigit('2')  // '0'~'9'인 char문자가 맞다면
```

- toUpperCase & toLowerCase

```java
// 대소문자 변환 (반환타입: char)
ch = Character.toUpperCase(ch);  // char문자를 대문자로 변환.
ch = Character.toLowerCase(ch);  // char문자를 소문자로 변환.
```

- isUpperCase & isLowerCase

```java
// 대소문자 확인 (반환타입: boolean)
Character.isUpperCase('a')  // false
Character.isLowerCase('a')  // true
```

### 변환

- char ↔ int

```java
// char -> int
int num = ch - '0';  // (int)는 붙여도 안붙여도 상관없음.

// int -> char
char ch = (char) (num + '0');  // (char) 빼면 에러남.
```

- char[] ↔ String

```java
// char[] -> String
String str = String.valueOf(charArray);  // 또는 new String(charArray);

// String -> char[]
char[] charArray = str.toCharArray();
```

<br>

## String

### 메소드

- indexOf

```java
// 검색 (반환타입: int)
int index = str.indexOf('w');  // 문자 검색
int index = str.indexOf("welcome");  // 문자열 검색
int index = str.indexOf("welcome", 10);  // 문자열을 10번째 index부터 검색

// - 주의사항 : 참고로 int[] 이러한 배열에는 사용이 불가능함.
```

- contains

```java
// 값존재여부 확인 (반환타입: boolean)
boolean isExist = str1.contains(str2);

// - 주의사항 : char 검색은 안되므로, 그럴경우엔 밑처럼 호출할것.
boolean isExist = str.contains(String.valueOf(ch));
```

- equals

```java
// 동등성 비교 (반환타입: boolean)
boolean isSame = str1.equals(str2);  // true
```

- compareTo

```java
// 사전식 비교 (반환타입: int)
int compareResult = str1.compareTo(str2);

// - 비교 결과 :
// 'str1.compareTo(str2) > 0'인 경우 : str1 > str2
// 'str1.compareTo(str2) < 0'인 경우 : str1 < str2
// 'str1.compareTo(str2) == 0'인 경우 : str1 == str2
// (VSCode 기준 출력값 : 1,-1,0)
// 참고로 "aa" < "aaa"
```

- repeat

```java
// 반복 (반환타입: String)
str = str.repeat(n);  // 문자열을 n번만큼 반복한 문자열을 반환
```

- substring

```java
// 부분 추출 (반환타입: String)
str = str.substring(index);  // index부터 끝까지 추출
str = str.substring(beginIndex, endIndex);  // beginIndex부터 endIndex-1까지 추출

// - 주의사항 : subString아님. substring으로 모두 소문자임.
```

- replace

```java
// 대체 (반환타입: String)
str = str.replace("a", "!");  // 문자열 내 모든 a를 !로 대체
```

- replaceAll

```java
// 대체 + 정규표현식 가능 (반환타입: String)
str = str.replaceAll("a", "!");  // 문자열 내 모든 a를 !로 대체
str = str.replaceAll("[abcde]", "!");  // 문자열 내 모든 a,b,c,d,e를 !로 대체

// - replace vs replaceAll 차이점 :
// replace와 사용법은 같지만, replaceAll은 정규표현식을 다룰수 있음.

// - 정규 표현식 예시 :
// "[abcde]" : 문자 a,b,c,d,e
// "[^a-e]" : 문자 a,b,c,d,e를 제외한 나머지
// "aya|ye|woo|ma" : 문자열 aya,ye,woo,ma
// "[\\[\\],]" : 문자 [,],','
```

- toUpperCase & toLowerCase

```java
// 대소문자 변환 (반환타입: String)
str = str.toUpperCase();  // 문자열 내 모든 문자를 대문자로 변환.
str = str.toLowerCase();  // 문자열 내 모든 문자를 소문자로 변환.
```

### 변환

- String ↔ 다른 타입

```java
// String -> 다른 타입
int num = Integer.parseInt(str_int);  // 참고로 파라미터에 char 불가능.
float f = Float.parseFloat(str_float);
double d = Double.parseDouble(str_double);
long l = Long.parseLong(str_long);

// 다른 타입 -> String
String str = String.valueOf(num);  // 또는 Integer.toString(num);
```

- String ↔ String[]

```java
// String -> String[]
String[] arr1 = str.split("");
String[] arr2 = str.split("[a-z]");  // 정규표현식 사용 가능

// String[] -> String
str = String.join("", arr);
```

- String ↔ List&lt;String>

```java
// String -> List<Sting>
List<String> list = str.chars()  // chars()는 IntStream을 반환하므로, (char)을 붙여주어야함.
                       .mapToObj(ch -> String.valueOf((char) ch))
                       .collect(Collectors.toList());

// List<String> -> String
str = String.join("", list);
```

<br>

## StringBuilder

### 특징

- String
    - 불변성 : 수정 시 매번 새로운 객체를 생성하여, 삽입/수정 등의 성능에 취약함.
    - 용도 : 불변성 덕분에 문자 검색/비교와 같은 기본적인 문자열 처리에 적합함.
- StringBuilder
    - 가변성 : 문자열을 배열처럼 관리함으로써, 삽입 및 수정 등의 성능에 우수함.
    - 용도 : 가변성 덕분에 추가/삭제/삽입/뒤집기 등 문자열 변경 작업에 적합함.

### 메소드

```java
// 선언
StringBuilder stb = new StringBuilder();
StringBuilder stb = new StringBuilder(str);

// 삽입 (반환타입: StringBuilder)
stb.append('a');  // 문자 삽입
stb.append("abc");  // 문자열 삽입
stb.append('a').append("bc");  // 반환타입이 StringBuilder이므로, 메소드 체이닝 가능.
stb.append(num).append("\n");  // stb.append(num + "\n"); 보다 성능 우수.

// 문자 조회 (반환타입: char)
char ch = stb.charAt(idx);  // 인덱스 기반 조회

// 문자 교체 (반환타입: void)
stb.setCharAt(idx, 'a');  // 인덱스 기반 교체

// 뒤집기 (반환타입: void)
stb.reverse();

// 문자열로 변환 (반환타입: String)
String str = stb.toString();
```

### Tip

- 초기화
    - `new 연산` 대신 `setLength(0)` 사용 고려
- 빈 상태 확인
    - `stb.isEmpty()`는 Java 15부터 지원되므로, `stb.length() > 0` 사용 권장

<br>

## StringTokenizer

### 메소드

```java
// 띄어쓰기를 기준으로 문자열을 분리
StringTokenizer stt = new StringTokenizer(str);
StringTokenizer stt = new StringTokenizer(str, " ");

// 구분자를 기준으로 문자열을 분리
StringTokenizer stt = new StringTokenizer(str, ",");

// hasMoreTokens() & nextToken()
while(stt.hasMoreTokens()) {  // (반환타입: boolean)
    String nextStr = stt.nextToken();  // (반환타입: String)
    System.out.println(nextStr);
}
```

<br>

## Array & List

### 특징

- 배열(Array) : 크기 고정. 조회 많은 경우 효과적
- 리스트(List) : 크기 가변. 삽입/삭제 많은 경우 효과적
- ArrayList 선언 : `List<Integer> list = new ArrayList<>();`
- LinkedList 선언 : `LinkedList<Integer> list = new LinkedList<>();`
- LinkedList 메소드
    - `list.addFirst(요소값)` , `addLast(요소값)` , `add(인덱스,요소값)`
    - `list.removeFirst()` , `removeLast()` , `remove(인덱스)`
- ArrayList vs LinkedList : LinkedList가 ArrayList보다 조회는 느리지만 삽입/삭제는 빠름

### Stream

- 주의사항
    - 반환타입이 void가 아니므로, 반환값을 변수에 다시 재할당 해주어야함.
- 스트림 호출 접두사
    - int[], Integer[] → ?  : `Arrays.stream(arr) ~`
    - List&lt;Integer> → ? : `list.stream() ~`
- 스트림 반환 접미사
    - ? → int[] : `~ .toArray();`
    - ? → Integer[] : `~ .toArray(Integer[]::new);`
    - ? → List&lt;Integer> : `~ .collect(Collectors.toList());`

### 메소드

- int[] - 정렬, 중복제거

```java
// 오름차순 정렬 (반환타입: void)
Arrays.sort(arr);  // 또는 arr = Arrays.stream(arr).sorted().toArray();

// 내림차순 정렬 (반환타입: Array)
arr = Arrays.stream(arr)
	    .boxed()  // int[] -> Integer[] 변환 (sorted 내림차순때만 필요)
	    .sorted((a, b) -> b - a)  // 내림차순 정렬 (boxed 및 mapToInt 필요)
	    .mapToInt(i->i)  // Integer[] -> int[] 변환 (sorted 내림차순때만 필요)
	    .toArray();  // int[]로 최종 리턴
// 만약 Integer[] 이라면, 'Arrays.sort(arr, Collections.reverseOrder());' 사용 가능.
						
// 중복 제거 (반환타입: Array)
arr = Arrays.stream(arr)
	    .distinct()  // 중복제거
	    .toArray();  // int[]로 최종 리턴
						
// 중복 제거 & 정렬 (반환타입: Array)
arr = Arrays.stream(arr)
	    .distinct()  // 중복제거
	    .sorted()  // 오름차순 정렬 (boxed로 Integer[] 변환하는 절차 필요없음)
	    .toArray();  // int[]로 최종 리턴
```

- List&lt;Integer> - 정렬, 뒤집기, 중복제거

```java
// 오름차순 정렬 (반환타입: void)
Collections.sort(list);

// 내림차순 정렬 (반환타입: void)
Collections.sort(list, Collections.reverseOrder());

// 뒤집기 (반환타입: void)
Collections.reverse(list);

// 중복 제거 (반환타입: List)
list = list.stream()
	   .distinct()  // 중복제거
	   .collect(Collectors.toList());  // 가변 List<Integer>로 최종 리턴
						
// 중복 제거 & 정렬 (반환타입: List)
list = list.stream()
	   .distinct()  // 중복제거
	   .sorted()  // 오름차순 정렬 (boxed로 Integer[] 변환하는 절차 필요없음)
	   .collect(Collectors.toList());  // 가변 List<Integer>로 최종 리턴
```

- int[] - 합계, 평균, 최소, 최대, 개수

```java
// 합계 (반환타입: int)
int sum = Arrays.stream(arr).sum();  // 배열이 비어있어도, 기본 sum=0 처리됨.

// 평균 (반환타입: double)
double avg = Arrays.stream(arr).average().orElse(0.0);

// 최소 (반환타입: int)
int min = Arrays.stream(arr).min().orElse(0);

// 최대 (반환타입: int)
int max = Arrays.stream(arr).max().orElse(0);

// 개수 (반환타입: long)
long cnt = Arrays.stream(arr).filter(num -> num > 3).count();

// - 필터링 Tip :
// 필터링 적용 시, stream 바로뒤에 호출하면되며 나머지는 동일함.
// ex) Arrays.stream(arr).filter(조건).~
```

- List&lt;Integer> - 합계, 평균, 최소, 최대, 개수

```java
// 합계 (반환타입: int)
int sum = list.stream().mapToInt(i->i).sum();  // 리스트가 비어있어도, 기본 sum=0 처리됨.

// 평균 (반환타입: double)
double avg = list.stream().mapToInt(i->i).average().orElse(0.0);

// 최소 (반환타입: int)
int min = list.stream().mapToInt(i->i).min().orElse(0);

// 최대 (반환타입: int)
int max = list.stream().mapToInt(i->i).max().orElse(0);

// 개수 (반환타입: long)
long cnt = list.stream().filter(num -> num > 3).count();
// 요소 개수 카운트는 타입에 관계없이 작동하므로, 리스트에서 mapToInt() 없이도 count() 호출이 가능함.

// - 필터링 Tip :
// 필터링 적용 시, stream 바로뒤에 호출하면되며 나머지는 동일함.
// ex) list.stream().filter(조건).~
```

- All - 값 채우기

```java
// int[] (반환타입: void)
Arrays.fill(arr, 7);  // 전체 요소 값을 7로 할당.
Arrays.fill(arr, 2, 5, 7);  // 2~4인덱스 요소 값을 7로 할당.

// List<Integer> (반환타입: void)
Collections.fill(list, 7);  // 전체 요소 값을 7로 할당.
Collections.fill(list.subList(2, 5), 7);  // 2~4인덱스 요소 값을 7로 할당.
```

- All - 깊은 복사

```java
// int[] (반환타입: int[])
int[] afterArr = beforeArr.clone();  // 또는 Arrays.copyOf(beforeArr, beforeArr.length);

// List<Integer> (반환타입: ArrayList<Integer>)
List<Integer> afterList = new ArrayList<>(beforeList);
// 또는
List<Integer> afterList = beforeList.stream().collect(Collectors.toList());
// 또는
List<Integer> afterList = new ArrayList<>();
afterList.addAll(beforeList);

// - 사용 이유 :
// int,String 이런건 참조타입이 아니라서 그냥 int afterNum = beforeNum; 이렇게 복사해도되지만,
// int[],Integer[],ArrayList<Integer> 이런건 참조타입이라서 그저 위처럼 =로 복사(얕은 복사)하면, 이후 변경사항이 이전 배열에도 반영됨.
```

- 기타 메소드

```java
// 리스트 내 요소 교환 (반환타입: void)
Collections.swap(list, 0, 2);  // 0인덱스 요소와 2인덱스 요소를 교환.

// 리스트 회전 (반환타입: void)
Collections.rotate(list, 2);  // 리스트를 오른쪽으로 2칸 밀음. (음수면 왼쪽으로 밀음.)

// - 주의사항 : Arrays.swap 및 Arrays.rotate는 존재하지 않음.
```

### 변환

- 기본형 배열 ↔ 참조형 리스트
    - int[] ↔ List&lt;Integer>

```java
// int[] -> List<Integer>
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());  // boxed() 또는 mapToObj(Integer::valueOf)

// List<Integer> -> int[]
int[] arr = list.stream().mapToInt(i->i).toArray();  // mapToInt(i->i) 또는 mapToInt(Integer::intValue)
```

- 기본형 배열 ↔ 참조형 배열
    - int[] ↔ Integer[]

```java
// int[] -> Integer[]
Integer[] integerArr = Arrays.stream(intArr).boxed().toArray(Integer[]::new);  // mapToObj(Integer::valueOf)는 사용 불가능.

// Integer[] -> int[]
int[] intArr = Arrays.stream(integerArr).mapToInt(i->i).toArray();  // mapToInt(i->i) 또는 mapToInt(Integer::intValue)
```

- 참조형 배열 ↔ 참조형 리스트
    - Integer[] ↔ List&lt;Integer>
    - String[] ↔ List&lt;String>

```java
// Integer[] -> List<Integer>
List<Integer> list = new ArrayList<>(Arrays.asList(arr));  // 또는 Arrays.stream(arr).collect(Collectors.toList());
// String[] -> List<String>
List<String> list = new ArrayList<>(Arrays.asList(arr));  // Arrays.stream(arr).collect(Collectors.toList());

// List<Integer> -> Integer[]
Integer[] arr = list.stream().toArray(Integer[]::new);  // 또는 list.toArray(new Integer[list.size()]);
// List<String> -> String[]
String[] arr = list.stream().toArray(String[]::new);  // 또는 list.toArray(new String[list.size()]);
```

### 일반 2차원

- List<List&lt;Integer>>

```java
// 선언
List<List<Integer>> list = new ArrayList<>();

// 응용 예시
List<Integer> li = new ArrayList<>();
int arr[] = {1,2,3,4,5,6};
for(int i=0; i<arr.length; i+=2) {
    li.add(arr[i]);
    li.add(arr[i+1]);
    
    list.add(new ArrayList<>(li));
    // - 틀린 삽입방식 : list.add(li);
    // list 내 li는 기존의 li 리스트에 참조되어, 이후 li.clear()에도 함께 영향을 받음.
    // 때문에 아예 새로운 리스트로 다시 생성해서 추가해야함.
    li.clear();
}

System.out.println(list.toString());  // 출력: [[1, 2], [3, 4], [5, 6]]
System.out.println(list.get(1).get(0));  // 출력: 3
```

### 혼합 2차원

- ArrayList&lt;Integer>[]

```java
// 선언
ArrayList<Integer>[] arr = new ArrayList[size];  // 'ArrayList[] arr = ~'도 가능하긴함.
// - 틀린 선언 1 : new ArrayList<>[size];
// - 틀린 선언 2 : new ArrayList<>()[size];

// 사용
for(int i=0; i<size; i++) {
    arr[i] = new ArrayList<>();
}
arr[0].add(3);
ArrayList<Integer> list = arr[0];
```

- ArrayList<int[]>

```java
// 선언
ArrayList<int[]> list = new ArrayList<>();

// 사용
list.add(new int[]{1, 2, 3});
int[] arr = list.get(0);
```

### Tip

- 주의사항
    - 배열 및 리스트에서, 사이즈 선언 및 for문 등의 인덱스 접근은 int 자료형으로만 가능.
- 초기화
    - 2차원 배열 : `int[][] answer = new int[num_list.length / n][n];`
    - size 큰 리스트 : `clear()` 대신 `new 연산` 사용 고려
- 출력
    - 배열 : `System.out.println(Arrays.toString(arr));`
    - 리스트 : `System.out.println(list.toString());`
- 메소드 호출
    - 배열 : `Arrays.메소드(arr);`
    - 리스트 : `Collections.메소드(list);`
- sort 시간복잡도
    - 배열 `Arrays.sort()` : 평균 O(nlog(n)), 최악 O(n^2)
    - 리스트 `Collections.sort()` : 최악 O(nlog(n))
    - 단, 공간복잡도는 배열이 리스트(객체 사용 및 동적 확장성)보다 우수.

<br>

## Set

### 특징

- 공통 : 중복허용 X
- HashSet : 일반적인 Set (정렬 X)
- LinkedHashSet : 삽입 순서를 유지하는 Set
- TreeSet : 정렬된 Set (오름차순 정렬 O)

```java
// 내림차순 TreeSet
TreeSet<Integer> m = new TreeSet<>(Collections.reverseOrder());  // 또는 (Comparator.reverseOrder())
```

<br>

## Map

### 특징

- 공통 : key 중복허용 X
- HashMap : 일반적인 Map (정렬 X)
- LinkedHashMap : 삽입 순서를 유지하는 Map
- TreeMap : 정렬된 Map (key 기준 오름차순 정렬 O)

```java
// 내림차순 TreeMap
TreeMap<Integer, String> m = new TreeMap<>(Collections.reverseOrder());  // 또는 (Comparator.reverseOrder())
```

### 순회

```java
// keySet
for(String key : m.keySet()) {  // key
    String value = m.get(key);  // value
}

// entrySet
for(Map.Entry<Integer, String> entry : m.entrySet()) {
    Integer key = entry.getKey();  // key
    String value = entry.getValue();  // value
}

// values
for(String value : m.values()) {  // value

}
```

### Tip

- Map은 List,Set과는 다르게, add()가 아닌 `put()` 사용
- Map은 List,Set과는 다르게, contains()가 아닌 `containsKey()` 사용
- `HashMap<String, int>` : 불가능
- `HashMap<String, Integer>` : 가능
- `HashMap<String, int[]>` : 가능

<br>

## 기타

### Stack

- `Stack<자료형> st = new Stack<>()` : 선언
- `push()` : 삽입
    - 대신 add()도 가능하지만, push() 권장
- `peek()` : top 요소 반환
    - 비어있으면 에러 발생
- `pop()` : top 요소 반환 및 제거
    - 비어있으면 에러 발생
- `isEmpty()`
- `size()`
- `clear()`
- `contains()`
- `search()` : 요소 검색 후 위치 반환
    - 밑에서부터 1번, 없으면 -1 반환

### Queue

- `Queue<자료형> qu = new LinkedList<>()` : 선언 방식 1
    - 선언한 자료형만 삽입/삭제 가능
- `Queue qu = new LinkedList()` : 선언 방식 2
    - 어떤 자료형이든 삽입/삭제 가능 (ex. 이전에 int형을 넣었어도 String형 삽입 가능)
- `offer()` : 삽입
    - 크기 제한을 넘은 경우 false 반환
    - 대신 add()도 가능하지만, 크기 제한을 넘은 경우 에러 발생
- `peek()` : front 요소 반환
    - 비어있으면 null 반환
- `poll()` : front 요소 반환 및 제거
    - 비어있으면 null 반환
- `isEmpty()`
- `size()`
- `clear()`
- `contains()`

### PriorityQueue

- `PriorityQueue<자료형> pq = new PriorityQueue<>()` : 선언 (최소힙)
    - C++과는 달리, 자바에서는 기본적으로 최소힙으로 구현됨.
- `〃 = new PriorityQueue<>(Collections.reverseOrder())` : 선언 (최대힙)
- 사용 메소드는 Queue와 동일함.

### Deque

- `Deque<자료형> dq = new ArrayDeque<>()` : 선언
- `offerFirst()` : 맨앞에 삽입
    - 크기 제한을 넘은 경우 false 반환
- `offerLast()` : 맨뒤에 삽입
    - 크기 제한을 넘은 경우 false 반환
- `pollFirst()` : 맨앞 제거 및 요소 반환
    - 비어있으면 null 반환
- `pollLast()` : 맨뒤 제거 및 요소 반환
    - 비어있으면 null 반환
- `peekFirst()` : 맨앞 요소 반환
    - 비어있으면 null 반환
- `peekLast()` : 맨뒤 요소 반환
    - 비어있으면 null 반환

### Point

- java.awt.Point 소속 클래스
- int x, int y 를 담고있으며, 마치 Pair와 유사함.

```java
// 사용법
Point p = new Point(3,5);
int x = p.x;  // x좌표값 조회
int y = p.y;  // y좌표값 조회

// 응용 1 (ex. Point + Queue)
Queue<Point> qu = new LinkedList<>();
qu.offer(new Point(3,5));
Point p = qu.poll();
int x = p.x;
int y = p.y;

// 응용 2 (ex. Point + Array)
Point[] arr = new Point[2];
Point p1 = new Point(6,4);
Point p2 = new Point(3,8);
arr[0] = p1;
arr[1] = p2;
System.out.println(arr[0].x);
```

### BigInteger

- java.math.BigInteger 소속 클래스
- long 타입의 범위를 넘는 무한대처럼 매우 큰 수를 다룰 때 사용.
- 단, > < 등의 비교 연산자를 적용할 수 없고 대신 compareTo() 메소드를 이용해야함.
- 단, + - 등의 산술 연산자를 적용할 수 없고 대신 내장 메소드를 이용해야함.

```java
// 선언 (? -> BigInteger)
BigInteger bNum1 = BigInteger.valueOf(15000);  // int or long -> BigInteger
BigInteger bNum2 = new BigInteger("10000");  // String -> BigInteger

// 변환 (BigInteger -> ?)
int iNum = bNum1.intValue();  // BigInteger -> int
long lNum = bNum1.longValue();  // BigInteger -> long
float fNum = bNum1.floatValue();  // BigInteger -> float
double dNum = bNum1.doubleValue();  // BigInteger -> double
String sNum = bNum1.toString();  // BigInteger -> String

// 객체식 비교 (반환타입: int)
int compareResult = bNum1.compareTo(bNum2);
if(compareResult > 0) { }  // bNum1 > bNum2 일때

// 산술연산 메소드 (반환타입: BigInteger)
BigInteger result1 = bNum1.add(bNum2);  // 덧셈 : bNum1 + bNum2
BigInteger result2 = bNum1.subtract(bNum2);  // 뺄셈 : bNum1 - bNum2
BigInteger result3 = bNum1.multiply(bNum2);  // 곱셈 : bNum1 * bNum2
BigInteger result4 = bNum1.divide(bNum2);  // 나눗셈(몫) : bNum1 / bNum2
BigInteger result5 = bNum1.remainder(bNum2);  // 나눗셈(나머지) : bNum1 % bNum2

// 참고로 일반적인 System.out.print(result) 방식으로도 출력 가능.
// - result 1~5 출력 : 25000, 5000, 150000000, 1, 5000
```

### 소수점 처리

```java
// 올림 (반환타입: double)
double dNum = Math.ceil(3.14);  // 4.0

// 내림 (반환타입: double)
double dNum = Math.floor(3.14);  // 3.0

// 기본 반올림 - 소수점 첫째 자리에서 (반환타입: long)
long lNum = Math.round(3.14);  // 3
long lNum = Math.round(3.54);  // 4

// 자리수 반올림 - 소수점 둘째 자리에서 (반환타입: double)
double dNum = Math.round(3.41 * 10) / 10.0;  // 3.4
double dNum = Math.round(3.414 * 10) / 10.0;  // 3.4
double dNum = Math.round(3.45 * 10) / 10.0;  // 3.5
double dNum = Math.round(3.454 * 10) / 10.0;  // 3.5

// 자리수 반올림 - 소수점 셋째 자리에서 (반환타입: double)
double dNum = Math.round(3.441 * 100) / 100.0;  // 3.44
double dNum = Math.round(3.4414 * 100) / 100.0;  // 3.44
double dNum = Math.round(3.445 * 100) / 100.0;  // 3.45
double dNum = Math.round(3.4454 * 100) / 100.0;  // 3.45
```

### 진수 변환

```java
int num = 12;  // 10진수 int
String str = "1100";  // 2진수 String

// 10진수 int -> 2진수 String
String str2 = Integer.toString(num, 2);  // 또는 Integer.toBinaryString(num);
// 참고로 반환될 str2 안의 영어는 모두 소문자임.
// - 헷갈릴때 Tip : toString(num, 2) => num toString 2 => num을 to해서 '2진수짜리 String'으로 변환.
// - str2 출력 : 1100

// 2진수 String -> 10진수 int
int num10 = Integer.parseInt(str, 2);
// 참고로 파라미터 str 안의 영어가 소문자든 대문자든 상관없음.
// - 헷갈릴때 Tip : parseInt(str, 2) => parse (str, 2) => 'str 2진수짜리'를 파싱해서 int로 변환.
// - num10 출력 : 12
```
