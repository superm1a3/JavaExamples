package programming.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.interviewbit.com/problems/spiral-order-matrix-i/
public class SpiralOrderMatrix {

	public static List<Integer> spiralOrder(final List<List<Integer>> a) {
		 List<Integer> result = new ArrayList<Integer>();
		 
		 int rows = a.size();
		 int cols = a.get(0).size();
		 
		 int top = 0;
		 int right = cols - 1;
		 int left = 0;
		 int bottom = rows - 1;
		 
		 while (top <= bottom && left <= right) {
			 for (int i = left; i <= right; i++) {
				 result.add(a.get(top).get(i));
			 }
			 top += 1;
			 
			 for (int i = top; i <= bottom; i++) {
				 result.add(a.get(i).get(right));
			 }
			 right -= 1;
			 
			 for (int i = right; i >= left; i--) {
				 result.add(a.get(bottom).get(i));
			 }
			 bottom -= 1;
			 
			 for (int i = bottom; i >= top; i--) {
				 result.add(a.get(i).get(left));
			 }
			 left += 1;
		 }
		 
		 
		 return result;
	}
	
	public static void main(String[] args) {
		List<List<Integer>> matrix = Arrays.asList(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5, 6),
				Arrays.asList(7, 8, 9));
		System.out.println(spiralOrder(matrix));
	}
}
