package com.metis.book.model.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private RoleName name;
	
	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	/*	Role don't have set and get user ( with new List because Lazy load policy)
	*	If we define getter and setter that way, its not lazy load which 
	*	lead to infinite loops whenever we call Role that go into getter and create new user
	*   But what we need is null user in role
	*/ 

	
}
