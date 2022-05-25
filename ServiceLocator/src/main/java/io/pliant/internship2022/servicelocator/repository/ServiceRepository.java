package io.pliant.internship2022.servicelocator.repository;

import io.pliant.internship2022.servicelocator.model.entity.NameEntity;
import io.pliant.internship2022.servicelocator.model.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    Optional<ServiceEntity> findByName(NameEntity name);

}
