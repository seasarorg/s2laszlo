package org.seasar.laszlo.example.basic.dataset;

import java.util.ArrayList;
import java.util.List;

public class UserListService {

	private List<UserDto> userList;

	private int ageMin = Integer.MIN_VALUE;

	private int ageMax = Integer.MAX_VALUE;

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public List<UserDto> getUserList() {
		List<UserDto> result = new ArrayList<UserDto>();
		for (UserDto dto : userList) {
			if (ageMin <= dto.getAge() && dto.getAge() <= ageMax) {
				result.add(dto);
			}
		}
		return result;
	}

	public void init() {
		userList = new ArrayList<UserDto>();
		for (int i = 0; i < 100; i++) {
			UserDto dto = new UserDto();
			dto.setUserName("hoge_" + i);
			dto.setAge(i);
			GroupDto group = new GroupDto();
			group.setGroupName("group_" + i);
			dto.setGroup(group);
			userList.add(dto);
		}
	}

}
