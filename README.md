# Android-Kangmin
![github_이강민_ver1-2](https://user-images.githubusercontent.com/70698151/135753336-a63f05c3-d45e-467f-9c0e-39fcb3f33cca.png)
# Seminar 7
## 실행화면
<table>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/146546541-3081de4b-8977-4352-808f-62e684c07f2e.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/146546597-9a360d8a-bc26-48d1-b7c6-1976820ed654.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/146546605-da9bb0bf-67ac-480b-bdd8-007325a16707.gif"></td>
  </tr>
</table>

### 과제 완료 : Level 1-1 & Level 1-2 & Level 1-3 & Level 2-1 & Level 2-2

## Level 1-1

### nav_boarding.xml
```xml
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_boarding"
    app:startDestination="@id/boardingFragment1">

    <fragment
        android:id="@+id/boardingFragment1"
        android:name="org.sopt.soptandroidseminar.view.fragment.BoardingFragment1"
        android:label="첫번째 화면"
        tools:layout="@layout/fragment_boarding1">
        <action
            android:id="@+id/action_boardingFragment1_to_boardingFragment2"
            app:destination="@+id/boardingFragment2" />
    </fragment>
    <fragment
        android:id="@+id/boardingFragment2"
        android:name="org.sopt.soptandroidseminar.view.fragment.BoardingFragment2"
        android:label="두번째 화면"
        tools:layout="@layout/fragment_boarding2">
        <action
            android:id="@+id/action_boardingFragment2_to_boardingFragment3"
            app:destination="@+id/boardingFragment3"
            app:popUpTo="@id/boardingFragment1"
            app:popUpToInclusive="false"/>
    </fragment>
    <fragment
        android:id="@+id/boardingFragment3"
        android:name="org.sopt.soptandroidseminar.view.fragment.BoardingFragment3"
        android:label="세번째 화면"
        tools:layout="@layout/fragment_boarding3" />
</navigation>
```
네이게이션을 이용한 온보딩 완료

## Level 1-2
### SOPTSharedPreferences.kt
```kotlin
object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context: Context) : Boolean {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }
    fun setAutoLogin(context: Context, value: Boolean) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}
```
object를 이용한 SharedPreferences 사용 & 자동 로그인 구현

## Level 1-3
<img width="434" alt="스크린샷 2021-12-17 오후 9 52 00" src="https://user-images.githubusercontent.com/56147398/146547424-4dde5fae-e934-434f-ad91-ad22b8327aec.png">

adapter, util, api ,view로 크게 나누어서 폴더링


## Level 2-1
### nav_boarding.xml
```xml
        <action
            android:id="@+id/action_boardingFragment2_to_boardingFragment3"
            app:destination="@+id/boardingFragment3"
            app:popUpTo="@id/boardingFragment1"
            app:popUpToInclusive="false"/>
```
popUpTo와 popUpToInclusive를 사용하여 BackStack 관리

## Level 2-2
### BoardingActivity.kt
```kotlin
class BoardingActivity: AppCompatActivity() {
    private lateinit var binding: ActivityBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
    }

    private fun initToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_boarding) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this,navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.container_boarding).navigateUp()
}
```
navigation의 label과 Toolbar를 연동하여 title의 내용을 구현했습니다.

- 배운 내용 : navigation을 활용한 온보딩에 대해 배울 수 있었습니다.