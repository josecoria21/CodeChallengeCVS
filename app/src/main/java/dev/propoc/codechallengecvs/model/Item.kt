package dev.propoc.codechallengecvs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ImageResponseModel(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Item>,
)

@Parcelize
data class Item(
    val title: String,
    val link: String,
    val media: Media,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    val tags: String,
) : Parcelable

@Parcelize
data class Media(
    val m: String,
) : Parcelable
