package com.dsc.repository;

import com.dsc.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {
    public Assets getAssetsByAssetsId(Long assetsId);
}
