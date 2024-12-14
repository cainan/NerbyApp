package com.cso.nearby.core.network

import com.cso.nearby.core.network.KtorHttpClient.httpClientAndroid
import com.cso.nearby.data.model.Coupon
import com.cso.nearby.data.model.Market
import com.cso.nearby.data.model.MarketDetails
import com.cso.nearby.data.model.NearbyCategory
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    suspend fun getCategories(): Result<List<NearbyCategory>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories").body<List<NearbyCategory>>()
        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets =
            httpClientAndroid.get("$BASE_URL/markets/category/$categoryId").body<List<Market>>()
        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val market = httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()
        Result.success(market)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()
        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }
}