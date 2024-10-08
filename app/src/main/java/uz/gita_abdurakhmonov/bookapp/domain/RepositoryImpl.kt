package uz.gita_abdurakhmonov.bookapp.domain

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.utils.toBookData
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
  @ApplicationContext private val context: Context
) : Repository {
    private val db = Firebase.firestore.collection("Book")
    override fun getAll(): Flow<Result<List<BookData>>> = callbackFlow {
        db.addSnapshotListener { value, error ->
            val list = mutableListOf<BookData>()
            if (value != null) {
                value.map { list.add(it.toBookData()) }
            }
            trySendBlocking(Result.success(list))
        }
        awaitClose()
    }


//    override fun downloadPdf(pdfUrl: String, name: String): Flow<Result<String>> = callbackFlow {
//        val client = OkHttpClient()
//
//        // HTTP so'rovini yaratish
//        val request = Request.Builder()
//            .url(pdfUrl)
//            .build()
//
//        // So'rovni bajarish
//        withContext(Dispatchers.IO) {
//            client.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    trySendBlocking(Result.failure(e))
//
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    // Yuklash muvaffaqiyatli bo'lsa
//                    if (!response.isSuccessful) {
//                        return
//                    }
//
//                    // Yuklangan faylni saqlash
//                    val pdfBytes = response.body?.byteStream()
//
//                    // Fayl saqlanish joyini tanlash (tashqi xotira)
//                    val directoryPath =
//                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
//                            .toString()
//
//                    // Foydalanuvchi tomonidan kiritilgan nom bilan fayl yaratish
//                    val file = File(directoryPath, "$name.pdf")
//
//                    try {
//                        // Faylni saqlash
//                        val outputStream = FileOutputStream(file)
//                        pdfBytes?.copyTo(outputStream)
//                        outputStream.close()
//                        trySendBlocking(Result.success(file.path))
//                        // Foydalanuvchiga xabar berish
//                    } catch (e: IOException) {
//                        trySendBlocking(Result.failure(e))
//
//                    }
//                }
//            })
//        }
//        awaitClose()
//    }

    override fun checkPdf(name: String): Flow<Result<String>> = flow {
        Log.d("AAA", "checkPdf: $name")
        val file: File = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10 yoki undan yuqori versiyalar uchun - ilovaning xotirasi ichida saqlash
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "$name.pdf")
        } else {
            // Android 9 va undan past versiyalar uchun - umumiy tashqi xotira
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "$name.pdf")
        }

        // Fayl mavjud yoki yo'qligini tekshirish
        if (file.exists()) {
            // Fayl mavjud bo'lsa, absolyut yo'lni qaytaradi
            emit(Result.success(file.path))
        } else {
            emit(Result.failure(Exception("topilmadi")))
        }
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)
}

