package com.skidata.x.hal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author firoz
 * @since 09/05/17
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerDeserializer extends JsonDeserializer<Customer>{

    private static final String UNEXPECTED_VALUE = "Expected URI cause property %s points to the managed domain type!";
    private static final TypeDescriptor URI_DESCRIPTOR = TypeDescriptor.valueOf(URI.class);

    @NonNull
    private UriToEntityConverter uriToEntityConverter;

    @NonNull
    private PersistentEntities persistentEntities;



    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String firstName = node.get("firstName").asText();
        String lastName = node.get("lastName").asText();
        String addressAsUri = node.get("address").asText();

        if (!StringUtils.hasText(addressAsUri)) {
            return null;
        }

        Address address = null;



        try {
            URI uri = new UriTemplate(addressAsUri).expand();
            TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(Address.class);
            address = (Address) uriToEntityConverter.convert(uri, URI_DESCRIPTOR, typeDescriptor);
        } catch (IllegalArgumentException o_O) {
            throw deserializationContext.weirdStringException(addressAsUri, URI.class, String.format(UNEXPECTED_VALUE, Address.class.getSimpleName()));
        }

        Customer customer = new Customer(firstName, lastName, address);

        log.info("test");
        return customer;
    }
}
