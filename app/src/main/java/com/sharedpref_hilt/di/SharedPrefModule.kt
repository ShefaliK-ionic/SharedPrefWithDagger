package com.sharedpref_hilt.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

  @Provides
  fun  provideSharedPrefObj(@ApplicationContext mCtx:Context):SharedPreferences{

      return mCtx.getSharedPreferences("MyPref",Context.MODE_PRIVATE)
  }


}