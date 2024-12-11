package com.UserService.UserService.Entity;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_table")
public class UserEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "U_id")
	private Long userId;
//	@Column(name = "U_email", unique = true, nullable = false)
	private String email;
	// @Column(name= "U_name" , unique =true , nullable =false)
	private String name;

	// @Column(name = "U_username", unique = true, nullable = false)
	private String username;

	private String firstName;
	private String lastName;

	private String pwd;
	
	@Transient
	 private Category[] categories;
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchTyp.LAZY)
//	private Category[] categories;

	 
	}


