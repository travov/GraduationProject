package ru.web.grad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.web.grad.model.Restaurant;
import ru.web.grad.service.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestaurantRestController.RESTAURANT_REST_URL)
public class RestaurantRestController {

    static final String RESTAURANT_REST_URL = "/rest";

    @Autowired
    private RestaurantService service;

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant r){
        Restaurant created = service.create(r);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT_REST_URL + "/restaurant/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant r){
        Restaurant updated = service.update(r);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT_REST_URL + "/restaurant/{id}")
                .buildAndExpand(updated.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(updated);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int userId){
        service.delete(userId);
    }

    @Secured(value = {"ROLE_USER"})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id){
        return service.get(id);
    }

    @Secured(value = {"ROLE_USER"})
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll(){
        return service.getAll();
    }

}
