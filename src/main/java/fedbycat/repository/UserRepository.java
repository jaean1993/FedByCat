package fedbycat.repository;

import org.springframework.data.repository.CrudRepository;

import fedbycat.model.UserModel;

import java.util.List;

/**
 * Created by anjin on 4/23/17.
 */
public interface UserRepository extends CrudRepository<UserModel, Long> {
    List<UserModel> findByGmtSendGreaterThanEqualAndActiveTrue(long timestamp);
}
