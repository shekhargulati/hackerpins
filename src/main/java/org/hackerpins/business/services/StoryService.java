package org.hackerpins.business.services;

import org.hackerpins.business.domain.Story;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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


    public Story update(Story story){
        return entityManager.merge(story);
    }

}
