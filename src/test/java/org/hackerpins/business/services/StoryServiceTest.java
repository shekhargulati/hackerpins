package org.hackerpins.business.services;

import org.hackerpins.business.builders.StoryBuilder;
import org.hackerpins.business.domain.Media;
import org.hackerpins.business.domain.MediaType;
import org.hackerpins.business.domain.Story;
import org.hibernate.validator.internal.util.Contracts;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by shekhargulati on 04/04/14.
 */
@RunWith(Arquillian.class)
public class StoryServiceTest {

    @Deployment
    public static Archive<?> deployment() {
        return ShrinkWrap.create(JavaArchive.class).
                addPackages(true, "org.hackerpins.business").
                addAsManifestResource("META-INF/persistence.xml", "persistence.xml").
                addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

    }

    @Inject
    private StoryService storyService;

    @Test
    public void shouldThrownConstraintVoilationWhenStoryIsNull() throws Exception {
        try {
            storyService.save(null);
        } catch (EJBException e) {
            Throwable cause = e.getCause();
            Assert.assertTrue(cause instanceof ConstraintViolationException);
        }
    }

    @Test
    public void shouldThrowConstraintViolationWhenStoryDataIsInvalid() throws Exception {
        try {
            storyService.save(new Story());
        } catch (EJBException e) {
            Throwable cause = e.getCause();
            Assert.assertTrue(cause instanceof ConstraintViolationException);
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) cause;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            Assert.assertEquals(3, constraintViolations.size());
        }
    }

    @Test
    public void shouldSaveStoryWhenDataIsValid() throws Exception {
        StoryBuilder storyBuilder = new StoryBuilder().setUrl("http://openshift.com").setTitle("OpenShift Rocks!!").setDescription("OpenShift Rocks!!");
        Story persistedStory = storyService.save(storyBuilder.createStory());
        Assert.assertNotNull(persistedStory.getId());
    }

    @Test
    public void shouldSaveStoryWithMedia() throws Exception {
        StoryBuilder storyBuilder = new StoryBuilder().setUrl("http://openshiftrocks.com").setTitle("OpenShift Rocks!!").setDescription("OpenShift Rocks!!").setMedia(new Media("http://abc.com/test.png", MediaType.PHOTO));
        Story persistedStory = storyService.save(storyBuilder.createStory());
        Assert.assertNotNull(persistedStory.getId());
    }

}
