# Android-Kangmin
![github_이강민_ver1-2](https://user-images.githubusercontent.com/70698151/135753336-a63f05c3-d45e-467f-9c0e-39fcb3f33cca.png)
# Seminar 3
## 실행화면
<table>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/139576693-ebca96b2-978c-4dba-b2f6-701fbd3d2bca.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/139576718-5cc57742-a9fc-435a-917b-01c74e5ad477.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/139576742-20c5b6de-657f-4c1d-b6c7-3a4f2bcf8040.gif"></td>
  </tr>
</table>

## Level 1
- 디자인 적용
### activity_sign_in.xml
```xml
    <EditText
        android:id="@+id/edit_id"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="20dp"
        android:layout_marginTop="5dp"
        android:background="@drawable/selector_edit"
        android:hint="@string/write_id"
        android:padding="15dp"
        android:textSize="14sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_id" />
```
### selector_edit.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/shape_edit" android:state_focused="true" />
    <item android:drawable="@drawable/shape_unselect_edit" android:state_focused="false" />
</selector>
```
EditText에 selector 활용 및 디자인 적용

## Level 2
### Level 2-1
### NestedScrollableHost.kt
```kotlin
class NestedScrollableHost : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var touchSlop = 0
    private var initialX = 0f
    private var initialY = 0f
    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2) {
                v = v.parent as? View
            }
            return v as? ViewPager2
        }

    private val child: View? get() = if (childCount > 0) getChildAt(0) else null

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun canChildScroll(orientation: Int, delta: Float): Boolean {
        val direction = -delta.sign.toInt()
        return when (orientation) {
            0 -> child?.canScrollHorizontally(direction) ?: false
            1 -> child?.canScrollVertically(direction) ?: false
            else -> throw IllegalArgumentException()
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        handleInterceptTouchEvent(e)
        return super.onInterceptTouchEvent(e)
    }

    private fun handleInterceptTouchEvent(e: MotionEvent) {
        val orientation = parentViewPager?.orientation ?: return

        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
            return
        }

        if (e.action == MotionEvent.ACTION_DOWN) {
            initialX = e.x
            initialY = e.y
            parent.requestDisallowInterceptTouchEvent(true)
        } else if (e.action == MotionEvent.ACTION_MOVE) {
            val dx = e.x - initialX
            val dy = e.y - initialY
            val isVpHorizontal = orientation == ORIENTATION_HORIZONTAL

            val scaledDx = dx.absoluteValue * if (isVpHorizontal) .5f else 1f
            val scaledDy = dy.absoluteValue * if (isVpHorizontal) 1f else .5f

            if (scaledDx > touchSlop || scaledDy > touchSlop) {
                if (isVpHorizontal == (scaledDy > scaledDx)) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)) {
                        parent.requestDisallowInterceptTouchEvent(true)
                    } else {
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
        }
    }
}
```
### fragment_home.xml
```xml
    <org.sopt.soptandroidseminar.util.NestedScrollableHost
        android:id="@+id/layout_follow"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tl_follow">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_follow"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </org.sopt.soptandroidseminar.util.NestedScrollableHost>
```
ViewPager2 중첩 스크롤 문제 해결하기 위한 NestedScrollableHost 구현
### Level 2-2
### FollowerListAdapter.kt
```kotlin
    inner class FollowerUserViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followerUserInfo: ResponseUserInfo, position: Int) {
            binding.followUserName.text = followerUserInfo.login
            binding.followUserContent.text = followerUserInfo.repos_url
            Glide.with(itemView.context).load(followerUserInfo.avatar_url).circleCrop()
                .into(binding.followUserImage)

            itemView.setOnClickListener {
                itemClickListener.onClick(userList[position])
            }
        }
    }
```
리스트에 각기 다른 이미지를 구현하기위한 코드 구현, 서버 통신으로 리스트 추가
## Level 3
### Level 3-2
### CameraFragment.kt
```kotlin
class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonEvent()

    }

    private fun buttonEvent() {
        binding.btnCamera.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            filterActivityLauncher.launch(intent)
        }
    }

    private val filterActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                var currentImageUri = it.data?.data
                Glide.with(requireActivity()).load(currentImageUri).into(binding.imageCamera)

            } else if (it.resultCode == RESULT_CANCELED) {
                requireActivity().showToast("사진 선택 취소")
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```
Intent를 이용한 갤러리 접근, 사진 데이터를 uri형식으로 받아온 후 Glide로 이미지로 보여줌


- 배운 내용 : 중첩 스크롤 문제를 해결하기 위해 NestedScrollableHost를 구현하여 중첩 문제를 해결하는 방법을 배울 수 있었다.