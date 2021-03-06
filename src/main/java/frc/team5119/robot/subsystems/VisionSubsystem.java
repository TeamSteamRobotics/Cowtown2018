package frc.team5119.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionSubsystem extends Subsystem {

	
	NetworkTableInstance tables = NetworkTableInstance.getDefault();
	NetworkTable table;
	double[] sizeTable;
	double[] xTable;
	double[] yTable;
	
	
	double[] defaultValue = {320};

	public VisionSubsystem() {
		table = tables.getTable("GRIP/boxContours");
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getBoxX() {
    	updateArrays();
    	if(xTable.length > 0) {
    		return xTable[0]-320;
    	}
    	return 0;
    }
    public double getBoxY() {
    	updateArrays();
    	return yTable[0];
    }
    public double getBoxArea() {
    	updateArrays();
    	return sizeTable[0];
    }
    public void updateArrays() {
    	sizeTable = table.getEntry("area").getDoubleArray(defaultValue);
    	xTable = table.getEntry("centerX").getDoubleArray(defaultValue);
    	yTable = table.getEntry("centerY").getDoubleArray(defaultValue);
    }
}

