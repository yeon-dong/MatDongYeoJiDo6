# 맛동여지도 (맛東輿地圖)
![image](https://github.com/user-attachments/assets/433ea114-c809-4ea2-aeab-5b10b7dd3214)

Copyright 2020. 맛동연 All rights reserved.

<br/>
<br/>

## 맛집 탐방의 대가 "맛동연"이 여러분께 서울의 맛집을 소개해드립니다!

**맛동연 경력**
- 1999년 맛의 고장 **전라도** 출생
- 2000년 **분유 섭취량** 최고치 달성
- 2009년 초등학생 전교생 대상 **예쁜 이빨 대회 1위**
- 2011년 전라도 50년 이상 거주하신 친할머니에게 지금까지 본 사람 중 **제일 잘 먹는다** 인정
- 2013년 중학교 **급식 많이 먹기** 대회 **1위**
- 2016년 애슐리 꽉 채운 **13그릇 섭취 돌파**
- 2019년 군입대 후 조리병과 친분 고도화
- 2020년 **맛동연** <a href="https://www.instagram.com/eat_yeon_">
    <img src="https://upload.wikimedia.org/wikipedia/commons/a/a5/Instagram_icon.png" alt="Instagram" width="16" height="16"> eat_yeon_
</a> 계정 운영

<br/>
<br/>

## 기획
조선 후기 지리학자이신 **고산자(古山子) 김정호** 선생님의 **〈대동여지도〉(大東輿地圖)** 에서 깊이 감명받아
<br/>맛집 탐방 지도를 그리기로 결심했습니다.
<br/><br/>
맛동여지도는 대동여지도의 제작 목적인 **번영한 시기에 백성의 삶을 더 나아지게 하자**는 뜻을 이어받아,<br/>
국민들에게 **맛의 즐거움을 전하고 행복을 선사**하기 위해 제작되었습니다.

기획서는 대동여지도와 같은 컨셉으로 **직접** 그림을 그려 작성했습니다.

<br/>

<img src="https://github.com/user-attachments/assets/9d3bf62a-ca1c-45cd-a20f-9694f28028df" width="500" /> <img src="https://github.com/user-attachments/assets/247bcc8b-fbab-4ee9-bc5f-b82d1f48c4ed" width="300" />

<br/>

## 맛동여지도 화면 소개
### 스플레쉬 화면
![image](https://github.com/user-attachments/assets/b708ee5d-60c8-4653-8395-03709c6fd5f1)

### 메인 화면
![image](https://github.com/user-attachments/assets/334acb35-0db9-4205-b964-0a254cf84d77)

### 음식점 리스트 화면
![image](https://github.com/user-attachments/assets/7d2ebbd3-fc3b-4694-970e-10e1d0523859)
![image](https://github.com/user-attachments/assets/0241a7e8-6308-4458-a00e-e4da2fd5af42)


### 음식점 상세 정보 화면
![image](https://github.com/user-attachments/assets/e820fd61-3b63-4729-872c-acf94edec4c5)

## 맛동여지도(맛東輿地圖) 개발자들
<a href="https://github.com/sejin">정세진 @sejin<a/> , <a href="https://github.com/DhoWor1d">김대호 @DhoWor1d<a/>, <a href="https://github.com/yeon-dong">임동연 @yeon-dong<a/>

## 에뮬레이터 실행시 본인 화면에서 시작하게 하는법
` app/manifests/AndroidManifest.xml ` 에서 본인이 작업하는 액티비티 사이에 
```xml
<intent-filter>
  <action android:name="android.intent.action.MAIN" />

  <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```
넣고 exported="true"로 변경


- 본인이 작업하는 액티비티가 DetailActivity일 경우
```xml
<activity
  android:name=".ui.detail.DetailActivity"
  android:exported="true" >
  <intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
  </intent-filter>
</activity>
```
