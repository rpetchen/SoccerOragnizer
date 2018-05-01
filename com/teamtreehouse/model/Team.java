package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team implements Comparable<Team> {

	private Set<Player> teamRoster;
	private String teamName;
	private String coach;

	public Team(String teamName, String coach) {
		this.teamName = teamName;
		this.coach = coach;
		teamRoster = new HashSet<Player>();
	}

	public String getTeamName() {
		return teamName;
	}

	public String getCoach() {
		return coach;
	}

	public void addPlayer(Player player) {

		teamRoster.add(player);
		System.out.println(player.getFirstName() + " has been added to the " + this.teamName);
	}

	public void removePlayer(Player player) {
		teamRoster.remove(player);
		System.out.print(player.getFirstName() + " has been removed");
	}

	@Override
	public int compareTo(Team o) {
		return this.teamName.compareTo(o.teamName);

	}

	public boolean teamMatch(String userChoice) {

		if (userChoice.equals(teamName.replaceAll("\\s", "").toLowerCase())) {
			return true;
		} else {
			return false;
		}

	}

	public void printTeamPlayers() {
		if (!teamRoster.isEmpty()) {
			System.out.println("Current Roster: \n");
			for (Player player : teamRoster) {
				System.out.println(player.toString());
			}
		}

	}

	public List<Player> getTeamPlayers() {
		if (!teamRoster.isEmpty()) {
			return new ArrayList<Player>(teamRoster);
		}

		else {
			System.out.println("\nTeam roster is empty");
			return null;
		}
	}

}
