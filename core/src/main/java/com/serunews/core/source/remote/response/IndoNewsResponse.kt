package com.serunews.core.source.remote.response

import com.google.gson.annotations.SerializedName


data class IndoNewsResponse(

    @field:SerializedName("important")
    val important: String,

    @field:SerializedName("posts")
    val posts: List<PostsItem>,

    @field:SerializedName("status")
    val status: Int
)

data class PostsItem(
    @field:SerializedName("aksesurl")
    val aksesurl: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("foto")
    val foto: String,

    @field:SerializedName("headline")
    val headline: String,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("rilisberita")
    val rilisberita: String,

    @field:SerializedName("news_badge")
    val news_badge: String,
)

