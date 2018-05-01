package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.treehouse.enums.Experience;

public class LeagueBalanceReport extends Report {

	private EnumMap<Experience, Integer> map;

	private List<Team> teams;

	public LeagueBalanceReport() {
		map = new EnumMap<>(Experience.class);

	}

	@Override
	public void generateReport() {
		if (!teams.isEmpty()) {
			for (Team team : teams) {
				for (Experience exp : Experience.values()) {
					map.put(exp, 0);
				}
				if (!team.getTeamPlayers().isEmpty()) {
					for (Player player : team.getTeamPlayers()) {
						int value = map.get(player.getExp());
						map.put(player.getExp(), value + 1);
					}
					System.out.println("\nBalance Report for the " + team.getTeamName() + ":");
					printReport();
				} else {
					System.out.println("Team roster is empty");
				}
			}
		} else {
			System.out.println("No Teams!");
		}
	}

	public void setTeams(List<Team> teams) {
		this.teams = new ArrayList<>(teams);
	}

	@Override
	public void printReport() {


		for (Map.Entry<Experience, Integer> entry : map.entrySet()) {
			System.out.println("\nThe number of " + entry.getKey().getValue() + " players: " + entry.getValue());
		}

	}

}