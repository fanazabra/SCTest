/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softclub.sctest.dao;

import com.softclub.sctest.entities.Account;
import com.softclub.sctest.entities.Client;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import com.softclub.sctest.controllers.ConstantsController;


/**
 * Класс для работы с сущьностью Client.
 * @author Зебер
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClientDAO extends AbstractDAO<Client>{

	private ResourceBundle constants = ConstantsController.getBundle();
    private static final Logger theLogger = Logger.getLogger(ClientDAO.class);
    /**
     * конструктор класса ClientDAO.
     */
    public ClientDAO() {
        super(Client.class);
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
