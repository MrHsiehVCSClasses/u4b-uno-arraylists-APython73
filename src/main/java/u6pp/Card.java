package u6pp;

public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD"; 
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    // Wild color is the default color for wilds, before they are played. 
    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4};

    // start you code here
    private value;
    private color;

    //default constrcutor, defaults to first values in VALUES and COLORS
   public Card(color,value){
     this.value = VALUES[0];
     this.color = COLORS[0];
   }
   //overloaded constructor, makes custom cards
   public Card(color,value){
     this.value = value;
     this.color = color;
   }

   public string getcolor(){
    return this.color;
   }

   public int getValue(){
     return this.value;
   }

   public String toString(){
    return this.color + " " + this.value;
   }

   public boolean isValid( String word, String[]){
   
     for(i = 0; i < string.length ; i++){
        if(word.equals(string[i])){
          return true;   
        }
        else{
          return false;
    }  
    }
    }



   }
    


}
