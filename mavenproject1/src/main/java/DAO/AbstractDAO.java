/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import backBeans.ClientsBackBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

/**
 * Абстрактный класс для работы с базой данных
 * @author Зебер
 */
public abstract class AbstractDAO<E> {

    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    /**
     * Поле для работы с EntityManagerfactory.
     */
    EntityManagerFactory emf;
    /**
     * Поле для указания класса с которым работаем.
     */
    private final Class<E> entityClass;
    
    /**
     * Конструктор класса AbstractDAO.
     * @param entityClass поле для указания класса с которым работаем.
     */
    protected AbstractDAO (Class<E> entityClass){
        theLogger.info("start construntor. E = " + entityClass.toString());
        
        this.entityClass = entityClass;
        emf = Persistence.createEntityManagerFactory("SCTestNB");
    }
    
    /**
     * Метод для добавления объекта в базу данных
     * @param entity объект для добавления.
     * @throws java.lang.Exception
     */
    public void save(E entity) throws Exception{
        theLogger.info("start method");
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        try
        {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            em.persist(entity);
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.info("Exception: " + e);
            System.err.println("Exception: " + e);
            throw e;
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
    }
    
    /**
     * Метод для удаления объекта из базы данных
     * @param id идентификатор объекта для удаления.
     * @throws java.lang.Exception
     */
    public void delete(int id) throws Exception {
        theLogger.info("start method");
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        try
        {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            E objToRemove = em.find(entityClass, id);
            em.remove(objToRemove);
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.info("Exception: " + e);
            System.err.println("Exceprion: " + e);
            throw e;
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
    }
    
    /**
     * Метод для обновления объекта в базе данных
     * @param entity объект для обновления.
     * @param id id Объекта для обновления.
     */
    public void update(E entity, int id) throws Exception {
        theLogger.info("start method");
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        try
        {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            E objToUpdate = em.find(entityClass, id);
            em.flush();
            objToUpdate = entity;
            em.merge(objToUpdate);
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.info("Exception: " + e);
            System.err.println("Exceprion: " + e);
            throw e;
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
    }
    
    /**
     * Метод для поиска объекта в базе данных по идентификатору.
     * @param id идентификатор объекта.
     * @return объект заданного класса с заданным идентификатором.
     */
    public E findById(int id) throws Exception {
        theLogger.info("start method");
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        E result = null;
        try
        {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            result = em.find(entityClass, id);
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.info("Exception: " + e);
            System.err.println("Exceprion: " + e);
            throw e;
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
        return result;
    }
    
}
