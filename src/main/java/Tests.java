import java.util.Arrays;

public class Tests {

    public static void main(String[] args){
        String[] shoots = {"a","b","c","d","e","f"};
        String[] shoots2;
        System.out.println(Arrays.toString(shoots));
        for (int i=0;i<shoots.length;i++){
            if (shoots[i].equals("c")){
                shoots2 = new String[shoots.length-1];
                int p=0;
                for (String shoot:shoots){
                    if(!shoot.equals("c")) {
                        shoots2[p] = shoot;
                        p++;
                    }
                }
                shoots=shoots2;
            }
        }
        System.out.println(Arrays.toString(shoots));

    }
}
