package com.janani.entity;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ConsentRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long rchId;
    private boolean consent;
    private Date consentedAt = new Date();

    // Navigation property (optional)
    @OneToOne
	@JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }

    public Date getConsentedAt() {
        return consentedAt;
    }

    public void setConsentedAt(Date consentedAt) {
        this.consentedAt = consentedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public int hashCode() {
		return Objects.hash(consent, consentedAt, id, rchId, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsentRecord other = (ConsentRecord) obj;
		return consent == other.consent && Objects.equals(consentedAt, other.consentedAt) && id == other.id
				&& rchId == other.rchId && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "ConsentRecord [id=" + id + ", rchId=" + rchId + ", consent=" + consent + ", consentedAt=" + consentedAt
				+ ", user=" + user + "]";
	}
    
    
}
