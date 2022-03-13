package app.bale.demoapplication.ui.dealList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.repository.RetrofitService
import app.bale.demoapplication.data.util.Resource
import io.reactivex.Single
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class DealsViewModelTest : TestCase() {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dealsRepository: DealsRepository

    lateinit var dealsViewModel: DealsViewModel

    @Mock
    var observer: Observer<Resource<List<Deal>>>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        dealsViewModel = DealsViewModel(dealsRepository)
        observer?.let { dealsViewModel.deals.observeForever(it) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get Deals Success Response`() = runBlockingTest {

    val dealsList = arrayListOf(Deal().apply {
            id = "1"
            name = "Deal Name"
        })

        whenever(dealsRepository.getAllDeals()).thenReturn(dealsList)

        dealsViewModel.getAllDeals()

        verify(observer)?.onChanged(Resource.loading(null))
        verify(observer)?.onChanged(Resource.success(dealsList))
    }

}