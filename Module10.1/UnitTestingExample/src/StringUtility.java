public class StringUtility {
 
 
    public String reverse(String str){

        if(str == null){
            throw new IllegalArgumentException("String cannot be null");
        }

        String reversed ="";

        for(int i = str.length()-1; i>=0;i--){
            reversed += str.charAt((i));
        }
 
        return reversed;
        
    }

    //String str = "Hello"
    //Str.length()=5
    //Last validIndex = 4
 
 
    public boolean isPalindrome(String str){

             if(str == null){
            throw new IllegalArgumentException("String cannot be null");
        }

        String reversed = reverse(str);

        return str.equalsIgnoreCase(reversed);
 
    }
}