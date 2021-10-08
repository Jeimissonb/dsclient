package com.person.client.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.person.client.entity.Client;



public interface ClientRepository extends JpaRepository<Client, Long>{

}
