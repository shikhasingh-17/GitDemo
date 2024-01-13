package tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Excel.ReadData;

public class LibraryAPI {
	public static void main(String[] args) throws IOException {
		
		//create Hashmap from json
		ReadData rd = new ReadData();
		ArrayList arr = new ArrayList();
		arr = rd.getTestData("Library");
		HashMap<String,Object> map = new HashMap();
		map.put("name", arr.get(1));
		map.put("isbn", arr.get(2));
		map.put("aisle",arr.get(3));
		map.put("author", arr.get(4));
		
		//Add Book
		String response = given().header("Content-Type","application/Json")
		.body(map)
		.when().post("http://216.10.245.166/Library/Addbook.php")
		.then().extract().response().asString();
		System.out.println(response);
	}

}
