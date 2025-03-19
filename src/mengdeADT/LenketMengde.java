package mengdeADT;


public class LenketMengde<T> implements MengdeADT<T> {
	public static class Node<T> {
		T data;
		Node<T> neste;

		Node(T data) {
			this.data = data;
			this.neste = null;
		}
	}

	private Node<T> start;
	private int antall;

	public LenketMengde() {
		start = null;
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (aktuell.data.equals(element)) {
				return true;
			}
			aktuell = aktuell.neste;
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (!annenMengde.inneholder(aktuell.data)) {
				return false;
			}
			aktuell = aktuell.neste;
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (annenMengde.inneholder(aktuell.data)) {
				return false;
			}
			aktuell = aktuell.neste;
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		LenketMengde<T> snittMengde = new LenketMengde<>();
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (annenMengde.inneholder(aktuell.data)) {
				snittMengde.leggTil(aktuell.data);
			}
			aktuell = aktuell.neste;
		}
		return snittMengde;
	}

		@Override
		public MengdeADT<T> union(MengdeADT<T> annenMengde) {
			LenketMengde<T> unionMengde = new LenketMengde<>();
			Node<T> aktuell = start;
			while (aktuell != null) {
				unionMengde.leggTil(aktuell.data);
				aktuell = aktuell.neste;
			}
			unionMengde.leggTilAlleFra(annenMengde);
			return unionMengde;
		}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		LenketMengde<T> differanseMengde = new LenketMengde<>();
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (!annenMengde.inneholder(aktuell.data)) {
				differanseMengde.leggTil(aktuell.data);
			}
			aktuell = aktuell.neste;
		}
		return differanseMengde;
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			Node<T> nyNode = new Node<>(element);
			nyNode.neste = start;
			start = nyNode;
			antall++;
		}
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		for (T element : annenMengde.tilTabell()) {
			leggTil(element);
		}
	}

	@Override
	public T fjern(T element) {
		if (erTom())
			return null;

		if (start.data.equals(element)) {
			T fjernetData = start.data;
			start = start.neste;
			antall--;
			return fjernetData;
		}

		Node<T> aktuell = start;
		while (aktuell.neste != null) {
			if (aktuell.neste.data.equals(element)) {
				T fjernetData = aktuell.neste.data;
				aktuell.neste = aktuell.neste.neste;
				antall--;
				return fjernetData;
			}
			aktuell = aktuell.neste;
		}

		return null;
	}

	@Override
	public T[] tilTabell() {
		@SuppressWarnings("unchecked")
		T[] tabell = (T[]) java.lang.reflect.Array.newInstance(start.data.getClass(), antall);
		Node<T> aktuell = start;
		int i = 0;
		while (aktuell != null) {
			tabell[i++] = aktuell.data;
			aktuell = aktuell.neste;
		}
		return tabell;
	}

	@Override
	public int antallElementer() {
		return antall;
	}
}
