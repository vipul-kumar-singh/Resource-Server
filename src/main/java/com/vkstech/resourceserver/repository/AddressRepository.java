package com.vkstech.resourceserver.repository;

import com.vkstech.resourceserver.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUsernameOrderByUpdatedDateAsc(String username);

    Address findByIdAndUsername(Long id, String username);

    @Transactional
    @Modifying
    void deleteByIdAndUsername(Long id, String username);
}
