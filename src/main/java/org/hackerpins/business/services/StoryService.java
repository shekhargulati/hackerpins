package org.hackerpins.business.services;

import org.hackerpins.business.domain.Story;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by shekhargulati on 04/04/14.
 */
@Stateless
public class StoryService {

    @Inject
    private Logger logger;
    @Inject
    private EntityManager entityManager;

    public Story save(@Valid @NotNull Story story) {
        entityManager.persist(story);
        return story;
    }

    public Story findOne(Long storyId) {
        return entityManager.find(Story.class, storyId);
    }

    public List<Story> findAll(int start, int max) {
        return entityManager.createNamedQuery("Story.findAllDescBySubmittedAt", Story.class).setFirstResult(start).setMaxResults(max).getResultList();
    }

    public long count() {
        return entityManager.createNamedQuery("Story.count", Long.class).getSingleResult();
    }


    public Story update(Story story) {
        return entityManager.merge(story);
    }

    public Story like(long id) {
        Story story = this.findOne(id);
        entityManager.refresh(story, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        story.setLikes(story.getLikes() + 1);
        return this.update(story);
    }

    public Story dislike(long id) {
        Story story = this.findOne(id);
        entityManager.refresh(story, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        story.setDislikes(story.getDislikes() + 1);
        return this.update(story);
    }
}
