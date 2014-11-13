/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backBeans;
import DAO.AccountDAO;
import DAO.ClientDAO;
import Entities.Account;
import Entities.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import controllers.ConstantsController;
import controllers.MessagesController;

import java.util.ResourceBundle;
/**
 * Класс для работы со страницами счетов.
 * @author Зебер
 */
@ManagedBean(name = "accountsBackBean")
@SessionScoped
public class AccountsBackBean implements Serializable {
    /** Поле для вывода на страницу идентификатора счета */
    private int id;
    /** Поле для вывода на страницу названия счета */
    private String name;
    /** Поле для вывода на страницу суммы счета */
    private long total;
    /** Поле для вывода на страницу даты открытия счета */
    private Date openDate;
    /** Поле для вывода на страницу даты закрытия счета */
    private Date closDate;
    /** Поле для вывода на страницу состояния счета */
    private String state;
    /** Поле для вывода на страницу имени владельца счета */
    private String clientName;
    /** Поле для вывода на страницу состояния или списка состояний счета */
    private List<String> states;
    /** Поле для вывода на страницу списка счетов */
    private List<Account> accountList;
    /** Поле для вывода на страницу списка имени клиентов */
    private List<String> clientNamesList;
    /** Поле для определения в каком режиме (добавления/изменения) счета открыта страница ввода счета */
    private boolean addition;
    /**Поле для работы с дао счета*/
    private AccountDAO accountDAO;
    /**Поле для работы с дао клиента*/
    private ClientDAO clientDAO;
    ResourceBundle messages = MessagesController.getBundle();
    ResourceBundle constants = ConstantsController.getBundle();

    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    /**
     * Конструктор класса accountBackBean по-умолчанию
     */
    public AccountsBackBean() {
    	theLogger.info(constants.getString("logger.method.start"));
        states = new ArrayList<String>();
        states.add(constants.getString("state.open"));
        states.add(constants.getString("state.close"));
        states.add(constants.getString("state.block"));
        accountDAO = new AccountDAO();
        clientDAO = new ClientDAO();
    }
    
    /**
     * Метод для получения специально сформированого списка имен клиентов для вывода в selectOneMenu
     * @return список имен клиентов
     */
    public List<String> getClientnamesList()
    {
    	theLogger.info(constants.getString("logger.method.start"));
        List<Client> clientList = new ArrayList<Client>();
        List <String> clientNamesList = new ArrayList<String>();
        try {
            clientList = clientDAO.getAllClients();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.client.listError"),messages.getString("msg.client.listError")));
        }
        for(Client c: clientList) {
            clientNamesList.add(c.getClientId() + " " +c.getSecondName() + " " + c.getFirstName() + " " + c.getThirdName());
        }
        return clientNamesList;
    }
    
    /**
     * Метод выполняемы при нажатии кнопки "добавить счет" на странице счетов.
     * @return имя страницы на которую перемещяемся.
     */
    public String addAccount() {
    	theLogger.info(constants.getString("logger.method.start"));
        
        try {
            id = accountDAO.getNextInsertId();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.idError"),messages.getString("msg.account.idError")));
            return "account";
        }
        
        name = "";
        total = 0;
        openDate = null;
        closDate = null;
        state = states.get(0);
        clientNamesList = getClientnamesList();
        addition = true;
        return constants.getString("pages.addAccount");
    }

    /**
     * метод полечения поля {@link #id}
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
     * Метод получения поля {@link #name}
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }
    
    /**
     * Метод установки поля {@link #name}
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод получения поля {@link #total}
     * @return {@link #total}
     */
    public long getTotal() {
        return total;
    }

    /**
     * Метод установки поля {@link #total}
     * @param total 
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Метод олучения поля {@link #openDate}
     * @return {@link #openDate}
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * Метод установки поля {@link #openDate}
     * @param openDate 
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * Метод получения поля {@link #closDate}
     * @return {@link #closDate}
     */
    public Date getClosDate() {
        return closDate;
    }

    /**
     * Метод установки поля {@link #closDate}
     * @param closDate 
     */
    public void setClosDate(Date closDate) {
        this.closDate = closDate;
    }

    /**
     * Метод получения поля {@link #state}
     * @return {@link #state}
     */
    public String getState() {
        return state;
    }

    /**
     * Метод установки поля {@link #state}
     * @param state 
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Метод получения поля {@link #clientName}
     * @return {@link #clientName}
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Метод установки поля {@link #clientName}
     * @param clientName 
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Метод получения поля {@link #accountList}
     * @return {@link #accountList}
     */
    public List<Account> getAccountList() {
        return accountList;
    }
    
    public String toAccounts() {
        try {
            accountList = accountDAO.getAllAccounts();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.listError"),messages.getString("msg.account.listError")));
        }
        return "account";
    }

    /**
     * Метод установки поля {@link #accountList}
     * @param accountList 
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }   

    /**
     * Метод получения поля {@link #clientNamesList}
     * @return {@link #clientNamesList}
     */
    public List<String> getClientNamesList() {
        return clientNamesList;
    }

    /**
     * Метод установки поля {@link #clientNamesList}
     * @param clientNamesList 
     */
    public void setClientNamesList(List<String> clientNamesList) {
        this.clientNamesList = clientNamesList;
    }

    /**
     * Метод получения поля {@link #states}
     * @return {@link #states}
     */
    public List<String> getStates() {
        return states;
    }

    /**
     * Метод установки поля {@link #states}
     * @param states 
     */
    public void setStates(List<String> states) {
        this.states = states;
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "отмена" на странице ввода счета.
     * @return имя страницы для перенаправления.
     */
    public String cancel() {
    	theLogger.info(constants.getString("logger.method.start"));
        return constants.getString("pages.account");
    }
    
    /**
     * Метод который выполниться при нажатии кнопки "сохранить" на странице ввода счета
     * @return имя страницы для перенаправления
     */
    public String saveAccount() {
    	theLogger.info(constants.getString("logger.method.start"));;
        String[] propOfClient = clientName.split(" ");
        int clientId = Integer.parseInt(propOfClient[0]);
        Client clientToSave = null;
        try {
            clientToSave = clientDAO.findById(clientId);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.ownerError"),messages.getString("msg.account.ownerError")));
            return constants.getString("pages.addAccount");
        }
        Account account = new Account();
        account.setAccountId(id);
        account.setClientId(clientToSave);
        account.setCloseDate(closDate);
        account.setName(name);
        account.setOpenDate(openDate);
        account.setState(state);
        account.setTotal(total);
        if(addition == true) {
            try {
                accountDAO.save(account);
            } catch (Exception e)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.createError"),messages.getString("msg.account.createError")));
                return constants.getString("pages.addAccount");
            }
        }
        else {
            try {
                accountDAO.update(account, id);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.updateError"),messages.getString("msg.account.updateError")));
            }
        }
        try {
			accountList = accountDAO.getAllAccounts();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.listError"),messages.getString("msg.account.listError")));
		}
        return constants.getString("pages.account");
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "изменить" на страницу счетов.
     * @param account Эллемент класса Account в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для епренаправления.
     */
    public String changeAccount(Account account) {
        theLogger.info(constants.getString("logger.method.start"));
        id = account.getAccountId();
        name = account.getName();
        state = account.getState();
        total = account.getTotal();
        openDate = account.getOpenDate();
        closDate = account.getCloseDate();
        clientNamesList = getClientnamesList();
        clientName = clientDAO.getClientName(account.getClientId());
        addition = false;
        return constants.getString("pages.addAccount");
    }
    
    /**
     * Метод который выполниться при нажатии кнопки удалить на страницу счетов.
     * @param account Эллемент класса Account в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для епренаправления.
     */
    public String deleteAccount(Account account) {
    	theLogger.info(constants.getString("logger.method.start"));
        try {
            accountDAO.delete(account);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.deleteError"),messages.getString("msg.account.deleteError")));
        }
        try {
			accountList = accountDAO.getAllAccounts();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.account.listError"),messages.getString("msg.account.listError")));
		}
        return constants.getString("pages.account");
    }
}
