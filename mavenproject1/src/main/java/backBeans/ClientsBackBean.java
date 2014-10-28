/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backBeans;
import DAO.ClientDAO;
import Entities.Client;
import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
/**
 * Класс для работы со страницами клиентов.
 * @author Зебер
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
    @EJB
    private ClientDAO clientDAO;
    
    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    
    /**
     * конструктор класса clientsBackBean по-умолчанию.
     */
    public ClientsBackBean() {
        clientDAO = new ClientDAO();
        theLogger.info("start constructor");
    }        
    
    /**
     * Метод который выполнится при нажатии кнопки "добавить клиента" на странице клиентов.
     * @return имя страницы для перенаправления.
     */
    public String addClient() {
        theLogger.info("start method");
        this.id = Types.NULL;
        this.firstName = null;
        this.secondName = null;
        this.thirdName = null;
        addition = true;
        return "addClient";
    }

    /**
     * Метод получения поля {@link #clientsList}
     * @return {@link #clientsList}
     */
    public List<Client> getClientsList() {
        theLogger.info("start method");
        try {
            clientsList = clientDAO.getAllClients();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "не удалось получить список клиентов","Не удалось получить список клиентов"));
        } 
        return clientsList;            
    }

    /**
     * Метод установки поля {@link #clientsList}
     * @param ClientsList 
     */
    public void setClientsList(List<Client> ClientsList) {
        theLogger.info("start method");
        this.clientsList = ClientsList;
    }

    /**
     * Метод получения поля {@link #id}
     * @return {@link #id}
     */
    public int getId() {
        theLogger.info("start method");
        return id;
    }

    /**
     * Метод установки поля {@link #id}
     * @param id 
     */
    public void setId(int id) {
        theLogger.info("start method");
        this.id = id;
    }

    /**
     * Метод получения поля {@link #firstName}
     * @return {@link #firstName}
     */
    public String getFirstName() {
        theLogger.info("start method");
        return firstName;
    }

    /**
     * Метод установки поля {@link #firstName}
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        theLogger.info("start method");
        this.firstName = firstName;
    }

    /**
     * Метод получения поля {@link #secondName}
     * @return {@link #secondName}
     */
    public String getSecondName() {
        theLogger.info("start method");
        return secondName;
    }

    /**
     * Метод установки поля {@link #secondName}
     * @param secondName 
     */
    public void setSecondName(String secondName) {
        theLogger.info("start method");
        this.secondName = secondName;
    }

    /**
     * Метод получения поля {@link #thirdName}
     * @return {@link #thirdName}
     */
    public String getThirdName() {
        theLogger.info("start method");
        return thirdName;
    }

    /**
     * Метод установки поля {@link #thirdName}
     * @param thirdName 
     */
    public void setThirdName(String thirdName) {
        theLogger.info("start method");
        this.thirdName = thirdName;
    }

     /**
     * Метод получения поля {@link #addition}
     * @return {@link #addition}
     */
    public boolean isAddition() {
        theLogger.info("start method");
        return addition;
    }

    /**
     * метод установки поля {@link #addition}
     * @param addition 
     */
    public void setAddition(boolean addition) {
        theLogger.info("start method");
        this.addition = addition;
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "изменить" на странице клиентов
     * @param client Эллемент класса Client в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для пернаправления.
     */
    public String changeClient(Client client) {
        theLogger.info("start method");
        idToUpdate = client.getClientId();
        this.id = client.getClientId();
        this.firstName = client.getFirstName();
        this.secondName = client.getSecondName();
        this.thirdName = client.getThirdName();
        addition = false;
        return "addClient";
    }

    /**
     * Метод который выполнится при нажатии кнопки "удалить" на странице клиентов
     * @param client Эллемент класса Client в котором хранится информация о текущей строчке таблицы.
     * @return имя страницы для пернаправления.
     */
    public String deleteClient(Client client) {
        theLogger.info("start method");
        try {
            clientDAO.delete(client.getClientId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Не удалось удалить клиента. Вероятно он владеет счетами","Не удалось удалить клиента. Вероятно он владеет счетами"));
        }
        clientsList = getClientsList();
        return "client";
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "сохранить" на страницу вода клиента.
     * @return имя страницы для перенаправления.
     */
    public String saveClient() {
        theLogger.info("start method");
        Client client = new Client();
        client.setClientId(id);
        client.setFirstName(firstName);
        client.setSecondName(secondName);
        client.setThirdName(thirdName);
        if(addition) {
            if (id == 0)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Идентификатор не может быть 0","Идентификатор не может быть 0")); 
                return "addClient";
            }
            try {
                clientDAO.save(client);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Не удалось добавить клиента вероятно такой идентификатор уже занят","Не удалось добавить клиента вероятно такой идентификатор уже занят"));
                return "addClient";
            }
        }
        else {
            try {
                clientDAO.update(client, idToUpdate);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Не удалось изменить клиента","Не удалось изменить киента"));
            }
        }
        return "client";
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "отмена" на страницу вода клиента.
     * @return имя страницы для перенаправления.
     */
    public String cancel() {
        theLogger.info("start method");
        return "client";
    }
    
}
