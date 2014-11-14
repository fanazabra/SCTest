package com.softclub.sctest.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.softclub.sctest.entities.User;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDAO extends AbstractDAO<User>{
    
	public UserDAO() {
		super(User.class);
	}
}
