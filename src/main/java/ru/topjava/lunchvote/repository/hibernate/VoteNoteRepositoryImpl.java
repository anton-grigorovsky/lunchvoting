package ru.topjava.lunchvote.repository.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.VoteNote;
import ru.topjava.lunchvote.model.hibernate.VoteNoteKey;
import ru.topjava.lunchvote.repository.VoteNoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Антон on 14.07.2018.
 */
@Repository
public class VoteNoteRepositoryImpl implements VoteNoteRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public VoteNote save(VoteNote note) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            if(entityManager.find(VoteNote.class, note.getKey()) != null)
            {
                entityManager.merge(note);
            }
            else {
                entityManager.persist(note);
            }
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return note;
    }

    @Override
    public List<VoteNote> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<VoteNote> voteNotes = null;
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<VoteNote> query = criteriaBuilder.createQuery(VoteNote.class);
            Root<VoteNote> root = query.from(VoteNote.class);
            query.select(root);
            voteNotes = entityManager.createQuery(query).getResultList();
            transaction.commit();
            return voteNotes;
        }
        catch (Exception e)
        {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return null;
    }
}
