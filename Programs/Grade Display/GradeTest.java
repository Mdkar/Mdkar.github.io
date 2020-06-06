
public class GradeTest {

	public static void main(String[] args) {
		Grade test = new Grade("src/scores.txt");
		double[] scores = test.getGrades();
		for(double score: scores)
			System.out.print(score + ", ");
		System.out.println("\n" + test.mean() + "\n" + test.median());
		for(double mode: test.mode())
			System.out.println(mode);
		System.out.println(test.variance() + "\n" + test.standardDeviation());
		for(int a: test.histogram(10, 1, 100))
			System.out.print(a + ", ");
	}

}
