package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.PuntoRecogida;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PuntoRecogidaRepository extends JpaRepository<PuntoRecogida, Long>{

}
