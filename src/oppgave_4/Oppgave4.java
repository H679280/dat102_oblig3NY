package oppgave_4;

import java.util.Arrays;
import java.util.HashSet;

public class Oppgave4 {

	public static void main(String[] args) {
		int antallElementer = 100000;
		int tall = 89;
		int antall_sok = 10_000;
		int soketall = 1049;

		HashSet<Integer> hashSet = new HashSet<>();
		int[] tabell = new int[antallElementer];

		for (int i = 0; i < antallElementer; i++) {
			tall = (tall + 45713) % 1000000;
			tabell[i] = tall;
			hashSet.add(tall);

		}

		Arrays.sort(tabell);
		int antallitabell = 0;
		int antalliHashSet = 0;

		long tidforHashSet = System.nanoTime();

		// for (int i = 0; i < antall_sok; i++) {
		if (hashSet.contains(soketall)) {
			antalliHashSet++;
		}

		long tidEtterHashset = System.nanoTime();

		for (int i = 0; i < antall_sok; i++) {
			if (Arrays.binarySearch(tabell, soketall) >= 0) {
				antallitabell++;
			}

		}
		long tidEtterTabell = System.nanoTime();

		System.out.println("tid brukt for HashSet: " + (tidEtterHashset - tidforHashSet) / 1e6 + "ms");
		System.out.println("Antall treff: " + antalliHashSet);
		System.out.println("tid brukt for Tabell: " + (tidEtterTabell - tidEtterHashset) / 1e6 + "ms");
		System.out.println("Antall treff: " + antallitabell);

	}

	
}
