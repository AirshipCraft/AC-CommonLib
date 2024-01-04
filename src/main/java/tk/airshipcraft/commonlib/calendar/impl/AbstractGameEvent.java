package tk.airshipcraft.commonlib.calendar.impl;

import org.bukkit.Bukkit;
import tk.airshipcraft.commonlib.calendar.IGameEvent;

public abstract class AbstractGameEvent implements IGameEvent {

    private String command;
    private String message;

    public AbstractGameEvent(String command, String message) {
        this.command = command;
        this.message = message;
    }

    protected void executeCommand() {
        if (command != null && !command.isEmpty()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }

    protected void broadcastMessage() {
        if (message != null && !message.isEmpty()) {
            Bukkit.getServer().broadcastMessage(message);
        }
    }
}
