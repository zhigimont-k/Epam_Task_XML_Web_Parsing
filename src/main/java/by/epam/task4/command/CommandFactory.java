package by.epam.task4.command;

public final class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory() {
    }

    public CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command initCommand(CommandType type) throws UnknownCommandException {
        Command command;
        switch (type) {
            case REGISTER:
                command = new RegisterCommand();
                break;
            case LOGIN:
                command = new LoginCommand();
                break;
            case LOGOUT:
                command = new LogoutCommand();
                break;
            case UPLOAD:
                command = new UploadFileCommand();
                break;
            case CHANGE_LOCALE:
                command = new ChangeLocaleCommand();
                break;
            default:
                throw new UnknownCommandException("Unknown command type.");
        }
        return command;
    }
}
