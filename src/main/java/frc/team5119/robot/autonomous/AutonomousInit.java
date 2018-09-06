package frc.team5119.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5119.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.ArrayList;
import java.util.List;

public class AutonomousInit {

	/*String gameData;
	gameData = DriverStation.getInstance().getGameSpecificMessage();
	if(gameData.charAt(0) == 'L')
	*/
	
	//Position = robot starting point, Side = switch/scale side
	/*  _______________________________________________________________________________
	 *  |                |                                           |                |
	 *  |     pos0       |                  -------                  |       pos2     |
	 *  |                |__________       |scSide1|       __________|                |
	 * R|               _|        |_|      |_______|      |_|        |_               |B
	 * E|             _|_|swiSide1|_           |           _|swiSide0|_|_             |L
	 * D|     pos1   |_|_|        |_|          |          |_|        |_|_|   pos1     |U
	 *  |              |_|swiSide0|_       ____|____       _|swiSide1|_|              |E
	 *  |                |________|_|      |       |      |_|________|                |
	 *  |                |                 |scSide0|                 |                |
	 *  |     pos2       |                  -------                  |       pos0     |
	 *  |________________|___________________________________________|________________|
	 */
	private static final boolean GOINGFORSWITCH = true;
	
	public void init() {

	    Strategy.buildStrategy();
		
		//position = autonomousSwitchPosition
		int position = Robot.autoSwitchSubsystem.getPosition();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();

        List<Object> switchPlan = (gameData.charAt(0) == 'L') ? Strategy.getSwitchLeft(position) : Strategy.getSwitchRight(position);
        List<Object> scalePlan = (gameData.charAt(1) == 'L') ?  Strategy.getScaleLeft(position) : Strategy.getScaleRight(position);

        if (GOINGFORSWITCH) {
            SmartDashboard.putString("plan", "switchPlan");
            Robot.strategy.execute(switchPlan);
        }
        else {
            SmartDashboard.putString("plan", "scalePlan");
            Robot.strategy.execute(scalePlan);
        }
	}
}
