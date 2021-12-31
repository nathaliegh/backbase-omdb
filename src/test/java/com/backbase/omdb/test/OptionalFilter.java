package com.backbase.omdb.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class OptionalFilter extends SimpleBeanPropertyFilter {

    @SuppressWarnings("java:S1872")
    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if ("java.util.Optional".equals(writer.getType().getRawClass().getName())) {
            PropertyDescriptor p = new PropertyDescriptor(writer.getName(), pojo.getClass());
            Object value = p.getReadMethod().invoke(pojo);
            if (value != null) { // Some & None.
                writer.serializeAsField(pojo, jgen, provider);
            } else {
                writer.serializeAsOmittedField(pojo, jgen, provider);
            }
        } else {
            super.serializeAsField(pojo, jgen, provider, writer);
        }
    }

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        return true;
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        return true;
    }
}
