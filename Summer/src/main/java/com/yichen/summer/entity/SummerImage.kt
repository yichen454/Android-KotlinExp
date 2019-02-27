package com.yichen.summer.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Chen on 2019/2/18
 */
data class SummerImage(
    var width: Int,
    var height: Int,
    var url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SummerImage> {
        override fun createFromParcel(parcel: Parcel): SummerImage {
            return SummerImage(parcel)
        }

        override fun newArray(size: Int): Array<SummerImage?> {
            return arrayOfNulls(size)
        }
    }
}