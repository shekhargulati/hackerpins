package org.hackerpinstest.business.services;

import org.hackerpins.business.bean_validation.ImageOrVideoSrcUrl;
import org.hackerpins.business.builders.StoryBuilder;
import org.hackerpins.business.domain.Profile;
import org.hackerpins.business.domain.Role;
import org.hackerpins.business.domain.Story;
import org.hackerpins.business.producers.EntityManagerProducer;
import org.hackerpins.business.services.ProfileService;
import org.hackerpins.business.services.StoryService;
import org.hackerpins.rest.config.RestConfig;
import org.hackerpins.rest.resources.StoryResource;
import org.hackerpins.rest.utils.Constants;
import org.hackerpins.rest.vo.Credentials;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Created by shekhargulati on 19/04/14.
 */
@RunWith(Arquillian.class)
public class ProfileServiceTest {

    @Deployment
    public static Archive<?> deployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class).
                addPackage(ImageOrVideoSrcUrl.class.getPackage()).
                addPackage(StoryBuilder.class.getPackage()).
                addPackage(Story.class.getPackage()).
                addPackage(EntityManagerProducer.class.getPackage()).
                addPackage(StoryService.class.getPackage()).
                addPackage(RestConfig.class.getPackage()).
                addPackage(StoryResource.class.getPackage()).
                addPackage(Constants.class.getPackage()).
                addPackage(Credentials.class.getPackage()).
                addAsResource("META-INF/persistence.xml").
                addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        System.out.println(webArchive.toString(true));
        return webArchive;

    }

    @Inject
    private EntityManager entityManager;
    @Inject
    private UserTransaction userTransaction;

    @Before
    public void setup() throws Exception {
        userTransaction.begin();
        entityManager.createQuery("DELETE from Profile p").executeUpdate();
        userTransaction.commit();
    }

    @Inject
    private ProfileService profileService;

    @Test
    public void testSave() throws Exception {
        Profile profile = new Profile("test_user", "test@test.com", "Test User");
        profile.setPassword("password");
        Profile savedProfile = profileService.save(profile);
        Assert.assertNotNull(savedProfile.getId());
        Assert.assertEquals(Role.USER, savedProfile.getRole());
        Assert.assertNotNull(savedProfile.getRegisteredOn());
    }

    @Test
    public void testFindOne() throws Exception {
        Profile profile = new Profile("test_user", "test@test.com", "Test User");
        profile.setPassword("password");
        Profile savedProfile = profileService.save(profile);

        Profile foundProfile = profileService.findOne(savedProfile.getId());
        Assert.assertEquals(savedProfile, foundProfile);
    }

    @Test
    public void testFindByEmail() throws Exception {
        Profile profile = new Profile("test_user", "test@test.com", "Test User");
        profile.setPassword("password");
        Profile savedProfile = profileService.save(profile);

        Profile foundProfile = profileService.findByEmail("test@test.com");
        Assert.assertEquals(savedProfile, foundProfile);
    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {
        Profile profile = new Profile("test_user", "test@test.com", "Test User");
        profile.setPassword("password");
        Profile savedProfile = profileService.save(profile);

        Profile foundProfile = profileService.findByUsernameOrEmailAndPassword("test@test.com","password");
        Assert.assertEquals(savedProfile, foundProfile);
    }

    @Test
    public void testFindByUsernameAndPassword() throws Exception {
        Profile profile = new Profile("test_user", "test@test.com", "Test User");
        profile.setPassword("password");
        Profile savedProfile = profileService.save(profile);

        Profile foundProfile = profileService.findByUsernameOrEmailAndPassword("test_user","password");
        Assert.assertEquals(savedProfile, foundProfile);
    }
}
