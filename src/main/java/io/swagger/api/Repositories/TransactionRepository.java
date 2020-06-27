package io.swagger.api.Repositories;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for saving transactions to the database
@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long>
{

}
