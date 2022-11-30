import apis.Client;
import apis.TodoListApi;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TodoListTest {

    @Test
    public void assertThatListFirstTaskHasTitle(){
        JsonPath jsonResponse = new TodoListApi().getTodoItemByTaskIndex(1);
        String actualString = jsonResponse.getString("title");
        String expectedString = "delectus aut autem";
        assertEquals(expectedString, actualString);
    }
}