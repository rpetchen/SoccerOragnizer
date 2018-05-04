package com.teamtreehouse.model;

import java.io.Serializable;

import com.treehouse.enums.Experience;

public class Player implements Comparable<Player>, Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private int heightInInches;
	private boolean previousExperience;
	private Experience experienced;
	
	public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.heightInInches = heightInInches;
		this.previousExperience = previousExperience;
		
		if (previousExperience) {
			experienced = Experience.EXP;
		}
		else {
			experienced = Experience.INEXP;
		}
	}

	public Experience getExp() {
		return experienced;
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getHeightInInches() {
		return heightInInches;
	}

	public boolean isPreviousExperience() {
		return previousExperience;
	}

	public boolean playerMatch(String userChoice) {
		String fullName = firstName + lastName;
		if (userChoice.equals(fullName.replaceAll("\\s", "").toLowerCase())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int compareTo(Player other) {
		int order = this.lastName.toLowerCase().compareTo(other.getLastName().toLowerCase());
		if (order == 0) {
			return this.firstName.toLowerCase().compareTo(other.getFirstName().toLowerCase());
		}
		return order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Player))
			return false;

		Player player = (Player) o;

		if (heightInInches != player.heightInInches)
			return false;
		if (previousExperience != player.previousExperience)
			return false;
		if (!firstName.equals(player.firstName))
			return false;
		return lastName.equals(player.lastName);

	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + heightInInches;
		result = 31 * result + (previousExperience ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ", Height: " + heightInInches + " inches, Previous experience: " + previousExperience;
	}

}
