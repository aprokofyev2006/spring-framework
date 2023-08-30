package org.cydeo;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Java {

    //Field Injection
//    @Autowired
//    OfficeHours officeHours;

    //Constructor Injection
    /* We can omit @Autowired annotation when there is only one constructor in the class.
    Spring will do injection and apply @Autowired by default */

    OfficeHours officeHours;
    //@Autowired
//    public Java(OfficeHours officeHours){
//        this.officeHours = officeHours;
//    }


    public void getTeachingHours(){
        System.out.println("Weekly teaching hours: " + (20+officeHours.getHours()));
    }
}
