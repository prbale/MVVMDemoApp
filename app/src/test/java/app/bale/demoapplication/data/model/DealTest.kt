package app.bale.demoapplication.data.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DealTest {

    lateinit var deal: Deal

    val id: String = "383747"
    val name: String = "Preorder Samsung"
    val description: String = "Preorder Samsung Galaxy Z"
    val type: String = "h"
    val cost = 99.99
    val originalCost = 1200.00
    val provider = "Samsung"
    val productLink = "https://picsum.photos/200"
    val imageUrl = "https://picsum.photos/200"
    val likeCount = 11
    val commentsCount = 4
    val shippingCost = 0.0

    @Before
    @Throws(Exception::class)
    fun setUp() {

        deal = Deal(
            id = "383747",
            name = "Preorder Samsung",
            description = "Preorder Samsung Galaxy Z",
            type = "h",
            cost = 99.99,
            original_cost = 1200.00,
            provider = "Samsung",
            product_link = "https://picsum.photos/200",
            image_url = "https://picsum.photos/200",
            like_count = 11,
            comments_count = 4,
            shipping_cost = 0.0
        )
    }

    @Test
    fun `test deal name`() {
        val dealCopy = deal.copy()
        Assert.assertEquals(dealCopy.name, name)
    }

}