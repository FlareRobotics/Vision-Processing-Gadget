package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.FollowTarget;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {
  public static int currentState = 0;

  public VisionSubsystem visionSubsystem = new VisionSubsystem();

  public RobotContainer() {
    configureButtonBindings();
    visionSubsystem.setDefaultCommand(new FollowTarget(visionSubsystem));
  }

  private void configureButtonBindings() {
    //new JoystickButton(joystick, 0).toggleOnTrue(getAutonomousCommand());
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
