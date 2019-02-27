package com.yichen.summer.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.ui.fragment.BaseMvpFragment
import com.yichen.summer.R
import com.yichen.summer.adapter.TypeAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerInfoData
import com.yichen.summer.injection.component.DaggerTypeComponent
import com.yichen.summer.injection.module.TypeModule
import com.yichen.summer.mvp.TypeContract
import com.yichen.summer.mvp.presenter.TypePresenter
import com.yichen.summer.ui.activity.CommentActivity
import com.yichen.summer.ui.activity.ProfileActivity
import kotlinx.android.synthetic.main.summer_fragment_type.*
import kotlinx.android.synthetic.main.summer_widget_short.*

/**
 * Created by Chen on 2019/2/18
 */
class TypeFragment : BaseMvpFragment<TypePresenter>(), TypeContract.View {

    private var type = ""
    private lateinit var mTypeAdapter: TypeAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var DEFAULTOFFSET: Int = 0
    private var offset: Int = DEFAULTOFFSET
    private var sort: String = Constant.SORT_TOP

    override fun injectComponent() {
        DaggerTypeComponent.builder()
            .activityComponent(mActivityComponent)
            .typeModule(TypeModule(this))
            .build().inject(this)
    }

    override fun initView() {
        mTypeAdapter = TypeAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mTypeAdapter)

        typeRecycler.layoutManager = LinearLayoutManager(activity!!)
        typeRecycler.adapter = mLRecyclerViewAdapter
        typeRecycler.setLoadMoreEnabled(true)
        tv_sort_top.isSelected = true
    }

    override fun initData() {
        type = arguments?.get(Constant.TYPE) as String
        mPresenter.getSummerInfo(type, offset, "", sort, Constant.STATE_REFRESH, false)
    }

    override fun getLayoutId(): Int {
        return R.layout.summer_fragment_type
    }

    override fun setListener() {
        //设置加载更多
        typeRecycler.setOnLoadMoreListener {
            offset = mTypeAdapter.getDatas().size
            if (offset == 0) {
                typeRecycler.setNoMore(true)
            } else {
                val lastData = mTypeAdapter.getDatas()[offset - 1]
                val since: String = when (type) {
                    Constant.TYPE_BLACK -> lastData.published_at
                    else -> lastData.created_at
                }
                mPresenter.getSummerInfo(type, offset, since, sort, Constant.STATE_LOADMORE, false)
            }
        }
        //设置下拉刷新
        typeRecycler.setOnRefreshListener {
            mPresenter.getSummerInfo(type, DEFAULTOFFSET, "", sort, Constant.STATE_REFRESH, false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            val itemData: SummerInfoData = mTypeAdapter.getDatas()[position]
            val intent = Intent(activity, CommentActivity::class.java)
            intent.putExtra(Constant.TYPE, type)
            intent.putExtra(Constant.COMMENT_KEY, itemData.id)
            startActivity(intent)
        }

        mTypeAdapter.innerClickListener = object : TypeAdapter.InnerClickListener {
            override fun onAvatarClick(itemData: SummerInfoData) {
                val intent = Intent(activity, ProfileActivity::class.java)
                val uid: String = itemData.user.id
                intent.putExtra(Constant.PROFILE_KEY, uid)
                startActivity(intent)
            }
        }

        ll_sort.setOnClickListener {
            when (sort) {
                Constant.SORT_TOP -> {
                    sort = Constant.SORT_TIME
                    tv_sort_time.isSelected = true
                    tv_sort_top.isSelected = false
                }
                Constant.SORT_TIME -> {
                    sort = Constant.SORT_TOP
                    tv_sort_time.isSelected = false
                    tv_sort_top.isSelected = true
                }
            }
            mPresenter.getSummerInfo(type, DEFAULTOFFSET, "", sort, Constant.STATE_REFRESH, false)
        }
    }

    override fun showSummerInfo(datas: List<SummerInfoData>, state: Int) {
        if (state == Constant.STATE_LOADMORE) {//加载更多
            mTypeAdapter.addAll(datas)
            typeRecycler.refreshComplete(datas.size)
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mTypeAdapter.updateData(datas)
            typeRecycler.refreshComplete(0)
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun getTypeContext(): RxFragment? {
        return this
    }

    override fun getSearchContext(): RxAppCompatActivity? {
        return null
    }
}