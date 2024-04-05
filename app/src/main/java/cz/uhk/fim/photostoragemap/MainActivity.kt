package cz.uhk.fim.photostoragemap

import android.database.CursorWindow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.uhk.fim.photostoragemap.ui.AppContainer
import cz.uhk.fim.photostoragemap.ui.theme.PhotoStorageMapTheme
import java.lang.reflect.Field


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.setAccessible(true)
            field.set(null, 500 * 1024 * 1024) //the 500MB is the new size
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setContent {
            PhotoStorageMapTheme {
                AppContainer()
            }
        }
    }
}