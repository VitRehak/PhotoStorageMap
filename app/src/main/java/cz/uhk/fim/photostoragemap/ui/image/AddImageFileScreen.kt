package cz.uhk.fim.photostoragemap.ui.image

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
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
import coil.compose.AsyncImage
import cz.uhk.fim.photostoragemap.Activities.AddImageFileActivity
import cz.uhk.fim.photostoragemap.Activities.ImageActivity
import cz.uhk.fim.photostoragemap.MainActivity
import cz.uhk.fim.photostoragemap.viewModels.ImageViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AddImageFileScreen(
    viewModel: ImageViewModel = getViewModel()
) {
    val context = LocalContext.current

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var content by remember {
        mutableStateOf<Boolean>(false)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                selectedImageUri = uri
                content = true
            }
        }
    )
    val inputText = remember { mutableStateOf("") }


    Column(
    ) {
        AsyncImage(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth(),
            model = selectedImageUri,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
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
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            ) {
                Text("Open from file")
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
                    selectedImageUri = null
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
                    viewModel.addImage(caption = inputText.value, uri = selectedImageUri)
                    selectedImageUri = null
                    inputText.value = ""
                    context.startActivity(Intent(context, ImageActivity::class.java))
                },
            ) {
                Text("Add")
            }
        }
    }
}