package com.skidata.x.hal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Resource;

/**
 * @author firoz
 * @since 08/05/17
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JacksonCustomization {

    @NonNull
    private CustomerDeserializer customerDeserializer;

    @Bean
    public Module customerModule() {
        return new CustomerModule(customerDeserializer);
    }

    @SuppressWarnings("serial")
    static class CustomerModule extends SimpleModule {

        public CustomerModule(CustomerDeserializer customerDeserializer){
            setMixInAnnotation(Customer.class, CustomerMixin.class);
            SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
            simpleDeserializers.addDeserializer(Customer.class, customerDeserializer);
            setDeserializers(simpleDeserializers);
        }

        @JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE)
        static abstract class CustomerMixin {

            @JsonCreator
            public CustomerMixin(@JsonProperty("firstName") String firstName,
                                 @JsonProperty("lastName") String lastName,
                                 @JsonProperty("address") Address address) {}
        }

    }
}
