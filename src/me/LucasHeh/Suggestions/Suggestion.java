package me.LucasHeh.Suggestions;

public class Suggestion {
	
	private String suggestion;
	private String suggester;
	
	public Suggestion(String string, String p) {
		this.suggester = p;
		this.suggestion = string;
	}

	public String getSuggestion() {
		return suggestion;
	}
	
	public String getPlayerWhoSuggestedName() {
		return suggester;
	}

}
