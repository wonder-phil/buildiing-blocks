package simple;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/*
 *
 *
 * From: http://tugdualgrall.blogspot.com/2017/01/getting-started-with-mqtt-and-java.html 
 *
 */


public class Subscriber {

  public static void main(String[] args) throws MqttException {

    System.out.println("== START SUBSCRIBER ==");

    MqttClient client=new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    client.setCallback( new SimpleMqttCallBack() );
    client.connect();

    client.subscribe("iot_data");

  }

}
