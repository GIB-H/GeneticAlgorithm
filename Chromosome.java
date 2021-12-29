package Project5;
import java.util.ArrayList;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{
    ArrayList<Integer> geneList = new ArrayList<>();
    ArrayList<Double> weightList;
    double fitness;

    public Chromosome(ArrayList<Double> weightList){
        for (int i = 0; i < 25; i++) {
            geneList.add(generateGeneList());
        }
        this.weightList = weightList;
        this.fitness = generateFitness();
    }

    public Chromosome(ArrayList<Double> weightList, ArrayList<Integer> geneList){
        this.geneList = geneList;
        this.weightList = weightList;
        this.fitness = generateFitness();
    }

    public int generateGeneList(){
        Random random = new Random();
        return random.nextInt(2);
    }

    public double generateFitness(){
        double leftWeight  = 0.0;
        double rightWeight = 0.0;

        for (int i = 0; i < 25; i++) {

            if(geneList.get(i) == 0){
                leftWeight += weightList.get(i);
            }
            else {
                rightWeight += weightList.get(i);
            }
        }
        return Math.abs(leftWeight - rightWeight);
    }

    public double getFitness(){
        return fitness;
    }

    public ArrayList<Integer> getGeneList(){
        return geneList;
    }

    public void flipValue(int positionToFlip){
        if(geneList.get(positionToFlip) == 1){
            geneList.set(positionToFlip, 0);
        }
        else if(geneList.get(positionToFlip) == 0){
            geneList.set(positionToFlip, 1);
        }
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return Double.compare(this.getFitness(), chromosome.getFitness());
    }
}