package frc.robot.subsystems;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.CANdleFaults;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.StrobeAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.limelightName;

public class Candle extends SubsystemBase {

    // CANdle 实例
    private final CANdle candle;

    // 动画实例
    private RainbowAnimation rainbowAnimation;
    private TwinkleAnimation twinkleAnimation;
    

    // CAN ID
    private final int candleID = 1; // 根据实际硬件配置修改
    
    public Candle() {
        // 初始化 CANdle
        candle = new CANdle(candleID);

        // 配置 CANdle 参数
        configureCandle();

        // 初始化默认动画
        
        rainbowAnimation = new RainbowAnimation(1.0, 0.5, 64);
        twinkleAnimation = new TwinkleAnimation(255, 255, 0, 0, 0.5, 64, TwinkleAnimation.TwinklePercent.Percent64);
        
    }

    /**
     * 配置 CANdle 的初始设置
     */
    private void configureCandle() {
        CANdleConfiguration config = new CANdleConfiguration();

        config.stripType = CANdle.LEDStripType.RGB; // 灯条类型（RGB 或 RGBW）
        config.brightnessScalar = 0.8;             // 灯条亮度比例
        config.v5Enabled = true;                  // 启用 5V 电源输出

        // 提交所有配置到 CANdle
        candle.configAllSettings(config);
    }

    /**
     * 设置静态 RGB 颜色
     * @param r 红色值 (0-255)
     * @param g 绿色值 (0-255)
     * @param b 蓝色值 (0-255)
     */
    public void setStaticColor() {
        candle.setLEDs(200, 100, 50); // 最后一个参数是白色值，用于 RGBW 灯条 
    }

    /**
     * 启动彩虹动画
     */
    public void startRainbowAnimation() {
        candle.animate(rainbowAnimation);
    }

    /**
     * 启动闪烁动画
     * @param r 红色值 (0-255)
     * @param g 绿色值 (0-255)
     * @param b 蓝色值 (0-255)
     */
    public void startStrobeAnimation(int r, int g, int b) {
        StrobeAnimation strobe = new StrobeAnimation(r, g, b);
        strobe.setSpeed(b);
        strobe.setNumLed(b);
        candle.animate(strobe);

    }

    /**
     * 启动闪烁动画（黄绿色 Twinkle）
     */
    public void startTwinkleAnimation() {
        candle.animate(twinkleAnimation);
    }

    /**
     * 停止动画并清除灯条
     */
    public void stopAnimation() {
        candle.clearAnimation(0); // 清除通道上的动画
    }

    /**
     * 检查 CANdle 是否有任何故障
     * @return 是否存在故障
     */
    public boolean hasFaults() {
        CANdleFaults faults = new CANdleFaults();
        candle.getFaults(faults);
        return faults.hasAnyFault();
    }

    @Override
    public void periodic() {
        // 定期更新方法，用于监控状态或执行任务
        double ID  = LimelightHelpers.getFiducialID("");
        if (ID != -1){
            startStrobeAnimation(255, 255, 255);
        }
        else{
            stopAnimation();

        }
        

    
    }
}