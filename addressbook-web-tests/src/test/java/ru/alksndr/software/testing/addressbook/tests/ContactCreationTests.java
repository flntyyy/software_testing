package ru.alksndr.software.testing.addressbook.tests;

import org.testng.annotations.Test;
import ru.alksndr.software.testing.addressbook.model.ContactData;
import ru.alksndr.software.testing.addressbook.model.Groups;

import java.io.File;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Groups groups = app.db().groups();
        app.goTo().gotoHomePage();
        app.contact().initContactCreation();
        File photo= new File("src/test/resources/pict.jpg");
       ContactData newContact =  new ContactData().withFirstname("rqewrewr").withLastname("smiererer").withPhoto(photo)
               .inGroup(groups.iterator().next());
        app.contact().fillContactForm(newContact);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }

}
