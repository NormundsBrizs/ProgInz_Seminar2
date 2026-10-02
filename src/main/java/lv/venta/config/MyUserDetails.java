package lv.venta.config;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lv.venta.model.security.MyAuthority;
import lv.venta.model.security.MyUser;

public class MyUserDetails implements UserDetails {

	private MyUser user;
	
	public MyUserDetails(MyUser user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authForSecurity = new ArrayList<>();
		
		for (MyAuthority tempA : user.getAuthorities()) {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(tempA.getTitle());
			authForSecurity.add(sga);
		}
		
		return authForSecurity;
	}

	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
}
