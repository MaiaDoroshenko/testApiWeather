package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<ValueEntity,Long> {
}
