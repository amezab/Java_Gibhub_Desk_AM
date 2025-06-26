package utils;
import org.example.utils.UserInputHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Scanner;

public class UserInputHandlerTest {



    @Test
    public void getIntegerInput_WhenGivenValidNumber_ReturnsThatNumber() {

        Scanner fakeScanner = new Scanner("2\n");
        UserInputHandler handler = new UserInputHandler(fakeScanner);
        // We call the one single method we want to test.
        int result = handler.getIntegerInput();
        assertEquals(2, result);
    }

  @Test
    public void testBadInputThenGoodInput(){
      Scanner fakeScanner = new Scanner("Hello World\n78\n");
      // Creates the handler and give it our fake scanner
      UserInputHandler handler = new UserInputHandler(fakeScanner);
      //Run the code we want to test
      int result = handler.getIntegerInput();
      assertEquals(78, result);

  }

}


