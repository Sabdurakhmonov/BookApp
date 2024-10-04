package uz.gita_abdurakhmonov.bookapp.domain

import kotlinx.coroutines.flow.Flow
import uz.gita_abdurakhmonov.bookapp.data.BookData

interface Repository {
    fun getAll():Flow<Result<List<BookData>>>
    fun downloadPdf(pdfUrl:String,name:String):Flow<Result<String>>
    fun checkPdf(name: String):Flow<Result<String>>
}