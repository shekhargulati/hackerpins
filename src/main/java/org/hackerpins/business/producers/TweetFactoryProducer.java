package org.hackerpins.business.producers;

import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import javax.enterprise.inject.Produces;

/**
 * Created by shekhargulati on 19/04/14.
 */
public class TweetFactoryProducer {

    @Produces
    public TwitterFactory twitterFactory(){
        Configuration configuration = new ConfigurationBuilder().
                setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY")).
                setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET")).
                setOAuth2AccessToken(System.getenv("TWITTER_OAUTH_ACCESS_TOKEN")).
                setOAuthAccessTokenSecret(System.getenv("TWITTER_OAUTH_ACCESS_TOKEN_SECRET")).
                setDebugEnabled(true).
                build();
        TwitterFactory twitterFactory = new TwitterFactory(configuration);
        return twitterFactory;
    }
}
