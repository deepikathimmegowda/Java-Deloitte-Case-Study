# Java-Deloitte-Case-Study

### Solution for the given Exercise: "Deloitte Digital Away Day"


### Includes : 
1. Source Code
1. Jar Executable
1. Unit test report


#### Implementation Logic

**Complete Directory Layout**

```
├── /src/main/java/DeloitteDigitalAway/DeloiteCaseStudy   # Code
│     └── App.java                                        # Main method. Program launches from here
│     └── /classes/
│         └── Activity.java                               # Class to hold all methods related to activities
│         └── Team.java                                   # Class to hold all methods related to teams
│     └── /models/ 
│         └── ActivityModel.java                          # Model class for Activity Object
│         └── TeamModel.java                              # Model class for Team Object
│     └── /utils/ 
│         └── Constants.java                              # Holds string constants
│         └── FileOperation.java                          # Static method for string operations
│         └── Utilities.java                              # Utility class
├── /src/test/java/DeloitteDigitalAway/                   # Test
│     └── /DeloiteCaseStudy/AppTest.java                  # Application unit test class, holds all unit test cases
│     └── /testData/activities.txt                        # Sample activities text
```

**Assumptions:**

1. Application excepts user to give file path for activities file
1. Activities file to include activity name followed by duration
1. Activity do not exceed more than 60mins
1. Based on the number of activites list, teams will be divided
1. 9AM to 12 PM is 180mins and 1 PM to 5 PM 240mins, to total of 420(180 + 240) minutes is added to each team at the most


**Steps**

> Mostly explained in the comments

1. Application expects to "Enter file Path" for activities text
1. Reads file content
1. Initialize ArrayList of ActivityModel with allActivities 
1. Based on the duration of all the activities, check the number of teams to be divided
1. Once we have the number of teams, create and initialise Arraylist of TeamModel
1. Keep assigning allactivities to each team sequentially and mark assigned
1. Check if any unassigned activities and assign them to teams if any duration is available
1. Start assigning start time for the activities. Calculate duration and assign start time for the next activities. Meanwhle check to add lunch break activity assuming lunch should be over by 1PM
1. At the end add speech motivation presentation


**Tests**


1. Here unit tests are written with progressive steps.
1. Test Data is hard coded in the app test file. Before executing unit tests update mockfilecontent and testFilePath to correct values.
1. AppTestUnitTestResults.xml is the unit test result report


