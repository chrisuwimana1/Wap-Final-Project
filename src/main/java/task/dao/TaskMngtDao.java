/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

    public T edit(T entity) {

        T t = null;
        try {
            em.getTransaction().begin();
            t = em.merge(entity);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return t;
    }

    public void remove(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.flush();
            em.getTransaction().commit();
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

    public List<T> executeNativeQuery(String query, Class<T> entityClass) {
        
        System.out.println("query = " + query);
        List<T> list = null;
        try {
            list = em.createNativeQuery(query, entityClass).getResultList();
            list.forEach((t) -> {
                em.refresh(t);
            });

        } finally {
            em.close();
        }
        return list;
    }

    public Query createNamedQuery(String query) {

        try {
            return em.createQuery(query);
        } finally {
            em.close();
        }
    }

    public List<T> executeNativeQuery(String queryStr, Class<T> entityClass, boolean like, Object[] params) throws Exception {
        try {

            Query query = em.createNativeQuery(queryStr, entityClass);

            int i = 1;
            for (Object param : params) {
                if (param instanceof Date) {
                    query.setParameter(i++, (Date) param, TemporalType.DATE);
                } else if (like) {
                    query.setParameter(i++, "%" + param + "%");
                } else {
                    query.setParameter(i++, param);
                }
            }

            List<T> list = query.getResultList();

            list.stream().filter((t) -> (t != null)).forEachOrdered((t) -> {
                em.refresh(t);
            });

            return list;
        } catch (Exception th) {
            throw th;
        }
    }
}
