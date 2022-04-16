package com.example.designapp

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import com.example.data.di.dataModule
import com.example.designapp.ui.home.HomeViewModel
import com.example.designapp.ui.lessons.LessonsViewModel
import com.example.designapp.utils.bindViewModel

class DesignApp : Application(), KodeinAware {

    override val kodein = Kodein {
        import(dataModule)
        bindViewModel<HomeViewModel>() with provider {
            HomeViewModel(instance(), instance())
        }
        bindViewModel<LessonsViewModel>() with provider {
            LessonsViewModel(instance())
        }
        bind<ViewModelFactory>() with singleton {
            ViewModelFactory(this)
        }
    }

}