package com.treehouse.client;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Report;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.HeightReport;
import com.teamtreehouse.model.LeagueBalanceReport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

public class LeagueController {

	private BufferedReader mReader;
	private List<Player> players;
	private Map<String, String> mMenu;
	private List<Team> teams;

	public LeagueController() {

		mReader = new BufferedReader(new InputStreamReader(System.in));
		players = new ArrayList<>(Arrays.asList(Players.load()));
		System.out.printf("There are currently %d registered players.%n", players.size());
		mMenu = new HashMap<String, String>();
		mMenu.put("create", "Create a new team for the league");
		mMenu.put("add", "Add a player to a team");
		mMenu.put("remove", "Remove a player from a team");
		mMenu.put("height", "View height report for selected team");
		mMenu.put("balance", "View team balance report for the league");
		mMenu.put("roster","View team roster");
		teams = new ArrayList<Team>();
		teams.add(new Team("Cardinals", "Ariens"));
		teams.add(new Team("Steelers", "Tomlin"));
		teams.get(0).addPlayer(players.get(0));
		teams.get(0).addPlayer(players.get(1));
		teams.get(0).addPlayer(players.get(2));
		teams.get(0).addPlayer(players.get(3));
		teams.get(0).addPlayer(players.get(4));
		teams.get(0).addPlayer(players.get(5));
		teams.get(1).addPlayer(players.get(6));
		teams.get(1).addPlayer(players.get(7));
		teams.get(1).addPlayer(players.get(8));
		teams.get(1).addPlayer(players.get(9));
		teams.get(1).addPlayer(players.get(10));
		teams.get(1).addPlayer(players.get(11));

	}

	private String promptAction() throws IOException {
		System.out.println("\n\nWhat do you want to do:  ");
		for (Map.Entry<String, String> option : mMenu.entrySet()) {
			System.out.printf("%s - %s %n", option.getKey(), option.getValue());
		}
		String choice = mReader.readLine();
		return choice.trim().toLowerCase();
	}

	public void run() {
		String choice = "";
		do {
			try {
				choice = promptAction();
				switch (choice) {
				case "create":
					teams.add(createTeam());
					System.out.println("ADDED");
					break;
				case "remove":
					removePlayerFromTeam();
					break;
				case "add":
					addPlayer();
					break;
				case "height":
					generateReport(new HeightReport());
					break;
				case "balance":
					generateReport(new LeagueBalanceReport());
					break;
				case "quit":
					mReader.close();
					break;
				case "roster":
					viewRoster();
					break;
				default:
					System.out.printf("default");
				}
			} catch (IOException ioe) {
				System.out.println("Problem with input");
				ioe.printStackTrace();
			}
		} while (!choice.equals("quit"));
	}

	private void viewRoster() throws IOException {
		Team teamChoice = displaySelectTeam();
		if (teamChoice != null) {
			teamChoice.printTeamPlayers();
		}
	}

	public void generateReport(Report reportType) throws IOException {
		Report report = reportType;
		
		
		if (reportType instanceof HeightReport) {
			Team team = displaySelectTeam();
			((HeightReport) report).setTeam(team);
		}

		if (reportType instanceof LeagueBalanceReport) {
			((LeagueBalanceReport) report).setTeams(teams);
		}
		
		report.generateReport();

	}

	public Team createTeam() {
		String teamName = "";
		String coach = "";

		try {
			System.out.println("Enter team name: ");
			teamName = mReader.readLine();
			System.out.println("Enter team coach: ");
			coach = mReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Team(teamName, coach);
	}

	public void addPlayer() throws IOException {

		Player playerChoice = null;
		Team teamChoice = displaySelectTeam();
		System.out.println("Available Players: \n");
		ClientUtilities.viewPlayers(players);

		if (teamChoice != null) {
			playerChoice = ClientUtilities.getPlayerChoice(mReader, players);

			if (playerChoice != null && teamChoice != null) {
				teamChoice.addPlayer(playerChoice);
				players.remove(playerChoice);
			}

		}

		else {
			System.out.println("\nPlease create a team");
		}
	}

	private Team displaySelectTeam() throws IOException {
		Team team = null;
		Boolean availableTeams = ClientUtilities.viewTeams(teams);
		if (availableTeams) {
			team = ClientUtilities.getTeamChoice(mReader, teams);
		}
		return team;
	}

	public void removePlayerFromTeam() throws IOException {
		Player playerChoice = null;
		Team teamChoice = displaySelectTeam();
		List<Player> teamRoster = null;
		if (teamChoice != null) {
			teamChoice.printTeamPlayers();
			teamRoster = teamChoice.getTeamPlayers();
			if (teamRoster != null && !teamRoster.isEmpty()) {
				playerChoice = ClientUtilities.getPlayerChoice(mReader, teamRoster);
				if (playerChoice != null) {
					teamChoice.removePlayer(playerChoice);
					players.add(playerChoice);

				}
			}
		}

		else {
			System.out.println("\nInvalid Selection");
		}
	}
}
