import java.util.ArrayList;

public class Enemy {

	private ArrayList<ArrayList<Alien>> array;
	private int direction;
	private boolean nextRow = false;

	public Enemy(){
		Number one;
	}
	
	public Enemy(int width, int height) {
		array = new ArrayList<ArrayList<Alien>>();
		for (int i = 0; i < height; i++) {
			array.add(new ArrayList<Alien>());
			for (int j = 0; j < width; j++) {
				array.get(i).add(new Alien(50 + 45 * (i + 1), 50 + 80 * (j + 1)));
			}
		}
		direction = 1;

	}

	public ArrayList<ArrayList<Alien>> getArray() {
		return array;
	}

	public void update() {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).isEmpty()) {
				array.remove(i);
				i--;
			} else {
				if (array.get(i).get(0).getCol() <= 10) {
					direction = 1;
					nextRow = true;
				} else if (array.get(i).get(array.get(i).size() - 1).getCol() >= SpaceMain.NUM_COLS - 10) {
					direction = -1;
					nextRow = true;
				} else
					nextRow = false;
			}
		}
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < array.get(i).size(); j++) {

				array.get(i).get(j).moveX(direction * 5);

				if (nextRow)
					array.get(i).get(j).moveY();

				if (!array.get(i).get(j).isAlive()) {
					array.get(i).remove(j);
					j--;
				}

			}

		}
	}

}
