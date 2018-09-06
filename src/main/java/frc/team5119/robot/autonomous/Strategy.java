package frc.team5119.robot.autonomous;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import frc.team5119.robot.autonomous.autoCommands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5119.robot.commands.SwitchLevel;

public class Strategy extends CommandGroup {

	private static LinkedHashMap<String, List<Object>> stratTable = new LinkedHashMap<>();

	//Angles are ints. Forward/backward are Strings
	public static void buildStrategy() {
	    stratTable.put("switchRightPosition0", Arrays.asList(4.9, new SwitchLevel(), "12.8", 90.0, "12.5", 73.0, "-2.0", 180.0, "2.0", 180.0, new AutoDropCube()));
        stratTable.put("switchRightPosition1", Arrays.asList("1.0", 45.0, new SwitchLevel(), "4.0", "3.0", new AutoDropCube()));
        stratTable.put("switchRightPosition2", Arrays.asList(4.9, "13.0", -90.0, new SwitchLevel(), "12.375", -82.0, "2.375", -180.0, new AutoDropCube(), "2.0", 180.0, new AutoDropCube()));

        stratTable.put("switchLeftPosition0", Arrays.asList(new SwitchLevel(), "8.5", 90.0, "3.0", 90.0, new AutoDropCube()));
        stratTable.put("switchLeftPosition1", Arrays.asList("1.0", -45.0, new SwitchLevel(), "5.0", "3.0", new AutoDropCube()));
        stratTable.put("switchLeftPosition2", Arrays.asList(4.9, "13.0", -90.0, new SwitchLevel(), "12.375", -82.0, "-2.375", -180.0, "2.0", 180.0, new AutoDropCube(), new AutoDropCube()));

        stratTable.put("scaleRightPosition0", Arrays.asList("12.0", 90.0, new SwitchLevel(), "9.75", 180.0, "2.0", 180.0, new AutoDropCube()));
        stratTable.put("scaleRightPosition1", Arrays.asList("1.0", 45.0, new SwitchLevel(), "4.0", "2.5", new AutoDropCube()));
        stratTable.put("scaleRightPosition2", Arrays.asList(new SwitchLevel(), "8.0", -90.0, "3.0", -90.0, new AutoDropCube()));

        stratTable.put("scaleLeftPosition0", Arrays.asList(new SwitchLevel(), "15.0", 90.0, "1.0", 90.0, new AutoDropCube()));
        stratTable.put("scaleLeftPosition1", Arrays.asList("1.0", -45.0, new SwitchLevel(), "4.0", "2.5", new AutoDropCube()));
        stratTable.put("scaleLeftPosition2", Arrays.asList("12.0", 90.0, new SwitchLevel(), "9.75", 180.0, "2.0", 180.0, new AutoDropCube()));
    }

    public static List<Object> getSwitchRight(int position) {
            return stratTable.get("switchRightPosition" + Integer.toString(position));
    }

    public static List<Object> getSwitchLeft(int position) {
        return stratTable.get("switchLeftPosition" + Integer.toString(position));
    }

    public static List<Object> getScaleRight(int position) {
        return stratTable.get("scaleRightPosition" + Integer.toString(position));
    }

    public static List<Object> getScaleLeft(int position) {
        return stratTable.get("scaleLeftPosition" + Integer.toString(position));
    }

	/**
	 *  @param movementList
	 *  A list containing values for use in a movement command. Ints are for forwards/backwards, while Strings
     *  are for turns.
	 */
	public void execute(List<Object> movementList) {

		for(int i= 0; i < movementList.size(); i++) {
		    if (movementList.get(i) instanceof String) {
                addSequential(new AutonomousStraight(1.0, Double.parseDouble( (String) movementList.get(i)) ));
            }
            else if (movementList.get(i) instanceof Double) {
                addSequential(new AutonomousTurn((double) movementList.get(i)));
            }
            else {
                addParallel( (Command) movementList.get(i));
            }
			
		}
	}
}
