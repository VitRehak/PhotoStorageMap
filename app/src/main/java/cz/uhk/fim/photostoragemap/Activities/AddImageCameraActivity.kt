package cz.uhk.fim.photostoragemap.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import cz.uhk.fim.photostoragemap.ui.image.AddImageCameraScreen
import cz.uhk.fim.photostoragemap.ui.theme.PhotoStorageMapTheme

class AddImageCameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoStorageMapTheme {
                AddImageCameraScreen()
            }
        }
    }
}
