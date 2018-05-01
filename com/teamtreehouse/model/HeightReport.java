package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import com.treehouse.enums.HeightRange;

public class HeightReport extends Report {

	private EnumMap<HeightRange, ArrayList<Player>> map;

	private Team team;

	public HeightReport() {
		map = new EnumMap<>(HeightRange.class);
		for (HeightRange range : HeightRange.values()) {
			map.put(range, null);
		}
	}

	@Override
	public void generateReport() {
		if (team == null && team.getTeamPlayers() != null) {
			System.out.println("Unable to run Height Report: No team");
		} else {
			for (Player player : team.getTeamPlayers()) {
				int height = player.getHeightInInches();
				for (HeightRange range : map.keySet()) {
					if (height >= range.getMinHeight() && height <= range.getMaxHeight()) {
						if (map.get(range) == null) {
							ArrayList<Player> value = new ArrayList<>();
							value.add(player);
							map.put(range, value);
						} else {
							map.get(range).add(player);
						}
					}
				}
			}
			printReport();
		}
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public void printReport() {
		System.out.println("Height Report:");
		for (Map.Entry<HeightRange, ArrayList<Player>> entry : map.entrySet()) {

			HeightRange range = entry.getKey();
			System.out.println(
					"Height Range: " + range.getMinHeight() + " inches" + " to " + range.getMaxHeight() + " inches");
			if (entry.getValue() == null) {
				System.out.println("No players in this height range");
			} else {
				for (Player player : entry.getValue()) {
					System.out.println(player.getFirstName() + " " + player.getLastName());
				}
			}
		}

	}
}
