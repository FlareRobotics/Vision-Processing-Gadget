package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  public final VictorSPX m_motor = new VictorSPX(7);
  private final VictorSPX m_led = new VictorSPX(8);
  public static PhotonCamera camera = new PhotonCamera("Camera");
  public int currentPipeline = 0;
  public double yawOffset = 4d;
  public boolean ForceLed = false;

  public VisionSubsystem() {
    SmartDashboard.putBoolean("Force Led Mode", false);
    }

  @Override
  public void periodic() {
    m_led.set(ControlMode.PercentOutput, camera.getPipelineIndex() == 2 ? 1 : 0);

    SmartDashboard.putBoolean("Target Visible", false);
    SmartDashboard.putNumber("Target Horizontal", 0);
    ForceLed = SmartDashboard.getBoolean("Force Led Mode", false);

    if(ForceLed)
    {
      ForceLed = false;
      SmartDashboard.putBoolean("Force Led Mode", ForceLed);
      changePipeline(currentPipeline > 1 ? 0 : currentPipeline + 1);
    }


    if(camera == null)
    return;

    double targetYaw = getYdistance(getBestTarget());

    SmartDashboard.putBoolean("Target Visible", false);
    SmartDashboard.putNumber("Target Horizontal", targetYaw);
  }

  public void changePipeline(int new_Pipeline)
  {
    if(ForceLed)
    {
      System.out.println("test");
      new_Pipeline = 1;
    }

    camera.setPipelineIndex(new_Pipeline);
    currentPipeline = new_Pipeline;

    System.out.println(new_Pipeline);

  }

  public PhotonTrackedTarget getBestTarget() {
    PhotonPipelineResult result = camera.getLatestResult();

    if (result == null)
      return null;

    if (result.hasTargets()) {
      return result.getBestTarget();
    } else {
      return null;
    }
  }

  
  public double getYdistance(PhotonTrackedTarget besTarget) {
    return besTarget == null ? 0 : besTarget.getYaw();
  }
}
