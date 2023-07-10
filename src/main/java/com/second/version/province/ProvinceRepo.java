package com.second.version.province;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<ProvinceEntity, Long> {
    ProvinceEntity findProvinceEntityByName(String name);
}
