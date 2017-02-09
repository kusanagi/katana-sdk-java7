package com.katana.api.commands;

import com.katana.api.schema.ServiceSchema;

import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class Mapping {

    public Map<String, Map<String, ServiceSchema>> serviceSchema;

    public Mapping() {
    }

    public Map<String, Map<String, ServiceSchema>> getServiceSchema() {
        return serviceSchema;
    }

    public void setServiceSchema(Map<String, Map<String, ServiceSchema>> serviceSchema) {
        this.serviceSchema = serviceSchema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mapping)) {
            return false;
        }

        Mapping that = (Mapping) o;

        return getServiceSchema() != null ? getServiceSchema().equals(that.getServiceSchema()) : that.getServiceSchema() == null;

    }

    @Override
    public int hashCode() {
        return getServiceSchema() != null ? getServiceSchema().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "serviceSchema=" + serviceSchema +
                '}';
    }

    public Mapping(Mapping other) {
        this.serviceSchema = other.serviceSchema;
    }
}
