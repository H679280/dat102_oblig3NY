package mengdeADT;


public final class TabellMengde1<T> implements MengdeADT<T> {

private static final int DEFAULT_KAPASITET = 10;

	private T[] tab;
	private int antall;

	public TabellMengde1() {
		this(DEFAULT_KAPASITET);
	}

	@SuppressWarnings("unchecked")
	public TabellMengde1(int kapasitet) {
		tab = (T[]) new Object[kapasitet];
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		for (int i = 0; i < antall; i++) {
			if (tab[i] == element) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		boolean svar = true;
		int i = 0;
		do {
			svar = annenMengde.inneholder(tab[i]);
			i++;
		} while (svar);

		return svar;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);

	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tab[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		TabellMengde1<T> snittMengde = new TabellMengde1<>();
		
		for (int i = 0; i<antall; i++) {
			if(annenMengde.inneholder(tab[i])) {
				snittMengde.leggTil(tab[i]);
			}
		}
		return null;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		TabellMengde1<T> uMengde = new TabellMengde1<>();
		uMengde.leggTilAlleFra(annenMengde);
		for (int i = 0; i < antall; i++) {
			uMengde.leggTil(tab[i]);

		}
		return null;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		TabellMengde1<T> mMengde = new TabellMengde1<>();

		int i = 0;
		while (i != antall) {
			if (!annenMengde.inneholder(tab[i])) {
				mMengde.leggTil(tab[i]);
			}
			i++;
		}
		return mMengde;

	}

	@Override
	public void leggTil(T element) {
		tab[antall] = element;
		antall++;

	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		int lengde = annenMengde.antallElementer();
		for (int i = 0; i < lengde; i++) {
			T[] annenTab = annenMengde.tilTabell();
			leggTil(annenTab[i]);
		}

	}

	@Override
	public T fjern(T element) {
		if (erTom()) {
			return null;
		}
		element = tab[antall - 1];
		tab[antall - 1] = null;
		antall--;
		return element;
	}

	@Override
	public T[] tilTabell() {
		return tab;
	}

	@Override
	public int antallElementer() {
		return antall;

	}

}