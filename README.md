# GominNo: BackEnd 개발
## 1. Member Domain
### 1.1. 구현 기능
* 회원 등록
* 회원 목록 조회
  1. 아이디로 조회
  2. 이름으로 조회
  3. 모두 조회
### 1.2. Class
#### Member Entity
#### Member Repository
#### Member Service

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






