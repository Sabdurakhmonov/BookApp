package uz.gita_abdurakhmonov.bookapp.data

import android.media.audiofx.AudioEffect.Descriptor

data class BookData(
    val bookAuthor:String,
    val bookTitle:String,
    val bookSize:String,
    val description: String,
    val imgUrl:String,
    val pdfUrl:String,
    val pp:String = "4.5",
    val pageCount:String
)