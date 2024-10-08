# Problem Solving - Java
### Algorithm for CodingTest
- **Java 정리 노트** : <a href="https://github.com/tkguswls1106/ProblemSolving-java/blob/master/java_notes.md">Click!</a>

### Notice
- C++에서 Java로 전환하며 Repo를 옮겨, 분량이 다소 적어보일 수 있음.
- **이전 C++ Repo** : <a href="https://github.com/tkguswls1106/ProblemSolving-cpp">Click!</a>

<br>


## ⚙️ Convention

### Class Naming
유형에 따라 적절히 선택 후, 순서를 지켜 언더바로 구분한다.
- **우선순위 1** : 알고리즘 종류 (ex. DP)
- **우선순위 2** : 소주제
  - 개념정리 (ex. Basic)
  - 일반적 (ex. PrefixSum)
- **우선순위 3** : 문제 종류
  - 백준 (ex. BOJ)
  - 프로그래머스 (ex. PGS)
- **우선순위 4** : 문제 정보
  - 번호 (ex. 11659)
  - 이름 (ex. 미로탈출)
- **우선순위 5** : 풀이 결과
  - 성공 (ex. 생략)
  - 실패 (ex. Fail)

&#8594;&nbsp;&nbsp;DP_PrefixSum_BOJ_11659.java

### Commit Prefix

| 종류            | 내용                                             |
|----------------| ----------------------------------------------- |
| Add            | 중요 알고리즘 정리                                  |
| Solve          | 일반적인 문제 풀이                                  |
| Fix            | 코드 수정 및 리팩토링                                |
| Chore          | 설명 보충 및 애매한 수정                             |
| Docs           | md 등의 문서 작업                                  |

```
< Commit Message >
Prefix_종류: 구현_내용 or 클래스_이름
ex) Add: BinarySearch_Basic
ex) Add: DFS_Basic_BOJ_1926
ex) Add: DP_PrefixSum_BOJ_11659
ex) Solve: BOJ_1043
ex) Solve: PGS_미로탈출
ex) Fix: DP 점화식 로직 수정
```

<br>


## 📂 Package

### Directory Structure
```
├── 📁 Algorithm : 중요 알고리즘 정리 및 PS
├── 📁 PS : 기타 Problem Solving
└── java_notes.md : Java 정리 노트
```
