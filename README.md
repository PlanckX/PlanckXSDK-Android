# PlanckXSDK-Android
---
[![](https://jitpack.io/v/PlanckX/PlanckXSDK-Android.svg)](https://jitpack.io/#PlanckX/PlanckXSDK-Android)

### Welcome to the PlanckX Studio SDK！

The PlanckX Studio SDK  contains  the basic SDK tools. You can embed the SDK into your game creation to support the mint and issuance of NFT (Non-fungible-token) assets in your game creation, Match the PlanckX platform account with your game account, And link the NFT assets holder（Usually the player who bought the NFT） by the asset owner to the game for use.

#### [中文版文档](https://github.com/PlanckX/PlanckXSDK-Android/blob/main/README-zh.md)

### Gradle Setup
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
### Usage

````
   Call the initialization method first and then the others.
````
### Interface
-  Init
````java
    /**
     * @param apiKey String API KEY.
     * @param secretKey String String Signature Key used for authentication and encryption.     */
    fun init(apiKey: String, secretKey: String)
````
- Determine the player's binding state
````java
  /**
     * @param playerId String Player ID
     * @param callback HttpCallback  If the binding is successful: isBind is true, if not: isBind is false, use WebView to open openUrl for binding.
     * 
     */
fun judgeBindStatus(playerId: String, callback: HttpCallback)
````

- Get all of the player's NFT
````java
  /**
     * @param playerId String Player ID
     * @param callback HttpCallback
     */
 fun searchPlayersNft(playerId: String, callback: HttpCallback)
````

- Get all NFT's in the game
````java
 /**
     * @param callback HttpCallback
     */
   fun searchGamesNft(callback: HttpCallback)
````
- Query NFT assets by TokenId
````java
  /**
     * @param tokenId
     * @param callback HttpCallback
     */
  fun searchTokenNft(tokenId: String, callback: HttpCallback)
````



