### perfecmeal project back-end

## 기술 스택
* Java 11
* Spring Boot
* Spring Security
* jwt
* Spring Data JPA
* Redis
* MySQL
* yolov5

## 구현 기능
* 계정관련
  - 로그인, 로그아웃, 회원가입
  - jwt 토큰을 이용해 로그인 관리
  - access token 만료 시 재발급
  - 로그아웃시 access token 과 refresh token 을 redis 저장소에 black list 로 추가하여 접근 제한
  
* 음식 분석
  - yolov5 python 파일을 실행하기 위해 processbuilder 사용
  
* 영양소 분석
  - 공공데이터를 이용해 음식 영양소 정보를 획득
  - xaml 형태로 받은 데이터를 리플렉션을 이용해 멤버 변수에 파싱
  - 파싱된 데이터들과 db에 저장된 사용자 데이터의 연산을 통해 부족/섭취 영양소 계산

## 프론트엔드 화면

### 로그인 화면
<img src='https://user-images.githubusercontent.com/70000247/207539594-6384d8ee-22a7-4ab1-a4df-eacc81def28b.png' width='300' height='500' />

### 회원가입 화면
<img src='https://user-images.githubusercontent.com/70000247/207540127-c769ab71-ebe8-4d73-b375-8ca4737180b1.png' width='300' height='500' />

### 메인 화면
<img src='https://user-images.githubusercontent.com/70000247/207540321-4db4a3e9-f51f-473d-ae09-bdad92416dc2.png' width='300' height='500' />
<img src='https://user-images.githubusercontent.com/70000247/207540354-d813f29d-653d-4b68-b11c-b50b1c488655.png' width='300' height='500' />
<img src='https://user-images.githubusercontent.com/70000247/207540692-4c353cc5-56d5-4672-af9b-d81474dd1800.png' width='300' height='500' />

### 음식 전송 화면
<img src='https://user-images.githubusercontent.com/70000247/207540602-bde1a579-7809-43bb-8474-94c250a8ddb2.png' width='300' height='500' />

### 음식 변경 화면
<img src='https://user-images.githubusercontent.com/70000247/207541247-7911c10a-7310-48c3-9b9b-e82b45977cb9.png' width='300' height='500' />

### 날짜별 섭취 음식 확인 화면
<img src='https://user-images.githubusercontent.com/70000247/207541310-a01287c3-0ce2-4b7f-bc5c-4fe29b362c77.png' width='300' height='500' />

### 설정화면
<img src='https://user-images.githubusercontent.com/70000247/207541324-0579ff31-5b07-405e-adeb-88b41ccf16b6.png' width='300' height='500' />
