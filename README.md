# tweet-oauth
## oauth server for twitter

Server for getting a fresh access and refresh token for you twitter bots.
Once you have the intial token, the [twitter api sdk](https://github.com/twitterdev/twitter-api-java-sdk) has an option to auto refresh.

Add `clientId` and `clientSecret` to the `application.properties` and run as a service.
Get new tokens whenever you need.
