import java.util.Random;
import java.util.ArrayList;

public class RandomDirections{

    private ArrayList<Character> directions  = new ArrayList<Character>();
    Random random = new Random();

    public RandomDirections(){
        algorithm();
    }

    public char addDirection(){
        int move = random.nextInt(3);
        char direction = 'a';
        switch(move){
          case 0: direction = 'l';
          break;
          case 1: direction = 'f';
          break;
          case 2: direction = 'r';
          break;
        }
        return direction;
    }

    public void fillArray(){
       directions.add('f');
       directions.add('r');
       directions.add('r');
       directions.add('l');
    }

    public void algorithm(){
        while(directions.size() < 8){
            directions.add(addDirection());
        }
    }

}