import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.net.InetAddress;
import java.io.InputStreamReader;

public class CarEngine{
	
	private static final int MINDISTANCE = 100;
	//private static final int DISTANCERROR = 10;
	private static final int MAXLOC = 200;
	private static final long VALIPERIODLOC = 1000;  /// sniffed location is valid for half sec
	private static final int EPSILON = 10;		 /// car supposed to be stopped if distance between "KLOCATION" sniffed locations <= epsilon
	//private static final int KLOCATION = 3;
	private static final int STEPNUMBERS = 4;
	public static String simfile = "";
	public static String sniffile = "";
	public static String VTLfile = "VTLlog.txt";
	public static SniffedLoc Sniffedlocations[] = new SniffedLoc[MAXLOC];
	public static SimLoc Simlocations[] = new SimLoc[10];
	private static InputStream in;
	//public static int carIdPool[][] = {{1,0},{2,0},{3,0},{4,0},{5,0}};  /// second dimension is to check whether we have found last postion for corresponding carid
	//public static VTLst VTLstate[] = new VTLst[500];

///////////////////////////////wait function///////////////////////////////
public static void waitSec(long s) {
     		try {
       			Thread.currentThread();
				Thread.sleep(s * 1000);
     		  }
	     catch (InterruptedException e) {
		       e.printStackTrace();
	       }
	     }
/////////////////////////////VTL state check/////////////////////////////////////
@SuppressWarnings("null")
public static int VTLstate(int flag) throws IOException
{
	int Dir1,Dir2,ret=0;
        File f = new File(VTLfile);
        FileInputStream fis = new FileInputStream(f);
        byte[] content = new byte[fis.available()];
        fis.read(content);
        String data = new String(content);
        String[] split = data.split(System.getProperty("line.separator"));
        String lastLine = "";
        if(split.length > 0){
            lastLine = split[split.length -1];
            String datavalue[];
            datavalue = ((String) lastLine).split("\t\t");
            Dir1 = Integer.parseInt(datavalue[0]);
            Dir2 = Integer.parseInt(datavalue[1]);
            if (flag == 1){
                in.close();
                ret=Dir1;
            }
            if (flag == 2){
                in.close();
                ret=Dir2;
            }
            return ret;
            }
    return ret;
}
////////////////////// Read simulation results ///////////////////
public static SimLoc[] getSimLocations(String simfile){		
	int i = 0;
	String fileRecord = null;
	BufferedReader buffReader = null;
	try {
		buffReader = new BufferedReader(new FileReader(simfile));
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
		}
	try{
        while (( fileRecord = buffReader.readLine()) != null){
			StringTokenizer st = new StringTokenizer(fileRecord);
			Simlocations[i].Steer = Float.parseFloat(st.nextToken().toString());
			Simlocations[i].TotalRotation = Float.parseFloat(st.nextToken().toString());
			Simlocations[i].Dirx = Float.parseFloat(st.nextToken().toString());
			Simlocations[i].Diry = Float.parseFloat(st.nextToken().toString());
			Simlocations[i].PositionX = Float.parseFloat(st.nextToken().toString());
			Simlocations[i++].PositionY = Float.parseFloat(st.nextToken().toString());
        }
		}catch (Exception e) {
			e.printStackTrace();
			}
		finally {
            try {
		      buffReader.close();
		    } catch (IOException e) {
			   		e.printStackTrace();
			}
            i=0;
            fileRecord = null;
            buffReader = null;
            try {
			buffReader = new BufferedReader(new FileReader(simfile));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try{
                while (( fileRecord = buffReader.readLine()) != null){
                    StringTokenizer st = new StringTokenizer(fileRecord);
                    Simlocations[i].Steer = Float.parseFloat(st.nextToken().toString());
                    Simlocations[i].TotalRotation = Float.parseFloat(st.nextToken().toString());
                    Simlocations[i].Dirx = Float.parseFloat(st.nextToken().toString());
                    Simlocations[i].Diry = Float.parseFloat(st.nextToken().toString());
                    Simlocations[i].PositionX = Float.parseFloat(st.nextToken().toString());
                    Simlocations[i++].PositionY = Float.parseFloat(st.nextToken().toString());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    buffReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileRecord = null;
                buffReader = null;
                try {
                    buffReader = new BufferedReader(new FileReader(simfile));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try{
                    while (( fileRecord = buffReader.readLine()) != null){
                        StringTokenizer st = new StringTokenizer(fileRecord);
                        Simlocations[i].Steer = Float.parseFloat(st.nextToken().toString());
                        Simlocations[i].TotalRotation = Float.parseFloat(st.nextToken().toString());
                        Simlocations[i].Dirx = Float.parseFloat(st.nextToken().toString());
                        Simlocations[i].Diry = Float.parseFloat(st.nextToken().toString());
                        Simlocations[i].PositionX = Float.parseFloat(st.nextToken().toString());
                        Simlocations[i++].PositionY = Float.parseFloat(st.nextToken().toString());
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        buffReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i = 0;
                    fileRecord = null;
                    buffReader = null;
                    try {
                        buffReader = new BufferedReader(new FileReader(simfile));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    try{
                        while (( fileRecord = buffReader.readLine()) != null){
                            StringTokenizer st = new StringTokenizer(fileRecord);
                            Simlocations[i].Steer = Float.parseFloat(st.nextToken().toString());
                            Simlocations[i].TotalRotation = Float.parseFloat(st.nextToken().toString());
                            Simlocations[i].Dirx = Float.parseFloat(st.nextToken().toString());
                            Simlocations[i].Diry = Float.parseFloat(st.nextToken().toString());
                            Simlocations[i].PositionX = Float.parseFloat(st.nextToken().toString());
                            Simlocations[i++].PositionY = Float.parseFloat(st.nextToken().toString());
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            buffReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    return Simlocations;
}
////////////////////	Read Sniffed location log file ////////////////////////
public static SniffedLoc[] getSniffedLocations(String sniffile){
    int i = 0;
	String fileRecord = null;
	BufferedReader buffReader = null;
	try {
		buffReader = new BufferedReader(new FileReader(sniffile));
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
    }
	try{
		while (( fileRecord = buffReader.readLine()) != null){
			StringTokenizer st = new StringTokenizer(fileRecord);
			System.out.println(String.valueOf(st.countTokens()) + Sniffedlocations[i].CarID);
			Sniffedlocations[i].CarID = Integer.parseInt(st.nextToken().toString());
			Sniffedlocations[i].PositionX = Float.parseFloat(st.nextToken().toString());
			Sniffedlocations[i].PositionY = Float.parseFloat(st.nextToken().toString());
			System.out.println(String.valueOf(Sniffedlocations[i].PositionY));
			Sniffedlocations[i].TimeofSniff = Long.parseLong(st.nextToken().toString());
			Sniffedlocations[i].flag = 0;
			i++;
    	}
	}catch (Exception e) {
		e.printStackTrace();
	}
    finally {
	      try {
	    	  buffReader.close();
	      } catch (IOException e) {
              e.printStackTrace();
          }
    }
	return Sniffedlocations;
}
//////////// Last Sniffed Location ////////////////////////////////
public static SniffedLoc getLastSniffedLocation(int id){
    SniffedLoc myCarlastLoc = new SniffedLoc();
	myCarlastLoc = null;
	int  i = 0;
	Sniffedlocations = getSniffedLocations(sniffile);
    for(i = Sniffedlocations.length - 1 ; i >= 0 ; i--){
        if (Sniffedlocations[i].CarID == id){
            myCarlastLoc = Sniffedlocations[i];
			System.out.println("get last location :"+String.valueOf(myCarlastLoc.PositionX) );
			return myCarlastLoc;
        }
    }
    return null;
}
/////////// Check the Traffic Light: VTL state should be read (Will be released in future versions)///////
/*public static boolean VTLpermit(){
	System.out.println("Traffic Light is green :)");
	return true;
}*/
////////////////// Calculate Distance  /////////////////
public static float calcDis(float a,float b,float c,float d){
	return pow((pow(a-b , 2)+pow(c-d , 2)),0.5);
}
private static float pow(float f, double d) {
    return 0;
}
////////////// Check Safety distance between ALL cars //////////////////
public static boolean checkCarDistances(){
	boolean result = true;
	Sniffedlocations = getSniffedLocations(sniffile);
	for (int i = 0 ; i <= Sniffedlocations.length ; i++)
		for(int j = i+1 ; j <= Sniffedlocations.length ; j++){
			if(Sniffedlocations[i].CarID != Sniffedlocations[j].CarID)
				if( calcDis(Sniffedlocations[i].PositionX,Sniffedlocations[j].PositionX,Sniffedlocations[i].PositionY,Sniffedlocations[j].PositionY) < MINDISTANCE)
					return false;
        }
	return result;
}
////////////////////check my car and other cars distance//////////
public static boolean checkMycarDistance(int id){
    SniffedLoc myCarlastLoc = null;
	int j = 1, i = 0;
	Sniffedlocations = getSniffedLocations(sniffile);
	for(i = Sniffedlocations.length - 1 ; i >= 0 ; i = i-j){
		if (Sniffedlocations[i].CarID == id){
			myCarlastLoc = Sniffedlocations[i];
			break;
		}
		j++;
	}
	j = 1;
	for(i = Sniffedlocations.length - 1 ; i > 0 ; i = i-j){
		if(Sniffedlocations[i].CarID != myCarlastLoc.CarID && Sniffedlocations[i].flag != 1){
			Sniffedlocations[i].flag = 1;
			if( calcDis(Sniffedlocations[i].PositionX,myCarlastLoc.PositionX,Sniffedlocations[i].PositionY,myCarlastLoc.PositionY) < MINDISTANCE){
				System.out.println( "Distance between Car  "+ String.valueOf(id)+"  and Car  "+ String.valueOf(i)+"  is less than threshold ");
				return false;
			}
		}
		j++;
	}
	return true;
}
///////////////////// Check Location is stable or not? Valid period has passed or not /////////////////
public static boolean isLocValid(int id){
	SniffedLoc loc = null;
	loc = getLastSniffedLocation(id);
	long difTime = System.currentTimeMillis() - loc.TimeofSniff;
	if( difTime <= VALIPERIODLOC ){
		System.out.println("time in checking file is : "+String.valueOf(System.currentTimeMillis()));
		System.out.println("Sniffed Location is in valid period");
		return true;
	}
	else{
		System.out.println("Sniffed Location is NOT in valid period");
		return false;
	}
}
/////////////// check whether car is moving or it has stopped , 3 similar locations ---> car is stopped///////////////
public static boolean isCarStopped(int id){    ///// Currently supports one car
	Sniffedlocations = getSniffedLocations(sniffile);
	SniffedLoc loc1 = null,loc2 = null,loc3 = null;
	int j = 1;
	for(int i = Sniffedlocations.length - 1 ; i >= 2 ; i = i-j){
		if(Sniffedlocations[i].CarID == id)
			loc1 = Sniffedlocations[i];
			loc2 = Sniffedlocations[i-1];
			loc3 = Sniffedlocations[i-2];
			System.out.println("loc2 pos x : "+String.valueOf(loc1.PositionX));
			j++;
			if(calcDis(loc1.PositionX,loc2.PositionX,loc1.PositionY,loc2.PositionY) < EPSILON){
				if(calcDis(loc3.PositionX,loc2.PositionX,loc3.PositionY,loc2.PositionY)< EPSILON){
					System.out.println("Car is Stopped");
					return true;
				}
			}
	}
	System.out.println("Car is not Stopped !");
	return false;
}
/////////////////// check post condition : after movement is car's location near to position where it supposed to be(compare with simulation result)?
public static boolean postCondition(int step, int id){
	SniffedLoc lastLoc = new SniffedLoc();
	lastLoc = getLastSniffedLocation(id);
	System.out.println("post condition posx:  "+String.valueOf(lastLoc.PositionX) );
	Simlocations = getSimLocations(simfile);
	float a = Simlocations[step].PositionX;
	float b = Simlocations[step].PositionY;
	System.out.println("post condition sim posx:  "+String.valueOf(a) );
	if(calcDis(a,lastLoc.PositionX,b,lastLoc.PositionY) < EPSILON)
		return true;
	else
		return false;
}
/////////////////////////8 shape experiment////////////////////
public static void eightExp(InetAddress host, int port,int k){
     move moveMycar =  new move();
	if (k<=5){
		moveMycar.sendPacket(host, port, 8, 12);
		waitSec(5);
	}else
		if (5<k && k<9){
            moveMycar.sendPacket(host, port, 8, 3);
			waitSec(5);
			}else
                if (8<k && k<15){
                    moveMycar.sendPacket(host, port, 8, -6);
                    waitSec(5);
					}else
						if (14<k && k<18){
							moveMycar.sendPacket(host, port, 8, 3);
							waitSec(5);
						}
}
///////////////////// main method//////////////////
public static void main(String args[]) throws IOException {
    if( args.length < 3 ){/// steer and speed are inputs in circle movement
	    System.out.println( "usage: java DatagramClient Command" ) ;
	    return;
    }
    sniffile = args[0];
	simfile = args[1];
	int carID = Integer.parseInt(args[2]);
    //////////// socket openning //////////////////////
    InetAddress host = InetAddress.getByName( "192.168.3.1" ) ;
    int port = Integer.parseInt( "15200" ) ; //car 3
    int flag;
	///////////////////////////////traffic light controller/////////////////////
    for(int k = 1; k <= 17; k++){         //myCarlastLoc
        	///// checks before moving ///////////
            System.out.println("time is :"+System.currentTimeMillis());
	    	System.out.println("Checking Traffic Light, Distance, Validity of Location ...");
	    if ((k!= 6) || (k!=13)){
            eightExp(host,port,k);
	    	}
		if (k== 6){
            flag = 1;
            if ((VTLstate(flag))!= 1)
                eightExp(host,port, k);
			}
		if (k==13){
            flag =2 ;
		if ((VTLstate(flag))!= 1)
			eightExp(host,port, k);
			}
    }
}
}