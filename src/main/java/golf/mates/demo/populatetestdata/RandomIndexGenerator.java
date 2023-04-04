package golf.mates.demo.populatetestdata;

import java.util.List;
import java.util.Random;

public class RandomIndexGenerator {
    protected static <T> int getRandomIndex(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return index;
    }
}
