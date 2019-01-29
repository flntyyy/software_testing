package ru.alksndr.software.testing.addressbook.tests;

import org.testng.annotations.Test;
import ru.alksndr.software.testing.addressbook.model.ContactData;

import java.io.File;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        app.contact().initContactCreation();
        File photo= new File("src/test/resources/pict.jpg");
        app.contact().fillContactForm(new ContactData().withFirstname("rqewrewr").withLastname("smiererer").withPhoto(photo));
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }


}
