package com.skidata.x.hal;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.beans.ConstructorProperties;

/**
 * @author firoz
 * @since 08/05/17
 */
@Getter
@Entity
@NoArgsConstructor(force = true)
public class Customer extends AbstractEntity{

    private String firstName;
    private String lastName;

    @ManyToOne
    private Address address;

    public Customer(String firstName, String lastName, Address address) {

        Assert.notNull(address, "Address cannot be null");

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
