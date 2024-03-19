/////////////////////////////////////////////////////////////////////////////////
//// CREDIT: https://www.torahdesigns.com/halachos-of-ikar-vtofel-chart/     ////
//// The information from the above link is based on lessons of Rabbi        ////
//// Eliezer Krohn and was reviewed by him for accuracy.                     ////
//// Note: The website has a few details that I omitted in my project.       ////
/////////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// create weighted graph
		WeightedGraph<MapEntry<Integer, String>> gr = new WeightedGraph<MapEntry<Integer, String>>(50);
		
		// create data points  - in this case a list of MapEntries in which the key is an unique integer and the value is a decision point
		MapEntry<Integer, String> start = new MapEntry<Integer, String>(0, "HELP! I have a combination of foods! Do I need to make two brachos???");

		
		MapEntry<Integer, String> breadMeal = new MapEntry<Integer, String>(1, "I am eating this combination of foods during a bread meal (i.e. I washed for bread)");

		MapEntry<Integer, String> hasMezonos = new MapEntry<Integer, String>(2, "One of the foods in the combination has the bracha of mezonos");

		MapEntry<Integer, String> notMezonosOrBread = new MapEntry<Integer, String>(3, "This combination is not eaten as part of a bread meal and it does not include a mezonos food");

		MapEntry<Integer, String> mezonosExceptions = new MapEntry<Integer, String>(4, "The mezonos in the mixture is not a typical form of mezonos from the perspective of hilchos brachos. It is either: "
																					+ "\n\t\ta) only used to hold the food together (as a binding agent or a tasteless container (such as a tasteless ice cream cone) "
																					+ "\n\t\tb) a thin coating (such as the coating around schnitzel)"
																					+ "\n\t\tc) a liquid (such as beer) and is therefore not actually mezonos"
																					+ "\n\t(NOTE: If the above option applies, enter \"4\" only, not \"4a\" or \"4b\")\n");
		
		MapEntry<Integer, String> noMezonosExceptions = new MapEntry<Integer, String>(5, "Mixture includes mezonos to which none of the exceptions apply");

		MapEntry<Integer, String> mixture = new MapEntry<Integer, String>(6, "The combination of foods that I'm eating fits onto a spoon or fork (such as a salad)");
		
		MapEntry<Integer, String> enhancer = new MapEntry<Integer, String>(7, "One of the foods in the combination is for the SOLE purpose of enhancing the other or for the purpose " 
																			+ "of diminishing the strong taste of the other (such as cream cheese on a bagel)");
		
		MapEntry<Integer, String> notMixtureOrEnhancer = new MapEntry<Integer, String>(8, "Combination of food that cannot fit on a spoon or fork and neither food is for the SOLE purpose of enhancing "
																						+ "\n\t\tor toning down the strong flavor of the other.");
		
		MapEntry<Integer, String> noMezonos = new MapEntry<Integer, String>(9, "The mixture does not include mezonos");
		
		MapEntry<Integer, String> mezonosWithLiquid = new MapEntry<Integer, String>(10, "The mezonos is combined with a liquid (such as a matza ball in soup)");
		
		MapEntry<Integer, String> mezonosWithSolid = new MapEntry<Integer, String>(11, "The mezonos is mixed with a solid (Such as crackers and herring)");
		
		MapEntry<Integer, String> preference = new MapEntry<Integer, String>(12, "You prefer one of the foods over the other");
		
		MapEntry<Integer, String> noPreference = new MapEntry<Integer, String>(13, "You are equally interested in all the foods in the mixure");
		
		MapEntry<Integer, String> reasonableMainMezonos = new MapEntry<Integer, String>(14, "It makes sense to call the mezonos food a \"main\" food");
		
		MapEntry<Integer, String> unreasonableMainMezonos = new MapEntry<Integer, String>(15, "It does NOT make sense to call the mezonos food a \"main\" food (such as in the case of salad with "
																							+ "\n\t\tcroutons, where it does not make sense to call the croutons the main food)");
		
		MapEntry<Integer, String> majorityBracha = new MapEntry<Integer, String>(16, "There is a bracha that would cover the majority of the foods in the mixure (Such as in a salad in which most of the ingredients are "
																					+ "\n\t\thaadama and a minority are be haetz)");
		
		MapEntry<Integer, String> noMajorityBracha = new MapEntry<Integer, String>(17, "There is an equal representation of each bracha in the food group");
		
		MapEntry<Integer, String> hasInclusiveBracha = new MapEntry<Integer, String>(18, "There is a bracha that can cover all foods in the mixture b'dieved (e.g. if there is shehakol, that would also cover all other foods, "
																						+ "\n\t\tand haadama covers all haetz)");
		
		MapEntry<Integer, String> noInclusiveBracha = new MapEntry<Integer, String>(19, "There is no bracha that would cover all foods in the mixture b'dieved");
		
		MapEntry<Integer, String> redistributeMixture = new MapEntry<Integer, String>(20, "Redistribute the mixture by taking out or adding some of the food so there becomes a clear majority");
		
		MapEntry<Integer, String> noOneBracha = new MapEntry<Integer, String>(21, "No");
		
		MapEntry<Integer, String> yesTwoBrachos = new MapEntry<Integer, String>(22, "Yes - a seperate bracha for each food is required");
		
		
		// Add all vertices to graph
		gr.addVertex(start);
		gr.addVertex(mixture);
		gr.addVertex(enhancer);
		gr.addVertex(breadMeal);
		gr.addVertex(notMezonosOrBread);
		gr.addVertex(hasMezonos);
		gr.addVertex(noMezonos);
		gr.addVertex(notMixtureOrEnhancer);
		gr.addVertex(mezonosExceptions);
		gr.addVertex(noMezonosExceptions);
		gr.addVertex(mezonosWithLiquid);
		gr.addVertex(mezonosWithSolid);
		gr.addVertex(preference);
		gr.addVertex(noPreference);
		gr.addVertex(reasonableMainMezonos);
		gr.addVertex(unreasonableMainMezonos);
		gr.addVertex(majorityBracha);
		gr.addVertex(noMajorityBracha);
		gr.addVertex(hasInclusiveBracha);
		gr.addVertex(noInclusiveBracha);
		gr.addVertex(redistributeMixture);
		gr.addVertex(noOneBracha);
		gr.addVertex(yesTwoBrachos);
		
		
		
		// create all edges
		gr.addEdge(start, breadMeal, 1);
		gr.addEdge(start, hasMezonos, 1);
		gr.addEdge(start, notMezonosOrBread, 1);
		gr.addEdge(breadMeal, noOneBracha, 2);
		gr.addEdge(hasMezonos, mezonosExceptions, 1);
		gr.addEdge(hasMezonos, noMezonosExceptions, 1);
		gr.addEdge(notMezonosOrBread, mixture, 1);
		gr.addEdge(notMezonosOrBread, enhancer, 1);
		gr.addEdge(notMezonosOrBread, notMixtureOrEnhancer, 1);
		gr.addEdge(noMezonosExceptions, mezonosWithLiquid, 1);
		gr.addEdge(noMezonosExceptions, mezonosWithSolid, 1);
		gr.addEdge(mezonosExceptions, noOneBracha, 4);
		gr.addEdge(mixture, preference, 1);
		gr.addEdge(mixture, noPreference, 1);
		gr.addEdge(enhancer, noOneBracha, 7);
		gr.addEdge(notMixtureOrEnhancer, yesTwoBrachos, 1);
		gr.addEdge(mezonosWithLiquid, yesTwoBrachos, 1);
		gr.addEdge(mezonosWithSolid, reasonableMainMezonos, 1);
		gr.addEdge(mezonosWithSolid, unreasonableMainMezonos, 1);
		gr.addEdge(reasonableMainMezonos, noOneBracha, 3);
		gr.addEdge(unreasonableMainMezonos, yesTwoBrachos, 1);
		gr.addEdge(preference, noOneBracha, 5);
		gr.addEdge(noPreference, majorityBracha, 1);
		gr.addEdge(noPreference, noMajorityBracha, 1);
		gr.addEdge(majorityBracha, noOneBracha, 6);
		gr.addEdge(noMajorityBracha, hasInclusiveBracha, 1);
		gr.addEdge(noMajorityBracha, noInclusiveBracha, 1);
		gr.addEdge(hasInclusiveBracha, noOneBracha, 8);
		gr.addEdge(noInclusiveBracha, redistributeMixture, 1);
		gr.addEdge(redistributeMixture, noOneBracha, 6);

		
				
		
		HashMap<Integer, String> finalVerdicts = new HashMap<Integer, String>(10);
		finalVerdicts.put(2, "Make a hamotzi");
		finalVerdicts.put(3, "Make a mezonos");
		finalVerdicts.put(4, "Make the bracha of the other (non-mezonos) food");
		finalVerdicts.put(5, "Make the bracha of the preferred food");
		finalVerdicts.put(6, "Make the bracha that covers the majority of the mixture");
		finalVerdicts.put(7, "Make the bracha of the food that is being enhanced");
		finalVerdicts.put(8, "Make the inclusive bracha that would cover all foods b'dieved");

		
		// create Scanner
		Scanner scan = new Scanner(System.in);
		
		// Create linked list to hold adjacencies of vertices
		Queue<MapEntry<Integer, String>> adjacencies = new LinkedList<MapEntry<Integer, String>>();
		
		// initialize arraylist of keys for later use
		ArrayList<Integer> keys = new ArrayList<Integer>();;
		
		//set current vertex to start vertex
		MapEntry<Integer, String> current = start;
		
		// print start start vertex's value
		System.out.println(current.value + "\n");

		// set adjacencies queue to adjacent vertices of the start vertex
		adjacencies = gr.goFromVertex(current);
					
		// while the adjacencies queue does not hold the final verdict, keep traveling through the graph
		while (!adjacencies.peek().equals(noOneBracha) && !adjacencies.peek().equals(yesTwoBrachos)) {
			
			// display each adjacency, and meanwhile save keys to an array
			for (MapEntry<Integer, String> entry : adjacencies) {
			    System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
			    keys.add(entry.key);
			}
			System.out.println();
			
			// As user to choose next vertex to travel through
			System.out.println("Enter number corresponding to correct option");
			int menuOpt = scan.nextInt();
			
			while (!keys.contains(menuOpt)) {
				System.out.println("\nInvalid option. Please retry.");
				menuOpt = scan.nextInt();
			}
			
			// go through all adjacencies
			for (MapEntry<Integer, String> entry : adjacencies) {
				if (entry.key.equals(menuOpt)) {
					// set the current vertex to the chosen vertex
					current = entry;
					// clear adjacency queue and keys list
					adjacencies.clear();
					keys.clear();
					// and stop searching through adjacencies
					break;
				} 
			}
			
			// set adjacencies queue to adjacent vertices of the start vertex
			adjacencies = gr.goFromVertex(current);
			
		}
		
		// now print final verdict
		MapEntry<Integer, String> finalAnswer = adjacencies.poll();
		
		// if the final verdict has a weight other than 1, print further explanation
		// that is stored in a hash table with the key corresponding to the weight of the final edge
	    System.out.print("ANSWER: " + finalAnswer.getValue());
	    int weight = gr.weightIs(current, finalAnswer);
	    if (gr.weightIs(current, finalAnswer) != 1) {
	    	System.out.print(" - " + finalVerdicts.get(weight));
	    }
	    System.out.println();
		
	    
	    System.out.println("\n\n\nCREDIT: https://www.torahdesigns.com/halachos-of-ikar-vtofel-chart/ (Rabbi Eliezer Krohn)");

	    //close scanner
	    scan.close();
	}
}
