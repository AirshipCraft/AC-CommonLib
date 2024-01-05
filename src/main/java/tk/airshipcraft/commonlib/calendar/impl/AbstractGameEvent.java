package tk.airshipcraft.commonlib.calendar.impl;

import org.bukkit.Bukkit;
import tk.airshipcraft.commonlib.calendar.IGameEvent;

import java.util.Optional;

/**
 * The AbstractGameEvent abstract class provides a structure for creating different types of in-game events.
 * Subclasses of AbstractGameEvent can define specific behaviors in their trigger method.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-04
 */
public abstract class AbstractGameEvent implements IGameEvent {

    private final Optional<String> command;
    private final Optional<String> message;

    /**
     * Creates a new AbstractGameEvent with the specified command and message.
     *
     * @param command The command to execute when the event is triggered.
     * @param message The message to broadcast when the event is triggered.
     */
    public AbstractGameEvent(Optional<String> command, Optional<String> message) {
        this.command = command;
        this.message = message;
    }

    /**
     * Creates a new AbstractGameEvent with the specified command.
     */
    protected void executeCommand() {
        if (command.isPresent()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.valueOf(command));
        }
    }

    /**
     * Creates a new AbstractGameEvent with the specified message.
     */
    protected void broadcastMessage() {
        if (message.isPresent()) {
            Bukkit.getServer().broadcastMessage(String.valueOf(message));
        }
    }
}
