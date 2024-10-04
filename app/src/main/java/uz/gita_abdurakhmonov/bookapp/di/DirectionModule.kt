package uz.gita_abdurakhmonov.bookapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped
import uz.gita_abdurakhmonov.bookapp.screens.home.download.DownloadContract
import uz.gita_abdurakhmonov.bookapp.screens.home.download.DownloadDirection
import uz.gita_abdurakhmonov.bookapp.screens.home.info.InfoContract
import uz.gita_abdurakhmonov.bookapp.screens.home.info.InfoDirection
import uz.gita_abdurakhmonov.bookapp.screens.home.menu.MenuContract
import uz.gita_abdurakhmonov.bookapp.screens.home.menu.MenuDirection
import uz.gita_abdurakhmonov.bookapp.screens.home.reader.ReaderContract
import uz.gita_abdurakhmonov.bookapp.screens.home.reader.ReaderDirection

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @[Binds ViewModelScoped]
    fun bindsMenuDirection(impl:MenuDirection):MenuContract.Direction

    @[Binds ViewModelScoped]
    fun bindsDownloadDirection(impl:DownloadDirection):DownloadContract.Direction

     @[Binds ViewModelScoped]
    fun bindsInfoDirection(impl:InfoDirection):InfoContract.Direction

     @[Binds ViewModelScoped]
    fun bindsReaderDirection(impl:ReaderDirection):ReaderContract.Direction

}