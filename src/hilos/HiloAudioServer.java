package hilos;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class HiloAudioServer extends Thread{

	
	private SourceDataLine sLine;
    private AudioFormat audioFormat;
    private AudioInputStream audioInputStream=null;
    private String host;
    private int port;
    private DatagramSocket server;
    private DatagramPacket packet;
    private long startTime;
    private long endTime=System.nanoTime();;
    private long elapsed=System.nanoTime();;
    private double sleepTime;
    private long sleepTimeMillis;
    private int sleepTimeNanos, epsilon;

    public HiloAudioServer(String host, int port) {      
        this.host=host;
        this.port=port;
        init();
    }

    public void init() {
        File file = new File("./docs/audio/audio.wav");
        try {
            audioInputStream=AudioSystem.getAudioInputStream(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        audioFormat = audioInputStream.getFormat();
        //audioFormat = new AudioFormat(44100, 16, 2, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            server = new DatagramSocket();

        } catch (SocketException e) {
            e.printStackTrace();
        }       
    }

    public void run() {
        try {
        	
            byte bytes[] =  new byte[4096];
            byte bytes2[] =  new byte[1024];
            int bytesRead=0;
            while ((bytesRead=audioInputStream.read(bytes, 0, bytes.length))!= -1) {
         	
                try {                   

                    packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
                    packet.setData(bytes);
                    packet.setLength(bytes.length);  
                    server.send(packet);                    

                    Thread.sleep(30);                   
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }   
            System.out.println("No bytes anymore !");                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLine.close();
        System.out.println("Line closed");

    }
	
}
