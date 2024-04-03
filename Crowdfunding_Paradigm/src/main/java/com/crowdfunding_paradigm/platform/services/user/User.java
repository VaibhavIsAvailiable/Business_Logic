package com.crowdfunding_paradigm.platform.services.user;

import java.util.List;

import com.crowdfunding_paradigm.platform.services.common.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User extends BaseEntity {

	private String name;

	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> role;

	private String password;

	private String mobileNumber;

	private String about;
}
