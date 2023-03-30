package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.button.Button;

@SuppressWarnings("deprecation")
public class DigitalButton extends Button
{
    public DigitalButton(DigitalInput input)
    {
        super(() -> input.get());
    }
}
