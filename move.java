
import java.net.* ;

public class move
{
   private final static int PACKETSIZE = 100 ;
   private static int speed = 0, steer = 0;
public void sendPacket(InetAddress host, int port, int speed, int steer){
    DatagramSocket socket = null;
    try{
	    byte[] data = new byte[6];
               data[0] = (byte)0x55; //constant
               data[2] = (byte)0x00; //constant
               data[4] = (byte)0x00; //constant
               data[1] = (byte)(0x96 + speed );
               data[3] = (byte)(0x96 - steer );
	           data[5] = (byte)(0xd4 - speed + steer);
        System.out.println( "data[5]:"+ data[3] ) ;
        DatagramPacket packet = new DatagramPacket(data, data.length, host, port);
        socket.send(packet);
	    System.out.println( "Packet has been sent ..." ) ;
    }catch( Exception e ){
        System.out.println( e ) ;
    }
    finally{
        if( socket != null )
        socket.close() ;
    }
}
}

