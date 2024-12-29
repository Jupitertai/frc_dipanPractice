package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.limelight;

public class Limelight extends SubsystemBase{

    NetworkTable table = NetworkTableInstance.getDefault().getTable(limelight.Name);

    public boolean Tag_Detected = false ;
    public double ID;
    public double ta;
    public double tx;
 

public double getFiducialID(){
    ID = LimelightHelpers.getFiducialID("");
    return ID;
    
}
public double getTag_Area(){
    ID = LimelightHelpers.getTA("");
    return ID;
}
public double getTX(){
    ID = LimelightHelpers.getTX("");
    return ID;   
}

  @Override
    public void periodic(){
        // 3D transform of the camera in the coordinate system of the robot (array (6))[tx, ty, tz, pitch, yaw, roll] (meters, degrees)
        // NetworkTableInstance.getDefault().getTable("").getEntry("camerapose_robotspace").getDoubleArray(new double[6]);

        // Tag_ID = SmartDashboard.getNumber("tid", 0);
        // Tag_Area = SmartDashboard.getNumber("ta", 0);
        // Tag_XPose = SmartDashboard.getNumber("tx", 0);

        // System.out.println("ID" + Tag_ID);
        // System.out.println("Tag_Area" + Tag_Area);
        // System.out.println("Tag_X" + Tag_XPose);
        getTX();
        getTag_Area();
        getFiducialID();
        
        SmartDashboard.putNumber("Tag_ID", ID);
        SmartDashboard.putNumber("Tag_Area", ta);
        SmartDashboard.putNumber("Tag_X", tx);

        System.out.println("Tag_ID" + ID);
        

    }

}

