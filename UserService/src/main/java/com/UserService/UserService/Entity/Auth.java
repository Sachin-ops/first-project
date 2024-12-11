package com.UserService.UserService.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_id")
    private int id;

    @Column(name = "A_access_token", nullable = false)
    private String accesstoken;

    @Column(name = "A_status", nullable = false)
    private short status;

    @Column(name = "A_loginTime", nullable = false)
    private long logintime;

//    @Column(name = "A_u_id", nullable = false) // Store the foreign key directly
//    private UserEntity userId; // Use this instead of mapping to UserEntity
    @ManyToOne
	@JoinColumn(name="A_u_id", nullable = false , referencedColumnName = "U_id")
	private UserEntity user;


}
