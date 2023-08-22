package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_leagues

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.*
import org.hamcrest.core.IsEqual.equalTo

import org.junit.Before
import org.junit.Test
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toLeague
import tech.jeanpaultossou.fdj.parissportifs.data.repository.FakeForTestLeagueRepositoryImpl

class GetLeaguesUCTest {

    private lateinit var getLeaguesUC: GetLeaguesUC
    private lateinit var fakeForTestLeagueRepositoryImpl: FakeForTestLeagueRepositoryImpl

    @Before
    fun setUp() {
        fakeForTestLeagueRepositoryImpl = FakeForTestLeagueRepositoryImpl()
        getLeaguesUC = GetLeaguesUC(fakeForTestLeagueRepositoryImpl)
    }

    @Test
    fun `Get League List, correct fourth league return` (): Unit = runBlocking{
        getLeaguesUC().onEach {res->
            if (res is tech.jeanpaultossou.fdj.parissportifs.core.Resource.Success){
                val fourthLeagueItem = res.data!![4]
                assertThat(fourthLeagueItem.strLeague, equalTo("German Bundesliga"))
            }
        }
    }

    @Test
    fun `Get League List, contains nba league return` (): Unit = runBlocking{
        getLeaguesUC().onEach {res->
            if (res is tech.jeanpaultossou.fdj.parissportifs.core.Resource.Success){
                assert(res.data!!.contains(fakeForTestLeagueRepositoryImpl.nbaLeague.toLeague()))
            }
        }
    }

    @Test
    fun `Get League List, incorrect league return` (): Unit = runBlocking{
        getLeaguesUC().onEach {res->
            if (res is tech.jeanpaultossou.fdj.parissportifs.core.Resource.Success){
                assert(!res.data!!.contains(fakeForTestLeagueRepositoryImpl.fakeFrenchLigueOne.toLeague()))
            }
        }
    }
}