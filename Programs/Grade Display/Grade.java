import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Grade {
	private double[] grades;
	
	/**
	 * Constructs a Grade Object
	 * @param filename
	 */
	public Grade(String filename){
		int count = 0;
		File f = new File(filename);
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextDouble()){
				sc.nextDouble();
				count++;
			}
			if(count == 0)
				throw new IllegalArgumentException("There must be at least one score");
			grades = new double[count];
			sc = new Scanner(f);
			int i = 0;
			while(sc.hasNextDouble()){
				grades[i] = sc.nextDouble();
				i++;
			}
			Arrays.sort(grades);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return grades
	 */
	public double[] getGrades(){
		return grades;
	}
	/**
	 * 
	 * @return  sum of grades
	 */
	private double sum(){
		double sum = 0;
		for(double grade: grades)
			sum+=grade;
		return sum;
	}
	/**
	 * 
	 * @return mean value of grades
	 */
	public double mean(){
		return sum()/grades.length; 
	}
	/**
	 * 
	 * @return median value of grades
	 */
	public double median(){
		if(grades.length%2 == 1)
			return grades[(int) (grades.length/2 +0.5)];
		else
			return (grades[grades.length/2] + grades[grades.length/2 - 1])/2;
	}
	/**
	 * 	
	 * @return array with all modes in the grades
	 */
	public double[] mode(){
		int reps = 0;
		int repMax = 0;
		int modeIndex = 0;
		double currNum = grades[0];
		double[] tempModes = new double[grades.length];
		for(int i = 0; i < grades.length; i++){
			if(grades[i] == currNum){
				reps++;
				if(reps > repMax){
					repMax = reps;
					modeIndex = 0;					
				}
				if(i == grades.length - 1){
					tempModes[modeIndex] = currNum;
					modeIndex++;
				}
			} 
			else {
				if(reps == repMax){
					tempModes[modeIndex] = currNum;
					modeIndex++;
				}
				reps = 0;
				currNum = grades[i];
			}
				
		}
		double[] modes = new double[modeIndex];
		for(int i = 0; i < modeIndex; i++ )
			modes[i] = tempModes[i];
		return modes;
		
	}
	/**
	 * 
	 * @return variance of the grades (s^2)
	 */
	public double variance(){
		double sum = 0;
		for(double a: grades)
			sum += (a-mean()) * (a-mean());
		return sum/grades.length;
	}
	/**
	 * 
	 * @return standard deviation of the grades (s)
	 */
	public double standardDeviation(){
		return Math.sqrt(variance());
	}
	/**
	 * 
	 * @param sizeOfBucket
	 * @param minValue
	 * @param maxValue
	 * @return int array with the number of items in each bin
	 */
	public int[] histogram(int sizeOfBucket, int minValue, int maxValue){
		minValue --;
		int length = (int) Math.ceil((maxValue-minValue)/sizeOfBucket);
		int[] histogram = new int[length];
		int currValue = minValue + sizeOfBucket;
		int i = 0;
		for(double a: grades){
			while(a>currValue){
				i++;
				currValue+=sizeOfBucket;
			}
			histogram[i]++;
		}
		return histogram;
	}
}
