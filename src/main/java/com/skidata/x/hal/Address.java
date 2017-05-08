package com.skidata.x.hal;

import lombok.*;

import javax.persistence.Entity;

/**
 * @author firoz
 * @since 08/05/17
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Address extends AbstractEntity{

    private String line1;
    private String line2;

}
