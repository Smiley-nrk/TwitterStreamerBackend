# Technology Used:
  - SpringBoot
  - MongoDB (Embedded)

# Concepts Used:
  - Reactive Webflux
  - Reactive Embedded MongoDB
  - Server Sent Events
  - Lombok

# Before you run:
- Add your 'bearerToken' in application.properties (This is generated after creating [twitter developer](https://developer.twitter.com/en/apply-for-access) account.)
- Configure CrossOrigin in "com.smileynrk.twitterstreamer.controller.StreamController" class to allow your front-end server to communicate with this project.
- I have used OpenJdk 11. If you want to use other version, change java.version in pom.xml (recemmended to use java 8 or higher)

# How to run?
- Run as maven build with following goal: spring-boot:run
    ### OR
- Run as java with following main file: com.smileynrk.twitterstreamer.TwitterStreamerApplication

# How to communicate?
  ## 1)Fetch current filters:
      -url: localhost:8080/getFilters
  ![Fetch Filters](/ss/getFilters.PNG?raw=true)
      
      -Notice that:
        1.Author filter is of format 'from:**' in value and 'a-**' in tag
        2.Tag filter is of format '**' in value and 't-**' in tag
        3.Filters having both values configured are of format '** from:*' in value and 'b-*:**' in tag
        
  ## 2)Add new filter:
      
      -url: localhost:8080/addFilter?author=**
  ![add author filter](/ss/addFilter-Author.PNG?raw=true)
  
      -url: localhost:8080/addFilter?tag=**
  ![add tag filter](/ss/addFilter-Tag.PNG?raw=true)
      
      -url: localhost:8080/addFilter?author=**&tag=**
  ![add author and tag filter](/ss/addFilter-Author%20and%20Tag.PNG?raw=true)
      
      -Notice that:
        1.On successful creation of filter, the response will have an id, "created":"1" and "not_created":"0"
        2.In case of any error/ invalid filter/ duplicate filter, you will see... "created":"0","not_created":"1"
        
  ## 3)Stream tweets:
      -url: localhost:8080?getTweets
  ![Streaming](/ss/Streaming.gif)
      
      -Notice that:
        1.In "matchingRules", you can see the filter tag which fetched the tweet.
        2.Non-readable characters are because currently all language tweets are being fetched and some characters are not supported here. (If you want to add language constraint, add a filter of language in tag. Refer 'lang:' operator from [here](https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule) for information)
        
  ## 4)Delete filter:
      -url: localhost:8080?deleteFilter?id=**
  ![delete filter](/ss/deleteFilter.PNG?raw=true)
  

# Want to use YOUR MongoDB instead of the embedded one?
     - Comment the file: com.smileynrk.twitterstreamer.MongoConfig
     - Comment "de.flapdoodle.embed.mongo" dependency in pom.xml:    
     - Configure application.properties to connect to your own MongoDB server. (Refer 'Defining MongoDB properties' section in [this](https://www.journaldev.com/18156/spring-boot-mongodb) page)
 
 # References to learn more
 https://www.baeldung.com/spring-data-mongodb-reactive
 
 https://developer.okta.com/blog/2019/02/21/reactive-with-spring-boot-mongodb
 
 https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/introduction
