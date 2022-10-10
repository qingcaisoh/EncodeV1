import java.util.*;
public class Encoder{
    public  final String ORIGINAL_ORDER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    public  int sizeOfOrignalString = ORIGINAL_ORDER.length();
    public  HashMap<Character, Integer> hm_original_order = new HashMap<>();

    public String encode(String plainText){
        //String firstChara = plainText.charAt(0)+"";
        populateHm(hm_original_order);
        String result = randomiseCharacter()+""  ;

        int movement = ORIGINAL_ORDER.indexOf(result) ;
        if(movement < 0){
            movement = 0;
        }
        if(movement > 0){
            for(int i = 0 ; i < plainText.length(); i ++){
                if(hm_original_order.get(plainText.charAt(i)) != null){
                    int getChar = (hm_original_order.get(plainText.charAt(i)) - movement);
                    if(getChar >= 0){
                        result +=ORIGINAL_ORDER.charAt(getChar);
                    }else{
                          result +=ORIGINAL_ORDER.charAt(sizeOfOrignalString + getChar);
                    }
                    
                }else{
                    result += plainText.charAt(i);
                }
            }
            return result;
        }else{
            return result+plainText;
        }
        
    }
    public String decode(String plainText){
        String result = "" ;
        String firstChara = plainText.charAt(0)+"";
        populateHm(hm_original_order);

        int movement = ORIGINAL_ORDER.indexOf(firstChara) ;
        if(movement < 0){
            movement = 0;
        }
        if(movement > 0){
            for(int i = 1 ; i < plainText.length(); i ++){
                if(hm_original_order.get(plainText.charAt(i)) != null){
                    int getChar = (hm_original_order.get(plainText.charAt(i)) + movement);
                    if(getChar >= sizeOfOrignalString){
                        result +=ORIGINAL_ORDER.charAt( getChar - sizeOfOrignalString  );
                    }else{
                          result +=ORIGINAL_ORDER.charAt(getChar);
                    }
                    
                }else{
                    result += plainText.charAt(i);
                }
            }
            return result;
        }else{
            return plainText.substring(1, plainText.length());
        }
        
    }
    public void populateHm(HashMap<Character, Integer> hm_original_order){
        hm_original_order = new HashMap<>();
         for(int i = 0 ; i < ORIGINAL_ORDER.length(); i ++){
            hm_original_order.put(ORIGINAL_ORDER.charAt(i), i);
        }
    }
    public Character randomiseCharacter (){
        int rand = 40 + (int)(Math.random() * ((90 - 40) + 1));
        return (char) rand;
    }
    public void testing(){
        int count = 0 ; 
        int pass= 0 ;
        for(int i = 0 ; i < 100 ; i ++){
            String encode = encode("HELLOWORLD");
            String decode = decode(encode);
            
            if(!"HELLOWORLD".equals(decode)){
                count ++;
                System.out.println("*********************"+i+"*****************");
                System.out.println("encode :" + encode + " || decode" + decode);
                System.out.println("*********************"+i+"*****************");
            }else{
                pass++;
            }
            System.out.println("count:" + count + " ||  pass" + pass);
        }
    }
}
