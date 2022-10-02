package com.timgarrick.puid_builder_db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PUIDRepo extends JpaRepository<PUID, Long> {

    //Statement will return a list of PUIDs by PUIDNAME where theres a partial match
    @Query("SELECT p FROM PUID p WHERE p.puidname LIKE LOWER(CONCAT(?1,'%')) ORDER BY p.puid DESC")
    List<PUID> findByPUIDName(String puidname);

}
