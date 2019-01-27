package ru.alksndr.software.testing.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.alksndr.software.testing.addressbook.model.ContactData;
import ru.alksndr.software.testing.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }


    public void initContactCreation() {
        click(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
    }

    public void initContactModificationById(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }


    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    private Contacts contactCache = null;

    /*public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();

        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(name));
        }
        return new Contacts(contactCache);
    }*/

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));

        }
        return contacts;
    }

    public ContactData infoFromEditFrom(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }
}
