package com.ideas2it.fileUpload.repository;

import com.ideas2it.fileUpload.model.ProgressCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenericRepository extends CrudRepository<ProgressCount, String> {

}
