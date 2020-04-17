/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

public class Player {
    
    /*
        This class contains information about player including
        firstname, surname, age, pirates points and dig points.
        This class also contain getter and setters for accessing
        and modifying it's all attributes
    */
    
    private String firstName;
    private String surname;
    private int age;
    private int piratesPoinsts = 0;
    private int digPoints = 0;
    
    public Player(){
        
        this.piratesPoinsts = 100;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPiratesPoinsts(int piratesPoinsts) {
        this.piratesPoinsts = this.piratesPoinsts + piratesPoinsts;
    }
    
    public void setDigPoints(int digPoints) {
        this.digPoints = this.digPoints + digPoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getPiratesPoinsts() {
        return piratesPoinsts;
    }
    
    public int getDigPoints() {
        return digPoints;
    }
    
}
