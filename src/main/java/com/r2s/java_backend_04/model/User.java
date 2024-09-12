package com.r2s.java_backend_04.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
//import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
//@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

	@Column(columnDefinition = " bit default 0")
	private Boolean deleted;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
//	@JsonIgnore
//	@JsonManagedReference
	private Profile profile;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "class_id", referencedColumnName = "id")
	private ClassRoom classRoom;

//	@ManyToMany(cascade = CascadeType.ALL)
////	@JoinTable(name = "tkb", 
////	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
////	inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
//	private List<Course> courses;

	@OneToMany(mappedBy = "user")
	private List<TKB> tkbs;

	private String userName;
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();
}
