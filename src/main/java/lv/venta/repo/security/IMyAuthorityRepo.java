package lv.venta.repo.security;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.security.MyAuthority;

public interface IMyAuthorityRepo extends CrudRepository<MyAuthority, Long> {

}
