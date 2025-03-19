package oppgave_c;

import java.util.HashSet;
import java.util.Set;

import mengdeADT.MengdeADT;

public class JavaSetToMengde<T> implements MengdeADT<T> {
	
	private Set<T> mengde;

    public JavaSetToMengde() {
        this.mengde = new HashSet<>();
    }
	
	@Override
	public boolean erTom() {
		return mengde.isEmpty();
	}
	
  
    @Override
    public boolean inneholder(T element) {
    	return mengde.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
    	
    	for (T element : mengde) {
            if (!annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
    	return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
    	for (T element : mengde) {
            if (annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
    	JavaSetToMengde<T> snittMengde = new JavaSetToMengde<>();
        for (T element : mengde) {
            if (annenMengde.inneholder(element)) {
                snittMengde.leggTil(element);
            }
        }
        return snittMengde;
        
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
    	
    	T[] elementer = annenMengde.tilTabell();
    	JavaSetToMengde<T> unionMengde = new JavaSetToMengde<>();
        unionMengde.mengde.addAll(this.mengde);
        
        for (int i = 0; i<elementer.length;i++) {
        	unionMengde.leggTil(elementer[i]);
        }
        return unionMengde;
        
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
    	JavaSetToMengde<T> differensMengde = new JavaSetToMengde<>();
        for (T element : mengde) {
            if (!annenMengde.inneholder(element)) {
                differensMengde.leggTil(element);
            }
        }
        return differensMengde;
    }

    @Override
    public void leggTil(T element) {
    	mengde.add(element);
    }
    
    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
    	T[] elementer = annenMengde.tilTabell();
    	for (int i = 0; i < elementer.length; i++) {
    		leggTil(elementer[i]);
    	} 
    }


    @Override
    public T fjern(T element) {
    	if (mengde.remove(element)) {
            return element;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        return mengde.toArray((T[]) new Integer[mengde.size()]); 
    }


    @Override
    public int antallElementer() {
    	return mengde.size();
    }

}
