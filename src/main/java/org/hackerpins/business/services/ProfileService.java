package org.hackerpins.business.services;

import org.hackerpins.business.domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Stateless
public class ProfileService {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public Profile save(@Valid @NotNull Profile profile) {
        entityManager.persist(profile);
        return profile;
    }

    public Profile findOne(Long profileId) {
        return entityManager.find(Profile.class, profileId);
    }

    public Profile findByEmail(@NotNull String email) {
        try {
            return entityManager.createNamedQuery("Profile.findByEmail", Profile.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Profile findByUsernameOrEmailAndPassword(@NotNull String username, @NotNull String password) {
        try {
            TypedQuery<Profile> query = entityManager.createNamedQuery("Profile.findByUsernameOrEmailAndPassword", Profile.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            query.getSingleResult();
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
