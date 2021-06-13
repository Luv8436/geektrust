import java.util.*;
import java.io.File;  // Import the File class

public class Geektrust {

	public static void main(String[] args)  {
		String filePath = args[0];
		//Parse the file and call your code
        // hashmap storing the routes and distance of each train
        HashMap<String , Integer> hma = new HashMap<>();
        HashMap<String , Integer> hmb = new HashMap<>();
        // storing the values in hashmap
        hashmapA(hma);
        hashmapB(hmb);
        // reading input from the input.txt file
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        String[] arrA = myReader.nextLine().split(" ");
        String[] arrB = myReader.nextLine().split(" ");
        myReader.close();
        LinkedList<String> trainA = new LinkedList<>();
        // storing the train A input in the linked list data structure
        for(int i=0;i<arrA.length;i++){
            trainA.add(arrA[i]);
        }
        LinkedList<String> trainB = new LinkedList<>();
        // storing the train A input in the linked list data structure
        for(int i=0;i<arrB.length;i++){
            trainB.add(arrB[i]);
        } 

        rmvBoggiesBeforeHYB(trainA , hma , 1200);
        rmvBoggiesBeforeHYB(trainB , hmb , 2000);
        // for(int s=trainA.size()-1;s>=0;s--){
        //     String curr= trainA.get(s);
        //     System.out.println(curr+" "+hma.get(curr));
        // }
        
        trainA.add(0 , "ARRIVAL");
        trainB.add(0 , "ARRIVAL");
        displayTrain(trainA);
        displayTrain(trainB);

        // at hyderabad change hashmap distances
        HashMap<String , Integer> AfterHyb = new HashMap<String ,Integer>() ;
        changeDistance(AfterHyb  , hma , 1200);
        changeDistance(AfterHyb , hmb , 2000);

        // new train AB
        LinkedList<String> trainAB = new LinkedList<>();
        mergeTwoTrains(trainAB , trainA , AfterHyb);
        mergeTwoTrains(trainAB , trainB , AfterHyb);
        trainAB.addFirst("ENGINE");
        trainAB.addFirst("ENGINE");
        trainAB.addFirst("TRAIN_AB");
        trainAB.addFirst("DEPARTURE");
        displayTrain(trainAB);
        
	}

    // change distances in the chart reaching hyb and make one single chart for further
    public static void changeDistance(HashMap<String , Integer>AfterHyb , HashMap<String,Integer> hma , int distance){   
        for(String city : hma.keySet()){
            if((hma.get(city)-distance)>0){
                AfterHyb.put(city ,hma.get(city)-distance );
            }
        }
    }

    // remove boggies in train before distance less than hyb
    public static void rmvBoggiesBeforeHYB(LinkedList<String> trainA , HashMap<String,Integer> hma , int distance ){
        for(int s=trainA.size()-1;s>=0;s--){
            String curr = trainA.get(s);
            if(curr.equals("ENGINE")){
                break;
            }
            // remove the bogie in train A before hyderabad(i.e. distance 1200km)
            if(hma.get(curr)==null){
                continue;
            }
            else if(hma.get(curr)<distance){
                trainA.remove(curr);
            }
            
        }
    }
    // display train
    public static void displayTrain(LinkedList<String> trainA){
        for(String s:trainA){
            System.out.print(s+" ");
        }
        System.out.println();
    }

    // merge two trains on reaching hyb according to the new chart
    public static void mergeTwoTrains(LinkedList<String> trainAB ,LinkedList<String> trainA , HashMap<String,Integer> AfterHyb){
        while(trainA.size()>0){
            String bogie = trainA.removeFirst();
            if(!bogie.equals("HYB")&&!bogie.equals("ARRIVAL")&&!bogie.equals("TRAIN_B")&&!bogie.equals("TRAIN_A")&&!bogie.equals("ENGINE")){
                int index = 0;
                while(index<trainAB.size()&&AfterHyb.get(trainAB.get(index))> AfterHyb.get(bogie) ){
                    index++;
                }
                trainAB.add(index , bogie);
            }
        }
    }
    
    // store chart for train A before hyderabad
    public static void hashmapA(HashMap<String , Integer> hma ){
        hma.put("CHN",0);
        hma.put("SLM",350);
        hma.put("BLR",550);
        hma.put("KRN",900);
        hma.put("HYB",1200);
        hma.put("NGP",1600);
        hma.put("ITJ",1900);
        hma.put("BPL",2000);
        hma.put("AGA",2500);
        hma.put("NDL",2700);
    }
    // store chart for train B before hyderabad
    public static void hashmapB(HashMap<String , Integer> hma ){
        hma.put("TVC" , 0);
        hma.put("SRR" , 300);
        hma.put("MAQ" , 600);
        hma.put("MAO" , 1000);
        hma.put("PNE" , 1400);
        hma.put("HYB" , 2000);
        hma.put("NGP" , 2400);
        hma.put("ITJ" , 2700);
        hma.put("BPL" , 2800);
        hma.put("PTA" , 3800);
        hma.put("NJP" , 4200);
        hma.put("GHY" , 4700);
        
    }

}
