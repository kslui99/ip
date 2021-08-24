package duke;

/**
 * Encapsulates a command by the user to add a task into Duke's to-do-list.
 */
public class AddCommand extends Command {
    private String description;
    private String taskType;

    /**
     * Constructor for an add command.
     *
     * @param taskType The type of task to add into Duke; can be a Todo, Deadline or Event.
     * @param remainingWords The description of the task, as well as deadlines or timing if applicable.
     */
    public AddCommand(String taskType, String remainingWords) {
        super();
        this.taskType = taskType;
        this.description = remainingWords;
    }

    /**
     * Executes the add command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        switch (taskType) {
        case "todo":
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                t = new Todo(description);
            }
            break;
        case "deadline":
            String d = description.split(" /by")[0];
            String by = description.split("/by ")[1];
            t = new Deadline(d, by);
            break;
        case "event":
            String s = description.split(" /at")[0];
            String at = description.split("/at ")[1];
            t = new Event(s, at);
            break;
        default:
            break;
        }
        tasks.add(t);
        storage.append(t.toStringForFile() + System.lineSeparator());
        ui.showResponse("Got it. I've added this task: \n\t\t "
                + t
                + "\n\t Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
