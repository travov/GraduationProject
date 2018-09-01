package ru.web.grad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.web.grad.model.Dish;
import ru.web.grad.service.DishService;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(DishRestController.DISH_REST_URL)
public class DishRestController {

    static final String DISH_REST_URL = "/dish";

    @Autowired
    private DishService service;

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping(value = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> add(@RequestBody Dish dish, @PathVariable("id") int restaurantId){
        Dish created = service.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(){
        return service.getAll();
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@RequestBody Dish dish, @PathVariable("id") int restaurantId){
        return service.update(dish, restaurantId);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id){
        return service.get(id);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getBetween(@RequestParam(value = "startDate", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @RequestParam(value = "endDate", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return service.getBetween(start, end);
    }

    @Secured(value = {"ROLE_USER"})
    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getExactRest(@PathVariable("id") int restaurantId){
        return service.getExactRest(restaurantId);
    }

}
