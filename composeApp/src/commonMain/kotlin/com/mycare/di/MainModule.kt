package com.mycare.di

import RootViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mainModule = module {
    factoryOf(::RootViewModel)
}