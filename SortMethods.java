/**
 *    SortMethods - Has various methods to sorts an array of Cities 
 * 	  in an order specified by a comparator.
 *
 *    @author	Alex Chen
 *    @since	Janurary 11, 2023
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class SortMethods {
    
    /**
     *    Bubble Sort algorithm 
     *    @param arr        List of City objects to sort
     *    @param cmp        a comparator describing the ordering to sort the cities.
     */
    public void bubbleSort(List<City> arr, Comparator <City> cmp) {
        for(int outer = arr.size() - 1; outer > 0; outer--){
            for(int inner = 0; inner < outer; inner++){
                if(cmp.compare(arr.get(inner), (arr.get(inner+1))) > 0){
                    swap(arr, inner, inner+1);
                }
            }
        }
    }
    
    /**
     *    Swaps two City objects in list arr
     *    @param arr      List of City objects to sort
     *    @param x        index of first object to swap
     *    @param y        index of second object to swap
     */
    private void swap(List<City> arr, int x, int y) {
        City temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }
    
    /**
     *    Selection Sort algorithm 
     *    @param arr        List of City objects to sort
     *    @param cmp        a comparator describing the ordering to sort the cities.
     */
    public void selectionSort(List<City> arr, Comparator <City> cmp) {
        for(int outer = arr.size() - 1; outer > 0; outer--){
            int largElem = 0;
            for(int inner = 1; inner <= outer; inner++){
                if(cmp.compare(arr.get(inner), arr.get(largElem)) > 0){
                    largElem = inner;
                }
            }
            swap(arr, largElem, outer);
        }
    }
    
    /**
     *    Insertion Sort algorithm
     *    @param arr        List of City objects to sort
     *    @param cmp        a comparator describing the ordering to sort the cities.
     */
    public void insertionSort(List<City> arr, Comparator <City> cmp) {
        for(int outer = 1; outer <= arr.size() - 1; outer++){
            int insertionPos = outer;
            while(insertionPos != 0 && 
				cmp.compare(arr.get(insertionPos-1), arr.get(outer)) > 0){
                insertionPos--;
            }
            for(int inner = outer; inner > insertionPos; inner--){
                swap(arr, inner, inner-1);
            }
        }
    }
    
    /**
     *    Merge Sort algorithm - in ascending order (you implement)
     *    @param arr        List of City objects to sort
     *    @param cmp        a comparator describing the ordering to sort the cities.
     */
    public void mergeSort(List<City> arr, Comparator <City> cmp) {
        List<City> auxSpace = new ArrayList<City>();
        for(int i = 0; i < arr.size(); i++){
            auxSpace.add(null);
        }
        recCall(arr, auxSpace, cmp, 0, arr.size() - 1);
    }
        
    /**
     *     This is a helper method of merge sort. It does a recursive call,
     *     keeping track of the left bound and right bound. It splits if
     *     there are > 2 elements in our range.
     *
     *     @param   arr           List of City objects to sort
     *     @param   auxSpace      an array of auxilliary space for the merge method to use
     *     @param   cmp           a comparator describing the ordering to sort the cities.
     *     @param   leftBound     a left bound on our range
     *     @param   rightBound    a right bound on our range
     */
    public void recCall(List<City> arr, List<City> auxSpace, 
		Comparator <City> cmp, int leftBound, int rightBound){
			
        int length = rightBound - leftBound + 1;
        if(length == 1){//Size 1
            return;
        }else if(length == 2){//Size 2
            if(cmp.compare(arr.get(leftBound), arr.get(rightBound)) > 0){
                swap(arr, leftBound, rightBound);
            }
        }else{
            int mid = (leftBound+rightBound)/2;
            recCall(arr, auxSpace, cmp, leftBound, mid);
            recCall(arr, auxSpace, cmp, mid+1, rightBound);
            merge(arr, auxSpace, cmp, leftBound, mid, mid+1, rightBound);
        }
    }
    
    
    /**
     * This method merges two already sorted ranges.
     * It uses some auxilliary space.
     *
     *  Precondition: The two ranges are already sorted.
     *  The first range is to the left of the second, and the last element
     *  of the first range is next to the first element of the second
     *  (rb1 + 1 = lb2)
     *
     *  @param  arr            List of City objects to sort
     *  @param  auxSpace       an array of auxilliary space
     *  @param  cmp            a comparator describing the ordering to sort the cities.
     *  @param  lb1            the left bound of the first range
     *  @param  rb1            the right bound of the first range
     *  @param  lb2            the left bound of the second range
     *  @param  rb2            the right bound of the second range
     */
    public void merge(List<City> arr, List<City> auxSpace, 
		Comparator <City> cmp, int lb1, int rb1, int lb2, int rb2){
        
        for(int i = lb1; i <= rb2; i++){
            auxSpace.set(i, arr.get(i));
        }
        int ptr1 = lb1;
        int ptr2 = lb2;
        int pos = lb1;
        while(pos != (rb2 + 1)){
            if(ptr1 == rb1+1){
                arr.set(pos, auxSpace.get(ptr2));
                ptr2++;
            }else if(ptr2 == rb2+1){
                arr.set(pos, auxSpace.get(ptr1));
                ptr1++;
            }else{
                if(cmp.compare(auxSpace.get(ptr2),auxSpace.get(ptr1)) > 0){
                    arr.set(pos, auxSpace.get(ptr1));
                    ptr1++;
                }else{
                    arr.set(pos, auxSpace.get(ptr2));
                    ptr2++;
                }
            }
            pos++;
        }
    }
}

