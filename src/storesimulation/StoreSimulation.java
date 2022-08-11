/**
 * Store Simulation Project
 * This file controls the flow of the store simulation.
 */
package storesimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jake Heyser 
 */
public class StoreSimulation {

    private static final int NUMBER_STANDARD_CHECKOUT = 6; // number of cashier registers
    private static final int NUMBER_SELF_CHECKOUTS = 4; // number of self-scan registers
    private static double simClock = 0; // elapsed time (minutes)
    private static EventHeap events = new EventHeap(); // events that occur in the store
    private static ArrayList<Register> registers = new ArrayList(); // registers used in the store

    public static void main(String[] args) {
        testHeap();
        testRegister();
        startSimulation(); //starts the simulation
    }
    
    /**
     * Method that tests the EventHeap class
     */
    public static void testHeap(){
        EventHeap h = new EventHeap();
        
        //testing the heap's constructor and insert(Event e), remove(), getSize() and peak() methods
        
        //testing insert
        System.out.println("Testing EventHeap Class:");
        System.out.println();
        Event e1 = new Event(null, 23.3, EventType.ARRIVAL);
        Event e2 = new Event(null, 11.65, EventType.ARRIVAL);
        System.out.println("Testing constructor:");
        System.out.println("Heap should be: []; the heap is: " + h.toString());
        System.out.println();
        h.insert(e1);
        h.insert(e2);
        System.out.println("Two-element insert test:");
        System.out.println("Heap should be: [[null, 11.65, ARRIVAL] [null, 23.3, ARRIVAL]]; the heap is: " + h.toString()) ;
        System.out.println("Heap's size should be 2; the heap's size is: " + h.getSize());
        System.out.println("The root of the heap should be 11.65; peak() says it is " + h.peak().getEventTime());
        System.out.println();
        
        //testing insert with more than three events (seeing if the swapping is done correctly)
        System.out.println("Four-element insert test:");
        Event e3 = new Event(null, 12.75, EventType.ARRIVAL);
        Event e4 = new Event(null, 9.67, EventType.ARRIVAL);
        h.insert(e3);
        h.insert(e4);
        System.out.println("Heap should be: [[null, 9.67, ARRIVAL] [null, 11.65, ARRIVAL] [null, 12.75, ARRIVAL]"
                + " [null, 23.3, ARRIVAL]]; the heap is: " + h.toString());
        System.out.println("Heap's size should be 4; the heap's size is: " + h.getSize());
        System.out.println("The root of the heap should be 9.67; peak() says it is " + h.peak().getEventTime());
        System.out.println();
        
        //testing remove after insert
        System.out.println("Remove after insert: test 1");
        Event ev = h.remove();
        System.out.println("Heap should be: [[null, 11.65, ARRIVAL] [null, 23.3, ARRIVAL] [null, 12.75]]; the "
                + "heap is: " + h.toString());
        System.out.println("The heap should have removed [null, 9.67, ARRIVAL]; the heap removed " + ev.toString());
        System.out.println("Heap's size should be 3; the heap's size is: " + h.getSize());
        System.out.println("The root of the heap should be 11.65; peak() says it is " + h.peak().getEventTime());
        System.out.println();        
        
        //testing remove after insert a second time
        System.out.println("Remove after insert: test 2");
        Event ev1 = h.remove();
        Event ev2 = h.remove();
        System.out.println("Heap should be: [[null, 23.3, ARRIVAL]]; the heap is: " + h.toString());
        System.out.println("The heap should have removed [null, 11.65, ARRIVAL] and [null, 12.75, ARRIVAL]; the "
                + "heap removed " + ev1.toString() + " and " + ev2.toString());
        System.out.println("Heap's size should be 1; the heap's size is: " + h.getSize());
        System.out.println("The root of the heap should be 23.3; peak() says it is " + h.peak().getEventTime());
        System.out.println();
        
        //testing remove when there is only one element to remove
        System.out.println("Remove after insert: test 3");
        Event ev5 = h.remove();
        System.out.println("Heap should be: []; the heap is: " + h.toString());
        System.out.println("Th heap should have removed [null, 23.3, ARRIVAL]; the heap removed " + ev5.toString());
        System.out.println("Heap's size should be 0; the heap's size is: " + h.getSize());
        System.out.println();
        
        //testing remove when there are no elements in the heap to remove
        System.out.println("Testing remove when there is nothing in the heap to remove:");
        h.remove();
        System.out.println("Heap should be: []; the heap is: " + h.toString());
        System.out.println("Heap's size should be 0; the heap's size is: " + h.getSize());
        System.out.println();
        
        //testing insert when an Event of the same time as the root is inserted
        System.out.println("Insert test 3:");
        Event e6 = new Event(null, 11.65, EventType.ARRIVAL);
        Event e7 = new Event(null, 9.75, EventType.ARRIVAL);
        Event e8 = new Event(null, 13.1, EventType.ARRIVAL);
        Event e9 = new Event(null, 9.75, EventType.ARRIVAL);
        h.insert(e6);
        h.insert(e7);
        h.insert(e8);
        h.insert(e9);
        System.out.println("Heap should be: [[null, 9.75, ARRIVAL] [null, 9.75, ARRIVAL] [null, 13.1, ARRIVAL] "
                + "[null, 11.65, ARRIVAL]]; the heap is: " + h.toString());
        System.out.println("Heap's size should be 4; the heap's size is: " + h.getSize());
        System.out.println("The root of the heap should be 9.75; peak() says it is " + h.peak().getEventTime());  
        System.out.println();
        System.out.println();
    }
    
    /**
     * Method that tests the Register class
     */
    public static void testRegister(){
        Register r = new Register(.015, 1.5);
        
        //testing the register's constructor and add(Customer c), remove(), and getLineLength() methods
        
        //testing constructor
        System.out.println("Testing Register class:");
        System.out.println();
        System.out.println("Testing constructor:");
        System.out.println("Register line should be: []; the register line is " + r.toString());
        System.out.println();
        
        //testing add
        System.out.println("Testing add:");
        Customer c1 = new Customer(0, 10, 25);
        Customer c2 = new Customer(0, 25, 25);
        r.add(c1);
        r.add(c2);
        System.out.println("Register line should be: [[0.0, 10, 25.0] [0.0, 25, 25.0]]; the register line is: " + r.toString());
        System.out.println("Register's size should be 2; the register's size is " + r.getLineLength());
        System.out.println();
        
        //testing remove after insert
        System.out.println("Testing remove after add:");
        r.remove();
        System.out.println("Register line should be: [[[0.0, 25, 25.0]]; the register line is: " + r.toString());
        System.out.println("Register's size should be 1; the register's size is " + r.getLineLength());
        System.out.println();
        
        //testing remove when there is nobody in line to remove
        System.out.println("Testing remove when there is nobody in line:");
        r.remove();
        r.remove();
        System.out.println("Register line should be: []; the register line is: " + r.toString());
        System.out.println("Register's size should be 0; the register's size is " + r.getLineLength());
        System.out.println();
        System.out.println();
    }
    
    public static void startSimulation(){
        
        loadRegisters(); //load registers
        loadCustomerData();

        // Events are stored in a priority queue, so they will always be returned in order.
        while (events.getSize() > 0) {
            Event e = events.remove();
            simClock = e.getEventTime(); // Always set the clock to the time of the new event.
            if (e.getEventType() == EventType.ARRIVAL) {
                handleArrival(e);
            } else if (e.getEventType() == EventType.END_SHOPPING) {
                handleEndShopping(e);
            } else {
                handleEndCheckout(e);
            }
        }// end while
        
        printCollectedStatistics();
    }
   
    /**
     * Method that loads all the Registers into registers
     */
    private static void loadRegisters() {
        //A loop for creatign standard checkous
        for (int i = 0; i < NUMBER_STANDARD_CHECKOUT; i++) {
            Register r = new Register(0.015, 1.5); //creats register
            registers.add(r);//adds register to registers arrayList
        }
        //A loop for creating self checkouts
        for (int i = 0; i < NUMBER_SELF_CHECKOUTS; i++) {
            Register r = new Register(0.04, 3.0); //Creates the same register as before but with different pay and scan times
            registers.add(r); //adds register to SAME arraylist
        }
        //registers is an arraylist that has all the standard checkouts then all the self checkouts.
    }

    /**
     * Method that loads all Customer data from the appropriate file
     */
    private static void loadCustomerData() {
        double arriveTime, avgSelectionTime;
        int items;

        try {
            File myFile = new File("arrival.txt"); //Finds the file
            Scanner inputFile = new Scanner(myFile); //connects to the file
            while (inputFile.hasNext()) { //while something to read
                arriveTime = inputFile.nextDouble();
                items = inputFile.nextInt();
                avgSelectionTime = inputFile.nextDouble();
                Customer customer = new Customer(arriveTime, items, avgSelectionTime);
                Event event = new Event(customer, arriveTime, EventType.ARRIVAL);
                events.insert(event); // insert event into heap
            }//end while
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("File was not found");
            System.exit(0);
        }
    }

    /**
     * Method that handles the arrival of a Customer to the store
     * @param e the "Arrival" Event
     */
    private static void handleArrival(Event e) {
        Customer c = e.getCustomer();
        double endShoppingTime = c.getArriveTime() + c.getNumItems() * c.getAvgSelectionTime();
        Event endShopping = new Event(c, endShoppingTime, EventType.END_SHOPPING);
        events.insert(endShopping);
    }
    
    /**
     * Method that handles what happens when a Customer ends shopping and enters a Register line
     * @param e the "end shopping" Event
     */
    private static void handleEndShopping(Event e) {
        Customer customer = e.getCustomer();
        customer.setStartWaitTime(e.getEventTime()); //setting the start wait time of the Event's Customer to the time of the event
        int shortest = getShortestLine(customer.getNumItems()); //shortest is an index of which register in the registers variable.
        customer.setCheckoutLine(shortest); // Customer will always enter shortest checkout line.
        registers.get(shortest).add(customer); // Even if line is empty, customer must be enqueued and dequeued so that the customer gets included in the stats for the register
        if (registers.get(shortest).getLineLength() == 1) { // If new customer is the only one in line, begin checkout.
            startCheckout(customer);
        }
    }
    
    /**
     * Method that handles what happens when a Customer finishes checking out
     * 
     * @param e the "end checkout" Event
     */
    private static void handleEndCheckout(Event e) {
        int line = e.getCustomer().getCheckoutLine();
        Customer c = registers.get(line).remove();
        if (registers.get(line).isEmpty()) {
            return;
        } else {
            Customer customer = registers.get(line).peek();
            startCheckout(customer);
        }
    }
    
    /**
     * Method that handles what happens when a Customer begins to checkout
     * @param customer (the Customer that is beginning the checkout process)
     */
    private static void startCheckout(Customer customer) {
        int line = customer.getCheckoutLine();
        double waitTime = simClock - customer.getStartWaitTime(); //calculating the amount of time the Customer waited in line
        registers.get(line).increaseRegisterWaitTime(waitTime); //incrementing the wait time for the appropriate Register
        customer.setEndWaitTime(simClock); //setting the end wait time for that Customer to the current time
        double checkoutLength = customer.getNumItems() * registers.get(line).getScanTime() + registers.get(line).getPayTime();
        Event endCheckout = new Event(customer, checkoutLength + simClock, EventType.END_CHECKOUT);
        events.insert(endCheckout);
    }

    /**
     * Method that prints the collected Statistics from the simulation
     */
    private static void printCollectedStatistics() {
        //print collected statistics for regular registers
        System.out.println("Printing the collected statistics:");
        System.out.println();
        System.out.println("Regular Registers:");
        System.out.println();
        int i = NUMBER_SELF_CHECKOUTS;
        int j = 1; //register number
        while (i < registers.size()){ //looping through all the Registers
            System.out.println("Register " + j + ":");
            Register r = registers.get(i);
            System.out.println("    Average wait time: " + r.getAverageWaitTime() + " minutes");
            System.out.println("    Customers served: " + r.getTotalNumberOfCustomers());
            System.out.println("    Longest line: " + r.getLongestLine() + " people");
            i++; //incrementing the index in registers
            j++; //incrementing our index for printing the Register number
        }
        System.out.println();
        System.out.println("*******************************");
        System.out.println();
        //print collected statistics for self-checkout registers
        System.out.println("Self-Checkout Registers:");
        System.out.println();
        i = 0;
        while (i < NUMBER_SELF_CHECKOUTS){
            System.out.println("Register " + j + ":");
            Register r = registers.get(i);
            System.out.println("    Average wait time: " + r.getAverageWaitTime() + " minutes");
            System.out.println("    Customers served: " + r.getTotalNumberOfCustomers());
            System.out.println("    Longest line: " + r.getLongestLine() + " people");
            i++; //incrementing the index in registers
            j++; //incrementing our index for printing the Register number
        }
        //print overall statistics
        System.out.println();
        System.out.println("*******************************");
        System.out.println();
        System.out.println("Overall:");
        //finding average wait times
        //FIND OVERALL AVG WAIT TIME
        System.out.println("    Average wait time: " + Customer.getSumWaitTime() / ((double)Customer.getTotalCustomers()) + " minutes");
        System.out.println("    Average wait time per regular register: " + getAverageWaitTimeRR() + " minutes");
        System.out.println("    Average wait time per self-checkour register: " + getAverageWaitTimeSC() + " minutes");
        System.out.println("    Maximum line length: " + getMaxLineLength() + " people"); //finding the maximum line length between all the Registers
        System.out.println("    Percentage of customers who waited:"); //finding the percentage of Customers who waited at least two, three, five and ten minutes in line
        
        System.out.println("        Two or more minutes: " + ((double)Customer.getTotalOfTwoMinuteWaits() / Customer.getTotalCustomers()) * 100 + "%");
        System.out.println("        Three or more minutes: " + ((double)Customer.getTotalOfThreeMinuteWaits()/ Customer.getTotalCustomers()) * 100 + "%");
        System.out.println("        Five or more minutes: " + ((double)Customer.getTotalOfFiveMinuteWaits() / Customer.getTotalCustomers()) * 100 + "%");
        System.out.println("        Ten or more minutes: " + ((double)Customer.getTotalOfTenMinuteWaits() / Customer.getTotalCustomers()) * 100 + "%");
    }
    
        
    /**
     * Method that returns the index in registers that has the least amount of people in line (among the register type that the customer can
     * go to (given their number of items)
     * 
     * @param items the number of items a customer has who is looking to enter a line
     * @return the index in registers that has the least amount of people in line among the registers that the customer go to 
     * with their amount of items
     */
    private static int getShortestLine(int items) {
        if (items < 50){ //customer must go to self-checkout lane
            int i = 1;
            int smallestIndex = 0; //the index of the shortest line 
            int ssf = registers.get(0).getLineLength(); //the amount of people in the first self-checkout line (the smallest so far)
            while (i < NUMBER_SELF_CHECKOUTS){ //loop through registers to see if there's a line that has a smaller number of people
                if (registers.get(i).getLineLength() < ssf){
                    ssf = registers.get(i).getLineLength();
                    smallestIndex = i;
                }
                i++;           
            }
            return smallestIndex;
        }
        else{ //the customer must go to a regular checkout lane
            int i = NUMBER_SELF_CHECKOUTS;
            int smallestIndex = NUMBER_SELF_CHECKOUTS; //the index of the shortest line
            int ssf = registers.get(NUMBER_SELF_CHECKOUTS).getLineLength(); //the amount of people in the first regular checkout line (the smallest so far)
            while (i < registers.size()){ //loop through registers to see if there's a line that has a smaller number of people
                if (registers.get(i).getLineLength() < ssf){
                    ssf = registers.get(i).getLineLength();
                    smallestIndex = i;
                }
                i++;           
            }
            return smallestIndex;
        }
    }
    
    /**
     * Method that finds and returns the maximum line between all the Registers
     * 
     * @return the maximum line between all the Registers
     */
    private static int getMaxLineLength(){
        int i = 1;
        int lsf = registers.get(0).getLongestLine(); //assuming the first Register in registers has the largest maximum line
        while (i < registers.size()){ //looping through the registers ArrayList
            Register r = registers.get(i);
            if (r.getLongestLine() > lsf){ //checking to see if the current Register has a greater maximum line length than the largest so far
                lsf = r.getLongestLine(); 
            }
            i++;
        }
        return lsf;
    }
    
    /**
     * Method that returns the average Customer wait time for all the standard Registers
     * 
     * @return the average Customer wait time for all the standard Registers
     */
    private static double getAverageWaitTimeRR(){
        double totalWaitTimeRR = 0; //total wait time for regular registers
        int i = NUMBER_SELF_CHECKOUTS;
        while (i < registers.size()){ //looping through all the Registers in the registers ArrayList
            Register r = registers.get(i);
            totalWaitTimeRR += r.getAverageWaitTime(); //updating the total wait time for regular Registers
            i++;
        }
        double averageWaitTimeRR = totalWaitTimeRR / ((double)NUMBER_STANDARD_CHECKOUT); //finding the average wait time 
        return averageWaitTimeRR;
    }
    
    /**
     * Method that returns the average Customer wait time for all the self-checkout Registers
     * 
     * @return the average Customer wait time for all the self-checkout Registers
     */
    private static double getAverageWaitTimeSC(){
        double totalWaitTimeSC = 0; //total wait time for self-checkout registers
        int i = 0;
        while (i < NUMBER_SELF_CHECKOUTS){ //looping through all the Registers in the registers ArrayList
            Register r = registers.get(i);
            totalWaitTimeSC += r.getAverageWaitTime(); //updating the total wait time for self-checkout Registers
            i++;
        }
        double averageWaitTimeSC = totalWaitTimeSC / ((double)NUMBER_SELF_CHECKOUTS); //finding the average wait time
        return averageWaitTimeSC;
    }
}

