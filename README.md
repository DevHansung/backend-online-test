## Spring boot online test

#### 1. Project Name
 - 프로젝트명: Spring boot online test


#### 2. 프로젝트 정보
- 개발환경(Windows10)
	- Language: Java_ver.1.8
	- Framework: Spring Boot_ver.2.3.3
	- DB: MySQL_ver.8.0.17
	- Library: JPA-Hibernate, Swagger
	- build: Maven
	- Tool: Eclips


#### 3. API설명
- 참고사항
	- 회원등록, 회원검색을 제외한 모든 서비스는 **RequestHeader에 name값을 포함하여** 전송해야합니다.
	- 컨트롤러의 진입 시점 이전에 Interceptor를 거쳐 Request header로 전송된 name값이 DB의 회원테이블에 저장 되어 있는지 간략한 인증 과정을 거치게 됩니다.
	- 인증과정을 통과하지 못하면 모든 서비스를 이용 할 수 없습니다.
	- 아래 테이블은 request header 예시입니다.

	|KEY|VALUE|
	|--|--|
	|name|hansung|

- API문서는 Swagger을 이용하여 작성 하였고, 프로젝트 실행을통해 확인 가능합니다.
	- 프로젝트 실행 후 아래 url 접속
	  `http://localhost:8080/swagger-ui.html`
	  
|API|설명|
|--|--|--|
|(POST)/api/user|회원등록|
|(POST)/api/user|이미지 폴더 생성|
|(GET)/api/image{imageFolderId}|이미지 등록|
|(GET)/api/folder/me|자신의 이미지 폴더 조회|
|(GET)/api/folder/{imageFolderId}|회원의 특정폴더 내부 이미지 조회|
|(GET)/api/image/rank|가장 많이 달린 이미지 태그 TOP10|
|(POST)/api/point/unspent/{id}|전체 폴더중 포인트 소모가 없는 폴더 목록|

#### 4. 설치/빌드/ 실행
 - 설치  
        
       git clone https://github.com/devhansung/spring-boot-online-test.git
 - 빌드

	   ./mvnw clean package
 - 실행  
	 1. maven을 사용하여 프로젝트 실행
	  
		    ./mvnw spring-boot : run
	2. jar파일로 패키징 된 경우 .jar파일 실행
		   
		   java -jar target/online_test-0.0.1-SNAPSHOT.jar


#### 4. 후기
> -구현 자체는 비교적 어렵지 않았으나 고민이 많이 되었던 문제였습니다. 각 문제마다 다양한 방법을 고민해보며 개인적으로 재밌는 경험이었으며, 고민하며 조금은 성장한것같아 대단히 유익하고 좋은 경험이었습니다.
