package com.backbase.omdb.test;

import com.backbase.omdb.security.config.JwtAuthenticationEntryPoint;
import com.backbase.omdb.security.filter.JwtRequestFilter;
import com.backbase.omdb.security.service.JwtUserDetailsService;
import com.backbase.omdb.security.utils.JwtTokenUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.mockito.Mockito.doReturn;


public class ControllerTest {


    @Autowired
    protected WebApplicationContext context;

    @MockBean
    protected JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    protected UserDetailsService userDetailsService;

    @Autowired
    protected JwtRequestFilter jwtRequestFilter;

    @Autowired
    protected PasswordEncoder passwordEncoder;


    @Autowired
    protected PasswordEncoder encoder;

    @MockBean
    protected JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    protected JwtTokenUtil jwtTokenUtil;
    protected MockMvc mvc;
    @Autowired
    protected MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
    @Autowired
    protected ObjectMapper mapper;
    @Autowired
    protected FilterProvider filters = new SimpleFilterProvider().addFilter("optionalFilter", new OptionalFilter());
    @Autowired
    private GenericWebApplicationContext genericWebApplicationContext;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        if (!genericWebApplicationContext.containsBean("objectMapper")) {
            genericWebApplicationContext.registerBean(ObjectMapper.class, mapper);
        }
        if (!genericWebApplicationContext.containsBean("mappingJackson2HttpMessageConverter")) {
            genericWebApplicationContext.registerBean(MappingJackson2HttpMessageConverter.class, jackson2HttpMessageConverter);
        }
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(org.springframework.security.test.web.servlet.setup
                        .SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    protected String objectToJsonString(Object payload) throws JsonProcessingException {
        return getObjectMapper().writer(filters).writeValueAsString(payload);
    }


    protected ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));

        return mapper;
    }



}

