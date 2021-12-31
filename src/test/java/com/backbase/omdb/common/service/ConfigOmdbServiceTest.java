package com.backbase.omdb.common.service;

import com.backbase.omdb.common.enums.Type;
import com.backbase.omdb.common.repository.ConfigOmdbRepository;
import com.backbase.omdb.data.ConfigOMDBData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigOmdbServiceTest {


    @Mock
    private ConfigOmdbService configOmdbService;

    @MockBean
    private ConfigOmdbRepository configOmdbRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Update Actor Config ")
    void updateActorConfig() {
        var actorConfig = ConfigOMDBData.actorConfigOmdb;
        actorConfig.setId(2);
        var expectedResult = actorConfig;
        expectedResult.setEnable(true);
        doReturn(expectedResult).when(configOmdbService).saveConfig(actorConfig);
        var actualResult = configOmdbService.saveConfig(actorConfig);
        assertNotNull(actualResult);
        assertTrue(actualResult.isEnable());
    }

    @Test
    @DisplayName("Get Config By Type")
    void getConfigByType() {
        var seriesConfig = ConfigOMDBData.seriesConfigOmdb;
        doReturn(seriesConfig).when(configOmdbService).getConfigByType(Type.SERIES);
        var actualResult = configOmdbService.getConfigByType(Type.SERIES);
        assertNotNull(actualResult);
        assertTrue(actualResult.isEnable());
        assertEquals(seriesConfig,actualResult);
    }

    @Test
    @DisplayName("Is Config Enabled")
    void isConfigEnabled() {
        var seriesConfig = ConfigOMDBData.seriesConfigOmdb;
        doReturn(seriesConfig.isEnable()).when(configOmdbService).isConfigEnabled(Type.SERIES);
        var actualResult = configOmdbService.isConfigEnabled(Type.SERIES);
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Disable Config")
    void disableConfig() {
        var movieConfig = ConfigOMDBData.configOmdb;
        var disableMovieConfig = movieConfig;
        disableMovieConfig.setEnable(false);
        Mockito.doCallRealMethod().when(configOmdbService)
                .disableConfig(movieConfig);
         configOmdbService.disableConfig(movieConfig);
        assertFalse(movieConfig.isEnable());
    }

}
