package at.htl.quarkus.spring;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;
import java.util.Optional;

@Path("/cars")
public class CarResource {
    private final CarRepository carRepository;

    public CarResource(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @GET
    @Produces("application/json")
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }


    @DELETE
    @Path("{id}")
    public void delete(@PathParam long id) {
        carRepository.deleteById(id);
    }

    @POST
    @Path("/brand/{brand}/model/{model}/HP/{HP}")
    @Produces("application/json")
    public Car create(@PathParam String brand, @PathParam String model, @PathParam Integer HP) {
        return carRepository.save(new Car(brand, model, HP));
    }

    @PUT
    @Path("/id/{id}/car/{HP}")
    @Produces("application/json")
    public Car changeHP(@PathParam Long id, @PathParam Integer HP) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            car.setHP(HP);
            return carRepository.save(car);
        }

        throw new IllegalArgumentException("No Car with id " + id + " exists");
    }

    @GET
    @Path("/car/{brand}")
    @Produces("application/json")
    public List<Car> findByBrand(@PathParam String brand) {
        return carRepository.findCarByBrand(brand);
    }
}