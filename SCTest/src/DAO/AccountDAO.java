 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Account;
import Entities.Client;
import backBeans.ClientsBackBean;

import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.jpamodelgen.xml.jaxb.Persistence.PersistenceUnit.Properties;
import org.hibernate.jpamodelgen.xml.jaxb.Persistence.PersistenceUnit.Properties.Property;

import controllers.ConstantsController;

/**
 * Класс для работы с сущностью Account
 * @author Зебер
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountDAO extends AbstractDAO<Account>{

	private ResourceBundle constants = ConstantsController.getBundle();
    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    
    /**
     * Конструтор класса AccountDAO.
     */
    public AccountDAO() {
        super(Account.class);
    }
    
     /** 
     * Метод получает список всех счетов из базы данных.
     * @return список всех счетов.
     */
    public List<Account> getAllAccounts() throws Exception {
        theLogger.info(constants.getString("logger.method.start"));
        List<Account> result = null;
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            theLogger.info(constants.getString("logger.transaction.begin"));
            result = session.createCriteria(Account.class).list();
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

    /**
     * Метод получает следующий идендификатор который будет сгенерирован в таблице account.
     * @return следующий идентификатор который будет сгенерирован в таблице account.
     */
    public int getNextInsertId() throws Exception{
        theLogger.info(constants.getString("logger.method.start"));
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        int result = 0;
        try
        {
            transaction = session.beginTransaction();
            theLogger.info(constants.getString("logger.transaction.begin"));
            SQLQuery query = session.createSQLQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name = 'account'");
            result = ((BigInteger)query.uniqueResult()).intValue();
            transaction.commit();
            theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
            theLogger.error(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
            throw e;
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
        return result;
    }
   
    /**
     * Получение списка счетов для клиента с заданым идентификатором.
     * @param c клиент для удаления.
     * @return список счетов.
     */
    public List<Account> getAccountsForClient(Client c) throws Exception{
        theLogger.info(constants.getString("logger.method.start"));
        List<Account> result = null;
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();   
            theLogger.info(constants.getString("logger.transaction.begin"));
            Criteria criteria = session.createCriteria(Account.class);
            SimpleExpression se = Restrictions.eq("clientId.clientId", c.getClientId());
            criteria.add(se);
            result = criteria.list();
            transaction.commit();
            theLogger.info(constants.getString("logger.transaction.end"));
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
               transaction.rollback();
               theLogger.info(constants.getString("logger.transaction.rollback"));
            }
            theLogger.info(constants.getString("logger.exception")+e);
            System.err.println(constants.getString("logger.exception")+e);
        } finally {
            session.close();
            theLogger.info(constants.getString("logger.session.close"));
        }
        return result;
    }
}
