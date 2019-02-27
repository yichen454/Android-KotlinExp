package com.yichen.summer.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Chen on 2019/2/18
 */
data class SummerCity(var id: String, var name: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SummerCity> {
        override fun createFromParcel(parcel: Parcel): SummerCity {
            return SummerCity(parcel)
        }

        override fun newArray(size: Int): Array<SummerCity?> {
            return arrayOfNulls(size)
        }
    }
}