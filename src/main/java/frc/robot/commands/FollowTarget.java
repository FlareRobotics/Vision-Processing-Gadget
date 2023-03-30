package frc.robot.commands;

import frc.robot.subsystems.VisionSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FollowTarget extends CommandBase {
  private final VisionSubsystem m_subsystem;

  public FollowTarget(VisionSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("Follow Target Command Started!");
  }

  @Override
  public void execute() {
    double horizontalDist = m_subsystem.getYdistance(m_subsystem.getBestTarget());

    if(Math.abs(horizontalDist) > m_subsystem.yawOffset)
    {
      System.out.println(horizontalDist);
      m_subsystem.m_motor.set(ControlMode.PercentOutput, horizontalDist < 0 ? -0.2d : 0.08d);
    }
    else
    {
      m_subsystem.m_motor.set(ControlMode.PercentOutput, 0);
    }
  }
}
