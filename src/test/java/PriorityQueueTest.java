import java.util.ConcurrentModificationException;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{6, 6, 6, 6, 6, 6}, new int[]{6, 6, 6, 6, 6, 6}),
                Arguments.of(new int[]{3, 0, -5, 6}, new int[]{-5, 0, 3, 6}),
                Arguments.of(new int[]{-10, 1, 2, -20}, new int[]{-20, -10, 1, 2}),
                Arguments.of(new int[]{4, 3, -6, -5, 1}, new int[]{-6, -5, 1, 3, 4}),
                Arguments.of(new int[]{9, -4, -2}, new int[]{-4, -2, 9})
        );
    }


    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Integer S;
        int[] result = new int[random_array.length];

        // random_array add to PriorityQueue
        for (int i : random_array) {
            test.offer(i);
        }

        // get PriorityQueue result
        for(int i=0; i<random_array.length; i++){
            S = test.poll();
            if(S != null){
                result[i] = S;
            }
            else{
                System.out.println("Null Value for Queue");
            }
        }

        assertArrayEquals(correct_array, result);
        System.out.println("Success");
    }


    @Test
    public void NullPointerException_offer(){
        Exception ex = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue<Integer>().offer(null);
        });
    }


    @Test
    public void IllegalArgumentException_PriorityQueue(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<Integer>(-1);
        });
    }


    @Test
    public void NoSuchElementException_remove(){
        Exception ex = assertThrows(NoSuchElementException.class, () -> {
            new PriorityQueue<Integer>().remove();
        });
    }

}
