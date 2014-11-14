/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softclub.sctest.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * Класс-сущьность для связи с таблицей клиентов из базы данных
 * @author Зебер
 */
@Entity
@Table(name = "user")
@XmlRootElement
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    /**Идентификатор Пользователя*/
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "login")
    /***/
    private String login;
    @Basic(optional = false)
    @Column(name = "password")
    /***/
    private String password;
    @Basic(optional = false)
    @Column(name = "access")
    /***/
    private Integer acess;

    private static final Logger theLogger = Logger.getLogger(User.class);
    /**

     */
    public User() {
        theLogger.info("start constructor");
    }

    /**

     */
    public User(Integer userId) {
        theLogger.info("start constructor");
        this.userId = userId;
    }

    /**

     */
    public User(Integer userId, String login, String password, Integer acess) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.acess = acess;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAcess() {
		return acess;
	}

	public void setAcess(Integer acess) {
		this.acess = acess;
	}

	
}
