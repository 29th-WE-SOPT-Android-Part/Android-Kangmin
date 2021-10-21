# Android-Kangmin
![github_이강민_ver1-2](https://user-images.githubusercontent.com/70698151/135753336-a63f05c3-d45e-467f-9c0e-39fcb3f33cca.png)
# Seminar 1
## 실행화면
<table>
  <tr>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/136689293-36d07634-6b3e-4b80-b527-ed32969e0ea7.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/136689338-3fe06534-a919-49b7-ad63-c002c7d993a3.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/136689380-771ae349-58a5-4ae0-9361-6f662313cb26.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>SignUpActivity</b></td>
    <td align="center"><b>SignInActivity</b></td>
    <td align="center"><b>HomeActivity</b></td>
  </tr>
</table>

## Level 1
- 회원가입, 로그인, 자기소개 페이지 구현
### SignInActivity
```kotlin
    private fun loginCheckEvent() {
        binding.btnLogin.setOnClickListener {
            if (checkInputText()) {
                showToast("로그인 실패")
            } else {
                goToHomeActivity()
            }
        }
    }
    
    private fun checkInputText(): Boolean {
        return binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()
    }

    private fun goToHomeActivity() {
        showToast("이강민님 환영합니다!")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
```
모든 입력이 되어있지 않을시 로그인 실패 출력, 되어 있으면 HomeAcitivity로 화면 전환
### SignUpActivity
```kotlin
    private fun signUpButtonClickEvent() {
        binding.btnSignUpSuccess.setOnClickListener {
            if (checkInputText()) {
                showToast("입력되지 않은 정보가 있습니다")
            } else {
                successSignUp()
            }
        }
    }

    private fun checkInputText(): Boolean {
        return binding.editName.text.isNullOrBlank() || binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()
    }
```
## Level 2
### Level 2-1
```kotlin
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val id = it.data?.getStringExtra("userId")
            val pw = it.data?.getStringExtra("userPw")

            binding.editId.setText(id)
            binding.editPw.setText(pw)

        }
    }
```
```kotlin
    private fun successSignUp() {
        val intent = Intent()
        intent.putExtra("userId", binding.editId.text.toString())
        intent.putExtra("userPw", binding.editPw.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
```
`registerForActivityResult`와 `putExtra`, `Intent`를 이용한 회원가입 성공 후 데이터 전달 및 화면 이동 구현
### Level 2-2
```kotiln
    private fun goToGithub() {
        binding.ivGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kkk5474096"))
            startActivity(intent)
        }
```
암시적 인텐트를 활용한 깃허브 페이지 이동<br><br>

#### 명시적/암시적 인텐트
- 명시적 인텐트 : 특정 컴포넌트나 액티비티가 명확하게 실행되어야할 경우에 사용, 패키지 내부의 액티비티를 실행할 때만 사용
- 암시적 인텐트 : 명시적 인텐트와는 다르게 어떤 의도만으로 원하는 컴포넌트를 실행할 수 있다, 클래스명이나 패키지명을 넣지 않는다.

### Level 2-3
```xml
    <ScrollView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginHorizontal="80dp"
        android:layout_marginTop="30dp"
        android:layout_marginBottom="10dp"
        android:fillViewport="true"
        app:layout_constraintBottom_toTopOf="@id/iv_github"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_mbti_title">

        <TextView
            android:id="@+id/tv_content"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="@string/introduce" />

    </ScrollView>
```
```xml
    <ImageView
        android:id="@+id/iv_profile"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginTop="30dp"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_title"
        app:layout_constraintWidth_percent="0.4"
        tools:src="@drawable/github" />
```
`ScrollView`를 사용하여 텍스트가 길어질 시 뷰가 스크롤 가능하게 구현<br>
`constraintDimensionRatio`를 사용하여 사진 비유를 1:1로 맞춤

- 느낌점 : 한번 더 공부하면서 알고 있던 내용을 확실 시 할 수 있었다.
