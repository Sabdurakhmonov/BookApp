package uz.gita_abdurakhmonov.bookapp.utils

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita_abdurakhmonov.bookapp.data.BookData

fun DocumentSnapshot.toBookData() = BookData(
    bookAuthor = get(BookData::bookAuthor.name).toString(),
    bookTitle = get(BookData::bookTitle.name).toString(),
    bookSize = get(BookData::bookSize.name).toString(),
    description = get(BookData::description.name).toString(),
    imgUrl = get(BookData::imgUrl.name).toString(),
    pdfUrl = get(BookData::pdfUrl.name).toString(),
    pp = get(BookData::pp.name).toString(),
    pageCount = get(BookData::pageCount.name).toString()
)