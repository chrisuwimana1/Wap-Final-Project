/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author celem
 * @param <T>
 */
public class TaskMngtDao<T> {

    private final EntityManager em = Persistence.createEntityManagerFactory("TaskMngtPU").createEntityManager();

    public void create(T... entities) {

        try {
            em.getTransaction().begin();
            for (T entity : entities) {
                em.persist(entity);
            }

            em.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity) {

        try {
            em.merge(entity);
        } finally {
            em.close();
        }
    }

    public void remove(T entity) {

        try {
            em.remove(em.merge(entity));
        } finally {
            em.close();
        }
    }

    public T find(Class<T> entityClass, Object id) {

        try {
            T t = em.find(entityClass, id);
            if (t != null) {
                em.refresh(t);
            }
            return t;
        } finally {
            em.close();
        }
    }

    public List<T> findAll(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

        cq.select(cq.from(entityClass));
        List resultList = em.createQuery(cq).getResultList();
        resultList.stream().filter((object) -> (object != null)).forEachOrdered((object) -> {
            em.refresh(object);
        });
        return resultList;
    }

    public List<T> findRange(int[] range, Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Object> executeNativeQuery(String query) {

        try {
            return em.createNativeQuery(query).getResultList();
        } finally {
            em.close();
        }
    }

    public Query createNamedQuery(String query) {

        try {
            return em.createQuery(query);
        } finally {
            em.close();
        }
    }
}