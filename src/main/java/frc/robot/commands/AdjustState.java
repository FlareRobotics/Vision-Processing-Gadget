package frc.robot.commands;

import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AdjustState extends CommandBase {
  private final VisionSubsystem m_subsystem;

  public AdjustState(VisionSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_subsystem.changePipeline(m_subsystem.currentPipeline > 1 ? 0 : m_subsystem.currentPipeline + 1);
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.changePipeline(m_subsystem.currentPipeline > 1 ? 0 : m_subsystem.currentPipeline + 1);
  }
}
