package com.yichen.summer.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Chen on 2019/2/18
 */
data class SummerUserData(
    var id: String,
    var nickname: String,
    var avatar: String,
    var gender: Int,
    var birthday: String,
    var relationship_status: Int,
    var constellation: Int,
    var im_id: String,
    var lat: Double,
    var lng: Double,
    var official: Boolean,
    var sexual_orientation: Boolean,
    var major: String,
    var enroll: Int,
    var degree: Int,
    var certification_status: Int,
    var movies: List<String>,
    var music: List<String>,
    var books: List<String>,
    var hobbies: List<String>,
    var bio: String,
    var paper_id: String,
    var albums: List<String>,
    var tags: Tags,
    var voice_description_url: String,
    var real_birthday_type: Int,
    var user_scope: String,
    var invite_friend_url: String,
    var can_use_radar: Boolean,
    var total_points: Int,
    var current_points: Int,
    var level: Level,
    var same_city: Boolean,
    var city: SummerCity,
    var province: SummerProvince,
    var school: SummerSchool,
    var department: SummerDepartment,
    var charaters: List<String>,
    var in_blocks: Boolean,
    var in_shields: Boolean,
    var show_game_invite: Boolean
) : Parcelable {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readParcelable(Tags::class.java.classLoader),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Level::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(SummerCity::class.java.classLoader),
        parcel.readParcelable(SummerProvince::class.java.classLoader),
        parcel.readParcelable(SummerSchool::class.java.classLoader),
        parcel.readParcelable(SummerDepartment::class.java.classLoader),
        parcel.createStringArrayList(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    data class Tags(
        var music: List<String>?,
        var charater: List<String>?,
        var book: List<String>?,
        var movie: List<String>?,
        var series: List<String>?,
        var sport: List<String>?,
        var food: List<String>?,
        var traval: List<String>?,
        var hangout: List<String>?,
        var pet: List<String>?,
        var dream: List<String>?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeStringList(music)
            parcel.writeStringList(charater)
            parcel.writeStringList(book)
            parcel.writeStringList(movie)
            parcel.writeStringList(series)
            parcel.writeStringList(sport)
            parcel.writeStringList(food)
            parcel.writeStringList(traval)
            parcel.writeStringList(hangout)
            parcel.writeStringList(pet)
            parcel.writeStringList(dream)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Tags> {
            override fun createFromParcel(parcel: Parcel): Tags {
                return Tags(parcel)
            }

            override fun newArray(size: Int): Array<Tags?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Level(
        var name: String,
        var level: Int
    ) : Parcelable {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(level)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Level> {
            override fun createFromParcel(parcel: Parcel): Level {
                return Level(parcel)
            }

            override fun newArray(size: Int): Array<Level?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nickname)
        parcel.writeString(avatar)
        parcel.writeInt(gender)
        parcel.writeString(birthday)
        parcel.writeInt(relationship_status)
        parcel.writeInt(constellation)
        parcel.writeString(im_id)
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
        parcel.writeByte(if (official) 1 else 0)
        parcel.writeByte(if (sexual_orientation) 1 else 0)
        parcel.writeString(major)
        parcel.writeInt(enroll)
        parcel.writeInt(degree)
        parcel.writeInt(certification_status)
        parcel.writeStringList(movies)
        parcel.writeStringList(music)
        parcel.writeStringList(books)
        parcel.writeStringList(hobbies)
        parcel.writeString(bio)
        parcel.writeString(paper_id)
        parcel.writeStringList(albums)
        parcel.writeParcelable(tags, flags)
        parcel.writeString(voice_description_url)
        parcel.writeInt(real_birthday_type)
        parcel.writeString(user_scope)
        parcel.writeString(invite_friend_url)
        parcel.writeByte(if (can_use_radar) 1 else 0)
        parcel.writeInt(total_points)
        parcel.writeInt(current_points)
        parcel.writeParcelable(level, flags)
        parcel.writeByte(if (same_city) 1 else 0)
        parcel.writeParcelable(city, flags)
        parcel.writeParcelable(province, flags)
        parcel.writeParcelable(school, flags)
        parcel.writeParcelable(department, flags)
        parcel.writeStringList(charaters)
        parcel.writeByte(if (in_blocks) 1 else 0)
        parcel.writeByte(if (in_shields) 1 else 0)
        parcel.writeByte(if (show_game_invite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SummerUserData> {
        override fun createFromParcel(parcel: Parcel): SummerUserData {
            return SummerUserData(parcel)
        }

        override fun newArray(size: Int): Array<SummerUserData?> {
            return arrayOfNulls(size)
        }
    }
}