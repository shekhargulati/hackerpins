package org.hackerpins.business.services;

import org.hackerpins.business.domain.Story;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by shekhargulati on 19/04/14.
 */
@Stateless
public class TweetService {

    @Inject
    private TwitterFactory twitterFactory;
    @Inject
    private Logger logger;


    @Asynchronous
    public void tweet(Story story) {
        Twitter twitter = twitterFactory.getInstance(new AccessToken(System.getenv("TWITTER_OAUTH_ACCESS_TOKEN"), System.getenv("TWITTER_OAUTH_ACCESS_TOKEN_SECRET")));
        try {
            StringBuilder builder = new StringBuilder(story.getTitle());
            builder.append(" " + story.getUrl()).append(" via @hackerpins");
            twitter.updateStatus(builder.toString());
        } catch (TwitterException e) {
            logger.warning("Unable to send tweet " + e.getMessage());
        }
    }

}
