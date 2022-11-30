package apis;

import io.restassured.path.json.JsonPath;

public class TodoListApi {
    private final String API_URL = "https://jsonplaceholder.typicode.com/todos/";
    Client apiClient;

    public TodoListApi(){
        apiClient  = new Client(API_URL);
    }

    public JsonPath getTodoItemByTaskIndex(int TaskIndex){
        return apiClient.getJsonResponse(String.valueOf(TaskIndex));
    }

}
