package com.planckxstudio.sdk

import android.util.Log
import com.planckxstudio.sdk.http.HttpCallback
import com.planckxstudio.sdk.http.HttpRequest

/**
 *
 * Description: API
 * Author: ricky
 */
object PlanckxStudio {

    /**
     * 初始化
     * @param apiKey String API访问密钥
     * @param secretKey String 签名认证加密所使⽤的密钥
     */
    fun init(apiKey: String, secretKey: String) {
        Constants.apiKey = apiKey
        Constants.secretKey = secretKey
    }

    /**
     * 判断该玩家是否绑定
     *
     * 已绑定返回：{"code":"200","success":true,"msg":"success","desc":null,"data":{"isBind":true,"openUrl":null}}
     *
     * 未绑定返回：{"code":"200","success":true,"msg":"success","desc":null,"data":{"isBind":false,"openUrl":"http://xxx"}}
     * 获取到openUrl，使用webview打开，进行授权绑定操作
     *
     * @param playerId String
     */
    fun judgeBindStatus(playerId: String, callback: HttpCallback) {
        HttpRequest.doGet(
            Constants.path + "checkBind/$playerId",
            null,
            callback
        )
    }

    /**
     * 获取该玩家的所有NFT
     *
     * @param playerId String
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
     * 获取游戏内所有的NFT
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
     * TokenId查询NFT资产
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