package com.yichen.gank.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.ui.fragment.BaseMvpFragment
import com.yichen.gank.R
import com.yichen.gank.adapter.CategoryAdapter
import com.yichen.gank.common.Constant
import com.yichen.gank.entity.GankItemData
import com.yichen.gank.injection.component.DaggerCategoryComponent
import com.yichen.gank.injection.module.CategoryModule
import com.yichen.gank.mvp.CategoryContract
import com.yichen.gank.mvp.presenter.CategoryPresenter
import com.yichen.gank.ui.activity.GankDetailActivity
import kotlinx.android.synthetic.main.gank_fragment_category.*

/**
 * Created by Chen on 2019/2/15
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {

    private var category = ""
    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private val DEFAULTPAGER = 1
    private var currentPage = DEFAULTPAGER
    private val totalPager = 20

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
            .activityComponent(mActivityComponent)
            .categoryModule(CategoryModule(this))
            .build().inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.gank_fragment_category
    }

    override fun initView() {

        mCategoryAdapter = CategoryAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mCategoryAdapter)

        categoryRecycler.layoutManager = LinearLayoutManager(activity!!)
        categoryRecycler.adapter = mLRecyclerViewAdapter
        categoryRecycler.setLoadMoreEnabled(true)
    }

    override fun initData() {
        category = arguments?.get(Constant.CATEGORY) as String
        mPresenter.getCategoryGanks(category, 1, Constant.STATE_REFRESH, false)
    }

    override fun setListener() {
        //设置加载更多
        categoryRecycler.setOnLoadMoreListener {
            currentPage++
            if (currentPage <= totalPager) {
                mPresenter.getCategoryGanks(category, currentPage, Constant.STATE_LOADMORE, false)
            } else {
                categoryRecycler.setNoMore(true)
            }
        }
        //设置下拉刷新
        categoryRecycler.setOnRefreshListener {
            currentPage = DEFAULTPAGER
            mPresenter.getCategoryGanks(category, currentPage, Constant.STATE_REFRESH, false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            val intent = Intent(activity, GankDetailActivity::class.java)
            intent.putExtra(Constant.DETAIL_URL, mCategoryAdapter.getDatas()[position].url)
            startActivity(intent)
        }
    }

    override fun showGanks(datas: List<GankItemData>, state: Int) {
        if (state == Constant.STATE_LOADMORE) {//加载更多
            mCategoryAdapter.addAll(datas)
            categoryRecycler.refreshComplete(datas.size)
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mCategoryAdapter.updateData(datas)
            categoryRecycler.refreshComplete(0)
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun getViewContext(): RxFragment {
        return this
    }
}