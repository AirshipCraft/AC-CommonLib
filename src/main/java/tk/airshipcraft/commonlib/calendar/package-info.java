/**
 * <p>
 * This package, a part of AirshipCraft's {@link tk.airshipcraft.commonlib.CommonLib} core library, provides a comprehensive calendar and event system
 * for Minecraft servers. Designed to offer functionalities like seasonal changes, event scheduling, and dynamic in-game news,
 * this system emphasizes ease of use, interoperability, and extensibility. It is particularly useful for enhancing server
 * features such as agriculture, economy, and AI-driven NPCs.
 * </p>
 *
 * <h2>Core Classes:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.clock.CustomDate}<b>CustomDate:</b> Represents a custom in-game date format with configurable days per month and months per year.
 *       Supports operations like adding days, comparing dates, and converting between real-world and in-game time.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.clock.WorldClock}<b>WorldClock:</b> Manages in-game time progression, synchronizing it with real-world time, and interacts with
 *       {@link tk.airshipcraft.commonlib.calendar.impl.CalendarManager} and {@link tk.airshipcraft.commonlib.calendar.impl.EventManager} for event triggers based on in-game dates.</li>
 * </ul>
 *
 * <h2>Interfaces:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.ICalendarManager}<b>ICalendarManager:</b> Manages in-game time and date information.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.IEventManager}<b>IEventManager:</b> Handles scheduling and triggering of in-game events.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.IGameEvent}<b>IGameEvent:</b> Defines the contract for custom game events.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.ISeasonManager}<b>ISeasonManager:</b> Manages in-game seasonal changes.</li>
 * </ul>
 *
 * <h2>Abstract Classes:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.impl.AbstractGameEvent}<b>AbstractGameEvent:</b> Provides a template for creating various types of in-game events, including execution
 *       of commands and broadcasting messages.</li>
 * </ul>
 *
 * <h2>Implementation Classes:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.impl.CalendarManager}<b>CalendarManager:</b> Manages conversion between real-world and in-game time, maintaining the current date
 *       and allowing for time progression.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.impl.SeasonManager}<b>SeasonManager:</b> Calculates current seasons based on in-game dates from {@link tk.airshipcraft.commonlib.calendar.impl.CalendarManager}.</li>
 *   <li>{@link tk.airshipcraft.commonlib.calendar.impl.EventManager}<b>EventManager:</b> Manages event lifecycle, including scheduling and triggering events on specific dates.</li>
 * </ul>
 *
 * <h2>Core Classes Usage Examples:</h2>
 * <pre>{@code
 * // CustomDate Example
 * CustomDate customDate = new CustomDate(1, 1, 1);
 * customDate.addDays(30);
 * LocalDate equivalentDate = customDate.toLocalDate();
 *
 * // WorldClock Example
 * WorldClock worldClock = new WorldClock(plugin, calendarManager, eventManager);
 * worldClock.start();
 *
 * // CalendarManager Example
 * ICalendarManager calendar = new CalendarManager();
 * LocalDate today = calendar.getCurrentDate();
 * calendar.advanceTime(1);
 *
 * // SeasonManager Example
 * ISeasonManager seasonManager = new SeasonManager(calendar);
 * Season currentSeason = seasonManager.getCurrentSeason();
 *
 * // EventManager Example
 * IEventManager eventManager = new EventManager();
 * eventManager.scheduleEvent(today.plusDays(10), new AbstractGameEvent(...) {
 *     @Override
 *     public void trigger() {
 *         // Event logic here.
 *     }
 * });
 * eventManager.triggerEvents(today);
 * }</pre>
 *
 * <h2>Example Use Cases:</h2>
 * <ul>
 *   <li><b>Agriculture Plugin:</b> Uses {@link tk.airshipcraft.commonlib.calendar.impl.SeasonManager} for dynamic crop growth and harvest schedules.</li>
 *   <li><b>Economy Plugin:</b> Integrates with {@link tk.airshipcraft.commonlib.calendar.impl.EventManager} for market events and sales linked to calendar events.</li>
 *   <li><b>NPC Interaction Plugin:</b> Uses {@link tk.airshipcraft.commonlib.calendar.impl.CalendarManager} for varying NPC behaviors and quests based on time of year.</li>
 * </ul>
 *
 * <p>
 * This package promotes a decoupled, layered approach to time-related functionalities for maintainable and scalable server architecture.
 * </p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
package tk.airshipcraft.commonlib.calendar;
