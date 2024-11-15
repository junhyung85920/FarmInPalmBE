#  스마트팜 농부들을 위한 모니터링 서비스 🪴Falm In Palm🪴
![Frame 1171275995](https://github.com/user-attachments/assets/aefce78f-b229-4734-87f3-7ff8d7fced64)

- 배포 client URL : 
- 배포 server URL : https://port-0-farminpalmserver-m1bow3hn7b1c4b46.sel4.cloudtype.app

<br>

## 프로젝트 소개

- Farm In Palm은 스마트 농업을 운영하는 사람들을 위한 농장 모니터링 서비스입니다.
- 핵심적으로, 각 농장에 설치된 센서와 카메라로부터 실시간 데이터를 수집하여 손쉽게 관리할 수 있습니다.
- 기존 스마트 농업을 운영하는 농부와 새롭게 스마트 농업에 뛰어드려는 젊은 층에게 유익합니다.

<br>

## 팀원 구성

<div align="center">

| **김도연** | **김준형** | **박수민** | **오성식** | **정서현** |
| :------: |  :------: | :------: | :------: | :------: |
| [<img src="https://avatars.githubusercontent.com/u/187283797?v=4" height=150 width=150> <br/> @Doring119](https://github.com/Doring119) | [<img src="https://avatars.githubusercontent.com/u/80797496?v=4" height=150 width=150> <br/> @junhyung85920](https://github.com/junhyung85920) | [<img src="https://avatars.githubusercontent.com/u/65269430?v=4" height=150 width=150> <br/> @Moderator11](https://github.com/Moderator11) | [<img src="https://avatars.githubusercontent.com/u/80496872?v=4" height=150 width=150> <br/> @OreoFlavor](https://github.com/OreoFlavor) | [<img src="https://avatars.githubusercontent.com/u/75007741?v=4" height=150 width=150> <br/> @hyunaeri](https://github.com/hyunaeri) |

</div>

<br>

## 1. 개발 환경

- Front : HTML, React, styled-components, Recoil
- Back-end : Java, Spring boot, Spring Data JPA, Spring Security, MariaDB, 
- 버전 및 이슈관리 : Github, Github Organization
- 협업 툴 : Discord, Notion
- 서비스 배포 환경 : Cloudtype
- 디자인 : [Figma](https://www.figma.com/design/RulZwY7fkeyYAGQYnc5aF7/GDG-2%ED%8C%80?node-id=0-1&m=dev&t=Z1PWu4YlAAtL93Gj-1)
<br>

## 2. 채택한 개발 기술

### 프론트

### 프론트

### 프론트

### 프론트

### Builder pattern
- service code를 작성할 때 code의 통일성과 setter의 단점을 보완하기 위해 builder pattern을 이용하였습니다.

### Security
- 사용자마다 재고, 모니터링, 이벤트 등을 관리함에 따라 로그인/회원가입 기능이 필요했습니다. 데이터 보안을 위해 각 요청에 대해 auth filter를 적용했습니다. 허용된 요청에 대해서만 데이터에 접근할 수 있도록 설정했습니다.

### Custom exception handler
- 서버에서 발생할 수 있는 예외에 대하여 custom format으로 구성하여 client에게 알려줍니다. project 전역에서 발생하는 에러를 catch하여 일관된 형식으로 쉽게 파악할 수 있습니다.

<br>


## 3. 역할 분담

### 🍊정서현

- **프론트엔드**
<br>

### 🍊박수민

- **프론트엔드**
<br>
    
### 👻김도연

- **디자이너**
<br>

### 😎오성식

- **백엔드**
<br>

### 🐬김준형

- **백엔드**

<br>

## 4. 신경 쓴 부분

### [Crawling]
- 농민들을 위한 기사를 제공하기 위해 Jsoup 라이브러리를 이용하여 최근 기사들을 가져와 제공합니다.

### [Weather API]
- 외부 API를 사용하여 사용자의 위치와 요청 시간을 기반으로 실시간 날씨 정보를 받아오는 로직을 구현하였습니다.

### [Video streaming]
- 모니터링 기능에 각 농장의 실시간 현황을 제공하기 위함입니다. 실시간 video를 client에게 streaming하는 service를 구현했습니다. 

<br>

## 5. 서비스 기능

### [메인화면]
- 현재 사용자 위치 기반 기상 정보와 농업 관련 소식을 제공합니다
    - 기상 정보, 이달의 농업 기술, 농업 관련 연구 모아보기
    - 로그인 하지 않은 사용자에게도 이용할 수 있는 화면입니다.

| 초기화면 |
|----------|
|![splash](https://user-images.githubusercontent.com/112460466/210172920-aef402ed-5aef-4d4a-94b9-2b7147fd8389.gif)|

<br>

### [뉴스 트렌드]
- 최신 동향의 뉴스 기사와 농사 꿀팁, 계절별 대비책 등을 제공합니다

| 초기화면 |
|----------|
|![splash](https://user-images.githubusercontent.com/112460466/210172920-aef402ed-5aef-4d4a-94b9-2b7147fd8389.gif)|

<br>

### [작물 모니터링]
- 등록된 스마트 농장들을 실시간으로 모니터링 할 수 있습니다.
    - 각 농장의 온, 습도 등의 정보와 실시간 영상을 제공합니다.
- 새로운 농장을 추가하거나 더 이상 필요없는 구역을 삭제할 수 있습니다.

| 초기화면 |
|----------|
|![splash](https://user-images.githubusercontent.com/112460466/210172920-aef402ed-5aef-4d4a-94b9-2b7147fd8389.gif)|

<br>

### [히스토리 - 이벤트]
- 여러 농작물을 일궈내는 과정에서 필요한 일정을 등록하여 관리할 수 있습니다.
    - 각 이벤트의 시작과 끝, 제목을 입력하여 생성하고, 수정, 삭제도 가능합니다.
- 등록된 이벤트는 캘린더에 표기되어 한눈에 보기 쉽습니다.

| 초기화면 |
|----------|
|![splash](https://user-images.githubusercontent.com/112460466/210172920-aef402ed-5aef-4d4a-94b9-2b7147fd8389.gif)|

<br>

### [히스토리 - 재고 관리]
- 농업 관련 도구 및 재료들을 등록하여 관리할 수 있습니다.
    - 각 품목마다 분류와 수량을 설정합니다.

| 초기화면 |
|----------|
|![splash](https://user-images.githubusercontent.com/112460466/210172920-aef402ed-5aef-4d4a-94b9-2b7147fd8389.gif)|

<br>

