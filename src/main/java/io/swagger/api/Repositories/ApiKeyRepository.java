package io.swagger.api.Repositories;

import io.swagger.model.ApiKey;
import org.springframework.data.repository.CrudRepository;

public interface ApiKeyRepository extends CrudRepository<ApiKey,String> {
    ApiKey findById(String principal);
}
