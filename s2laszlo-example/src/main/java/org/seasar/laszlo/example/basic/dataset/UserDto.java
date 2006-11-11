package org.seasar.laszlo.example.basic.dataset;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "user")
public class UserDto {
	private String userName;

	private int age;

	private GroupDto group;

	public GroupDto getGroup() {
		return group;
	}

	public void setGroup(GroupDto group) {
		this.group = group;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
