package com.artemissoftware.njordshop.features.form.data.di

import com.artemissoftware.njordshop.features.form.data.validator.EmailPatternValidator
import com.artemissoftware.njordshop.features.form.domain.validator.PatternValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidatorModule {

    @Provides
    @Singleton
    fun provideEmailPatternValidator(): PatternValidator {
        return EmailPatternValidator()
    }
}