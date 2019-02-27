package com.yichen.summer.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.common.ui.activity.BaseMvpActivity
import com.yichen.summer.R
import com.yichen.summer.adapter.CommentAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerCommentData
import com.yichen.summer.injection.component.DaggerCommentComponent
import com.yichen.summer.injection.module.CommentModule
import com.yichen.summer.mvp.CommentContract
import com.yichen.summer.mvp.presenter.CommentPresenter
import kotlinx.android.synthetic.main.summer_avtivity_comment.*
import kotlinx.android.synthetic.main.summer_widget_short.*

/**
 * Created by Chen on 2019/2/18
 */
class CommentActivity : BaseMvpActivity<CommentPresenter>(), CommentContract.View {
    private var type = ""
    private var qid = ""
    private lateinit var mCommentAdapter: CommentAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var DEFAULTOFFSET: Int = 0
    private var offset: Int = DEFAULTOFFSET
    private var sort: String = Constant.SORT_TOP

    override fun injectComponent() {
        DaggerCommentComponent.builder()
            .activityComponent(mActivityComponent)
            .commentModule(CommentModule(this))
            .build().inject(this)
    }

    override fun initView() {
        setStatusBar()

        mCommentAdapter = CommentAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mCommentAdapter)

        commentRecycler.layoutManager = LinearLayoutManager(this)
        commentRecycler.adapter = mLRecyclerViewAdapter
        commentRecycler.setLoadMoreEnabled(true)
        tv_sort_top.isSelected = true
    }

    override fun initData() {
        type = intent.getStringExtra(Constant.TYPE)
        qid = intent.getStringExtra(Constant.COMMENT_KEY)
        mPresenter.getComments(type, qid, offset, "", sort, Constant.STATE_REFRESH, false)
    }

    override fun getLayoutId(): Int {
        return R.layout.summer_avtivity_comment
    }

    override fun setListener() {
        super.setListener()
        //设置加载更多
        commentRecycler.setOnLoadMoreListener {
            offset = mCommentAdapter.getDatas().size
            if (offset == 0) {
                commentRecycler.setNoMore(true)
            } else {
                val lastData = mCommentAdapter.getDatas()[offset - 1]
                val since: String = lastData.created_at
                mPresenter.getComments(type, qid, offset, since, sort, Constant.STATE_LOADMORE, false)
            }
        }
        //设置下拉刷新
        commentRecycler.setOnRefreshListener {
            mPresenter.getComments(type, qid, DEFAULTOFFSET, "", sort, Constant.STATE_REFRESH, false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->

        }

        mCommentAdapter.innerClickListener = object : CommentAdapter.InnerClickListener {
            override fun onAvatarClick(itemData: SummerCommentData) {
                val intent = Intent(this@CommentActivity, ProfileActivity::class.java)
                val uid: String = itemData.user.id
                intent.putExtra(Constant.PROFILE_KEY, uid)
                startActivity(intent)
            }
        }
        iv_back.setOnClickListener { finish() }

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
            mPresenter.getComments(type, qid, DEFAULTOFFSET, "", sort, Constant.STATE_REFRESH, false)
        }
    }

    override fun showComments(datas: List<SummerCommentData>, state: Int) {
        if (state == Constant.STATE_LOADMORE) {//加载更多
            mCommentAdapter.addAll(datas)
            commentRecycler.refreshComplete(datas.size)
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mCommentAdapter.updateData(datas)
            commentRecycler.refreshComplete(0)
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun getViewContext(): RxAppCompatActivity {
        return this
    }
}