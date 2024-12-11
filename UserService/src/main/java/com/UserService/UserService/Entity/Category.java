package com.UserService.UserService.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Category {
	private long CategoryId;
@JsonProperty("Description")
	private String Description;
	private Long id;
	private String name;
	private Long userId;
	private Budget budget;
//   @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private UserEntity user;
}
