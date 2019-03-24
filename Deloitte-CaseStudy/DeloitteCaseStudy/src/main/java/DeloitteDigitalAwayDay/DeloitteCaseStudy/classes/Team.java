package DeloitteDigitalAwayDay.DeloitteCaseStudy.classes;

import java.time.LocalTime;
import java.util.ArrayList;

import DeloitteDigitalAwayDay.DeloitteCaseStudy.models.*;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.utils.Constants;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.classes.Activity;

public class Team {
	Activity activityObj = new Activity();
	
	/**
	 * A private method to divide all activities among team, alternatively divide all activities among the teams
	 * by checking if the duration allocated to each team is less than 420 (max mins of given activities to be allocated ) 
	 * @param teams
	 * @param allactivites
	 * @param numberOfTeams
	 * @throws Exception
	 */
	private void initialAssignedToTeams(ArrayList<TeamModel> teams, 
			ArrayList<ActivityModel> allactivites, 
			int numberOfTeams) throws Exception {
		
		if(teams != null && teams.size() <= 0) { 
			throw new Exception("teams initialAssignedToTeams() in null or Empty");
		}
		
		if(allactivites != null && allactivites.size() <= 0) { 
			throw new Exception("allactivites initialAssignedToTeams() in null or Empty");
		}
		
		int index = 0;
		for(ActivityModel eachActivity: allactivites) {
			if(!eachActivity.assigned)
			{
				if(teams.get(index) != null) 
				{
					if(activityObj.getTotalDurationOfActivitiesAdded(teams.get(index).teamActivities) <= Constants.MAX_ALLOCATED)
					{
						teams.get(index).teamActivities.add(eachActivity);
					
						eachActivity.assigned = true;
					}
					index = index < numberOfTeams - 1 ? index + 1 : 0;
				}
			}
		}
	}
	
	/**
	 * A private method to check if there are any un-assigned activities which could be allocated to team if there is bandwidth
	 * @param teams
	 * @param allactivites
	 * @param numberOfTeams
	 * @throws Exception
	 */
	private void checkIfAnyUnassignedAndAssignToTeams(ArrayList<TeamModel> teams, 
			ArrayList<ActivityModel> allactivites, 
			int numberOfTeams) throws Exception {
		
		if(teams != null && teams.size() <= 0) { 
			throw new Exception("teams checkIfAnyUnassignedAndAssignToTeams() in null or Empty");
		}
		
		if(allactivites != null && allactivites.size() <= 0) { 
			throw new Exception("allactivites checkIfAnyUnassignedAndAssignToTeams() in null or Empty");
		}
		
		int index = 0;
		for(ActivityModel eachActivity: allactivites) {
			if(!eachActivity.assigned)
			{
				int assignedDuration = activityObj.getTotalDurationOfActivitiesAdded(teams.get(index).teamActivities);
				if(assignedDuration + eachActivity.duration <= Constants.MAX_ALLOCATED) {
					teams.get(index).teamActivities.add(eachActivity);
					index = index < numberOfTeams - 1 ? index + 1 : 0;
				}
			}
			
		}
	}
	/**
	 * A private method to assign start time for the activites for each team.
	 * Once the activities are divided among teams assign the start time.
	 * Add lunch break so that lunch break activity is completed before 13:00
	 * At the end assign staff motivation speech activity. this can be added at the end as condition of 420mins is already checked
	 * @param teams
	 * @throws Exception
	 */
	private void allocateTimeToActivities(ArrayList<TeamModel> teams) throws Exception {
		if(teams != null && teams.size() <= 0) { 
			throw new Exception("teams allocateTimeToActivities() in null or Empty");
		}
		
		//assign time now each team
		for (TeamModel item : teams) {
		    LocalTime time = LocalTime.parse(Constants.INIT_TIME);
		    boolean lunchAdded = false;
			for(int i = 0; i < item.teamActivities.size(); i++) {
				item.teamActivities.get(i).startTime = time;
				
				if(!lunchAdded) {					
					if((time.plusMinutes(item.teamActivities.get(i).duration).getHour() >= 13 &&
					   time.plusMinutes(item.teamActivities.get(i).duration).getHour() != 12)
					||
					(time.plusMinutes(item.teamActivities.get(i).duration).getHour() >= 12 && 
					 time.plusMinutes(item.teamActivities.get(i).duration).getHour() <= 13)) {
						item.teamActivities.add(i, activityObj.getLunchActivity());
						item.teamActivities.get(i).startTime = time;
						time = time.plusMinutes(60);
						lunchAdded = true;
						continue;
					}
				}
				time = time.plusMinutes(item.teamActivities.get(i).duration);
			}
			//since the duration is already calculated for 420mins
			item.teamActivities.add(activityObj.getSpeechActivity());
			item.teamActivities.get(item.teamActivities.size() - 1).startTime = time;
			}
	}
	
	/**
	 * Create teams based on the input
	 * 
	 * @param numberOfTeams
	 * @return team list with names assigned
	 */
	public ArrayList<TeamModel> getAllTeams(int numberOfTeams){
		ArrayList<TeamModel> teams = new ArrayList<TeamModel> ();
		for(int i = 1; i <= numberOfTeams ; i++) {	
			TeamModel team = new TeamModel();
			team.teamname = Constants.TEAM + i;
			team.teamActivities = new ArrayList<ActivityModel>();
			teams.add(team);
		}
		return teams;
	}
	
	/**
	 * Based on the activities get the number to be divided as teams
	 * from 9AM to 12PM and 4PM to 5PM - 420 mins of activities can be allocated excluding lunch and  Staff Motivation Presentation
	 * @param activities
	 * @return number of teams
	 */
	public int getNumberOfTeams(ArrayList<ActivityModel> activities) {
		int sum = 0;
		for (ActivityModel item : activities) {
			sum = sum + item.duration;
		}
		int totalTeam = sum / Constants.MAX_ALLOCATED;
		totalTeam =  sum % Constants.MAX_ALLOCATED > 0 ? totalTeam + 1 : totalTeam;
		return totalTeam;
	}
	
	/**
	 * Assign activities to teams
	 * @param teams
	 * @param allactivites
	 * @param numberOfTeams
	 * @throws Exception
	 */
	public void assignActivitiesToTeams(ArrayList<TeamModel> teams, ArrayList<ActivityModel> allactivites, int numberOfTeams) throws Exception {
		initialAssignedToTeams(teams,allactivites,numberOfTeams);
		checkIfAnyUnassignedAndAssignToTeams(teams,allactivites,numberOfTeams);
		allocateTimeToActivities(teams);
	}
	
	public void printTeamDetails(ArrayList<TeamModel> teams) throws Exception {
		
		if(teams != null && teams.size() <= 0) { 
			throw new Exception("teams printTeamDetails() in null or Empty");
		}
		
		for (TeamModel item : teams) {
			System.out.println("\n" + item.teamname + ":");
			for(ActivityModel eachActivity : item.teamActivities) {
				System.out.println(Constants.DATE_TIME_FORMATTER.format(eachActivity.startTime) + " : " + eachActivity.activityName + "   " + eachActivity.duration + "min");
			}
		}
	}
}
