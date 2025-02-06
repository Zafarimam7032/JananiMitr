package com.janani.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuthUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private String tokenId;
	private Date lastLoginAt = new Date();

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lastLoginAt, tokenId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthUser other = (AuthUser) obj;
		return id == other.id && Objects.equals(lastLoginAt, other.lastLoginAt)
				&& Objects.equals(tokenId, other.tokenId) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "AuthUser [id=" + id + ", userId=" + userId + ", tokenId=" + tokenId + ", lastLoginAt=" + lastLoginAt
				+ "]";
	}

}
