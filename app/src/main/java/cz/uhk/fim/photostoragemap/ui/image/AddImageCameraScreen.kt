package cz.uhk.fim.photostoragemap.ui.image

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import coil.compose.AsyncImage
import cz.uhk.fim.photostoragemap.Activities.AddImageFileActivity
import cz.uhk.fim.photostoragemap.Activities.ImageActivity
import cz.uhk.fim.photostoragemap.ComposeFileProvider
import cz.uhk.fim.photostoragemap.MainActivity
import cz.uhk.fim.photostoragemap.R
import cz.uhk.fim.photostoragemap.viewModels.ImageViewModel
import org.koin.androidx.compose.getViewModel
import java.io.File


@Composable
fun AddImageCameraScreen(
    viewModel: ImageViewModel = getViewModel()

) {
    val context = LocalContext.current

    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var content by remember {
        mutableStateOf<Boolean>(false)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
            if (hasImage) {
                content = true
            }
        }
    )

    val inputText = remember { mutableStateOf("") }


    Column(
    ) {
        if (hasImage) {
            AsyncImage(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                model = null,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    val uri = ComposeFileProvider.getImageUri(context)
                    cameraLauncher.launch(uri)
                    imageUri = uri
                }
            ) {
                Text("Open camera")
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                ),
                label = {
                    Text(
                        text = "Caption",

                        )
                },
                value = inputText.value,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onValueChange = { inputText.value = it },
            )
        }
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                modifier = Modifier
                    .weight(1F),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    imageUri = null
                    inputText.value = ""
                    context.startActivity(Intent(context, ImageActivity::class.java))
                },
            ) {
                Text("Cancel")
            }

            Button(
                modifier = Modifier
                    .weight(1F),
                shape = RoundedCornerShape(4.dp),
                enabled = content,
                onClick = {
                    viewModel.addImage(caption = inputText.value, uri = imageUri)
                    imageUri = null
                    inputText.value = ""
                    context.startActivity(Intent(context, ImageActivity::class.java))
                },
            ) {
                Text("Add")
            }
        }
    }
}