package frc.robot.gamepieces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frc.robot.RobotMap;
import frc.robot.sensors.Gyrometer;
/**
 * TiltMonitor monitors the tilting of the robot using the 
 * gyrometer and will retract the elevator if the robot tips too much.
 */
public class TiltMonitor {
	private static TiltMonitor instance;
	private static final Logger LOGGER = LogManager.getLogger(TiltMonitor.class);

	public static TiltMonitor getInstance() {
		if (instance == null) {
			instance = new TiltMonitor();
		}
		return instance;
	}

	public void periodic() {
		double pitchDegrees = Gyrometer.getInstance().getPitchDegrees();
		if (pitchDegrees > RobotMap.FORWARD_PANIC_ANGLE || 
				pitchDegrees < RobotMap.BACKWARD_PANIC_ANGLE) {
			LOGGER.info("Pitch= {}", pitchDegrees);
			Elevator.getInstance().moveToHeight(Elevator.Stops.floor);
		}
	}
}