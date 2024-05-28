package com.teacherattendance.entity;

import lombok.*;

import java.util.Set;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
public class Usuarios {
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
    
    

}
