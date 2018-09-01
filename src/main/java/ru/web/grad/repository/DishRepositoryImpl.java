package ru.web.grad.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.grad.model.Dish;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Dish get(int id) {
        //return em.find(Dish.class, id);
        return em.createNamedQuery(Dish.GET, Dish.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).getResultList();
    }

    @Override
    public List<Dish> getExactRest(int restaurant_id){
        return em.createNamedQuery(Dish.GET_EXACT_REST_ID, Dish.class).setParameter("restaurant_id", restaurant_id).getResultList();
    }

    @Override
    public List<Dish> getBetween(LocalDate start, LocalDate end) {
        return em.createNamedQuery(Dish.GET_BETWEEN, Dish.class).setParameter("start", start).setParameter("end", end).getResultList();
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurant_id) {
        dish.setRestaurant(restaurantRepository.get(restaurant_id));
        if (dish.isNew()){
            em.persist(dish);
            return dish;
        }
        else return em.merge(dish);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE).setParameter("id", id).executeUpdate() != 0;
    }
}
