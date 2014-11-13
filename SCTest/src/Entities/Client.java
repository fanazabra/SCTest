/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import backBeans.ClientsBackBean;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;

/**
 * Класс-сущьность для связи с таблицей клиентов из базы данных
 * @author Зебер
 */
@Entity
@Table(name = "client")
@XmlRootElement
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ClientId")
    /**Идентификатор Клиента*/
    private Integer clientId;
    @Basic(optional = false)
    @Column(name = "Second_Name")
    /**Фамилия Клиента*/
    private String secondName;
    @Basic(optional = false)
    @Column(name = "First_Name")
    /**Имя Клиента*/
    private String firstName;
    @Basic(optional = false)
    @Column(name = "Third_Name")
    /**Отчество Клиента*/
    private String thirdName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Collection<Account> accountCollection;

    private static final Logger theLogger = Logger.getLogger(ClientsBackBean.class);
    /**
     * Конструктор класса Client по-умолчанию.
     */
    public Client() {
        theLogger.info("start constructor");
    }

    /**
     * Конструктор класса Client 
     * @param clientId инициализирует ID объекта {@link #clientId}
     */
    public Client(Integer clientId) {
        theLogger.info("start constructor");
        this.clientId = clientId;
    }

    /**
     * Конструктор класса Client
     * @param clientId инициализирует ID объекта {@link #clientId}
     * @param secondName инициализирует фамилию объекта {@link #secondName}
     * @param firstName инициализирует имя объекта {@link #firstName}
     * @param thirdName инициализирует отчество объекта {@link #thirdName}
     */
    public Client(Integer clientId, String secondName, String firstName, String thirdName) {
        theLogger.info("start constructor");
        this.clientId = clientId;
        this.secondName = secondName;
        this.firstName = firstName;
        this.thirdName = thirdName;
    }

    /**
     * Метод получения поля {@link #clientId}
     * @return {@link #clientId}
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Метод установки поля {@link #clientId}
     * @param clientId 
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
     * Метод получения поля {@link #firstName}
     * @return {@link #firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод Установки поля {@link #firstName}
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }
}
