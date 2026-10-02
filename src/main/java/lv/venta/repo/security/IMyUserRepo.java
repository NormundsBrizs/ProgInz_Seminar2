package lv.venta.repo.security;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.security.MyUser;

public interface IMyUserRepo extends CrudRepository<MyUser, Long> {

}
