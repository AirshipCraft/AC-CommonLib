/**
 * <h1>AirshipCraft Calendar and Event System Package</h1>
 * <p>
 * This package, part of AirshipCraft's <code>CommonLib</code> core library, provides a comprehensive calendar and event system
 * for Minecraft servers. It is designed to serve as an API, facilitating the integration of time-based functionalities such as
 * seasonal changes, event scheduling, and dynamic in-game news. The system emphasizes ease of use, interoperability, and
 * extensibility to enhance server features like agriculture, economy, and AI-driven NPCs.
 * </p>
 *
 * <h2>Core Classes:</h2>
 * <ul>
 *   <li><b>CustomDate:</b> Represents a custom in-game date format with configurable days per month and months per year.
 *       It supports adding days, comparing dates, and converting between real-world and in-game time.</li>
 *   <li><b>WorldClock:</b> Manages the progression of in-game time and synchronizes it with real-world time. It also
 *       integrates with {@code CalendarManager} and {@code EventManager} to trigger events based on the in-game date.</li>
 * </ul>
 *
 * <h2>Interfaces:</h2>
 * <ul>
 *   <li><b>ICalendarManager:</b> Defines the contract for managing in-game time and date information.</li>
 *   <li><b>IEventManager:</b> Outlines methods for scheduling and triggering in-game events.</li>
 *   <li><b>ISeasonManager:</b> Provides functionality for determining in-game seasonal changes.</li>
 * </ul>
 *
 * <h2>Implementation Classes:</h2>
 * <ul>
 *   <li><b>CalendarManager:</b> Implements <code>ICalendarManager</code>, handling the conversion between real-world and in-game time.
 *       It maintains the current date and allows for time progression.</li>
 *   <li><b>SeasonManager:</b> Implements <code>ISeasonManager</code>, calculating the current season based on the in-game date
 *       provided by <code>CalendarManager</code>.</li>
 *   <li><b>EventManager:</b> Implements <code>IEventManager</code>, managing the lifecycle of scheduled events and providing
 *       mechanisms to trigger them on specific dates.</li>
 * </ul>
 *
 * <h2>Core Classes Usage Examples:</h2>
 * <pre>{@code
 * // Example for CustomDate
 * CustomDate customDate = new CustomDate(1, 1, 1); // Start of Year 1
 * customDate.addDays(30); // Adds 30 days, potentially changing month/year
 * LocalDate equivalentDate = customDate.toLocalDate(); // Converts to LocalDate
 *
 * // Example for WorldClock
 * WorldClock worldClock = new WorldClock(plugin, calendarManager, eventManager);
 * worldClock.start(); // Starts the clock to sync in-game time with real-time
 *
 * // Example for CalendarManager
 * ICalendarManager calendar = new CalendarManager();
 * LocalDate today = calendar.getCurrentDate();
 * calendar.advanceTime(1); // Advances the in-game calendar by one day.
 *
 * // Example for SeasonManager
 * ISeasonManager seasonManager = new SeasonManager(calendar);
 * ISeasonManager.Season currentSeason = seasonManager.getCurrentSeason();
 *
 * // Example for EventManager
 * IEventManager eventManager = new EventManager();
 * eventManager.scheduleEvent(today.plusDays(10), new GameEvent() {
 *     @Override
 *     public void trigger() {
 *         // Event logic goes here.
 *     }
 * });
 * eventManager.triggerEvents(today); // Triggers all events scheduled for today.
 * }</pre>
 *
 * <h2>Example Use Cases:</h2>
 * <ul>
 *   <li><b>Agriculture Plugin:</b> Uses <code>SeasonManager</code> to adjust crop growth rates and harvesting schedules
 *       dynamically based on the current season, enhancing realism and gameplay depth.</li>
 *   <li><b>Economy Plugin:</b> Integrates with <code>EventManager</code> to create and manage market events or sales
 *       tied to in-game holidays, seasons, or other calendar events, impacting the server's economy dynamically.</li>
 *   <li><b>NPC Interaction Plugin:</b> Leverages <code>CalendarManager</code> to alter NPC behaviors or available quests
 *       depending on the time of year, special events, or seasonal changes, contributing to a dynamic game environment.</li>
 * </ul>
 *
 * <p>
 * The design of this package allows for a decoupled and layered approach to handling time-related functionalities,
 * promoting maintainable and scalable server architecture.
 * </p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
package tk.airshipcraft.commonlib.calendar;
