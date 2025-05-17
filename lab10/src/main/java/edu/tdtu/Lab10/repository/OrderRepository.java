package edu.tdtu.Lab10.repository;

import edu.tdtu.Lab10.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> { }
