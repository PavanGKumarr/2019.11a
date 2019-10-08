package org.user.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.user.app.model.User;

@Repository
public class UserDaoService {
	private static List<User> userList = new ArrayList<User>();
	private static int id = 4;
	static {
		userList.add(new User(1, "John", new Date()));
		userList.add(new User(2, "Ranu", new Date()));
		userList.add(new User(3, "Mondal", new Date()));
		userList.add(new User(4, "Ashu", new Date()));
	}

	public List<User> findUsers() {
		return userList;
	}

	public User findUser(Integer id) {
		for (User user : userList) {
			if (user.getId().equals(id))
				return user;
		}
		return null;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++id);
		}
		userList.add(user);
		return user;
	}
	
	public User deleteUser(int id) {
		Iterator<User> itr = userList.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId().equals(id)) {
				itr.remove();
				return user;
			}
		}
		return null;
	}

}
