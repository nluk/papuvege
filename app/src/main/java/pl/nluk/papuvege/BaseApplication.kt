package pl.nluk.papuvege

import android.app.Application
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.nluk.papuvege.api.MenuApi
import pl.nluk.papuvege.services.MenuService
import pl.nluk.papuvege.services.impl.MenuServiceImpl
import pl.nluk.papuvege.viewmodels.MenuViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseApplication : Application() {

    private val appModule = module {
        single<MenuService> { MenuServiceImpl(get()) }
        viewModel { MenuViewModel() }
    }
    val networkModule = module {
        factory { }
        factory { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        factory { provideMenuApi(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(appModule, networkModule)
        }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    fun provideMenuApi(retrofit: Retrofit): MenuApi = retrofit.create(MenuApi::class.java)

}