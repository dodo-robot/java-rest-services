package com.dodo.webservice.rest.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dodo.webservice.rest.example.model.User;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "Andrea", new Date()));
		users.add(new User(2, "Luca", new Date()));
		users.add(new User(3, "Manuel", new Date()));
	}

	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iter = users.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			if(user.getId() == id) {
				iter.remove();
				return user;
			}
		}
		return null;
	}
}
