package org.cydeo.loosely_coupled;

public class CydeoApp {
    public static void main(String[] args) {
        FullTimeMentor fullTime = new FullTimeMentor();
        PartTimeMentor partTime = new PartTimeMentor();

        MentorAccount fullTimeMentorAccount = new MentorAccount(fullTime);
        MentorAccount partTimeMentorAccount = new MentorAccount(partTime);

        fullTimeMentorAccount.manageAccount();
        partTimeMentorAccount.manageAccount();
    }
}
