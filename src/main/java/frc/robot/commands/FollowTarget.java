package frc.robot.commands;

import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FollowTarget extends CommandBase {
  private final VisionSubsystem m_subsystem;
  private int currentDegree = 90;
  private boolean moving = false;
  public FollowTarget(VisionSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("Follow Target Command Started!");
    m_subsystem.mServo.setAngle(90);
  }

  @Override
  public void execute() {
    double horizontalDist = m_subsystem.getYdistance(m_subsystem.getBestTarget());

    if(Math.abs(horizontalDist) > m_subsystem.yawOffset && !moving)
    {
      currentDegree -= horizontalDist;
       m_subsystem.mServo.setAngle(currentDegree);
       moving = true;
    }
    
    if(Math.abs(horizontalDist) <= m_subsystem.yawOffset)
    {
      moving = false;
    }
  }
}
