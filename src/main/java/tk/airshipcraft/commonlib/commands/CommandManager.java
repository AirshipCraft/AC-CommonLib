package tk.airshipcraft.commonlib.commands;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * This abstract class CommandManager extends BukkitCommand to facilitate the registration and management of custom commands in a Minecraft server.
 * It allows for the registration of commands directly to the server's CommandMap without the need for specifying them in the plugin.yml file.
 * This approach offers greater flexibility and programmatic control over command registration and handling.
 *
 * @author notzune
 * @version 0.0.1
 * @since 2022-07-28
 */
public abstract class CommandManager extends BukkitCommand {

    /**
     * Constructs and registers a new command with the provided details.
     * The command is immediately registered to the server's CommandMap upon creation of the instance.
     *
     * @param command     The primary name of the command (e.g., "teleport").
     * @param description A brief description of what the command does, displayed in command help.
     * @param permission  The permission node required to use this command.
     * @param aliases     Alternative names for this command (e.g., "tp" as an alias for "teleport").
     */
    protected CommandManager(@NotNull String command,
                             @NotNull String description,
                             @NotNull String permission,
                             @NotNull String[] aliases) {
        super(command);

        this.setDescription(description);
        this.setPermission(permission);
        this.setAliases(Arrays.asList(aliases));

        // Reflection is used here to access the private commandMap of the Bukkit server.
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(command, this);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is invoked when a command registered to this CommandManager is executed.
     * It should be overridden in subclasses to define the specific behavior of the command.
     * This method wraps the abstract execute method with exception handling for SQL exceptions.
     *
     * @param sender       The entity that sent the command (player, console, command block, etc.).
     * @param commandLabel The exact command alias used by the sender.
     * @param args         Command arguments separated by spaces.
     * @return true if the command was executed successfully, false otherwise.
     * @throws SQLException If a database access error occurs in the command's execution.
     */
    @SneakyThrows
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        try {
            execute(sender, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Abstract method to be implemented by subclasses to define the command's behavior.
     * This method should contain the core logic for the command's execution.
     *
     * @param sender Source object which is executing this command.
     * @param args   Command arguments passed to the command.
     * @throws SQLException If a database access error occurs.
     */
    public abstract void execute(CommandSender sender, String[] args) throws SQLException;

    /**
     * Provides tab completion options for this command.
     * This method can be overridden to customize the tab completion behavior for the command.
     *
     * @param sender Source object which is executing this command.
     * @param alias  The alias of the command used.
     * @param args   Command arguments passed to the command.
     * @return A List of Strings containing potential tab complete options.
     * @throws IllegalArgumentException If the arguments are not valid for tab completion.
     */
    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    /**
     * Abstract method to provide custom tab completion options.
     * This method is called by tabComplete and should be overridden in subclasses to define specific tab completion logic.
     * <p>
     * Example:
     * <pre>{@code
     * if (args.length == 2) {
     *      List<String> names = new ArrayList<>();
     *      for (Player player : Bukkit.getOnlinePlayers()) {
     *          names.add(player.getName());
     *      }
     *      return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
     * }
     * }</pre>
     *
     * @param sender The entity that sent the command.
     * @param args   Command arguments passed to the command.
     * @return A List of Strings containing tab completion options.
     */
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}
