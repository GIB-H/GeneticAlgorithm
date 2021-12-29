package Project5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private static final ArrayList<Chromosome> population = new ArrayList<>();
    private static final ArrayList<Double> weightData = new ArrayList<>();

    public static void main(String[] arg){
        try{
            getWeights();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        createStartPopulation(); // Generates the initial chromosomes.

        int generations = 100;

        // Specific Variable Testing
        /*for (int i = 0; i < 300; i++) {
            for (int k = 0; k < generations; k++) {
                sortByFitness();
                mutate(4);
                crossover(19);
            }
            sortByFitness();
            System.out.println("The fitness value for Mutation Rate " + 4 + " and Crossover Point " + 19 + " was: " + population.get(0).getFitness());
            // System.out.println(population.get(0).getFitness());
            population.clear();
            createStartPopulation();
        }*/


        // Every Permutation Testing
        for (int i = 1; i < 26; i++) {
            for (int j = 1; j < 26; j++) {
                for (int k = 0; k < generations; k++) {
                    sortByFitness();
                    mutate(i);
                    crossover(j);
                }
                sortByFitness();
                System.out.println("The fitness value for Mutation Rate " + i + " and Crossover Point " + j + " was: " + population.get(0).getFitness());
                population.clear();
                createStartPopulation();
            }
        }
    }

    public static void createStartPopulation() {
        for (int i = 0; i < 10; i++) {
            population.add(new Chromosome(weightData));
        }
    }

    public static void getWeights() throws IOException {
        String path = "C:\\Users\\George\\Documents\\Code\\ADS\\Project 5 Genetic Algorithm\\Project 5\\GeneticWeights\\weights.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String weight;

        while ((weight = br.readLine()) != null) {
            String[] weights = weight.split(",");

            for (String value : weights) {
                weightData.add(Double.valueOf(value));
                // System.out.println(value); // Checks weights are added to list
            }
        }
    }

    public static void sortByFitness(){
        Collections.sort(population);
    }

    public static void mutate(int mutationRate){
        Chromosome parent = population.get(0);
        Chromosome child  = new Chromosome(weightData, parent.getGeneList());

        // System.out.println("Parent: " + parent.getGeneList());
        Random random = new Random();

        for (int i = 0; i < mutationRate; i++) {
            child.flipValue(random.nextInt(25));
        }

        // System.out.println("Child:  " + child.getGeneList());
    }

    public static void crossover(int crossoverPoint){
        ArrayList<Integer> parent1 = population.get(0).getGeneList();
        ArrayList<Integer> parent2 = population.get(1).getGeneList();

        List<Integer> child1Front  = parent1.subList(0,crossoverPoint);
        List<Integer> child2Front  = parent2.subList(0,crossoverPoint);

        List<Integer> child1Back   = parent1.subList(crossoverPoint,25);
        List<Integer> child2Back   = parent2.subList(crossoverPoint,25);

        ArrayList<Integer> child1  = new ArrayList<>();
        ArrayList<Integer> child2  = new ArrayList<>();

        for (int i = 0; i < crossoverPoint; i++) {
            child1.add(child1Front.get(i));
            child2.add(child2Front.get(i));
        }

        for (int i = 0; i < 25-crossoverPoint; i++) {
            child1.add(child2Back.get(i));
            child2.add(child1Back.get(i));
        }

        population.add(new Chromosome(weightData,child1));
        population.add(new Chromosome(weightData,child2));
    }
}
