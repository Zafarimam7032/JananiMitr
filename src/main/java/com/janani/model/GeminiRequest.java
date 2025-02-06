package com.janani.model;

import java.util.Objects;

public class GeminiRequest {
    private String query;
    private String language;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

	@Override
	public String toString() {
		return "GeminiRequest [query=" + query + ", language=" + language + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(language, query);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeminiRequest other = (GeminiRequest) obj;
		return Objects.equals(language, other.language) && Objects.equals(query, other.query);
	}
    
    
}
