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
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controllers.ConstantsController;


/**
 * Класс для работы с сущьностью Client.
 * @author Зебер
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClientDAO extends AbstractDAO<Client>{

	private ResourceBundle constants = ConstantsController.getBundle();
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
        theLogger.info(constants.getString("logger.method.start"));
        List<Client> result = null;
        Session session = sessionFactory.openSession();
        theLogger.info(constants.getString("logger.session.open"));
		Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            theLogger.info(constants.getString("logger.transaction.begin"));
            result = session.createCriteria(Client.class).list();
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
     * Метод формирует имя клиента из идентификатора, фамилии, имени и отчества для вывода в selectOneMenu
     * @param client
     * @return правильно сформированое имя клиента.
     */
    public String getClientName(Client client) {
        String result = client.getClientId() + " " + client.getSecondName() + " " + client.getFirstName() + " " + client.getThirdName();
        return result;
    }

    @Override
    public void delete(Client client) throws Exception {
        theLogger.info(constants.getString("logger.method.start"));
        AccountDAO accountDAO = new AccountDAO();

        List<Account> accForClient = accountDAO.getAccountsForClient(client);
        	
	    if (accForClient.isEmpty() == false) {
	        throw new Exception();
	    }
	    
        super.delete(client);
    }
    
}
