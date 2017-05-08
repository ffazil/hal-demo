package com.skidata.x.hal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author firoz
 * @since 08/05/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Initializer {

    @NonNull
    private CustomerRepository customerRepository;

    @NonNull
    private AddressRepository addressRepository;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        Address address = new Address("Dolby", "Nagar");
        address = addressRepository.save(address);

        Customer customer = new Customer("Tankappan", "Chettan", address);
        customer = customerRepository.save(customer);

        log.info("Added {}", customer.getFirstName() + " " + customer.getLastName());
    }
}
