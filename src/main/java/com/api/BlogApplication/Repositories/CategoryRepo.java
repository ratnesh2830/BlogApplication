package com.api.BlogApplication.Repositories;

import com.api.BlogApplication.Entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category,Integer> {

}
