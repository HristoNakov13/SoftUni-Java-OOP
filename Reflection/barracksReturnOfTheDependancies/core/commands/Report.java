package barracksReturnOfTheDependancies.core.commands;

import barracksReturnOfTheDependancies.data.UnitRepository;

public class Report extends Command {
    private UnitRepository repo;

    public Report(String[] data) {
        super(data);
    }

    private UnitRepository getRepo() {
        return repo;
    }

    @Inject
    public void setRepo(UnitRepository repo) {
        this.repo = repo;
    }

    @Override
    public String execute() {
        return this.getRepo().getStatistics();
    }
}
