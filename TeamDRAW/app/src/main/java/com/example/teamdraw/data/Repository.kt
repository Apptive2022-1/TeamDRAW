package com.example.teamdraw.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(localDataSources: LocalDataSources) {
    val local = localDataSources
}