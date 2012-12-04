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
public class AllCombinationTeamsHelper {
    private int n;
    private int[] permutation;
    private int[] invPermutation;
    private int maxMobileValue;
    private int maxMobileIndex;
    private boolean mobileElementExists = true;
    private List list;
    
    public List buildPermutationList(int num){
        list = new ArrayList();          
        n = num;
        permutation = new int[n];
        invPermutation = new int[n];
        
        for (int i = 0; i < n; i++) {
            permutation[i] = i+1;
            invPermutation[i] = -1;
        } //for  
        maxMobileValue = n;
        maxMobileIndex = n-1;
        
        addCombinationToList(); 
        while (mobileElementExists) {
            //do the number swap
            swap();
            //add the new permutation to list
            addCombinationToList();
            //do the next swap, if any
            mobileElementExists = false;
            maxMobileValue = 0;
            maxMobileIndex = -1;
            for (int i = 0; i < n; i++) { 
                if (isMobile(i)) {
                    mobileElementExists = true;
                    if (permutation[i] > maxMobileValue) {
                        maxMobileValue = permutation[i];
                        maxMobileIndex = i;
                    }
                }
            }
        }               
        return list;
    }
    
    /**
     * Is Mobile?
     * @param i
     * @return 
     */
    private boolean isMobile(int i){
        return ((i > 0) &&   (invPermutation[i] == -1) && (permutation[i] > permutation[i-1])) || 
                ((i < n-1) && (invPermutation[i] == 1)  && (permutation[i] > permutation[i+1]));
    }
    
    /**
     * Swap numbers in a combination
     */
    private void swap() {
        int neighbour = maxMobileIndex + invPermutation[maxMobileIndex];
        permutation[maxMobileIndex] = permutation[neighbour];
        permutation[neighbour] = maxMobileValue;
        int temp = invPermutation[maxMobileIndex];
        invPermutation[maxMobileIndex] = invPermutation[neighbour];
        invPermutation[neighbour] = temp;
        for (int i = 0; i < n; i++) {
            if (permutation[i] > maxMobileValue) {
                invPermutation[i] *= -1;
            }
        }
    }
    
    /**
     * Add number combination to list
     */    
    private void addCombinationToList() {
        int[] newPermutation = new int[n]; 
        System.arraycopy(permutation, 0, newPermutation, 0, n);
        list.add(newPermutation);
    }
}
