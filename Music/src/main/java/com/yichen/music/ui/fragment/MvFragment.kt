package com.yichen.music.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.recyclerview.XRecyclerView
import com.yichen.common.ui.fragment.BaseMvpFragment
import com.yichen.music.R
import com.yichen.music.adapter.MvListAdapter
import com.yichen.music.common.Constant
import com.yichen.music.entity.MusicMvListEntity
import com.yichen.music.injection.component.DaggerMvListComponent
import com.yichen.music.injection.module.MvListModule
import com.yichen.music.mvp.MvListContract
import com.yichen.music.mvp.presenter.MvListPresenter
import kotlinx.android.synthetic.main.music_fragment_songlist.*

/**
 * Created by Chen on 2019/3/1
 */
class MvFragment : BaseMvpFragment<MvListPresenter>(), MvListContract.View {

    private lateinit var mAdapter: MvListAdapter
    private var DEFAULTOFFSET: Int = 0
    private var offset: Int = DEFAULTOFFSET

    override fun injectComponent() {
        DaggerMvListComponent.builder()
            .activityComponent(mActivityComponent)
            .mvListModule(MvListModule(this))
            .build().inject(this)
    }

    override fun initView() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        mAdapter = MvListAdapter(activity!!)
        mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        mPresenter.getMvList(10, 0, Constant.STATE_REFRESH, true)
    }

    override fun getLayoutId(): Int {
        return R.layout.music_fragment_mv
    }

    override fun setListener() {
        mRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                mPresenter.getMvList(10, DEFAULTOFFSET, Constant.STATE_REFRESH, false)
            }

            override fun onLoadMore() {
                offset = mAdapter.itemCount
                if (offset == 0) {
                    mRecyclerView.setNoMore(true)
                } else {
                    mPresenter.getMvList(10, offset, Constant.STATE_LOADMORE, false)
                }
            }
        })
    }

    override fun showMvList(datas: List<MusicMvListEntity>, state: Int) {
        if (state == Constant.STATE_LOADMORE) {//加载更多
            mAdapter.addItemsToLast(datas)
            mRecyclerView.loadMoreComplete()
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mAdapter.setListAll(datas)
            mRecyclerView.refreshComplete()
        }
    }

    override fun getViewContext(): RxFragment {
        return this
    }
}