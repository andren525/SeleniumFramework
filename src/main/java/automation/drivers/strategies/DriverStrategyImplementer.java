package automation.drivers.strategies;

import automation.utils.Constants;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(String strategy) {
        return switch (strategy) {
            case Constants.CHROME -> new Chrome();
            case Constants.FIREFOX -> new Firefox();
            default -> null;
        };
    }
}
