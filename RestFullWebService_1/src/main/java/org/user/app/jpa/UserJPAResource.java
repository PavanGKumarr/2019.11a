package org.user.app.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.user.app.resource.customException.UserNotFoundException;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<org.user.app.jpa.User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<org.user.app.jpa.User> getUser(@PathVariable Integer id)  {
		Optional<org.user.app.jpa.User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		Resource<org.user.app.jpa.User> resource = new Resource<org.user.app.jpa.User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody org.user.app.jpa.User user) {
		org.user.app.jpa.User savedUser = userRepository.save(user);
		/*
		 * After created a object into DB we will get below url in header which represent newely created 
		 * resource
		 * http://localhost:8081/user/5
		 */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);;
		
	}
}
