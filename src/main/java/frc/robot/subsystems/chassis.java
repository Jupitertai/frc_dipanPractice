// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.chassisID;

public class chassis extends SubsystemBase {
  public final WPI_VictorSPX Frontleft = new WPI_VictorSPX(chassisID.FL);
  public final WPI_VictorSPX Frontright = new WPI_VictorSPX(chassisID.FR);
  public final WPI_VictorSPX RearLeft = new WPI_VictorSPX(chassisID.RL);
  public final WPI_VictorSPX Rearright = new WPI_VictorSPX(chassisID.RR);


  public DifferentialDrive tank = new DifferentialDrive(Frontleft, Frontright);
  
/** Creates a new ExampleSubsystem. */
  
  public chassis() {
    Frontleft.configFactoryDefault();
    Frontright.configFactoryDefault();
    RearLeft.configFactoryDefault();
    Rearright.configFactoryDefault();

    Frontleft.setInverted(false);
    Frontright.setInverted(false); 
    RearLeft.setInverted(false);
    Rearright.setInverted(false);

    RearLeft.follow(Frontleft);
    Rearright.follow(Frontright);
    }
  
  public void drive(double X, double Y){
    tank.arcadeDrive(-X, -Y);
  }
  
  /**
   * Example command factory method.                                                
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override 
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
