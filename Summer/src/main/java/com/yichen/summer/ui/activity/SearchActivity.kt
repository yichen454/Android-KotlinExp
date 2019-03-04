package com.yichen.summer.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.recyclerview.XRecyclerView
import com.yichen.common.ui.activity.BaseMvpActivity
import com.yichen.common.utils.ToastUtils
import com.yichen.summer.R
import com.yichen.summer.adapter.TypeAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerInfoData
import com.yichen.summer.injection.component.DaggerTypeComponent
import com.yichen.summer.injection.module.TypeModule
import com.yichen.summer.mvp.TypeContract
import com.yichen.summer.mvp.presenter.TypePresenter
import kotlinx.android.synthetic.main.summer_activity_search.*

/**
 * Created by Chen on 2019/2/21
 */
class SearchActivity : BaseMvpActivity<TypePresenter>(), TypeContract.View {

    var type: String = Constant.TYPE_BLACK
    var tag: String = ""
    private lateinit var mTypeAdapter: TypeAdapter
    private var DEFAULTOFFSET: Int = 0
    private var offset: Int = DEFAULTOFFSET

    override fun injectComponent() {
        DaggerTypeComponent.builder()
            .activityComponent(mActivityComponent)
            .typeModule(TypeModule(this))
            .build().inject(this)
    }

    override fun initView() {
        setStatusBar()
        mTypeAdapter = TypeAdapter(this)

        searchRecycler.layoutManager = LinearLayoutManager(this)
        searchRecycler.adapter = mTypeAdapter
        searchRecycler.isLoadingMoreEnabled = true
        searchRecycler.isPullRefreshEnabled = false

    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.summer_activity_search
    }

    override fun setListener() {
        super.setListener()
        iv_back.setOnClickListener { finish() }

        radio.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_black -> type = Constant.TYPE_BLACK
                R.id.rb_secret -> type = Constant.TYPE_SECRET
                R.id.rb_activity -> type = Constant.TYPE_ACTIVITY
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    tag = p0
                    mPresenter.searchSummerInfo(type, tag, DEFAULTOFFSET, Constant.STATE_REFRESH, false)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        searchRecycler.setLoadingListener(object :XRecyclerView.LoadingListener{
            override fun onRefresh() {

            }

            override fun onLoadMore() {
                offset = mTypeAdapter.itemCount
                if (offset == 0) {
                    searchRecycler.setNoMore(true)
                } else {
                    mPresenter.searchSummerInfo(type, tag, offset, Constant.STATE_LOADMORE, false)
                }
            }

        })

        mTypeAdapter.setOnItemClickListener { _, item, _ ->
            val intent = Intent(this@SearchActivity, CommentActivity::class.java)
            intent.putExtra(Constant.TYPE, type)
            intent.putExtra(Constant.COMMENT_KEY, (item as SummerInfoData).id)
            startActivity(intent)
        }

        mTypeAdapter.innerClickListener = object : TypeAdapter.InnerClickListener {
            override fun onAvatarClick(itemData: SummerInfoData) {
                val intent = Intent(this@SearchActivity, ProfileActivity::class.java)
                val uid: String = itemData.user.id
                intent.putExtra(Constant.PROFILE_KEY, uid)
                startActivity(intent)
            }
        }
    }

    override fun showSummerInfo(datas: List<SummerInfoData>, state: Int) {
        if (datas.isEmpty()) {
            ToastUtils.instance.showToast("没有结果")
        }
        if (state == Constant.STATE_LOADMORE) {//加载更多
            mTypeAdapter.addItemsToLast(datas)
            searchRecycler.loadMoreComplete()
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mTypeAdapter.setListAll(datas)
            searchRecycler.refreshComplete()
        }
    }

    override fun getTypeContext(): RxFragment? {
        return null
    }

    override fun getSearchContext(): RxAppCompatActivity {
        return this
    }
}
