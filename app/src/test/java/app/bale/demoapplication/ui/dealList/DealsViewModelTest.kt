package app.bale.demoapplication.ui.dealList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.repository.RetrofitService
import io.reactivex.Single
import junit.framework.TestCase
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
    lateinit var retrofitService: RetrofitService

    lateinit var dealsRepository: DealsRepository

    lateinit var dealsViewModel: DealsViewModel

    @Mock
    var observer: Observer<DealsUiState>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        dealsRepository = DealsRepository(retrofitService)
        dealsViewModel = DealsViewModel(dealsRepository)
        observer?.let { dealsViewModel.dealsUiState.observeForever(it) }
    }

    @Test
    fun `get Deals Success Response`() {

        val dealsList = arrayListOf(Deal().apply {
            id = "1"
            name = "Deal Name"
        })

        whenever(dealsRepository.getAllDeals()).thenReturn(Single.just(dealsList))

        dealsViewModel.getAllDeals()

        verify(observer)?.onChanged(DealsUiState.Loading)
        verify(observer)?.onChanged(DealsUiState.Success(arrayListOf()))
    }

}