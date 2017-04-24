package fedbycat.repository;

import org.springframework.data.repository.CrudRepository;

import fedbycat.model.UserModel;

/**
 * Created by anjin on 4/23/17.
 */
public interface UserRepository extends CrudRepository<UserModel, Long> {

}
