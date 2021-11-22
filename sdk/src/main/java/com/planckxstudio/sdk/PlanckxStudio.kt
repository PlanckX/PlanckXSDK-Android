package com.planckxstudio.sdk

import com.planckxstudio.sdk.http.HttpCallback
import com.planckxstudio.sdk.http.HttpRequest


object PlanckxStudio {

    /**
     * init
     * @param apiKey String API key
     * @param secretKey String Signature Key used for authentication and encryption
     */
    fun init(apiKey: String, secretKey: String) {
        Constants.apiKey = apiKey
        Constants.secretKey = secretKey
    }

    /**
     * @param playerId String Player ID
     * @param callback HttpCallback  If the binding is successful: isBind is true, if not: isBind is false, use WebView to open openUrl for binding.
     *
     */
    fun judgeBindStatus(playerId: String, callback: HttpCallback) {
        HttpRequest.doGet(
            Constants.path + "checkBind/$playerId",
            null,
            callback
        )
    }

    /**
     * Get all of the player's NFT
     *
     * @param playerId String Player ID
     * @param callback HttpCallback
     */
    fun searchPlayersNft(playerId: String, callback: HttpCallback) {
        HttpRequest.doGet(
            Constants.path + "NFT/player/list/$playerId",
            null,
            callback
        )
    }

    /**
     * Get all NFT's in the game
     * tokenId
     * gameId
     * authorAddress
     * ownerAddress
     * nftType
     * nftName
     * nftDescription
     * nftContent
     * nftData
     *
     * @param callback HttpCallback
     */
    fun searchGamesNft(callback: HttpCallback) {
        HttpRequest.doGet(
            Constants.path + "NFT/list",
            null, callback
        )
    }

    /**
     * Query NFT assets by TokenId
     * tokenId
     * gameId
     * authorAddress
     * ownerAddress
     * nftType
     * nftName
     * nftDescription
     * nftContent
     * nftData
     *
     * @param tokenId String
     * @param callback HttpCallback
     */
    fun searchTokenNft(tokenId: String, callback: HttpCallback) {
        HttpRequest.doGet(
            Constants.path + "NFT/token/$tokenId",
            null, callback
        )
    }

}