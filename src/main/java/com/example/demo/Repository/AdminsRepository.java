package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Admins;
import java.lang.String;
import java.util.List;

public interface AdminsRepository extends JpaRepository<Admins, Long>{

    List<Admins> findByMail(String mail);

}