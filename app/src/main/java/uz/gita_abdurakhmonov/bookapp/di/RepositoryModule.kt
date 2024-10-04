package uz.gita_abdurakhmonov.bookapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita_abdurakhmonov.bookapp.domain.Repository
import uz.gita_abdurakhmonov.bookapp.domain.RepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @[Binds ViewModelScoped]
    fun bindsRepository(impl:RepositoryImpl):Repository
}