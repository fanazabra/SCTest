package com.softclub.sctest.backBeans;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.softclub.sctest.controllers.*;
import com.softclub.sctest.dao.UserDAO;
import com.softclub.sctest.entities.User;

/**
 * Класс для работы со страницами счетов.
 * @author Зебер
 */
@ManagedBean(name = "usersBackBean")
@SessionScoped
public class UsersBackBean implements Serializable {
	private UserDAO userDAO;
	private String login;
	private String password;
	private Integer access;
	private long userId;
	private boolean logined = false;
	private ResourceBundle messages = MessagesController.getBundle();
    private ResourceBundle constants = ConstantsController.getBundle();
    
    private static final Logger theLogger = Logger.getLogger(UsersBackBean.class);
	
	public UsersBackBean() {
		userDAO = new UserDAO();
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

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public String login() {
		boolean valid = Validate(login, password);
		if(valid)
		{
			logined = true;
		} 
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.user.userNotValid"),messages.getString("msg.user.userNotValid")));
		}
		return constants.getString("pages.mainMenu");
	}
	
	public String logout() {
		logined = false;
		return constants.getString("pages.mainMenu");
	}
	
	public String registration() {
		login = null;
		password = null;
		return constants.getString("pages.registration");
	}
	
	public boolean Validate(String log, String pass) {
		List<User> validUsers;
		try {
			validUsers = userDAO.getList();
			for (User u: validUsers) {
				theLogger.fatal(u.getLogin() + u.getPassword());
				if(u.getLogin().equals(log) && u.getPassword().equals(pass))
				{
					access = u.getAcess();
					userId = u.getUserId();
					return true;
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.user.userList"),messages.getString("msg.user.userList")));
		}
		return false;
	}
	
	public String createNewUser() {
		User user = new User();
		List<User> validUsers = null;
		try {
			validUsers = userDAO.getList();
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.user.userList"),messages.getString("msg.user.userList")));
		}
		for (User u: validUsers) {
			if (u.getLogin().equals(login)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.user.registration.busy"),messages.getString("msg.user.registration.busy")));
				return constants.getString("pages.registration");
			}
		}
		user.setPassword(password);
		user.setLogin(login);
		user.setAcess(1);
		try {
			userDAO.save(user);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("msg.user.registration.fail"),messages.getString("msg.user.registration.fail")));
		}
		return constants.getString("pages.mainMenu");
	}
	
	public String cancel() {
		password = null;
		login = null;
		return constants.getString("pages.mainMenu");
	}
}
