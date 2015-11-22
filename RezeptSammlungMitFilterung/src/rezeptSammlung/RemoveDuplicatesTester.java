package rezeptSammlung;

import java.util.ArrayList;

public class RemoveDuplicatesTester {

	public static void main(String[] args) {
		
		ArrayList<String> zutatenJa = new ArrayList<>();
		zutatenJa.add("Zwiebel");
		zutatenJa.add("Zwiebel");
		zutatenJa.add("Rindssuppe");
		System.out.println("ArrayList vor Remove Duplicates " + zutatenJa);
		removeDuplicates(zutatenJa);
		System.out.println("ArrayList nach Remove Duplicates " + zutatenJa);
	}
	
	public static int removeDuplicates(ArrayList<String> strings) {

	    int size = strings.size();
	    int duplicates = 0;

	    // not using a method in the check also speeds up the execution
	    // also i must be less that size-1 so that j doesn't
	    // throw IndexOutOfBoundsException
	    for (int i = 0; i < size - 1; i++) {
	        // start from the next item after strings[i]
	        // since the ones before are checked
	        for (int j = i + 1; j < size; j++) {
	            // no need for if ( i == j ) here
	            if (!strings.get(j).equals(strings.get(i)))
	                continue;
	            duplicates++;
	            strings.remove(j);
	            // decrease j because the array got re-indexed
	            j--;
	            // decrease the size of the array
	            size--;
	        } // for j
	    } // for i

	    return duplicates;

	}

}
