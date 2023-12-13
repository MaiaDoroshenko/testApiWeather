package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.HeadlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadlineRepsotory extends JpaRepository<HeadlineEntity,Long> {
}
