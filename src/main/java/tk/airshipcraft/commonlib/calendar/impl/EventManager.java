package tk.airshipcraft.commonlib.calendar.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tk.airshipcraft.commonlib.calendar.IEventManager;
import tk.airshipcraft.commonlib.calendar.clock.CustomDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The EventManager class is responsible for managing and scheduling in-game events in the Minecraft world.
 * It allows for the creation, scheduling, and triggering of events based on in-game dates, providing a dynamic
 * gameplay experience that changes with the calendar.
 * </p>
 * <p>
 * The AbstractGameEvent abstract class provides a structure for creating different types of in-game events.
 * Subclasses of AbstractGameEvent can define specific behaviors in their trigger method
 * </p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class EventManager implements IEventManager {

    /**
     * A map to hold scheduled events, keyed by the date on which they occur.
     */
    private Map<LocalDate, List<AbstractGameEvent>> scheduledEvents;
    /**
     * The file in which the scheduled events are stored.
     */
    private final File eventFile;
    private final Gson gson = new Gson();

    /**
     * Initializes a new EventManager. Creates a new empty map to hold scheduled events.
     */
    public EventManager() {
        scheduledEvents = new HashMap<>();
        //todo: fix this to correct path
        this.eventFile = new File("path/to/your/plugin/directory", "event-calendar.json");
        loadEvents();
    }

    /**
     * Schedules an event to occur on a specific in-game date.
     *
     * @param date  The date on which the event should occur.
     * @param event The event to be scheduled.
     */
    @Override
    public void scheduleEvent(LocalDate date, AbstractGameEvent event) {
        scheduledEvents.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
    }

    /**
     * Triggers all events scheduled for the current in-game date.
     *
     * @param currentDate The current in-game date.
     */
    @Override
    public void triggerEvents(CustomDate currentDate) {
        List<AbstractGameEvent> eventsToday = scheduledEvents.getOrDefault(currentDate, new ArrayList<>());
        for (AbstractGameEvent event : eventsToday) {
            event.trigger();
        }
    }

    /**
     * Saves the scheduled events to a file.
     */
    private void saveEvents() {
        try (Writer writer = new FileWriter(eventFile)) {
            gson.toJson(scheduledEvents, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the scheduled events from a file.
     */
    private void loadEvents() {
        try {
            if (eventFile.exists()) {
                Reader reader = new FileReader(eventFile);
                Type type = new TypeToken<HashMap<LocalDate, List<AbstractGameEvent>>>(){}.getType();
                scheduledEvents = gson.fromJson(reader, type);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
