# Trouble-solution: Web Service
___

# 상세 화면
___
## 회원 등록 화면
* 회원 등록 시, ID와 password, 세대 필수 입력
  ![gominNo_SignUp](https://user-images.githubusercontent.com/50542537/144974621-2b3e6ca2-461c-4feb-8711-2d90df68fcf2.png)
___
## 고민글
* 고민글 쓰기
  ![gominNo_addWorry](https://user-images.githubusercontent.com/50542537/144622360-fe94c97c-0b6c-4aa6-9ed4-3cf896229720.png)

* 고민글 수정
  ![gominNo_editWorry](https://user-images.githubusercontent.com/50542537/144868050-5b02a03a-2833-444e-9ff6-70c23b4f9282.png)
___
## 게시판
* 고민글 상세 조회
  ![gominNo_worryDesc](https://user-images.githubusercontent.com/50542537/144622372-c820677f-fee7-434b-8393-699aeafd6347.png)
___
## 마이페이지
* 로그인한 경우, 마이페이지에서 사용자가 작성한 고민글, 답변, 채택받은 답변 수를 볼 수 있다.
  ![gominNo_MyPage](https://user-images.githubusercontent.com/50542537/144622363-5783f432-cf86-4a5d-b444-da4a7a59a890.png)

___
# 도메인 별 개발 구조
## 1. Member Domain
### 1.1. 회원 관련
### 1.2. 

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






