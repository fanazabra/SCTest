/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ResourceBundle;

import hibernateUtil.MyHibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controllers.ConstantsController;
import backBeans.ClientsBackBean;


/**
 * Абстрактный класс для работы с базой данных
 * @author Зебер
 */
public abstract class AbstractDAO<E> {

	private ResourceBundle constants = ConstantsController.getBundle();
    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    /**
     * Поле для работы с SessionFactory.
     */
    public SessionFactory sessionFactory;
    
    /**
     * Поле для указания класса с которым работаем.
     */
    private final Class<E> entityClass;
    
    /**
     * Конструктор класса AbstractDAO.
     * @param entityClass поле для указания класса с которым работаем.
     */
    protected AbstractDAO (Class<E> entityClass){
        theLogger.info(constants.getString("logger.method.start"));
        
        this.entityClass = entityClass;
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    /**
     * Метод для добавления объекта в базу данных
     * @param entity объект для добавления.
     * @throws java.lang.Exception
     */
    public void save(E entity) throws Exception{
        theLogger.info(constants.getString("logger.method.start"));
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            theLogger.info(constants.getString("logger.transaction.begin"));
            session.save(entity);
            transaction.commit();
            theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
            theLogger.info(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
            throw e;
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
    }
    
    //В данный момент метод удаления не работеает, так как на нем провожу испытания с JTA
    /**
     * Метод для удаления объекта из базы данных
     * @param id идентификатор объекта для удаления.
     * @throws java.lang.Exception
     */
    public void delete(E entity) throws Exception {
        theLogger.info(constants.getString("logger.method.start"));
		Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try
        {
			transaction = session.beginTransaction();
	        theLogger.info(constants.getString("logger.transaction.begin"));
            session.delete(entity);
			transaction.commit();
	        theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
			if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
			theLogger.info(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
            throw e;
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
    }
    
    /**
     * Метод для обновления объекта в базе данных
     * @param entity объект для обновления.
     * @param id id Объекта для обновления.
     */
    public void update(E entity, int id) throws Exception {
        theLogger.info(constants.getString("logger.method.start"));
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            theLogger.info(constants.getString("logger.transaction.begin"));
            session.update(entity);
			transaction.commit();
	        theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
            theLogger.info(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
            throw e;
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
    }
    
    /**
     * Метод для поиска объекта в базе данных по идентификатору.
     * @param id идентификатор объекта.
     * @return объект заданного класса с заданным идентификатором.
     */
    public E findById(int id) throws Exception {
        theLogger.info(constants.getString("logger.method.start"));
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        E result = null;
        try
        {
			transaction = session.beginTransaction();
	        theLogger.info(constants.getString("logger.transaction.begin"));
            result = (E) session.get(entityClass, id);
            transaction.commit();
            theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
            theLogger.info(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
            throw e;
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
        return result;
    }
    
}
