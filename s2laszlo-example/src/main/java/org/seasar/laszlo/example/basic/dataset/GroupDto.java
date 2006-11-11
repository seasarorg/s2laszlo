package org.seasar.laszlo.example.basic.dataset;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "group")
public class GroupDto {
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
