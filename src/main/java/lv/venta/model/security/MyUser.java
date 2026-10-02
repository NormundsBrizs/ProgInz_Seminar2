package lv.venta.model.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "UserTable")
@Entity
public class MyUser {
	
	@Id
	@Column(name = "Idu")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idu;
	
	@Column(name = "Username")
	@NotNull
	@NotEmpty
	private String username;
	
	@Column(name = "Password")
	@NotNull
	@NotEmpty
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER)
	private MyAuthority authority;
	
	public MyUser(String username, String password, MyAuthority authority) {
		setUsername(username);
		setPassword(password);
		setAuthority(authority);
	}
}
