package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Description
import org.junit.rules.TestWatcher

/*
@ExperimentalCoroutinesApi
class MainDispatcherRulle(val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {

    override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description?) = Dispatchers.resetMain()

}
*/