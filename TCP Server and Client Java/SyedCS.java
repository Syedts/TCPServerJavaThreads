import java.util.Scanner;

/**
 *
 * @author Syed
 */
public class SyedCS
{

    public static void count()
    {
         System.out.println("Count executed");
    }
     public static void file()
    {
         System.out.println("file executed");
    }
      public static void dict()
    {
         System.out.println("dict executed");
    }
       public static void convert()
    {
         System.out.println("convert executed");
    }
       
   public static void main(String[] args )
   {
         Scanner input = new Scanner(System.in);
         System.out.println("Enter the following commands : \n count \n file \n dict \n convert \n___________________ \n Command Choice: ");
         String UsrInput = input.nextLine();
         
         switch (UsrInput)
         {
            case "Count":
            case "count":
               count();
               break;
               
            case "file":
            case "File":
               file();
               break;
           
               
            case "dict":
            case "Dict":
               dict();
               break;  
               
            case "convert":
            case "Convert":
               convert();
               break;  
               
            default:
                  System.err.print("Not a valid command");
               
         }
   }
}
