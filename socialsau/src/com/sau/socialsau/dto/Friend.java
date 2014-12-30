package com.sau.socialsau.dto;

public class Friend {

	private Integer friendId;
	private Integer userIdA;
	private Integer userIdB;
	
	// Constructor
	public Friend() {}
	public Friend(Integer userIdA, Integer userIdB) {
		this.userIdA = userIdA;
		this.userIdB = userIdB;
	}
	
	// Generate Getter and Setter
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public Integer getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(Integer userIdA) {
		this.userIdA = userIdA;
	}
	public Integer getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(Integer userIdB) {
		this.userIdB = userIdB;
	}
	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", userIdA=" + userIdA
				+ ", userIdB=" + userIdB + "]";
	}
}
