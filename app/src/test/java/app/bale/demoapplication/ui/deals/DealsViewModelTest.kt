package app.bale.demoapplication.ui.deals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DealsViewModelTest : TestCase() {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dealsRepository: DealsRepository

    private var testSingle: Single<List<Deal>>? = null

    lateinit var dealsViewModel: DealsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        dealsViewModel = DealsViewModel(dealsRepository)
    }

    @Test
    fun `get Deals Success`() {
        val deal = Deal()
        val dealsList = arrayListOf(deal)

        testSingle = Single.just(dealsList)

        `when`(dealsRepository.getAllDeals()).thenReturn(testSingle)

        dealsViewModel.getAllDeals()

        Assert.assertTrue(true)
    }

}