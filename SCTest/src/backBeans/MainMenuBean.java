/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backBeans;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import controllers.ConstantsController;
/**
 *
 * @author Зебер
 */
@ManagedBean(name = "mainMenuBean")
@SessionScoped
public class MainMenuBean implements Serializable {
    
    private static final Logger theLogger = Logger.getLogger(MainMenuBean.class);
    private ResourceBundle constants = ConstantsController.getBundle();

    public MainMenuBean() {
        theLogger.info(constants.getString("logger.method.start"));
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "Клиенты" на страницах главного меню, клиентов, счетов.
     * @return имя страницы для перенаправления.
     */
    public String getClientList() {
        return constants.getString("pages.client");
    }
    
    /**
     * Метод который выполнится при нажатии кнопки "Счета" на страницах главного меню, клиентов, счетов.
     * @return имя страницы для перенаправления.
     */
    public String getAccountList() {
        return constants.getString("pages.account");
    }
}
