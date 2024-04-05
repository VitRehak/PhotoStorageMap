package cz.uhk.fim.photostoragemap.ui.image

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.uhk.fim.photostoragemap.ui.theme.PhotoStorageMapTheme
import cz.uhk.fim.photostoragemap.viewModels.ImageViewModel
import org.koin.androidx.compose.getViewModel
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import cz.uhk.fim.photostoragemap.model.entity.ImageModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cz.uhk.fim.photostoragemap.Activities.AddImageCameraActivity
import cz.uhk.fim.photostoragemap.Activities.AddImageFileActivity

@SuppressLint("SuspiciousIndentation")
@Composable
fun ImageScreen(
    viewModel: ImageViewModel = getViewModel(),
) {
    PhotoStorageMapTheme {


        val images = viewModel.images.collectAsState(emptyList())

        Column() {
            LazyColumn(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    items = images.value,
                    key = { it.id },
                ) { image ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(color = MaterialTheme.colorScheme.background),
                    ) {
                        Row(horizontalArrangement = Arrangement.End) {
                            Button(
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                onClick = {
                                    viewModel.deleteImage(image)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = null,
                                )
                            }
                        }
                        Row {
                            AImage(image = image)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)

                        ) {
                            Text(
                                text = image.caption,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Buttons()
            }
        }
    }
}


@Composable
fun Buttons() {
    val context = LocalContext.current
    Column {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                context.startActivity(Intent(context, AddImageCameraActivity::class.java))
            },
            content = {
                Text(
                    text = "Add image camera"
                )
            },
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                context.startActivity(Intent(context, AddImageFileActivity::class.java))
            },
            content = {
                Text(
                    text = "Add image file"
                )
            },
        )
    }
}


@Composable
fun AImage(image: ImageModel) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth(),
        model = image.data,
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}