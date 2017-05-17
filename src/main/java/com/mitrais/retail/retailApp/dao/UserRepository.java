package com.mitrais.retail.retailApp.dao;

import org.springframework.data.repository.CrudRepository;

import com.mitrais.retail.retailApp.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByFirstNameOrLastName(String firstname, String lastname);

}