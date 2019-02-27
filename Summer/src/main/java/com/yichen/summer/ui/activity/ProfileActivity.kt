package com.yichen.summer.ui.activity

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.common.ui.activity.BaseMvpActivity
import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.common.utils.ImageLoader
import com.yichen.summer.R
import com.yichen.summer.adapter.ProfilePagerAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerUserData
import com.yichen.summer.injection.component.DaggerUserComponent
import com.yichen.summer.injection.module.UserModule
import com.yichen.summer.mvp.UserContract
import com.yichen.summer.mvp.presenter.UserPresenter
import com.yichen.summer.ui.fragment.ProfileDetailFragment
import com.yichen.summer.ui.fragment.ProfileSthFragment
import kotlinx.android.synthetic.main.summer_activity_profile.*
import kotlinx.android.synthetic.main.summer_head_profile.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Chen on 2019/2/18
 */
class ProfileActivity : BaseMvpActivity<UserPresenter>(), UserContract.View {

    private val mTabTitles = arrayOf("个人资料", "新鲜事")
    private val mFragments = ArrayList<BaseFragment>()

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(mActivityComponent)
            .userModule(UserModule(this))
            .build().inject(this)
    }

    override fun initView() {
        setStatusBar()
    }

    override fun initData() {
        val uid: String = intent.getStringExtra(Constant.PROFILE_KEY)
        mPresenter.getUserInfo(uid, false)
    }

    override fun getLayoutId(): Int {
        return R.layout.summer_activity_profile
    }

    override fun setListener() {
        super.setListener()
        headRoot.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { p0, p1 ->
            val progress: Float = Math.abs(p1) / headRoot.totalScrollRange.toFloat()
            tv_title.alpha = (progress - 0.8f) * 5f
        })
        iv_back.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showUserInfo(data: SummerUserData) {
        tv_name.text = data.nickname
        tv_title.text = data.nickname
        ImageLoader.loadUrlCircleImage(this, data.avatar, iv_avatar)
        ImageLoader.loadUrlBlurImage(this, data.avatar, iv_head_bg)
        iv_head_bg.alpha = 0.5f
        tv_bio.text = data.bio

        val gd: GradientDrawable = tv_birth.background as GradientDrawable
        when (data.gender) {
            1 -> {
                tv_birth.text = "♂ ${getBirth(data.birthday)}"
                gd.setColor(resources.getColor(R.color.summer_blue))
            }
            2 -> {
                tv_birth.text = "♀ ${getBirth(data.birthday)}"
                gd.setColor(resources.getColor(R.color.summer_pink))
            }
        }
        when (data.relationship_status) {
            1 -> iv_relationship.setImageResource(R.drawable.relation_status_single)
            2 -> iv_relationship.setImageResource(R.drawable.relation_status_secret)
            3 -> iv_relationship.setImageResource(R.drawable.relation_status_love)
        }
        //tv_desc.text = data.toString()
        val pfd = ProfileDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constant.PROFILE_DETRAIL_KEY, data)
        pfd.arguments = bundle
        mFragments.add(pfd)
        mFragments.add(ProfileSthFragment())
        mViewPager.offscreenPageLimit = mFragments.size
        mViewPager.adapter = ProfilePagerAdapter(mFragments, mTabTitles, supportFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun getViewContext(): RxAppCompatActivity {
        return this
    }

    private var astro =
        arrayOf("摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座")
    private var astro_day = intArrayOf(20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22, 20)

    private fun getBirth(birthday: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date = sdf.parse(birthday)
        val year = date.year
        val month: Int = date.month
        val day: Int = date.day
        val now = Date()

        var index: Int = month + 1
        if (day < astro_day[month]) {
            index -= 1
        }
        var age: Int = now.year - year
        if (now.month > month) {
            age -= 1
        }
        return "$age ${astro[month]}"
    }

}