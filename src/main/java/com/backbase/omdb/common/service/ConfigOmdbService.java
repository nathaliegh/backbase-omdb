package com.backbase.omdb.common.service;

import com.backbase.omdb.common.entity.ConfigOmdbEntity;
import com.backbase.omdb.common.enums.Type;
import com.backbase.omdb.common.repository.ConfigOmdbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration OMDB Service implementation
 *
 * @author NG
 * @version 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigOmdbService {

    private final ConfigOmdbRepository configOmdbRepository;


    public ConfigOmdbEntity saveConfig(ConfigOmdbEntity config) {
        return configOmdbRepository.save(config);
    }

    public ConfigOmdbEntity getConfigByType(Type type) {
        return configOmdbRepository.findFirstByType(type);
    }

    public boolean isConfigEnabled(Type type) {
        return getConfigByType(type).isEnable();
    }

    @Transactional
    public void disableConfig(ConfigOmdbEntity configOmdb) {
        configOmdb.setEnable(false);
        saveConfig(configOmdb);
    }


}
