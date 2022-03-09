package app.bale.demoapplication.ui.deals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.ui.MyViewModelFactory
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import androidx.lifecycle.LifecycleOwner
import app.bale.demoapplication.data.repository.RetrofitService
import org.mockito.Mockito.verify


@RunWith(JUnit4::class)
class DealsViewModelTest : TestCase() {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dealsRepository: DealsRepository

    @Mock
    lateinit var retrofitService: RetrofitService

    private var testSingle: Single<List<Deal>>? = null

    lateinit var dealsViewModel: DealsViewModel

    @Mock
    var observer: Observer<DealsUiState>? = null

    @Mock
    var lifecycleOwner: LifecycleOwner? = null
    var lifecycle: Lifecycle? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        dealsViewModel = DealsViewModel(dealsRepository)
        observer?.let { dealsViewModel.dealsUiState.observeForever(it) }
    }

    @Test
    fun `get Deals Success Response`() {

        val dealsList = arrayListOf(Deal())

        `when`(dealsRepository.getAllDeals()).thenReturn(Single.just(dealsList))

        dealsViewModel.getAllDeals()

        verify(observer)?.onChanged(DealsUiState.Loading)
        verify(observer)?.onChanged(DealsUiState.Success(dealsList))

    }

}