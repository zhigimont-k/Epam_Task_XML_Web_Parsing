package by.epam.task4.command;

public final class CommandFactory {
    private static CommandFactory INSTANCE;

    private CommandFactory() {
    }

    public CommandFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandFactory();
        }
        return INSTANCE;
    }

    public Command initCommand(TypeCommand type){
        Command command = null;
        switch(type){
            case REGISTER:
                command = new RegisterCommand();
            case LOGIN:
                command = new LoginCommand();
            case LOGOUT:
                command = new LogoutCommand();
            case UPLOAD:
                command = new UploadFileCommand();
            case CHANGE_LOCALE:
                command = new ChangeLocaleCommand();
        }
        return command;
    }
}
