package DeloitteDigitalAwayDay.DeloitteCaseStudy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import DeloitteDigitalAwayDay.DeloitteCaseStudy.classes.*;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.models.*;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.utils.FileOperation;

@RunWith(MockitoJUnitRunner.class)
public class AppTest
{
    @InjectMocks
    Activity activity;
    
    @InjectMocks
    Team team;
    
    ArrayList<ActivityModel> allActivities;
    ArrayList<TeamModel> teams;
    String MockFileContent = "Duck Herding 60min\n" + 
    		"Archery 45min\n" + 
    		"Learning Magic Tricks 40min\n" + 
    		"Laser Clay Shooting 60min\n" + 
    		"Human Table Football 30min\n" + 
    		"Buggy Driving 30min\n" + 
    		"Salsa & Pickles sprint\n" + 
    		"2-wheeled Segways 45min\n" + 
    		"Viking Axe Throwing 60min\n" + 
    		"Giant Puzzle Dinosaurs 30min\n" + 
    		"Giant Digital Graffiti 60min\n" + 
    		"Cricket 2020 60min\n" + 
    		"Wine Tasting sprint\n" + 
    		"Arduino Bonanza 30min\n" + 
    		"Digital Tresure Hunt 60min\n" + 
    		"Enigma Challenge 45min\n" + 
    		"Monti Carlo or Bust 60min\n" + 
    		"New Zealand Haka 30min\n" + 
    		"Time Tracker sprint\n" + 
    		"Indiano Drizzle 45min";
    
    String testFilePath = "/Users/deepikathimmegowda/Desktop/Java-Deloitte/Deloitte-CaseStudy/DeloitteCaseStudy/src/test/java/DeloitteDigitalAwayDay/testData/activities.txt";
       
    @Test
    public void testReadFileContent() throws Exception{
		String readFileContent = FileOperation.readFileContent(testFilePath);
		assertEquals(MockFileContent, readFileContent);
    }
    
    @Test
    public void testGetAllActivitiesFromFile() throws Exception
    {
    	allActivities = activity.getAllActivitiesFromFile(MockFileContent);
        assertEquals(20, allActivities.size());
    }

    @Test
    public void testGetTotalDurationOfActivitiesAdded() throws Exception
    {
    	allActivities = activity.getAllActivitiesFromFile(MockFileContent);
        int total = activity.getTotalDurationOfActivitiesAdded(allActivities);
        assertEquals(835, total);
    }
    
    @Test
    public void testGetNumberOfTeams() throws Exception
    {
    	allActivities = activity.getAllActivitiesFromFile(MockFileContent);
        int totalTeams = team.getNumberOfTeams(allActivities);
        assertEquals(2, totalTeams);
    }

    @Test
    public void tesActivitiesAssignedtoTeams() throws Exception
    {
    	allActivities = activity.getAllActivitiesFromFile(MockFileContent);
        int totalTeams = team.getNumberOfTeams(allActivities);
        teams = team.getAllTeams(totalTeams);
        team.assignActivitiesToTeams(teams,allActivities,totalTeams);
        assertEquals(2, teams.size());
        assertEquals(12, teams.get(0).teamActivities.size());
        assertEquals(12, teams.get(1).teamActivities.size());
    }
    
}