package uz.gita_abdurakhmonov.bookapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigationDispatcher
import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import uz.gita_abdurakhmonov.bookapp.navigation.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigator(dispatcher: AppNavigationDispatcher): AppNavigator
    @Binds
    fun bindNavigationHandler(dispatcher: AppNavigationDispatcher): NavigationHandler
}