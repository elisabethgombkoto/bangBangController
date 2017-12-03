package controllers.BngBraitenbergVehikelLiebe;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Elisabeth on 27.11.2017.
 */
public class BngBraitenbergVehikelLiebe extends DifferentialWheels {

  private static int TIME_STEP = 15;

  private static int S_LEFT = 0; // Sensor left
  private static int S_FRONT_LEFT = 1; // Sensor front left
  private static int S_FRONT_RIGHT = 2; // Sensor front right
  private static int S_RIGHT = 3; // Sensor left

  private static int MIN_SPEED = 0; // min. motor speed
  private static int MAX_SPEED = 1000; // max. motor speed

  private LightSensor[]sensors;

  /**
   * Constructor
   */
  public BngBraitenbergVehikelLiebe(){
    super();
    sensors = new LightSensor[]{
        getLightSensor("ls5"), getLightSensor("ls7"), getLightSensor("ls0"), getLightSensor("ls2")
    };
    for (int i = 0; i<4; i++){
      sensors[i].enable(10);//enabled the sensor for 10 milli sec
    }
  }

  public void run(){
    while (step(TIME_STEP) != -1){
      if(sensors[S_LEFT].getValue() > sensors[S_RIGHT].getValue()){
        driveLeft();
      }else if(sensors[S_FRONT_RIGHT].getValue() > sensors[S_FRONT_LEFT].getValue()){
        driveRight();
      }else if (sensors[S_FRONT_RIGHT].getValue() == sensors[S_FRONT_LEFT].getValue()) {//schon stark aber noch nicht da
        driveForward();
      }else {
        stop();
      }
    }
  }

  private void driveForward() {
    setSpeed(MAX_SPEED, MAX_SPEED);
  }

  private void driveRight() {
    setSpeed(MAX_SPEED,MIN_SPEED);
  }

  private void driveLeft() {
    setSpeed(MIN_SPEED, MAX_SPEED);
  }
  private void stop(){
    setSpeed(0.0, 0.0);
  }

  /**
   * Main method - in this method an instance of the controller is created and
   * the method to launch the robot is called.
   *
   * @param args
   */
  public static void main(String[] args) {
   controllers.BngBraitenbergVehikelLiebe.BngBraitenbergVehikelLiebe controller = new controllers.BngBraitenbergVehikelLiebe.BngBraitenbergVehikelLiebe();
    controller.run();
  }
}
