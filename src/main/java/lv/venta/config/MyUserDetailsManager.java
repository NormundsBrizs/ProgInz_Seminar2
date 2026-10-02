package lv.venta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lv.venta.model.security.MyUser;
import lv.venta.repo.security.IMyUserRepo;

@Service
public class MyUserDetailsManager implements UserDetailsManager {

	@Autowired
	private IMyUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userRepo.existsByUsername(username)) {
			MyUser user = userRepo.findByUsername(username);
			return new MyUserDetails(user);
		}
		else {
			throw new UsernameNotFoundException(username + " not found");
		}
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		return userRepo.existsByUsername(username);
	}

}
