 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Account;
import Entities.Client;
import backBeans.ClientsBackBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 * Класс для работы с сущностью Account
 * @author Зебер
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountDAO extends AbstractDAO<Account>{

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
        theLogger.info("start method");
        List<Account> result = null;
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            CriteriaQuery<Account> select = cb.createQuery(Account.class);   
            Root<Account> rootEntry = select.from(Account.class);
            select.select(rootEntry);
            result = em.createQuery(select).getResultList();
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.error("Exception: " + e);
            System.err.println("Exceprion: " + e);
            throw e;
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
        return result;
    }

    /**
     * Метод получает следующий идендификатор который будет сгенерирован в таблице account.
     * @return следующий идентификатор который будет сгенерирован в таблице account.
     */
    public int getNextInsertId() throws Exception{
        theLogger.info("start method");
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        int nextID = 0;
        try
        {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            Query query = em.createNativeQuery("SELECT Auto_increment FROM information_schema.tables WHERE table_name = 'account'");
            long result = (long)query.getSingleResult();
            nextID = (int)result;
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
        return nextID;
    }
   
    /**
     * Получение списка счетов для клиента с заданым идентификатором.
     * @param id идентификатор клиента.
     * @return список счетов.
     */
    public List<Account> getAccountsForClient(Client c){
        theLogger.info("start method");
        List<Account> result = null;
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            CriteriaQuery<Account> select = cb.createQuery(Account.class);   
            Root<Account> rootEntry = select.from(Account.class);
            select.select(rootEntry);
            select.where(rootEntry.get("clientId").in(c));
            result = em.createQuery(select).getResultList();
            em.getTransaction().commit();
            theLogger.info("Transaction end");
        } catch (Exception e) {
            if(em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               theLogger.info("Transaction rollback");
            }
            theLogger.info("Exception: " + e);
            System.err.println("Exceprion: " + e);
        } finally {
            em.close();
            theLogger.info("EntityManager close");
        }
        return result;
    }
}
