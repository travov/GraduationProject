package ru.web.grad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.web.grad.model.Vote;
import ru.web.grad.service.VoteService;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(VoteRestController.VOTE_REST_URL)
public class VoteRestController {

    static final String VOTE_REST_URL = "/vote";

    @Autowired
    private VoteService service;

    @Secured(value = {"ROLE_USER"})
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> get(@PathVariable("id") int id){
        Vote vote = service.get(id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @Secured(value = {"ROLE_USER"})
    @PostMapping(value = "/{id}")
    public ResponseEntity<Vote> vote(@PathVariable("id") int restaurantId){
        Vote vote = service.vote(restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_REST_URL + "/restaurant{id}/voted")
                .buildAndExpand(vote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll(){
        return service.getAll();
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote update(@RequestBody Vote vote){
        return service.update(vote);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getBetween(@RequestParam(value = "startDate", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
       return service.getBetween(startDate, endDate);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/list/{id}")
    public List<Vote> getBetweenWithRestId(@PathVariable("id") int restaurant_id,
                                           @RequestParam(value = "startDate", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam(value = "endDate", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
       return service.getBetweenWithRestId(restaurant_id, startDate, endDate);
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping(value = "/list_rest/{id}")
    public List<Vote> getWithExactRestId(@PathVariable("id") int restaurantId){
        return service.getWithExactRestId(restaurantId);
    }
}

