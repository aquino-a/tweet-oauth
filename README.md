# tweet-oauth
## oauth server for twitter

Server for getting a fresh access and refresh token for you twitter bots.
Once you have the intial token, the [twitter api sdk](https://github.com/twitterdev/twitter-api-java-sdk) has an option to auto refresh.

### Build
Add `clientId` and `clientSecret` to the `application.properties` and run as a service.
Get new tokens whenever you need.

This project requires the [tweet-delete](https://github.com/aquino-a/tweet-delete) project to build.

Build with `mvn install`

### Run
You can run the release jar with your own settings without building.

`java -jar tweet-oauth.jar --server.port=<port> --clientId=<id> --clientSecret=<secret>`
