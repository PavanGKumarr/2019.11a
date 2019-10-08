package org.user.app.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.user.app.dao.UserDaoService;
import org.user.app.model.User;
import org.user.app.resource.customException.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userDaoService.findUsers();
	}

	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable Integer id)  {
		User user = userDaoService.findUser(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		/*
		 * After created a object into DB we will get below url in header which represent newely created 
		 * resource
		 * http://localhost:8081/user/5
		 */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteUser(id);
		if (user == null)
			throw new UserNotFoundException("id -" + id);
	}
}
