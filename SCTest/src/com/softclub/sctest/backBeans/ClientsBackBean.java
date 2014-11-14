/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softclub.sctest.backBeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import com.softclub.sctest.controllers.*;
import com.softclub.sctest.dao.AccountDAO;
import com.softclub.sctest.dao.ClientDAO;
import com.softclub.sctest.entities.Account;
import com.softclub.sctest.entities.Client;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;
/**
 * Класс для работы со страницами клиентов.
 * @author Зебер
 */
/**
 * @author zhahalski
 *
 */
/**
 * @author zhahalski
 *
 */
@ManagedBean(name = "clientsBackBean")
@SessionScoped
public class ClientsBackBean implements Serializable {
    /**Поле для вывода на страницу идентификатора клиента*/
    private int id;
    /**Поле для вывода на страницу имени клиента*/
    private String firstName;
    /**Поле для вывода на страницу фамилии клиента*/
    private String secondName;
    /**Поле для вывода на страницу отчества клиента*/
    private String thirdName;
    /**Поле для вывода на страницу списка клиентов*/
    private List<Client> clientsList;  
    /**Поле для определения в каком режиме (добавления/изменения) счета открыта страница ввода клиента*/
    private boolean addition;
    /**Поле для хранения идентификатора изменяемого пользователя*/
    private int idToUpdate;
    /**Поле для работы с дао клиента*/
    private ClientDAO clientDAO;
    private AccountDAO accountDAO;
	private ResourceBundle messages = MessagesController.getBundle();
	private ResourceBundle constants = ConstantsController.getBundle();
	private List<Account> accountsOfClient;
    
    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    
    /**
     * конструктор класса clientsBackBean по-умолчанию.
     */
    public ClientsBackBean() {
        theLogger.info(constants.getString("logger.method.start"));
        clientDAO = new ClientDAO();
        accountDAO = new AccountDAO();
    }        
    
    /**
     * Метод который выполнится при нажатии кнопки "добавить клиента" на странице клиентов.
     * @return имя страницы для перенаправления.
     */
    public String addClient() {
    	theLogger.info(constants.getString("logger.method.start"));
        this.id = Types.NULL;
        this.firstName = null;
        this.secondName = null;
        this.thirdName = null;
        addition = true;
        return constants.getString("pages.addClient");
    }

    /**
     * Метод получения поля {@link #clientsList}
     * @return {@link #clientsList}
     */
    public List<Client> getClientsList() {
        return clientsList;          
    }
    
    public String toClients() {
        try {
            clientsList = clientDAO.getList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.listError"),messages.getString("msg.client.listError")));
        } 
        return constants.getString("pages.client");          
    }

    /**
     * Метод установки поля {@link #clientsList}
     * @param ClientsList 
     */
    public void setClientsList(List<Client> ClientsList) {
        this.clientsList = ClientsList;
    }

    /**
     * Метод получения поля {@link #id}
     * @return {@link #id}
     */
    public int getId() {
        return id;
    }

    /**
     * Метод установки поля {@link #id}
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод получения поля {@link #firstName}
     * @return {@link #firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод установки поля {@link #firstName}
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Метод получения поля {@link #secondName}
     * @return {@link #secondName}
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Метод установки поля {@link #secondName}
     * @param secondName 
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Метод получения поля {@link #thirdName}
     * @return {@link #thirdName}
     */
    public String getThirdName() {
        return thirdName;
    }

    /**
     * Метод установки поля {@link #thirdName}
     * @param thirdName 
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

     /**
     * Метод получения поля {@link #addition}
     * @return {@link #addition}
     */
    public boolean isAddition() {
        return addition;
    }

    /**
     * метод установки поля {@link #addition}
     * @param addition 
     */
    public void setAddition(boolean addition) {
        this.addition = addition;
    }
    
    
    public List<Account> getAccountsOfClient() {
		return accountsOfClient;
	}

	public void setAccountsOfClient(List<Account> accountsOfClient) {
		this.accountsOfClient = accountsOfClient;
	}

	/**
     * Метод который выполнится при нажатии кнопки "изменить" на странице клиентов
     * @param client Эллемент класса Client в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для пернаправления.
     */
    public String changeClient(Client client) {
    	theLogger.info(constants.getString("logger.method.start"));
        idToUpdate = client.getClientId();
        this.id = client.getClientId();
        this.firstName = client.getFirstName();
        this.secondName = client.getSecondName();
        this.thirdName = client.getThirdName();
        addition = false;
        return constants.getString("pages.addClient");
    }

    /**
     * Метод который выполнится при нажатии кнопки "удалить" на странице клиентов
     * @param client Эллемент класса Client в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для пернаправления.
     */
    public String deleteClient(Client client) {
    	theLogger.info(constants.getString("logger.method.start"));
        try {
            clientDAO.delete(client);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.deleteError"),messages.getString("msg.client.deleteError")));
        }
        try {
			clientsList = clientDAO.getList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.listError"),messages.getString("msg.client.listError")));
		}
        return constants.getString("pages.client");
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "сохранить" на страницу вода клиента.
     * @return имя страницы для перенаправления.
     */
    public String saveClient() {
    	theLogger.info(constants.getString("logger.method.start"));
        Client client = new Client();
        client.setClientId(id);
        client.setFirstName(firstName);
        client.setSecondName(secondName);
        client.setThirdName(thirdName);
        if(addition) {
            if (id == 0)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.nullIdError"),messages.getString("msg.client.nullIdError"))); 
                return constants.getString("pages.addClient");
            }
            try {
                clientDAO.save(client);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.idError"),messages.getString("msg.client.idError")));
                return constants.getString("pages.addClient");
            }
        }
        else {
            try {
                clientDAO.update(client, idToUpdate);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.updateError"),messages.getString("msg.client.updateError")));
            }
        }
        try {
			clientsList = clientDAO.getList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.listError"),messages.getString("msg.client.listError")));
		}
        return constants.getString("pages.client");
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "отмена" на страницу вода клиента.
     * @return имя страницы для перенаправления.
     */
    public String cancel() {
    	theLogger.info(constants.getString("logger.method.start"));
        return constants.getString("pages.client");
    }
    
    
    public void getAccountsForClient(Client c) {
    	theLogger.info(constants.getString("logger.method.start"));
    	InputStream inputStream = null;
    	BufferedOutputStream pdfOutput = null;
		BufferedInputStream pdfInput = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
    	try {
    		accountsOfClient = accountDAO.getAccountsForClient(c);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(accountsOfClient);
			
			Map parameters = new HashMap();
			parameters.put(constants.getString("report.titleTag"), constants.getString("report.title") + c.getSecondName() + " " + c.getFirstName() + " " + c.getThirdName());
			
			JasperReport report = (JasperReport) JRLoader.loadObject(ClientsBackBean.class.getResourceAsStream("../reports/accounts_report.jasper"));	
			
			response.reset();
			pdfOutput = new BufferedOutputStream(response.getOutputStream());
			

			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
			JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());

			pdfOutput.flush();
            pdfOutput.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			theLogger.error(e1);
		} catch (Exception e) {
			theLogger.error(e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.listError"),messages.getString("msg.account.listError")));
		} 
    	facesContext.responseComplete();
    }
    
}
