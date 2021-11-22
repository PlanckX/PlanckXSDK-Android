# PlanckXSDK-Android
---
[![](https://jitpack.io/v/PlanckX/PlanckXSDK-Android.svg)](https://jitpack.io/#PlanckX/PlanckXSDK-Android)

### 欢迎使用 PlanckX Studio SDK！
​
The PlanckX Studio SDK  包括了基本的SDK工具，您可以将SDK嵌入您的游戏创作中，从而支持在您的游戏创作中铸造和发行NFT（Non-fungible-token）资产，关联PlanckX平台账户和您的游戏账户，并将资产所有者（通常是购买了NFT的玩家）持有的NFT资产关联到游戏中使用。


### 引入
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
	implementation 'com.github.PlanckX:PlanckXSDK-Android:1.0.0'
}
````
### 使用

````
   先调用初始化方法，再调用其他接口
````
### 方法
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
     * @param callback HttpCallback 如果绑定成功：isBind为true，
     * 如果尚未绑定：isBind为false,使用WebView打开openUrl进行绑定操作。
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



