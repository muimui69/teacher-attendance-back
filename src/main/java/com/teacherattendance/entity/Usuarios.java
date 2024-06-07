package com.teacherattendance.entity;

import lombok.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
public class Usuarios implements UserDetails{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    
    private String apellido;
    
    private String email;
    
    private String password; 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    		name = "user_rol",
    		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    		inverseJoinColumns =  @JoinColumn(name = "rol_id", referencedColumnName = "id")
    		)
    private Set<Roles> roles;

	public Usuarios(String nombre, String apellido, String email, String password, Set<Roles> roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
	            .map(role -> new SimpleGrantedAuthority(role.getNombre()))
	            .collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
