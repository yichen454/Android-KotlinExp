package com.yichen.summer.ui.fragment

import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.summer.R
import com.yichen.summer.adapter.ProfileTagAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerTagEntity
import com.yichen.summer.entity.SummerUserData
import com.yichen.summer.widgets.flowlayout.SQLFlowLayout
import com.yichen.summer.widgets.tagcloud.TagCloudView
import kotlinx.android.synthetic.main.summer_fragment_profile_detail.*

/**
 * Created by Chen on 2019/2/22
 */
class ProfileDetailFragment : BaseFragment() {

    private lateinit var data: SummerUserData
    private val taglist = ArrayList<SummerTagEntity>()

    override fun initView() {
    }

    override fun initData() {
        data = arguments?.getParcelable(Constant.PROFILE_DETRAIL_KEY) as SummerUserData
        addWall()
        addText("家乡", "${data.province.name} ${data.city.name}")
        addText("学校", data.school.name)
        addText("学院", data.department.name)
        addText("专业", data.major)
        var degree = ""
        when (data.degree) {
            1 -> degree = "本科"
            2 -> degree = "硕士"
            3 -> degree = "博士"
        }
        addText("年级", "${data.enroll}级 $degree")
        checkTag(data)
        addCloud()
        addTag("性格标签", data.tags.charater)
        addTag("音乐/歌手", data.tags.music)
        addTag("书籍/作家", data.tags.book)
        addTag("电影/演员", data.tags.movie)
        addTag("近期追剧", data.tags.series)
        addTag("运动", data.tags.sport)
        addTag("美食", data.tags.food)
        addTag("旅行足迹", data.tags.traval)
        addTag("出没地点", data.tags.hangout)
        addTag("宠物", data.tags.pet)
        addTag("梦想", data.tags.dream)
    }

    override fun getLayoutId(): Int {
        return R.layout.summer_fragment_profile_detail
    }

    private fun addWall() {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.summer_item_profile_wall, ll, false)
        ll.addView(view)
    }

    private fun addText(item: String, content: String) {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.summer_item_profile_text, ll, false)
        view.findViewById<TextView>(R.id.item).text = item
        view.findViewById<TextView>(R.id.content).text = content
        ll.addView(view)
    }

    private fun addCloud() {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.summer_item_profile_cloud, ll, false)
        view.findViewById<TagCloudView>(R.id.tagCloudView).setAdapter(ProfileTagAdapter(taglist))
        ll.addView(view)
    }

    private fun checkTag(data: SummerUserData) {
        if (data.tags.charater == null || data.tags.charater.isEmpty()) {
            data.tags.charater = ArrayList()
            (data.tags.charater as ArrayList).add("性格莫测")
        }
        if (data.tags.music == null || data.tags.music.isEmpty()) {
            data.tags.music = ArrayList()
            (data.tags.music as ArrayList).add("还没有喜欢的音乐")
        }
        if (data.tags.book == null || data.tags.book.isEmpty()) {
            data.tags.book = ArrayList()
            (data.tags.book as ArrayList).add("还没有喜欢的书籍")
        }
        if (data.tags.movie == null || data.tags.movie.isEmpty()) {
            data.tags.movie = ArrayList()
            (data.tags.movie as ArrayList).add("还没有喜欢的电影")
        }
        if (data.tags.series == null || data.tags.series.isEmpty()) {
            data.tags.series = ArrayList()
            (data.tags.series as ArrayList).add("追剧？我习惯被追")
        }
        if (data.tags.sport == null || data.tags.sport.isEmpty()) {
            data.tags.sport = ArrayList()
            (data.tags.sport as ArrayList).add("睡觉算运动么")
        }
        if (data.tags.food == null || data.tags.food.isEmpty()) {
            data.tags.food = ArrayList()
            (data.tags.food as ArrayList).add("什么都爱吃")
        }
        if (data.tags.traval == null || data.tags.traval.isEmpty()) {
            data.tags.traval = ArrayList()
            (data.tags.traval as ArrayList).add("去过月球")
        }
        if (data.tags.hangout == null || data.tags.hangout.isEmpty()) {
            data.tags.hangout = ArrayList()
            (data.tags.hangout as ArrayList).add("学校食堂")
        }
        if (data.tags.pet == null || data.tags.pet.isEmpty()) {
            data.tags.pet = ArrayList()
            (data.tags.pet as ArrayList).add("🐶 or 🐱")
        }
        if (data.tags.dream == null || data.tags.dream.isEmpty()) {
            data.tags.dream = ArrayList()
            (data.tags.dream as ArrayList).add("I have a dream")
        }
    }

    private fun addTag(item: String, list: List<String>?) {
        val colorId: Int = when (item) {
            "性格标签" -> R.color.summer_blue_light
            "音乐/歌手" -> R.color.summer_yellow
            "书籍/作家" -> R.color.summer_pink
            "电影/演员" -> R.color.summer_orange
            "近期追剧" -> R.color.summer_green
            "运动" -> R.color.summer_orange_light
            "美食" -> R.color.summer_pink_dark
            "旅行足迹" -> R.color.summer_green_dark
            "出没地点" -> R.color.summer_blue
            "宠物" -> R.color.summer_green_light
            "梦想" -> R.color.summer_gray_light
            else -> 0
        }
        val view: View = LayoutInflater.from(activity).inflate(R.layout.summer_item_profile_tag, ll, false)
        view.findViewById<TextView>(R.id.item).text = item
        val flow: SQLFlowLayout = view.findViewById(R.id.flow)
        list!!.forEach { t ->
            taglist.add(SummerTagEntity(colorId, t))
            val tv: TextView =
                LayoutInflater.from(activity).inflate(R.layout.summer_item_tag_cloud, flow, false) as TextView
            (tv.background as GradientDrawable).setColor(activity!!.resources.getColor(colorId))
            tv.text = t
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            flow.addView(tv)
        }

        ll.addView(view)
    }
}