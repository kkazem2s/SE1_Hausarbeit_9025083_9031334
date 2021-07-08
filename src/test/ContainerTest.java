
package test;

import commands.analyze.Analyze_single;
import model.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    private static Container container;
    private static Container container2 = Container.getInstance();
    private static Container container3 = Container.getInstance();

    private UserStory us1 = new UserStory("Test1", 1,1,1,1,1,"123456789101112131415","123456789101112131415");

    /**
     *  SETUP
     */

    @BeforeEach
    void setup() {
        //Testing method to create Instance of Singleton
        //Only for Testing Purposes
        container = Container.TEST_CreateInstance();
    }

    /**
     *  TESTING CONTAINER CLASS
     */

    @Test //Container Singleton Test
    void containerSingletonTest() {
        assertSame(container2,container3,"Objektgleichheit");
    }
    @Test //Adding UserStory
    void addStoryTest() {
        assertEquals(0,container.getCurrentList().size(), "Beide müssen 0 sein");
        container.getCurrentList().add(us1);
        assertEquals(1,container.getCurrentList().size(), "Beide müssen 1 sein");
    }
    @Test //Adding Actor
    void addActorTest() {
        assertEquals(0,container.getCurrentListAkteur().size(),"Beide müssen 0 sein");
        container.addAkteur("Kevin");
        assertEquals(1, container.getCurrentListAkteur().size(), "Beide müssen 1 sein");
    }
    @Test //Adding Story and remove
    void undoStoryTest() {
        container.addUS(us1);
        assertEquals(1, container.getCurrentList().size(), "Beide müssen 1 sein");
        container.undo();
        assertEquals(0,container.getCurrentList().size(),"Beide müssen 0 sein");
    }
    @Test //Adding Actor and remove
    void undoActorTest() {
        container.addAkteur("Kevin");
        assertEquals(1, container.getCurrentListAkteur().size(), "Beide müssen 1 sein");
        container.undo();
        assertEquals(0,container.getCurrentListAkteur().size(),"Beide müssen 0 sein");
    }
    @Test //Store & Load Story
    void storeLoadStoryTest() {
        container.addUS(us1);
        container.store();
        container.undo();
        assertEquals(0, container.getCurrentList().size(), "Beide müssen 0 sein");
        container.load();
        assertEquals(1, container.getCurrentList().size(), "Beide müssen 1 sein");
    }
    @Test //Store & Load Actor
    void storeLoadActorTest() {
        container.addAkteur("Kevin");
        container.store();
        container.undo();
        assertEquals(0, container.getCurrentListAkteur().size(), "Beide müssen 0 sein");
        container.load();
        assertEquals(1, container.getCurrentListAkteur().size(), "Beide müssen 1 sein");
    }

    @Test //Contains UserStory
    void containsTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        container.addUS(us1);
        container.addUS(us1);
        assertEquals("User Story mit ID: " + us1.getID()+ " wurde im System registriert! (Prio: " + us1.getPrio() + ")\r\nID bereits vergeben!\r\n",outContent.toString(), "ID bereits vergeben");
        System.setOut(System.out);
    }
    @Test //Dump
    void dumpTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        container.dump();
        assertEquals("Keine UserStories vorhanden!\r\n",outContent.toString(), "Dump Test nicht erfolgreich!");
        System.setOut(System.out);
    }
    @Test //Change Status of UserStory
    void statusTest() {
        container.addUS(us1);
        assertEquals(Status.TODO,us1.getStatus(),"Todo Test");
        us1.setStatus(Status.PROGRESS);
        assertEquals(Status.PROGRESS,us1.getStatus(),"Progress Test");
        us1.setStatus(Status.DONE);
        assertEquals(Status.DONE, us1.getStatus(), "Done Test");
    }
    @Test //Analyze UserStory
    void analyzeTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        container.addUS(us1);
        container.addAkteur("student");
        us1.setHauptakteur(container.getRealAkteur(0));
        Analyze_single as = new Analyze_single(us1,false,false);
        as.execute();
        assertEquals("User Story mit ID: 1 wurde im System registriert! (Prio: 1.0)\r\n" +
                        "Der Akteur 'student' wurde im System registriert!\r\n" +
                        "Die UserStory mit der ID: 1 hat folgenden Qualität: \r\n100% (sehr gut)\r\n",outContent.toString(),
                "Analyze Test nicht erfolgreich!");
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */