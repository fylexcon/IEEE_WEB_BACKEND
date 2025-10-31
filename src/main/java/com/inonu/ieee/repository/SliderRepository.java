package com.inonu.ieee.repository;

import com.inonu.ieee.model.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SliderRepository extends JpaRepository<Slider, UUID> {
}
