package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_teams

import com.google.common.truth.Truth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.manipulation.Ordering.Context
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toLeague
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toTeam
import tech.jeanpaultossou.fdj.parissportifs.data.repository.FakeForTestTeamRepositoryImpl
import tech.jeanpaultossou.fdj.parissportifs.presentation.team_list.TeamListViewModel
import javax.inject.Inject

class GetTeamsUCTest {

    private lateinit var fakeForTestTeamRepositoryImpl: FakeForTestTeamRepositoryImpl
    private lateinit var getTeamsUC: GetTeamsUC

    @Before
    fun setUp() {
        fakeForTestTeamRepositoryImpl = FakeForTestTeamRepositoryImpl()
        getTeamsUC = GetTeamsUC(fakeForTestTeamRepositoryImpl)
    }

    @Test
    fun `Get Team List by league name (french ligue 1), contains brest team return` (): Unit = runBlocking{
        getTeamsUC("French Ligue 1").onEach {res->
            if (res is Resource.Success){
                Truth.assertThat(res.data!!.contains(fakeForTestTeamRepositoryImpl.brest.toTeam()))
            }
        }
    }

    @Test
    fun `Get Team List by league name, order descending returns` (): Unit = runBlocking{
        getTeamsUC("French Ligue 1").onEach {res->
            if (res is Resource.Success){
                Truth.assertThat(res.data!!.first() === fakeForTestTeamRepositoryImpl.brest.toTeam())
            }
        }
    }
}