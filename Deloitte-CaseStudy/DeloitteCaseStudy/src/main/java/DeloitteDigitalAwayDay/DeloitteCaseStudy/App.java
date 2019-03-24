package DeloitteDigitalAwayDay.DeloitteCaseStudy;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import DeloitteDigitalAwayDay.DeloitteCaseStudy.classes.*;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.models.*;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.utils.FileOperation;

/**
 * Delloite Digital Away Day
 *
 */
public class App 
{
	static Activity activityObj = new Activity();
	static Team teamObj = new Team();

	/**
	 * This main method is the start point for this console application
	 * Enter the file path for activities file
	 * Prints the team with the allocated activities with start time
	 */
	public static void main (String [] arguments) throws Exception
    {
		String filePath = FileOperation.getFilePathInput();
		String fileContent = FileOperation.readFileContent(filePath);
		
		ArrayList<TeamModel> teams = new ArrayList<TeamModel>();
		ArrayList<ActivityModel> allActivities = activityObj.getAllActivitiesFromFile(fileContent);
		
		int numberOfTeams = teamObj.getNumberOfTeams(allActivities);
		teams = teamObj.getAllTeams(numberOfTeams);
		
		teamObj.assignActivitiesToTeams(teams,allActivities,numberOfTeams);
		teamObj.printTeamDetails(teams);
    }
}
