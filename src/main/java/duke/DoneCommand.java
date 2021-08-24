package duke;

/**
 * Encapsulates a command by the user to mark a task in Duke's to-do-list as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for a mark-as-done command.
     *
     * @param index The index of the task to mark as done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the mark-as-done command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getSize() || index <= 0) {
            throw new DukeException("OOPS!!! I'm sorry, but you've entered an invalid index.");
        }
        Task t = tasks.markAsDone(index);
        storage.save(tasks);
        ui.showResponse("Nice! I've marked this task as done: \n\t\t " + t.toString());
    }
}
