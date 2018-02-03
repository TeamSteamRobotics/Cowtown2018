/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5119.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GripperSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	Talon gripMotor = new Talon(2);
	DigitalInput closedLimit = new DigitalInput(11);
	DigitalInput openLimit = new DigitalInput(12);
	DigitalInput boxGrabbedLimit = new DigitalInput(13);
	double speedModifier = 1;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void move(double speed) {
		gripMotor.set(speed*speedModifier);
	}
	
	public boolean isFullClosed() {
		return closedLimit.get();
	}
	
	public boolean isFullOpen() {
		return openLimit.get();
	}
	
	public boolean isBoxGrabbed() {
		return boxGrabbedLimit.get();
	}
}
