# 경기대학교 컴퓨터공학부 캡스톤디자인 캡사이신팀: SUNHAN
### 결식 아동을 위한 아동급식 가맹점과 선한영향력 가게를 아동의 gps 위치에 기반해 거리순으로 가게 리스트를 제공해주는 어플리케이션 개발 프로젝트 
* gps 기반 거리순 리스트 제공 -> 아동급식 가맹점과 선항영향력 가게의 정보를 동시에 제공해 준다. 
* 검색 기능을 제공하여 아동의 위치의 가까운 순으로 검색 결과를 제공 해준다. 
* 커뮤니티 기능을 제공 한다.
* 감사편지 기능을 제공한다. 
* 아동급식 가맹점과 선한영향력 가게의 상세 정보들을 제공해준다. 
* 찜 하기 기능을 제공한다. 
* 자신이 활동한 로그 내역들을 마이페이지를 통해 활동가능하다. 
* 카카오 소셜 로그인 기능을 제공하여 간단한 로그인 기능 제공한다 .
* 비회원 모드도 제공하여 그들에게도 위치에 기반하여 거리순으로 가게 리스트를 제공한다. 
* 카카오톡 메세지 api 를 이용하여 공유하기 기능 제공한다. 

### 안드로이드 개발 참여
1. 경기대학교 박의진
 - 카카오톡 로그인 api 기능 구현 및 토큰 유효성 판단 api 연동
 - 마이페이지의 전 기능(유저 정보제공, 프로필 수정, 차단관리, 로그아웃, 탈퇴, 내 활동 보기, 아동급식카드잔액 조회 연동) 구현
 - 회원일 경우의 위치기반 거리순 리스트 제공 api 레트로핏 연동
 - 찜하기 리스트 api 레트로핏 연동
 - 찜하기 및 해제 api 로직 마무리 구현
 - 전체적인 UI 관리 및 로직 생성 
2. 경기대학교 태형배
3. 경기대학교 이윤지

### 기술스택 (클라이언트 파트)
- Android Java
- MVC 
### 아키텍처 
- mvc 구조 사용
- model 
- view
- controller 
### 연동 api 
- 카카오톡 로그인 api 사용
- 선한 서버 api 연동 (서버 url: https://sunhan.site )
- 카카오톡 메세지 전송 api 사용 

### api 명세서
- https://documenter.getpostman.com/view/13091019/UyrEgaLy

### 개발일지
#### 4월 7일
- [박의진] launch.xml, Login.xml, Mypage.xml 초기완성 
- [박의진] delete_account.xml 진행 중
#### 4월 13일
- [박의진] MyPage.xml 수정, 차단관리 adapter, 차단관리 item 생성 , edit_profile.xml 생성, 내 활동관리 adpater, item 생성 
#### 4월 14일
- [박의진] activity_sunhanst_store.xml 아이콘 background 수정
- [박의진] 오픈소스 및 약관정책 UI 추가 + 마이페이지 UI 수정
#### 4월 15일
- [박의진] 바텀 네비게이션 작업 중
#### 4월 16일
- [박의진] 바텀네비게이션 설정 완료 + 마이페이지 어댑터 완성 + 바텀네비게이션 hide기능 오류 진행중
- [박의진] 툴바 수정
- [박의진] activity_navi.xml 프레임레이아웃 영역 수정함
- [박의진] 리사이클러뷰 아이템 클릭이벤트
- [박의진] 마이페이지 리사이클러뷰 클릭이벤트 리스너 진행중 + 툴바 수정중
#### 4월 17일
- [박의진] 마이페이지 리사이클러뷰 클릭이벤트 리스너 진행중 + 툴바 수정중 + 로그아웃 다이얼로그
- [박의진] 내활동 보기 탭바 진행 중
#### 4월 19일
- [박의진] 내활동 보기 수정, 차단리스트 수정
- [박의진] 찜한가게, 가게찾기, 가게 메인화면 리사이클러뷰와 어댑터 생성 
#### 4월 21일
- [박의진] 카카오 로그인 api 연동 + 카드 아이템 생성 
- [박의진]  선한가게/가맹점 어댑터 정리
#### 4월 22일
- [박의진] 마이페이지 클릭 이벤트 오류 수정 / 메인스토어화면이랑 찜한가게 리사이클러뷰 짤리는 것 해결 / 찜한가게 + 가게찾기 + 메인가게화면+ 마이페이지의 가장 바깥 레이아웃 Framelayout으로 수정
#### 4월 25일
- [박의진] 내 활동보기 (게시글, 댓글) 탭 생성 및 리사이클러뷰/ 어댑터 생성 + 프로필수정 화면 수정
#### 4월 26일
- [박의진] 아동급식카드조회 수정, 프로필 갤러리 연동
#### 4월 27일
- [박의진]  마이페이지 수정
- [박의진] 패키지 도메인 명 변경+ 구글로그인 + 카카오 로그인 수정
#### 4월 28일
- [박의진] 툴바에 GPS 아이콘 기능 추가 및 gps tracker 연동
#### 5월 2일
- [박의진]  카드 잔액 조회 UI 수정, 내 편지 보기 UI 수정
#### 5월 3일
- [박의진] 레트로핏 싱글톤 클래스 생성 및 api 인터페이스 생성, 서버 연결 
#### 5월 4일
- [박의진] 카카오톡 로그인을 통한 서버의 accessToken, refreshToken 연결 성공 
#### 5월 9일
- [박의진] 유저 정보 받아오는 api 연동 완료
#### 5월 12일
- [박의진] 유저정보 받아오기 이미지, 닉네임 완료/ 프로필 수정 사진, 닉네임 완료
- [박의진] 메인 가게 리스트 UI 탭바 수정
#### 5월 13일
- [박의진] 회원전용 가맹점 거리순 리스트 api 연동 ( 첫 로딩 오류있음)
- [박의진] 회원전용 가맹점 거리순 리스트 완료
#### 5월 14일
- [박의진] 탈퇴 api url 수정 후 완료
- [박의진] 선한영향력 카테고리별 거리순 리스트 완료 + 상세페이지 수정중 + 클릭 리스너 오류 고침
- [박의진] 가게찾기 클릭 오류 수정
- [박의진] 바텀네비게이션 오류 수정, 런처액티비티 수정
- [박의진] 비회원 선한카테고리 오류 수정 + 찜한가게 스크랩 선한가게랑 가맹점 리스트 완료
#### 5월 15일
- [박의진] 선한영향력 가게 상세페이지 완료+ 가맹점 상세페이지 완료 + info 프래그먼트 xml 파일 스크롤뷰 및 레이아웃 수정
#### 5월 16일
- [박의진] 검색하기 리스트 오류 수정
- [박의진] 커뮤니티 글쓰기 오류 수정 / 내가 쓴 게시글, 댓글 보기 리스트 완성
#### 5월 18일
- [박의진] 상세화면 프래그먼트 안뜨는 오류 수정완료
- [박의진] 로그인 화면 수정
- [박의진] 로그아웃 후 로직 수정 + 주소설정 UI 수정 완료 + 바텀메인에 스와이프 추가 + statusBar 색상 변경 + 툴바 로고 추가수정
#### 5월 19일
- [박의진] 내편지 로그 수정중 + 편지 쓰는 액티비티 수정 중
- [박의진] 편지 쓰는 액티비티 수정 중
- [박의진] 감사편지 이미지 부분 완성+ 편지 삭제 신고 완성 + 차단리스트 수정 및 차단해제 + 비회원 이동 오류 수정 + 내가 쓴 편지 완료
#### 5월 20일
- [박의진] 감사편지 삭제 적용 완료 + 찜한가게 상세페이지 이동
- [박의진] GPS 위치 수정 후 리스트 바로 적용되게 함 + 가맹점 검색 결과 오류 수정 완료
- [박의진] 주석 삭제
- [박의진] 앱아이콘 설정
#### 5월 21일
- [박의진] 편지 이미지 오류 수정 완료
- [박의진] 카톡 링크공유 가게이름 추가
- [박의진] 가게 상세화면 편지쓰기 UI 수정
- [박의진] 가게 상세화면 정보 UI 카드뷰 추가 및 배너 색상 변경
#### 5월 22일
- [박의진] 마이페이지 UI 수정
- [박의진] 주석 정리 및 메인화면 리사이클러뷰 margin 정리
- [박의진] 내활동 게시글 item UI 수정 + 차단해제 완료
- [박의진] 차단해제 및 편지삭제/신고 버튼 오류 수정
- [박의진] 편지 삭제 기능 수정 (내가쓴 편지 목록으로 옮김)
- [박의진] 내활동 보기 스와이프 추가
#### 5월 23일
- [박의진] 가게 상세정보화면 수정
- [박의진] 주석 삭제 및 필요 없는 파일 삭제 및 네워크연결 토스트 메세지
- [박의진] 찜하기 리사이클러뷰 마진 수정
#### 5월 24일
- [박의진] 코드정리
#### 5월 26일
- [박의진] 상세화면 찜한가게 표시 완료 + 찜해제 바로 적용 수정필요
- [박의진] 상세화면 찜하기표시 수정 완료
#### 5월 28일
- [박의진] 마이페이지 아이콘 변경
- [박의진] 바텀네비게이션 및 탭바 이동 시 hide and show 로 변경
- [박의진] 바텀네비게이션 UI및 애니메이션 변경
- [박의진] 바텀네비게이션 height 수정 및 마이페이지 아이템 높이 수정
#### 5월 29일
- [박의진] 아이디 정보 초기 요청 추가(로그인액티비티에서)
- [박의진] 리싸이클러뷰 및 버튼에 리플효과 추가 + 본인편지 신고 막음
- [박의진] Manifest portrait 추가
#### 6월 8일
- [박의진] 리프레시토큰 추가 및 편지 부분 사진 오류 해결
- [박의진] 스와이프 페이징 오류 해결
### 레트로핏 네트워크 통신
![image](https://user-images.githubusercontent.com/77429906/175981693-4c09be9d-9d22-49a5-bcd1-e2179a9f2c74.png)
### 선한 어플리케이션 개발 완성 
![image](https://user-images.githubusercontent.com/77429906/175980767-e5f41d65-102c-46b2-b209-96c73d3db34a.png)
![image](https://user-images.githubusercontent.com/77429906/175980525-ce6987a4-d2f6-4bcb-89ca-64c8331b1a4a.png)
![image](https://user-images.githubusercontent.com/77429906/175980617-2ce2deae-c2df-4311-8671-a3203cc01e6f.png)
![image](https://user-images.githubusercontent.com/77429906/175980919-17da596d-0633-4e63-8d07-a02e5a571495.png)
![image](https://user-images.githubusercontent.com/77429906/175981011-4c7f90eb-efa0-41d0-bf8c-3d24daee5c2e.png)
![image](https://user-images.githubusercontent.com/77429906/175981111-b4c41de5-8c43-4256-95b6-4d2b4a65add8.png)
![image](https://user-images.githubusercontent.com/77429906/175981176-67477032-78cb-44f1-a81c-26cfccd5fb8c.png)
![image](https://user-images.githubusercontent.com/77429906/175981268-4b075e77-4e03-41b8-bbf3-2240ed3b50f7.png)
![image](https://user-images.githubusercontent.com/77429906/175981330-1daf998e-69da-4f4a-9b60-1581d8a90de6.png)
![image](https://user-images.githubusercontent.com/77429906/175981427-85c95682-17ca-4531-84d8-c1ce05d56eb8.png)
