package ru.alksndr.software.testing.addressbook.tests;

import org.testng.annotations.Test;
import ru.alksndr.software.testing.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("Test1", "test2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
