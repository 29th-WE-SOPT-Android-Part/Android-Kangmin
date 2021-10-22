# Android-Kangmin
![github_이강민_ver1-2](https://user-images.githubusercontent.com/70698151/135753336-a63f05c3-d45e-467f-9c0e-39fcb3f33cca.png)
# Seminar 2
## 실행화면
<table>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/138402829-b9b3a7bc-bd12-4764-aaac-c084ae88c1ba.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/138403692-bbb8279b-40ba-4d46-8f0a-4f9bf196a2d7.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/56147398/138404403-a0062cd4-9758-408d-a1c4-c50a0f0366ae.gif"></td>
  </tr>
</table>

## Level 1
### Level 1-1
- FollowerRecyclerView, RepoRecyclerView 구현
### HomeActivity
```kotlin
    private fun changeFragmentEvent() {
        val followerListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_list, followerListFragment)
            .commit()

        repoListChangeEvent(repoListFragment)
        followerListChangeEvent(followerListFragment)
    }

    private fun repoListChangeEvent(repoListFragment: RepoListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        binding.btnRepoList.setOnClickListener {
            transaction.replace(R.id.fragment_list, repoListFragment)
            transaction.commit()
        }
    }

    private fun followerListChangeEvent(followerListFragment: FollowerListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        binding.btnFollowerList.setOnClickListener {
            transaction.replace(R.id.fragment_list, followerListFragment)
            transaction.commit()
        }
    }
```
각각의 버튼을 눌렀을때 알맞은 RecyclerView가 있는 Fragment로 전환

### item_follower_list.xml
```xml
    <TextView
        android:id="@+id/follow_user_name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="16dp"
        android:ellipsize="end"
        android:maxLines="1"
        android:textColor="@color/black"
        android:textSize="12sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/follow_user_image"
        app:layout_constraintTop_toTopOf="@id/follow_user_image"
        tools:text="userId" />
```
`ellipsize`와 `maxLines`를 사용하여 글자수가 일정길이 이상이 되었을 때 ...으로 보이게 구현

### Level 1-2
### RepoListFragment
```kotlin
binding.recyclerRepoList.layoutManager = GridLayoutManager(requireContext(), 2)
```
RepoListFragment의 RecyclerView는 GridLayout으로 구현

## Level 2
### Level 2-1
### FollowerListFragment
```kotlin
    private fun configureClickEvent() {
        adapter.setItemClickListener(object : FollowerListAdapter.ItemClickListener {
            override fun onClick(data: ResponseUserInfo) {
                var githubId = data.login
                var githubUrl = data.repos_url
                var githubImage = data.avatar_url
                showDetailActivity(githubId, githubUrl, githubImage)
            }
        })
    }

    private fun showDetailActivity(githubId: String, githubUrl: String, githubImage: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("githubId", githubId)
            putExtra("githubUrl", githubUrl)
            putExtra("githubImage", githubImage)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
```
RecyclerViewAdapter에 setItemClickListener 추가하여 리스트별 클릭 이벤트 구현 및 화면전환 시 데이터 넘겨줌
### Level 2-2
### FollowerListFragment
```kotlin
    private fun recyclerViewDecoration() {
        val spaceDecoration = VerticalItemDecoration(10)
        val dividerItemDecoration =
            DividerItemDecoration(
                binding.recyclerFollowerList.context,
                LinearLayoutManager(requireContext()).orientation
            )
        binding.recyclerFollowerList.addItemDecoration(dividerItemDecoration)
        binding.recyclerFollowerList.addItemDecoration(spaceDecoration)
    }
```
ItemDecoration 활용하여 리스트 간 간격과 구분선 표시

### Level 2-3
```kotlin
private fun myFollowList() {
        recyclerViewDecoration()
        binding.recyclerFollowerList.layoutManager = LinearLayoutManager(requireContext())
        val callback = MyTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerFollowerList)
        binding.recyclerFollowerList.adapter = adapter
        adapter.startDrag(object : FollowerListAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerListAdapter.FollowerUserViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
        userFollowingList()
    }
```
`ItemTouchHelper.Callback` 클래스 생성후 활용하여 RecyclerView Item 이동 및 삭제 구현

## Level 3
### Level 3-2
### MyDiffUtil
```kotlin
class MyDiffUtil<RepoInfo>(
    private val oldItems: List<RepoInfo>,
    private val newItems: List<RepoInfo>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.hashCode() == newItem.hashCode()

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}
```
### FollowerListAdapter
```kotlin
    fun setItems(newItems: List<ResponseUserInfo>) {
        val diffUtil = MyDiffUtil(userList, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        userList.clear()
        userList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
```
notifyDataSetChanged를 사용하면 리스트 내의 데이터가 바뀌었을때 모든 리스트를 다 바꿔야해서 아이템들이 많다면 지연시간도 길어지고 비효율적일수 밖에 없다. 그래서 MyDiffUtil 클래스를 만들고 DiffUtil을 사용하여 현재 리스트와 교체될 리스트를 비교하고 바꿔야할 리스트만 바꿔줌으로써 notifyDataSetChanged보다 효율적인 데이터 교환을 할 수 있게 함.

- 배운 내용 : DiffUtil을 사용하여 데이터 교환을 할 수 있게 하는 방법은 알았으나 백그라운드에서 calculateDiff를 처리하는 방법에 대해 계속해서 공부하고 있다.
