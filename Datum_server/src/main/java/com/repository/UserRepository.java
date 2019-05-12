package com.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.model.Role;
import com.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findAllByEmailContaining(String email);
	List<User>  findAllByOrderByEmailAsc();
	List<User> findByRoleId(Integer id);
		
}
		

