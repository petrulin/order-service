package com.otus.orderservice.domain.request.auth;


import com.otus.orderservice.domain.dto.RoleData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

	private Long id;

	private List<RoleData> roles = new ArrayList<>();

	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String mobilePhone;

	private Long sfDepartmentId;

}
