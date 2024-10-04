package uz.gita_abdurakhmonov.bookapp.ui.theme.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import com.valentinilk.shimmer.shimmer
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.ui.theme.BookAppTheme
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
import uz.gita_abdurakhmonov.bookapp.ui.theme.display
import uz.gita_abdurakhmonov.bookapp.ui.theme.inter

@Composable
fun AppInputTextComponent(
    modifier: Modifier,
    hint: String,
    text: String,
    minHeight: Dp,
    value: ((String) -> Unit)? = null,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable() (() -> Unit)? = null,
    endIcon: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
) {
    BasicTextField(
        value = text, onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth(1f)
            .defaultMinSize(minHeight = minHeight)
            .background(color = Cl.bgSearch, shape = RoundedCornerShape(32.dp)),
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter()
        ),
        cursorBrush = SolidColor(Cl.textColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,

        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(minHeight),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.invoke()
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Cl.tabColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = inter(),
                            modifier = Modifier.focusable(false)
                        )
                    }
                    innerTextField()
                    Spacer(modifier = Modifier.weight(0.2f))

                    endIcon?.invoke()
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun Views() {
    BookAppTheme {
        val value = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
        }
    }

}

@Composable
fun ItemTab(tabName: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
            .border(1.dp, color = Cl.tabColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = tabName,
            fontFamily = inter(),
            fontWeight = FontWeight.Normal,
            color = Cl.tabColor,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ItemDownload(data:BookData,listener: (BookData) -> Unit) {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 8.dp)
            .width(280.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(color = Cl.btnTextColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight()
                .background(color = Cl.bgItem)
                .padding(16.dp)

        ) {
            Log.d("AAA", "ItemDownload: ${data.imgUrl}")
            Image(
                painter = rememberAsyncImagePainter(model = data.imgUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(
                text = data.bookTitle,
                fontFamily = display(),
                fontWeight = FontWeight.Bold,
                color = Cl.textColor,
            )
            currentProgress = 70f
            Text(
                text = data.bookAuthor,
                fontFamily = inter(),
                fontWeight = FontWeight.Normal,
                color = Cl.author,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.End)
                    .padding(top = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .border(1.2.dp, color = Cl.btnColor, shape = RoundedCornerShape(8.dp))
                    .clickable { listener.invoke(data)}
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ko'rish",
                    fontSize = 13.sp,
                    fontFamily = inter(),
                    fontWeight = FontWeight.Normal,
                    color = Cl.btnColor
                )
            }

        }
    }
}

@Composable
fun ItemGrid(bookData: BookData,listener:(BookData)->Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Cl.btnTextColor)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(color = Cl.bdItem2, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp)
                .padding(horizontal = 20.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(model = bookData.imgUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = bookData.bookTitle,
                fontWeight = FontWeight.Bold,
                fontFamily = display(),
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .background(
                        color = Cl.bgStar,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 4.dp)
                    .padding(vertical = 1.8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "icon",
                    tint = Cl.starColor
                )
                Text(
                    text = bookData.pp,
                    fontFamily = inter(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
            }

        }
        Text(
            text = bookData.bookAuthor,
            fontSize = 12.sp,
            fontFamily = inter(),
            fontWeight = FontWeight.Normal,
            color = Cl.tabColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .border(
                    1.2.dp,
                    color = Cl.tabColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { listener.invoke(bookData)}
                .padding(horizontal = 8.dp)
                .padding(vertical = 4.dp)

                .align(Alignment.End)
        ) {
            Text(
                text = "Ko'rish",
                fontWeight = FontWeight.Normal,
                fontFamily = inter(),
                fontSize = 13.sp
            )
        }


    }
}

@Composable
fun ShimmerUi(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cl.bg)
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()) {
            item(span = { GridItemSpan(2) }) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    AppInputTextComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        hint = "search",
                        text = "",
                        minHeight = 48.dp,
                        onValueChange = { },
                        leadingIcon = {
                            androidx.compose.material3.Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "",
                                modifier = Modifier.padding(end = 6.dp)
                            )
                        }
                    )
                }
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Continue Reading",
                    fontFamily = display(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 12.dp)
                )
            }
            item(span = { GridItemSpan(2) }) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .shimmer()
                ) {
                    items(count = 2) {
                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 12.dp)
                                .fillMaxWidth(0.7f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Cl.btnTextColor)
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp)
                                    .fillMaxHeight()
                                    .background(color = Cl.bgItem)
                                    .padding(16.dp)

                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .width(140.dp)
                                    .fillMaxHeight()
                                    .padding(16.dp)
                            ) {
                            }
                        }
                    }
                }
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Best Sellers",
                    fontFamily = display(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 12.dp)
                )
            }

            items(4) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Cl.btnTextColor)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.7f)
                            .background(color = Cl.bdItem2, shape = RoundedCornerShape(12.dp))
                            .padding(vertical = 16.dp)
                            .padding(horizontal = 20.dp)

                    ) {
                        Box(
                            modifier = Modifier
                                .shimmer()
                                .fillMaxSize()
                                .background(Color.LightGray),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Book of Night",
                            fontWeight = FontWeight.Bold,
                            fontFamily = display(),
                            fontSize = 16.sp,
                            maxLines = 1,
                            modifier = Modifier
                                .shimmer()
                                .weight(1f)
                        )

                        Row(
                            modifier = Modifier
                                .shimmer()
                                .background(
                                    color = Cl.bgStar,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 4.dp)
                                .padding(vertical = 1.8.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = "icon",
                                tint = Cl.starColor
                            )
                            Text(
                                text = "4.5",
                                fontFamily = inter(),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                    Text(
                        text = "Holly Black",
                        fontSize = 12.sp,
                        fontFamily = inter(),
                        fontWeight = FontWeight.Normal,
                        color = Cl.tabColor,
                        modifier = Modifier
                            .shimmer()
                            .padding(horizontal = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .border(
                                1.2.dp,
                                color = Cl.tabColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { }
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 4.dp)

                            .align(Alignment.End)
                    ) {
                        Text(
                            text = "Ko'rish",
                            fontWeight = FontWeight.Normal,
                            fontFamily = inter(),
                            fontSize = 13.sp
                        )
                    }


                }
            }

        }

    }
}