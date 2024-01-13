package tests;

import java.io.IOException;
import java.util.ArrayList;

import Excel.ReadData;

public class Test1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReadData rd = new ReadData();
		ArrayList arr = new ArrayList();
		arr = rd.getTestData("Purchase");
		System.out.println(arr);
		

	}

}
