
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesimulation;

/**
 *
 * @author Jake Heyser
 */

//MIN HEAP
class EventHeap{
    
   Event[] eventHeap =  new Event[5000];  
   //capacity of 5000
   private int currentSize = 0;
    

   /**
    * Returns the current number of events in the heap
    * @return the current number of events in the heap
    */
    public int getSize() { 
        return currentSize;
    }

    /**
     * Removes and returns the event at the top of the heap
     * 
     * @return the Event that was removed from the top of the heap
     */
    public Event remove() {
        if (currentSize == 1){ //if there's only one event in the heap; want to just remove eventHeap[0]
            Event temp = eventHeap[0]; //the Event we want to return
            currentSize--; //decrementing currentSize
            return temp;
        }
        if (currentSize > 1){ //if there's more than one event in the heap
            Event temp = eventHeap[0]; //the root we want to delete from the heap
            eventHeap[0] = eventHeap[currentSize - 1]; //swapping the root with the last element in the heap
            eventHeap[currentSize - 1] = temp;
            currentSize--; //deleting the original root
            Event temp2 = eventHeap[0]; //the Event we need to find the right place for in the heap
            int index = 0;
            while (2 * index + 1 < currentSize){ //while temp2 has at least one child
                if ((2 * index) + 2 < currentSize){ //temp2 has 2 children
                    if ((eventHeap[(2 * index) + 1].isFirst(temp2)) || (eventHeap[(2 * index) + 2].isFirst(temp2))){ //if a swap needs to be made
                        if ((eventHeap[(2 * index) + 1].isFirst(temp2)) && (eventHeap[(2 * index) + 2].isFirst(temp2))){ //both children have a smaller time than temp2
                            //need to find the smaller time between the two children
                            if (eventHeap[2 * index + 2].isFirst(eventHeap[(2 * index) + 1])){ //if (the right child has a time < left child
                                //need to swap temp2 with its right child
                                eventHeap[index] = eventHeap[2 * index + 2];
                                eventHeap[2 * index + 2] = temp2;
                                index = (2 * index) + 2;
                            }
                            else{
                                //need to swap temp2 with its left child
                                eventHeap[index] = eventHeap[2 * index + 1];
                                eventHeap[2 * index + 1] = temp2;
                                index = (2 * index) + 1;
                                
                            }
                        }
                        else{ //only one child has a smaller time than temp2
                            if ((eventHeap[(2 * index) + 1].isFirst(temp2))){ //if temp2's left child has a smaller time than temp2
                                //need to swap temp2 with its left child
                                eventHeap[index] = eventHeap[2 * index + 1];
                                eventHeap[2 * index + 1] = temp2;
                                index = (2 * index) + 1;                     
                            }
                            else{ //temp2's right child has a smaller time than temp2
                                //need to swap temp2 with its right child
                                eventHeap[index] = eventHeap[2 * index + 2];
                                eventHeap[2 * index + 2] = temp2;
                                index = (2 * index) + 2;                           
                            }
                        }
                    }
                    else{
                        break; //no swap needs to be made; temp2 is in the right spot in the heap
                    }
                }
                else{ //temp2 only has one child
                    if ((eventHeap[(2 * index) + 1].isFirst(temp2))){ //if temp2's left child has a smaller time than temp2
                        //need to swap temp2 with its left child and break the loop
                        eventHeap[index] = eventHeap[2 * index + 1];
                        eventHeap[2 * index + 1] = temp2;
                        break; //temp2 has no children to swap with at this point
                    }
                    else{ //temp2 is in the correct spot in the heap; we can break the loop
                        break;
                    }
                }
            }
            return temp; //return the original root we deleted
        }
        return null; //if currentSize == 0
    }
        
    /**
     * Insert an event into the heap. Events are ordered based on the time of the event.
     * @param item 
     */
    public void insert(Event item) {
        if (currentSize == 0){ //if the heap is empty
            //we don't need to worry about rearranging the Events; we can just insert at the root
            eventHeap[0] = item; 
            currentSize++;
        }
        else{
            //need to insert item at the right spot in eventHeap to make the tree complete, which is at eventHeap[currentSize]
            //item must have a parent
            eventHeap[currentSize] = item; 
            currentSize++;
            int index = currentSize - 1; //the index of item
            //need to make any necessary swaps up the heap to satisfy the properties of a min heap
            while (true){
                Event e = eventHeap[((index - 1)/2)]; //the parent of item
                if (e.getEventTime() > item.getEventTime()){ //need to swap item and e
                    eventHeap[index] = e;
                    eventHeap[(index - 1)/2] = item;
                    index = (index - 1)/2;
                    if (index == 0){ //if item is the root, we are done
                        break;
                    } 
                }
                else{ //no swaps are needed anymore
                    break;        
                }
            }
        }
    }
    
    /**
     * Method that returns the Event on top of the heap without removing it
     * 
     * @return the event on top of the heap
     */
    public Event peak(){
        if (currentSize != 0){ //want to return the Event at index 0 in the heap
            return eventHeap[0]; 
        }
        return null; //the heap is empty
    }
          
   /**
    * Method that returns a String representing the EventHeap
    * 
    * @return a String representing the eventHeap
    */
    public String toString(){
        String s = "[";
        for (int i = 0; i < currentSize; i++) { //looping through the EventHeap
            s = s + eventHeap[i] + " ";
        }
        return s + "]"; //returning the final String
    }  
}