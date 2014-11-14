/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softclub.sctest.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 * Класс-сущьность для связи с таблицей счетов из базы данных
 * @author Зебер
 */
@Entity
@Table(name = "account")
@XmlRootElement
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AccountId")
    /**Идентификатор счета*/
    private Integer accountId;
    @Basic(optional = false)
    @Column(name = "Name")
    /**Название счета*/
    private String name;
    @Basic(optional = false)
    @Column(name = "Total")
    /**Сумма счета*/
    private long total;
    @Basic(optional = false)
    @Column(name = "Open_Date")
    @Temporal(TemporalType.DATE)
    /**Дата открытия счета*/
    private Date openDate;
    @Basic(optional = false)
    @Column(name = "Close_Date")
    @Temporal(TemporalType.DATE)
    /**Дата закрытия счета*/
    private Date closeDate;
    @Basic(optional = false)
    @Column(name = "State")
    /**Состояние счета*/
    private String state;
    @JoinColumn(name = "ClientId", referencedColumnName = "ClientId")
    @ManyToOne(optional = false)
    /**Владелец счета*/
    private Client clientId;

    private static final Logger theLogger = Logger.getLogger(Account.class);
    
    //Конструктор класса Account по-умолчанию.
    public Account() {
        theLogger.info("start constructor");
    }

    /**
     * Конструктор класса Account
     * @param accountId Инициализирует ID Элемента.
    */
    public Account(Integer accountId) {
        theLogger.info("start constructor");
        this.accountId = accountId;
    }
    /**
     * Конструктор класса Account
     * @param accountId Инициализирует ID объекта {@link #accountId}.
     * @param name Инициализирует имя объекта {@link #name}.
     * @param total Инициализирует сумму объекта {@link #total}.
     * @param openDate Инициализирует дату открытия объекта {@link #openDate}.
     * @param closeDate Инициализирует дату закрытия объекта {@link #closeDate}.
     * @param state Инициализирует состояние объекта {@link #state}.
    */
    public Account(Integer accountId, String name, long total, Date openDate, Date closeDate, String state) {
        theLogger.info("start constructor");
        this.accountId = accountId;
        this.name = name;
        this.total = total;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.state = state;
    }

    /**
     * Метод получения поля {@link #accountId}
     * @return {@link #accountId}
    */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * Метод установки поля {@link #accountId}
     * @param accountId
    */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
     * Метод получения поля {@link #openDate}
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
     * Метод получения поля {@link #closeDate}
     * @return {@link #closeDate}
    */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * Метод установки поля {@link #closeDate}
     * @param closeDate 
    */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
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
     * Метод получения поля {@link #clientId}
     * @return {@link #clientId}
    */
    public Client getClientId() {
        return clientId;
    }
    
    /**
     * Метод установки поля {@link #clientId}
     * @param clientId 
     */
    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }
}
