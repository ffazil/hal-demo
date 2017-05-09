package com.skidata.x.hal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author firoz
 * @since 08/05/17
 */
@Slf4j
@RepositoryRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    @NonNull
    private CustomerRepository customerRepository;

    @NonNull
    private Repositories repositories;

    @RequestMapping(value = "/customers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<?> save(@RequestBody Resource<Customer> customerResource){
        Customer customer = customerResource.getContent();
        customer = customerRepository.save(customer);
        return ResponseEntity.ok(PersistentEntityResource.build(customer, repositories.getPersistentEntity(Customer.class)).build());
    }
}
