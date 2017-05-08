package com.skidata.x.hal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author firoz
 * @since 08/05/17
 */
@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long>{
}
