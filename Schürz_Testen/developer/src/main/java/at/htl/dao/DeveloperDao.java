package at.htl.dao;

import at.htl.model.Developer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.inject.Singleton;

@Singleton
public class DeveloperDao implements PanacheRepository<Developer> {

    public Developer findByName(String name){
        return find("name",name).singleResult();
    }

}
