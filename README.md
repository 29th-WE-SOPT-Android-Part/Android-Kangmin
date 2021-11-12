# Android-Kangmin
![github_이강민_ver1-2](https://user-images.githubusercontent.com/70698151/135753336-a63f05c3-d45e-467f-9c0e-39fcb3f33cca.png)
# Seminar 4
## 실행화면
<table>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/141430046-b71c53ef-facb-4950-9224-42855d81cb71.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/141430079-46134da3-5294-4c4f-8561-b03a5026ce47.gif"></td>
  </tr>
</table>

### 과제 완료 : Level 1 & Level 2-1 & Level 2-2 & Level 2-3

## Level 1 & Level 2-1, Level 2-2
### 회원가입 TEST
<img width="650" src="https://user-images.githubusercontent.com/56147398/141431203-c39c04e0-251c-4153-a1dc-6568413ebd98.png">


### 로그인 TEST
<img width="650" src="https://user-images.githubusercontent.com/56147398/141431234-3b4d36af-042b-4afb-b1eb-175c926213a5.png">

### RequestLogin & RequestSignUp
```kotlin
data class RequestLogin(
    val email: String,
    val password: String
)

data class RequestSignUp(
    val email: String,
    val name: String,
    val password: String
)
```

### GithubApiService & SoptApiService
```kotlin
interface GithubApiService {
    @GET("users/kkk5474096/repos")
    fun reposForUser(): Call<List<ResponseRepoInfo>>

    @GET("users/kkk5474096")
    fun getUserInfo(): Call<ResponseUserInfo>

    @GET("users/kkk5474096/followers")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>
}

interface SoptApiService {
    @POST("user/signup")
    fun postSignUp(@Body body: RequestSignUp): Call<ResponseWrapper<ResponseSignUp>>

    @POST("user/login")
    fun getLoginInfo(@Body body: RequestLogin): Call<ResponseWrapper<ResponseLogin>>
}
```
Gibhub Api 연동해서 Follower 리스트 연동 완료 & Sopt Api 연동해서 로그인 완료

### ApiServiceCreator
```kotlin
object ApiServiceCreator {
    private val gson = GsonBuilder().setLenient().create()
    private const val BASE_URL_SOPT = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            proceed(newRequest)
        }
    }

    val soptApiService: SoptApiService = soptRetrofit.create(SoptApiService::class.java)
    val githubApiService: GithubApiService = githubRetrofit.create(GithubApiService::class.java)
}
```
OkHttp3 와 Retrofit2를 활용하여 겹치는 헤더 사용 해결 및 서버 연동

### Level 2-3

### ResponseWrapper
```kotlin
data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)
```

### ResponseSignUp & ResponseLogin
```kotlin
data class ResponseSignUp(
    val email: String,
    val id: Int,
    val name: String,
)

data class ResponseLogin(
    val email: String,
    val id: Int,
    val name: String,
    val password: Int
)
```

### SoptApiService
```kotlin
interface SoptApiService {
    @POST("user/signup")
    fun postSignUp(@Body body: RequestSignUp): Call<ResponseWrapper<ResponseSignUp>>

    @POST("user/login")
    fun getLoginInfo(@Body body: RequestLogin): Call<ResponseWrapper<ResponseLogin>>
}
```
Wrapper 클래스를 사용하여 중복되는 내용을 해결

- 배운 내용 : OkHttp를 사용하여 중복되는 헤더를 계속해서 사용하는 문제를 해결할 수 있었다.