package tech.jeanpaultossou.fdj.parissportifs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.jeanpaultossou.fdj.parissportifs.core.Constants
import tech.jeanpaultossou.fdj.parissportifs.core.Constants.BASE_URL
import tech.jeanpaultossou.fdj.parissportifs.core.isDeviceOnline
import tech.jeanpaultossou.fdj.parissportifs.data.remote.TheSportsDBApi
import tech.jeanpaultossou.fdj.parissportifs.data.repository.LeagueRepositoryImpl
import tech.jeanpaultossou.fdj.parissportifs.data.repository.TeamRepositoryImpl
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.LeagueRepository
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.TeamRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheSportsDBApi(@ApplicationContext context:Context):TheSportsDBApi{
        val cacheSize = (5 *1024*1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val clientBuilder = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isDeviceOnline(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheSportsDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLeagueRepository(api: TheSportsDBApi):LeagueRepository{
        return LeagueRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(api: TheSportsDBApi): TeamRepository{
        return TeamRepositoryImpl(api)
    }
}