/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesimulation;

import java.util.LinkedList;



/**
 *
 * @author Jake Heyser
 */
class Register { 

    private LinkedList<Customer> line = new LinkedList<Customer>();
    private double scanTime, payTime;
    private int maxLength;
    private double waitTime;
    private int totalNumberOfCustomers;
    
    /**
     * Constructor for Register class
     * 
     * @param scanTime
     * @param payTime 
     */
    public Register(double scanTime, double payTime) {
        this.scanTime = scanTime;
        this.payTime = payTime;
        maxLength = 0;
        waitTime = 0;
        totalNumberOfCustomers = 0;
    }

    /**
     * Method that returns the size of the current Register line
     * 
     * @return the size of the current Register line
     */
    public int getLineLength() {
        return line.size();
    }

    /**
     * Method that adds a Customer to the back of the Register line
     * 
     * @param customer 
     */
    public void add(Customer customer) {
        line.add(customer);
        if (getLineLength() > maxLength){
            maxLength = getLineLength();
        }
        totalNumberOfCustomers++;
    }

    /**
     * Method that removes a Customer from the start of the Register line
     * 
     * @return the Customer that was removed from the start of the Register line
     */
    public Customer remove() {
        if (line.size() == 0){ //the line is empty; there's nothing to return
            return null;
        }
        Customer c = line.peek(); //the Customer at the start of the Register line that we want to return
        line.remove();
        return c;
    }

    /**
     * Method that determines whether the Register line is empty or not
     * 
     * @return true if the line is empty and false otherwise
     */
    public boolean isEmpty() {
        if (line.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Method that looks to see who is at the start of the Register line and returns that Customer
     * 
     * @return the Customer who is at the start of the Register line
     */
    public Customer peek() {
        return line.peek();
    }

    /**
     * Method that returns the scan time for the Register
     * 
     * @return the scan time
     */
    public double getScanTime() {
        return scanTime;
    }

    /**
     * Method that returns the pay time for the Register
     * 
     * @return the pay time for the Register
     */
    public double getPayTime() {
        return payTime;
    }
    
    /**
     * Method that returns the total number of Customers that have checked out at this Register line
     * 
     * @return the total number of Customers that have checked out at this Register line
     */
    public int getTotalNumberOfCustomers(){
        return totalNumberOfCustomers;
    }
    
    /**
     * Method that returns the longest line length that the Register has had 
     * 
     * @return the longest line length that the Register has had 
     */
    public int getLongestLine(){
        return maxLength;
    }
    
    /**
     * Method that returns the average Customer wait time for this Register
     * 
     * @return the average Customer wait time for this Register
     */
    public double getAverageWaitTime(){
        if (totalNumberOfCustomers == 0){
            return 0;
        }
        return waitTime / totalNumberOfCustomers;
    }
    
    /**
     * Method that increases the wait time at the Register by the amount of time specified by the parameter value
     * 
     * @param minutes 
     */
    public void increaseRegisterWaitTime(double minutes){
        waitTime += minutes;
    }
    
    @Override
    /**
     * Method that returns a String representing the Register
     * 
     * @return a String representing the Register
     */
    public String toString(){
        String s = "[";
        for (int i = 0; i < line.size(); i++) {
            s = s + line.get(i) + " ";
        }
        return s + "]";
    }
}