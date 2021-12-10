
# Trouble-solution: Web Service
___
# 기획
## 컨셉
* 세대 간의 고민과 해결
## 의도
* 젊은 세대에게 나이든 사람의 조언은 꼰대스러울 수 있다. 하지만, 나이든 사람들 중에는 분명히 전문적이고 현명한 사람이 있고, 젊은 세대에게 이들의 조언/답변은 도움이 될 수 있다. 예를 들면, 우리끼리 연애/직업에 대해서 질문/답변 상황에서 결국 비슷한 답변을 얻을 수밖에 없다. 경험이나 이해가 깊지 않기 때문이다. 반면, 나이든 세대는 간단한 앱 서비스 같은 기능을 사용하지 못하는 경우가 많다. 50대 이상 분들도 스마트폰과 앱을 사용하지만, 최근에 나오는 앱 서비스를 어떻게 이용하는지 빨리 습득하지 못한다. 따라서 이들에게도 젊은 세대의 조언/답변은 도움이 될 수 있다.
## 기대 효과
* 세대 간의 질문과 답변을 통해, 서로 새로운 도움을 받을 수 있다. 다른 세대의 고유한 능력이나 생각을 이해할 수 있다. 
## 타겟층
* 전 연령층
___
## 구현해야 하는 기능
### [회원가입] 
* 세대 입력 필수.
### [고민 게시판]
* 세대 별 게시판
* 고민글 상세보기
  1. 고민글 제목, 내용, 글쓴이, 카테고리, 해결됨 유무
  2. 고민글에 달린 댓글들 목록
### [고민글]
* 고민글 작성 
  1. 검증: 답변 받고자 하는 세대('generation') 선택 여부
* 고민글 수정
  1. 검증: 카테고리 선택 여부
### [답변 등록]
* 고민글 작성자는 본인 글에 답변을 달 수 없다.
* 고민글에서 고민러가 설정한 연령대와 답변자의 정보(연령대)가 일치해야만 답변 글쓰기가 가능하다.
### [답변 채택 & 고민 해결]  Case2로 개발한다.
#### Case1 해결 자동
1. 고민글에 달린 답변들 중에서, 고민러는 '채택' 버튼을 누른다.
2.   고민글 상태는 자동으로 '채택됨'으로 변경된다.
#### Case2 해결 수동
1. 고민글에 달린 답변 중에서, 글쓴이는 '좋아요' 버튼을 누른다.
2. 고민이 해결되었을 경우, 글쓴이는 '고민글 수정'을 통해 '해결 상태'를 변경한다.
### [마이 페이지]
* 사용자가 받은 '종아요' 갯수
* 사용자가 쓴 고민글 목록
* 사용자가 단 답변글 목록
### [랭킹 페이지]
* 채택 많이 받은 답변자 순위

___
# 상세 화면
___
## 회원 등록 화면
* 회원 등록 시, ID와 password, 세대 필수 입력
  ![gominNo_SignUp](https://user-images.githubusercontent.com/50542537/144974621-2b3e6ca2-461c-4feb-8711-2d90df68fcf2.png)
___
## 로그인 화면
* 로그인 실패 시, 검증해준다.
  ![gominNo_loginForm](https://user-images.githubusercontent.com/50542537/145182978-310755a7-2345-4cf4-912f-98a5fe3672cc.png)

* 로그인 성공 시, navbar에 로그인한 사용자의 이름으로 버튼을 보여준다.
  ![gominNo_login](https://user-images.githubusercontent.com/50542537/145535424-0ab95ff1-b818-49ef-8335-3b9125366b2e.png)
___
## 고민글
* 고민글 쓰기
  ![gominNo_addWorry](https://user-images.githubusercontent.com/50542537/144622360-fe94c97c-0b6c-4aa6-9ed4-3cf896229720.png)

* 고민글 수정
  ![gominNo_editWorry](https://user-images.githubusercontent.com/50542537/144868050-5b02a03a-2833-444e-9ff6-70c23b4f9282.png)
___
## 게시판

* 고민글 상세 화면1: 로그인한 사용자의 글인 경우
  ![gominNo_worryDesc_myWorry](https://user-images.githubusercontent.com/50542537/145535423-dd341763-88d7-4a94-95e6-24535dd26070.png)
* 고민글 상세 화면2: 로그인한 사용자의 글이 아닐 때
  ![gominNo_worryDesc_NotMyWorry](https://user-images.githubusercontent.com/50542537/145535421-3068bf3e-69b0-46bb-a17e-3755b0bcc75c.png)

___
## 마이페이지
* 로그인한 경우, 마이페이지에서 사용자가 작성한 고민글, 답변, 채택받은 답변 수를 볼 수 있다.
  ![gominNo_MyPage](https://user-images.githubusercontent.com/50542537/144622363-5783f432-cf86-4a5d-b444-da4a7a59a890.png)

___
## 답변
* 고민글 쓰기
  ![gominNo_addSolution](https://user-images.githubusercontent.com/50542537/145535416-93d35225-9ab3-43c5-bf25-bbbb91d35fa0.png)

___
# 도메인 별 개발 구조
## 1. Member Domain
### 1.1. 회원 관련 기능
### 1.2. CLass 별 기능 구현
#### member Entity
#### member Repository
#### member Service


## 2. Worry Domain
### 2.1. 구현 기능
* 고민글 등록
* 고민글 목록 조회
  1. id로 조회
  2. 제목에 포함된 단어로 조회
  3. 모두 조회
* 고민글 수정

### 2.2. Class 별 기능 구현
#### worry Entity
* generationStatus 값 설정
#### worry Repository
#### worry Service

## 3. Solution Domain
### 3.1. Solution Entity
* 답변 등록
* 답변 조회
* 답변 수정
### 3.2. Class
#### solution Entity
#### solution Repository
* save: solution 저장
* findOne: solutionId로 검색
* findByWorry: worry로 검색
* findByMember: memberId로 검색
#### solution Service

___
## 4. Controller
### 4.1. AccountController
* member account 관련
  1. member join
  2. member login
  3. member list 조회(관리자)
### 4.2. HomeController
* 고민 유형 선택: (1) 일상고민, (2) 전문적 도움이 필요한 고민
  1. 일상 고민 화면 내 세대별 게시판 선택 화면 이동
### 4.3. WorryController
* 고민글 목록 
* 고민글 작성
* 고민글 상세
* 고민글 수정/삭제
### 4.4. SolutionController
* 해결글 목록 불러오기
* 해결글 채택하기
* 해결글 수정하기
* 해결글 삭제하기
### 4.5. AdminController
* 회원 목록
* 회원 상세 정보
  1. 회원 아이디, 이름, 가입일
  2. accepted 갯수
  3. 작성 고민글 목록
  4. 작성 해결글 목록






