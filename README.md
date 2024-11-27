# 맛동여지도
![image](https://github.com/user-attachments/assets/433ea114-c809-4ea2-aeab-5b10b7dd3214)

Copyright 2020. 맛동연 All rights reserved.

## 맛집 탐방의 대가 "맛동연"이 여러분께 서울의 맛집을 소개해드립니다!

맛동연 경력
- 1999년 맛의 고장 전라도 출생
- 2000년 전라도 출생 답게 반찬 3종 모두 섭취
- 2009년 초등학생 전교생 대상 이쁜 이빨 대회 1위
- 2015년 전라도 50년 이상 거주하신 친할머니에게 지금까지 본 사람 중 제일 잘 먹는다 인정
- 2019년 군입대
- 2020년 맛동연 eat_yeon_ 계정 운영

## 기획서
대동여지도와 같은 컨셉으로 **직접** 그림을 그려 기획서를 작성해봤습니다.

<br/>

<img src="https://github.com/user-attachments/assets/9d3bf62a-ca1c-45cd-a20f-9694f28028df" width="500" /> <img src="https://github.com/user-attachments/assets/247bcc8b-fbab-4ee9-bc5f-b82d1f48c4ed" width="300" />



<br/>

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
