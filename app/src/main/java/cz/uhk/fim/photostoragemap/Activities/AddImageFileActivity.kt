package cz.uhk.fim.photostoragemap.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.uhk.fim.photostoragemap.ui.image.AddImageFileScreen
import cz.uhk.fim.photostoragemap.ui.theme.PhotoStorageMapTheme

class AddImageFileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoStorageMapTheme {
                AddImageFileScreen()
            }
        }
    }
}