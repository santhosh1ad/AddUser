package com.example.demo.Controller;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.example.demo.Table.Users;

public interface JpaRepo extends JpaRepository<Users, Integer>  {

}
