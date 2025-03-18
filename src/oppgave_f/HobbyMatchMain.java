package oppgave_f;

import java.util.HashSet;
import java.util.Set;

public class HobbyMatchMain {

	public static void main(String[] args) {

		// Oppretter personer med hobbyer
        Person arne = new Person("Arne", "jakt", "sykling", "venner", "data");
        Person bente = new Person("Bente", "sykling", "data", "musikk", "lesing");
        Person carl = new Person("Carl", "fotball", "gaming", "venner", "bøker");

        // Beregner match-score mellom personer
        double matchArneBente = match(arne, bente);
        double matchArneCarl = match(arne, carl);
        double matchBenteCarl = match(bente, carl);

        // Skriver ut resultatene
        System.out.println("Match mellom Arne og Bente: " + matchArneBente);
        System.out.println("Match mellom Arne og Carl: " + matchArneCarl);
        System.out.println("Match mellom Bente og Carl: " + matchBenteCarl);

        // Finne de to som matcher best
        if (matchArneBente >= matchArneCarl && matchArneBente >= matchBenteCarl) {
            System.out.println("Beste match: Arne og Bente");
        } else if (matchArneCarl >= matchArneBente && matchArneCarl >= matchBenteCarl) {
            System.out.println("Beste match: Arne og Carl");
        } else {
            System.out.println("Beste match: Bente og Carl");
        }
        
	}
	
	
	
	public static double match(Person a, Person b) {
        Set<String> felles = new HashSet<>(a.getHobbyer());
        felles.retainAll(b.getHobbyer()); // Snittet av hobbyene

        Set<String> kunHosA = new HashSet<>(a.getHobbyer());
        kunHosA.removeAll(b.getHobbyer()); // Hobbyer bare hos A

        Set<String> kunHosB = new HashSet<>(b.getHobbyer());
        kunHosB.removeAll(a.getHobbyer()); // Hobbyer bare hos B

        Set<String> totalt = new HashSet<>(a.getHobbyer());
        totalt.addAll(b.getHobbyer()); // Unionen av hobbyene

        if (totalt.isEmpty()) return 0.0; // Unngå deling på null

        return (double) (felles.size() - (kunHosA.size() + kunHosB.size())) / totalt.size();
    }

}
