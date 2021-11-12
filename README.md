# PlanckXSDK-Android

Welcome to the PlanckX Studio SDK！

The PlanckX Studio SDK  contains  the basic SDK tools. You can embed the SDK into your game creation to support the mint and issuance of NFT (Non-fungible-token) assets in your game creation, Match the PlanckX platform account with your game account, And link the NFT assets holder（Usually the player who bought the NFT） by the asset owner to the game for use.

欢迎使用 PlanckX Studio SDK！

The PlanckX Studio SDK  包括了基本的SDK工具，您可以将SDK嵌入您的游戏创作中，从而支持在您的游戏创作中铸造和发行NFT（Non-fungible-token）资产，关联PlanckX平台账户和您的游戏账户，并将资产所有者（通常是购买了NFT的玩家）持有的NFT资产关联到游戏中使用。

##导入
````groovy
allprojects {
	repositories {
			...
			maven { url 'https://jitpack.io' }
	}
}
````
````groovy
dependencies {
	...
	implementation 'com.github.PlanckX:PlanckXSDK-Android:Tag'
}
````
## 用法

-  初始化
````java
    /**
     * @param apiKey String API访问密钥
     * @param secretKey String 签名认证加密所使⽤的密钥
     */
    fun init(apiKey: String, secretKey: String)
````
- 判断该玩家是否绑定
````java
  /**
     * @param playerId String 玩家ID
     * @param callback HttpCallback
     */
fun judgeBindStatus(playerId: String, callback: HttpCallback)
````

- 获取该玩家的所有NFT
````java
  /**
     * @param playerId String 玩家ID
     * @param callback HttpCallback
     */
 fun searchPlayersNft(playerId: String, callback: HttpCallback)
````

- 获取游戏内所有的NFT
````java
   fun searchGamesNft(callback: HttpCallback)
````
- TokenId查询NFT资产
````java
  /**
     * @param tokenId
     * @param callback HttpCallback
     */
  fun searchTokenNft(tokenId: String, callback: HttpCallback)
````



