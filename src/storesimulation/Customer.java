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
class Customer {

    private double arriveTime; //the arrival time of the Customer
    private int numItems; //the number of items the Customer is buying
    private double avgSelectionTime; //the average selection time of the Customer
    private int registerIndex; //the index of the register that the Customer will check out at
    private double startWaitTime; //the time the Customer begins waiting in line to check out
    private double totalWaitTime; //the total wait time of the Customer in line
    private double endWaitTime; //the time the Customer ends waiting in line
    public static int twoMinuteWaitTime = 0; //the total number of Customers that waited at least two minutes in line
    private static int threeMinuteWaitTime = 0; //the total number of Customers that waited at least three minutes in line
    private static int fiveMinuteWaitTime = 0; //the total number of Customers that waited at least five minutes in line
    private static int tenMinuteWaitTime = 0; //the total number of Customers that waited at least ten minutes in line
    private static int totalCustomers = 0; //the total number of Customers in the store
    private static double sumWaitTime = 0; //the running total of wait times for all Customers
    
    Customer(double arriveTime, int items, double avgSelectionTime) {
        setArriveTime(arriveTime);
        setNumItems(items);
        setAvgSelectionTime(avgSelectionTime);
        registerIndex = -1;
        startWaitTime = 0;
        endWaitTime = 0;
        totalWaitTime = 0;
    }

    /**
     * @return the arriveTime of the Customer
     */
    public double getArriveTime() {
        return arriveTime;
    }

    /**
     * @param arriveTime the arriveTime to set
     */
    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * @return the numItems
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * @param numItems the numItems to set
     */
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    /**
     * @return the avgSelectionTime
     */
    public double getAvgSelectionTime() {
        return avgSelectionTime;
    }

    /**
     * @param avgSelectionTime the avgSelectionTime to set
     */
    public void setAvgSelectionTime(double avgSelectionTime) {
        this.avgSelectionTime = avgSelectionTime;
    }

    /**
     * Method that sets the Customer in the specified register line
     * 
     * @param registerIndex 
     */
    public void setCheckoutLine(int registerIndex) {
        this.registerIndex = registerIndex;
    }

    /**
     * Method that returns the index of the register line the Customer is in
     * 
     * @return the index of the register lines the Customer is in
     */
    public int getCheckoutLine() {
        return this.registerIndex;
    }
    
    /**
     * Method that sets startWaitTime to a specified time
     * 
     * @param beginWaitTime 
     */
    public void setStartWaitTime(double beginWaitTime){
        startWaitTime = beginWaitTime;
    }
    
    /**
     * Method that returns the time that the Customer began waiting
     * 
     * @return the time that the Customer began waiting
     */
    public double getStartWaitTime(){
        return startWaitTime;
    }
    
    /**
     * Method that sets endWaitTime to a specified time, updates the total amount of time that the Customer waited,
     * and updates the static fields for the number of Customers that waited two, three, five and ten minutes, as well as
     * increments the total amount of Customers that are in the store
     * 
     * @param minutes 
     */
    public void setEndWaitTime(double minutes){
        endWaitTime = minutes;
        totalWaitTime = endWaitTime - startWaitTime;
        totalCustomers++;
        if (totalWaitTime >= 2.0){ //updating the total number of Customers that waited two or more minutes in line  
            twoMinuteWaitTime++;
        }
        if (totalWaitTime >= 3.0){ //updating the total number of Customers that waited three or more minutes in line 
            threeMinuteWaitTime++;
        }
        if (totalWaitTime >= 5.0){ //updating the total number of Customers that waited five or more minutes in line 
            fiveMinuteWaitTime++;
        }
        if (totalWaitTime >= 10.0){ //updating the total number of Customers that waited ten or more minutes in line 
            tenMinuteWaitTime++;
        }
        sumWaitTime += totalWaitTime;
    }
    
    /**
     * Method that returns the total amount of time the Customer waited in line to checkout
     * 
     * @return the Customer's total wait time
     */
    public double getTotalWaitTime(){
        return totalWaitTime;
    }
    
    /**
     * Method that returns the total number of Customers that waited at least two minutes in line to checkout
     * 
     * @return the total number of Customers that waited at least two minutes in line to checkout
     */
    public static int getTotalOfTwoMinuteWaits(){
        return twoMinuteWaitTime;
    }
    
    /**
     * Method that returns the total number of Customers that waited at least three minutes in line to checkout
     * 
     * @return the total number of Customers that waited at least three minutes in line to checkout
     */
    public static int getTotalOfThreeMinuteWaits(){
        return threeMinuteWaitTime;
    }
    
    /**
     * Method that returns the total number of Customers that waited at least five minutes in line to checkout
     * 
     * @return the total number of Customers that waited at least five minutes in line to checkout
     */
    public static int getTotalOfFiveMinuteWaits(){
        return fiveMinuteWaitTime;
    }
    
    /**
     * Method that returns the total number of Customers that waited at least ten minutes in line to checkout
     * 
     * @return the total number of Customers that waited at least ten minutes in line to checkout
     */  
    public static int getTotalOfTenMinuteWaits(){
        return tenMinuteWaitTime;
    }
    
    /**
     * Method that returns the total number of Customers in the store during the simulation
     * 
     * @return the total number of Customers in the store during the simulation
     */
    public static int getTotalCustomers(){
        return totalCustomers;
    }
    
    /**
     * Method that returns a String representing the Customer
     * 
     * @return a String representing the Customer
     */
    @Override
    public String toString(){
        String s = "[";
        s = s + getArriveTime() + " " + getNumItems() + " " + getAvgSelectionTime();
        return s + "]";
    }
    
    /**
     * Method that returns the total wait time for all Customers in the store
     * 
     * @return the total wait time for all the Customers in the store
     */
    public static double getSumWaitTime(){
        return sumWaitTime;
    }
}
