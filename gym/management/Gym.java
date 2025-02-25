package gym.management;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * The Gym class represents a singleton gym management system.
 * It manages the gym's name, secretary, clients, instructors, and sessions.
 */
public class Gym {//
    private static Gym instance;
    private Secretary gymSecretary;
    private String gymName;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private Gym() {

    }

    /**
     * Returns the singleton instance of the Gym.
     * @return The singleton instance of Gym.
     */
    public static Gym getInstance() {
        if (instance == null) {
            synchronized (Gym.class){
            if (instance == null){
                instance=new Gym();
            }
         }
        }
        return instance;
    }

    /**
     * Sets the name of the gym.
     * @param gym_Name The name to set for the gym.
     */
public void setName(String gym_Name){
        gymName = gym_Name;
}

    /**
     * Sets the secretary of the gym.
     * Transfers data from the previous secretary if one exists.
     * @param p      The person who will be the secretary.
     * @param salary The salary of the secretary.
     */
public void setSecretary(Person p, int salary){
       if (gymSecretary==null) {
           gymSecretary = new Secretary(p, salary);
       }else {

           ArrayList<Client> copyGymClientList = gymSecretary.getGymClientList();
           ArrayList<Instructor> copyGymInstructorList = gymSecretary.getGymInstructorList();
           ArrayList<Session> copySessionList = gymSecretary.getSessionList();
           int copyGymBalanc = gymSecretary.getGymBalanc();
           ArrayList<String> copyGymActions = gymSecretary.getGymActions();

           gymSecretary = new Secretary(p, salary);
           gymSecretary.setGymClientList(copyGymClientList);
           gymSecretary.setGymInstructorList(copyGymInstructorList);
           gymSecretary.setSessionList(copySessionList);
           gymSecretary.setGymBalanc(copyGymBalanc);
           gymSecretary.setGymActions(copyGymActions);
       }
    for (int i = 0; i < gymSecretary.getGymClientList().size(); i++) {
        if (gymSecretary.getGymClientList().get(i).getPerson()==p){
            gymSecretary.getGymClientList().remove(i);
        }
    }
       gymSecretary.addGymActions("A new secretary has started working at the gym: "+p.getName());

}

    /**
     * Retrieves the current secretary of the gym.
     * @return The current gym secretary.
     */
public Secretary getSecretary(){
        return gymSecretary;
}

    /**
     * Provides a string representation of the gym's details, including secretary, clients, instructors, and sessions.
     * @return A formatted string containing gym details.
     */
    @Override
    public String toString() {
        String outPut ="Gym Name: "+this.gymName+"\nGym Secretary: ID: "+gymSecretary.getPerson().getId()+" | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\nGym Balance: "+gymSecretary.getGymBalanc()+"\n\n";
        outPut+="Clients Data:\n";
        for (Client client: gymSecretary.getGymClientList()) {
            outPut+="ID: "+client.getPerson().getId()+" | Name: "+client.getName()+" | Gender: "+client.getPerson().getGender()+" | Birthday: "+client.getPerson().getDate_of_birth()+" | Age: "+client.getPerson().getAge()+" | Balance: "+client.getPerson().getBalance()+"\n";
        }
        outPut+="\nEmployees Data:\n";
        for (Instructor instructor: gymSecretary.getGymInstructorList()){
            outPut+="ID: "+instructor.getInstructorPerson().getId()+" | Name: "+instructor.getInstructorPerson().getName()+" | Gender: "+instructor.getInstructorPerson().getGender()+" | Birthday: "+instructor.getInstructorPerson().getDate_of_birth()+" | Age: "+instructor.getInstructorPerson().getAge()+" | Balance: "+instructor.getInstructorPerson().getBalance()+" | Role: Instructor | Salary per Hour: "+instructor.getSalaryPerHour()+" | Certified Classes:"+arrayToString(instructor.getSessionQualified())+"\n";
        }
       outPut+="ID: "+gymSecretary.getPerson().getId()+" | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\n\n";
        outPut+="Sessions Data:";
        for (Session session: gymSecretary.getSessionList()){
            outPut+="\nSession Type: "+session.getSessionType()+" | Date: "+ session.getDateAndHour() +" | Forum: "+session.getForumType()+" | Instructor: "+ session.getThisSessionInstructor().getInstructorPerson().getName()+" | Participants: "+session.getListOfClientsInCurrentClass().size()+"/"+session.getNumber_of_people_in_the_class();
        }

        return outPut;
    }

    /**
     * Converts a list of SessionType to a comma-separated string.
     * @param sessionQualifiedList List of session types.
     * @return A comma-separated string of session types.
     */
    private String arrayToString(ArrayList<SessionType>sessionQualifiedList){
        String str = " "+sessionQualifiedList.get(0);
        for (int i = 1; i < sessionQualifiedList.size(); i++) {
            str+= ", "+sessionQualifiedList.get(i);
        }
        return str;
    }



}
