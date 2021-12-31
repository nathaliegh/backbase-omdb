package com.backbase.omdb.common.repository;

import com.backbase.omdb.common.entity.ConfigOmdbEntity;
import com.backbase.omdb.common.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Configuration OMDB Repository
 */
public interface ConfigOmdbRepository extends JpaRepository<ConfigOmdbEntity, Integer> {

  ConfigOmdbEntity findFirstByType(Type type);
}
