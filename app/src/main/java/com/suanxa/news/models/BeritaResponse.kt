package com.suanxa.news.models

data class BeritaResponse(
    val succes : String,
    val message : String,
    val data : ArrayList<ListItems>
){
    data class ListItems(
        val id: String,
        val judul : String,
        val isi : String,
        val tanggal: String,
        val gambar : String,
        val rating : Double
    )
}
