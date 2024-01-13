package com.todoApp.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

  private UserDAOService service;

  public UserResource(UserDAOService service){
    this.service = service;
  }

  @GetMapping(value = "/users")
  public List<User> retrieveAllUsers(){
    return service.findAll();
  }

  @GetMapping(value = "/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable Integer id){

    User user = service.find(id);
    if(user == null){
      throw new UserNotFoundException("id: "+ id);
    }
    EntityModel<User> entityModel = EntityModel.of(user);

    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));
    return entityModel;
  }

  @DeleteMapping(value="/users/{id}")
  public void deleteUser(@PathVariable Integer id){
    service.deleteById(id);
  }

  @PostMapping(value = "/users")
  public ResponseEntity<User> createUsers(@Valid @RequestBody User user){
     User savedUser = service.save(user);

     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
    return ResponseEntity.created(location).build();
  }
}
