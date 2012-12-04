/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;


import java.util.*;

/**
 *
 * @author Lam
 */

class PermutationIterator <T> implements Iterator <List <T>> {

    private int  current = 0;
    private final long last;
    private final List <T> lilio;

    public PermutationIterator (final List <T> llo) {
        lilio = llo;
        long product = 1;
        for (long p = 1; p <= llo.size (); ++p) {
            product *= p;
        } 
        last = product;
    }

    @Override
    public boolean hasNext () {
        return current != last;
    }

    @Override
    public List <T> next () {
        ++current;
        return get (current - 1, lilio);
    }

    @Override
    public void remove () {
        ++current;
    }

    private List <T> get (final int code, final List <T> li) {
        int len = li.size ();
        int pos = code % len;
        if (len > 1) {
            List <T> rest = get (code / len, li.subList (1, li.size ()));
            List <T> a = rest.subList (0, pos);
            List <T> res = new ArrayList <T> ();
            res.addAll (a);
            res.add (li.get (0));
            res.addAll (rest.subList (pos, rest.size ()));
            return res;
        }
        return li;
    }
}

class PermutationIterable <T> implements Iterable <List <T>> {

    private List <T> lilio; 

    public PermutationIterable (List <T> llo) {
        lilio = llo;
    }

    @Override
    public Iterator <List <T>> iterator () {
        return new PermutationIterator <T> (lilio);
    }
}
