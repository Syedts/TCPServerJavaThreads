import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
public class SyedServer {
	

    public final static int PORT = 40067;
    
    public static void main(String[] args) {
    
	ExecutorService pool = Executors.newFixedThreadPool(3);
	
	 
	try (ServerSocket server = new ServerSocket(PORT)) 
	{
		int clientNo=1;
		String setName = "Client# " + clientNo ;
		System.out.println("Starting multi threaded server on port " + server.getLocalPort()); 
	    while (true) {
		try {
		
		    Socket connection = server.accept();
		   System.out.println();
		   
		    Callable<Void> task = new CommandServer(connection,setName);
		    
		    pool.submit(task);
		   clientNo++;
		   setName = "Client# " + clientNo ;
		} 
		
		catch (IOException ex) {System.err.println("Couldn't start server");}
		  
	    }
	} catch (IOException ex) {
	    System.err.println("Couldn't start server");
	}
	
    }
    

    
    
    
    private static class CommandServer implements Callable<Void> {
	private Socket connection;
        private String name;
       
            
     public static String count()
    {
         System.out.println("Count executed");
         return "Count Executed";
    }
     public static String file()
    {
         System.out.println("file executed");
         return "File Executed";
    }
      public static String dict()
    {
         System.out.println("dict executed");
         return "Dict Executed";
    }
       public static String convert()
    {
         System.out.println("convert executed");
         
         return "Convert Executed";
    }
    
	CommandServer(Socket connection,String name)  	// Constructor
	{
	    this.connection = connection;
            this.name=name; 
	}
	@Override
	    public Void call() throws IOException {
		try {
                    System.out.println("The name of this client is: " + name); 
		     DataInputStream inputFromClient=new DataInputStream(connection.getInputStream());
          DataOutputStream outputToClient=new DataOutputStream(connection.getOutputStream());
                
                    //Display client information on the Command Server 
	System.out.println("We have a connection for client " + name + " at " + new Date());
	InetAddress inetAddress = connection.getInetAddress();
	System.out.println("Client " + name + "'s hostname is "+  inetAddress.getHostName()+ ":" + connection.getPort()); //getPort() not in textbook
	System.out.println("Client " + name + "'s ipAddress is "+  inetAddress.getHostAddress());
	
outputToClient.writeChars(name + "\n Port: " + PORT +"\n" );

       String Prompt ="\n Enter the following commands : \n count \n file \n dict \n convert \n___________________ \n Command Choice: ";
       while(true)
       {
       	
       	outputToClient.writeChars(Prompt);
       	String Input = inputFromClient.readLine();
       	 switch (Input)
         {
            case "Count":
            case "count":
               outputToClient.writeChars(count());
               break;
               
            case "file":
            case "File":
               outputToClient.writeChars(file());
               break;
           
               
            case "dict":
            case "Dict":
               outputToClient.writeChars(dict());
               break;  
               
            case "convert":
            case "Convert":
               outputToClient.writeChars(convert());
               break;  
               
            default:
                  outputToClient.writeChars("Not a valid command ");
               
         }
	
	}
	
                   
		} catch (IOException ex) {
		    System.err.println(ex);
		} finally {
		    connection.close();
		}
		return null;
	    }
    }
}

