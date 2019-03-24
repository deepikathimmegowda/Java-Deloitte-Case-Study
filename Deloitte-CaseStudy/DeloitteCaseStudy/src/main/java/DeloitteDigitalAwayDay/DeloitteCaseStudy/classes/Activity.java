package DeloitteDigitalAwayDay.DeloitteCaseStudy.classes;

import java.util.ArrayList;

import DeloitteDigitalAwayDay.DeloitteCaseStudy.models.ActivityModel;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.utils.Constants;
import DeloitteDigitalAwayDay.DeloitteCaseStudy.utils.Utilities;

public class Activity {
	
	public ActivityModel getLunchActivity() {
		ActivityModel activity = new ActivityModel();
		activity.activityName = Constants.LUNCH_BREAK;
		activity.duration = 60;
		return activity;
	}
	
	public ActivityModel getSpeechActivity() {
		ActivityModel activity = new ActivityModel();
		activity.activityName = Constants.STAFF_MOTIVATION_PRESENTATION;
		activity.duration = 60;
		return activity;
	}
	

	public ArrayList<ActivityModel> getAllActivitiesFromFile(String fileContent) throws Exception {
		
		if(Utilities.isNullOrEmpty(fileContent)) throw new Exception("File Content is empty");
		
		ArrayList<ActivityModel> activities = new ArrayList<ActivityModel>();
		String[] splitlines = fileContent.split("\n");
		int lines = splitlines.length;
		
		if(lines <= 0) throw new Exception("Less than zero lines in file");
		
		for(int index = 0; index < lines; index++) {
			
			ActivityModel item = new ActivityModel();
			String text = splitlines[index].trim().toLowerCase();
			
			if(text.contains(Constants.SPRINT))
			{
				item.duration = 15;
				item.activityName = text.replace(Constants.SPRINT, "");
			}
			else if(text.endsWith(Constants.MIN))//lenduck herding 60min
			{
				String textWithoutMin = text.substring(0, text.length() -3); //lenduck herding 60
				String name = (textWithoutMin.substring(0,textWithoutMin.length() - 2)).trim();//lenduck herding
				String duration = textWithoutMin.substring(textWithoutMin.length() - 2, textWithoutMin.length());
				item.duration  = Integer.parseInt(duration);
				item.activityName =  name;

			}
			activities.add(item);
		}
		return activities;
	}
	
	
	public int getTotalDurationOfActivitiesAdded(ArrayList<ActivityModel> teamActivities) {
		int sum = 0;
		if(teamActivities != null && teamActivities.size() > 0) {
			for(ActivityModel activity : teamActivities) {
				sum = sum + activity.duration;
			}
		}
		return sum;
	}
}
