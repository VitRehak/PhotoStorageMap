package cz.uhk.fim.photostoragemap.dependenciInjection

import androidx.room.Room
import cz.uhk.fim.photostoragemap.model.ImageDatabase
import cz.uhk.fim.photostoragemap.viewModels.ImageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModules by lazy {
    listOf(dataModule, uiModule)
}

val dataModule = module {
    db()
}

val uiModule = module {
    viewModel {
        ImageViewModel(
            get(),
            context = androidApplication(),
        )
    }
}

private fun Module.db() {
    // DB
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = ImageDatabase::class.java,
            name = ImageDatabase.Name,

            ).fallbackToDestructiveMigration()
            .build()
    }
    // DAO
    single {
        get<ImageDatabase>().imageDao()
    }
}