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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;


/**
 * Класс для работы с сущьностью Client.
 * @author Зебер
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClientDAO extends AbstractDAO<Client>{

    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    /**
     * конструктор класса ClientDAO.
     */
    public ClientDAO() {
        super(Client.class);
    }

    /**
     * Метод получает список всех клиентов из базы данных.
     * @return список всех клиентов.
     */
    public List<Client> getAllClients() throws Exception {
        theLogger.info("start method");
        List<Client> result = null;
        EntityManager em = emf.createEntityManager();
        theLogger.info("EntityManager create");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            em.getTransaction().begin();
            theLogger.info("Transaction begin");
            CriteriaQuery<Client> select = cb.createQuery(Client.class);   
            Root<Client> rootEntry = select.from(Client.class);
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
     * Метод формирует имя клиента из идентификатора, фамилии, имени и отчества для вывода в selectOneMenu
     * @param client
     * @return правильно сформированое имя клиента.
     */
    public String getClientName(Client client) {
        theLogger.info("start method");
        String result = client.getClientId() + " " + client.getSecondName() + " " + client.getFirstName() + " " + client.getThirdName();
        return result;
    }

    @Override
    public void delete(int id) throws Exception {
        theLogger.info("start method");
        AccountDAO accountDAO = new AccountDAO();
        Client c = findById(id);
        List<Account> accForClient = accountDAO.getAccountsForClient(c);
        if (accForClient.isEmpty() == false)
        {
            throw new Exception();
        }
        super.delete(id);
    }
    
}
