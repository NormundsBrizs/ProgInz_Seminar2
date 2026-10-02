package lv.venta.model.security;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyAuthorityTable")
@Entity
public class MyAuthority {
	
	@Id
	@Column(name = "Ida")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long ida;
	
	@Column(name = "Title", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z_]{3,10}")
	private String title;
	
	@ManyToMany
	@JoinTable(name = "AuthUserTable", 
	inverseJoinColumns = @JoinColumn(name = "MyUserTable"), 
	joinColumns = @JoinColumn(name = "MyAuthorityTable"))
	@ToString.Exclude
	private Collection<MyUser> users = new ArrayList<MyUser>();
	
	public void addUser(MyUser user) {
		if (!users.contains(user)) {
			users.add(user);
		}
	}
	
	public void removeUser(MyUser user) {
		if (users.contains(user)) {
			users.remove(user);
		}
	}
	
	public MyAuthority(String title) {
		setTitle(title);
	}
}
