package com.treehouse.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

public class ClientUtilities {

	public static boolean viewTeams(List<Team> teams) {
		System.out.printf("%n");
		if (teams.isEmpty()) {
			System.out.println("No Teams! Please add a team");
			return false;
		} else {
			Collections.sort(teams);
			System.out.println("Available Teams: \n");
			for (Team team : teams) {

				System.out.println(team.getTeamName() + "\n");
			}
			return true;
		}
	}

	public static void viewPlayers(List<Player> players) {

		Collections.sort(players);
		for (Player player : players) {
			System.out.println(player.toString());
		}
	}

	public static Player getPlayerChoice(BufferedReader mReader, List<Player> players) throws IOException {
		Player playerChoice = null;
		String choice = "";

		System.out.println("\nPlease select a player");
		choice = mReader.readLine().replaceAll("\\s", "").toLowerCase();

		for (Player player : players) {

			if (player.playerMatch(choice)) {
				playerChoice = player;
				break;
			}

		}
		if (playerChoice == null) {
			System.out.println("\nPlayer not found");
		}
		return playerChoice;
	}

	public static Team getTeamChoice(BufferedReader mReader, List<Team> teams) throws IOException {
		String choice = "";
		Team teamChoice = null;

		System.out.println("\nPlease select team");
		choice = mReader.readLine().replaceAll("\\s", "").toLowerCase();

		for (Team team : teams) {
			if (team.teamMatch(choice)) {
				teamChoice = team;
				break;

			}
		}
		if (teamChoice == null) {
			System.out.println("\nTeam not found");
		}
		return teamChoice;
	}
}
