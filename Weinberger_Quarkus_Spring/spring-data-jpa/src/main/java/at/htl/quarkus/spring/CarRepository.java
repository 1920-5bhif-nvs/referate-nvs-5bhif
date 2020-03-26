package at.htl.quarkus.spring;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findCarByBrand(String brand);

    List<Car> findCarByBrandAndHP(String brand, Integer HP);
}
