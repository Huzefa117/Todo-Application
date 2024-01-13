package com.todoApp.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/jpa")
public class UserJpaResource {

  private PostRepository postRepository;

  private UserRepository userRepository;

  public UserJpaResource(UserRepository userRepository, PostRepository postRepository){
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @GetMapping(value = "/users")
  public List<User> retrieveAllUsers(){
    return userRepository.findAll();
  }

  @GetMapping(value = "/users/{id}")
  public EntityModel<Optional<User>> retrieveUser(@PathVariable Integer id){

    Optional<User> user = userRepository.findById(id);
    if(user == null){
      throw new UserNotFoundException("id: "+ id);
    }
    EntityModel<Optional<User>> entityModel = EntityModel.of(user);

    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));
    return entityModel;
  }

  @DeleteMapping(value="/users/{id}")
  public void deleteUser(@PathVariable Integer id){
    userRepository.deleteById(id);
  }

  @PostMapping(value = "/users")
  public ResponseEntity<User> createUsers(@Valid @RequestBody User user){
     User savedUser = userRepository.save(user);

     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/users/{id}/posts")
  public List<Post> retrievePostsForUser(@PathVariable int id){
    Optional<User> user = userRepository.findById(id);
    if(user == null){
      throw new UserNotFoundException("id: "+ id);
    }
     return user.get().getPosts();
  }
  @PostMapping("/users/{id}/posts")
  public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post ){
    Optional<User> user = userRepository.findById(id);
    if(user == null){
      throw new UserNotFoundException("id: "+ id);
    }
    post.setUser(user.get());
     Post savedPost = postRepository.save(post);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPost.getId())
            .toUri();
    return ResponseEntity.created(location).build();
     //return user.get().getPosts();
  }
}
