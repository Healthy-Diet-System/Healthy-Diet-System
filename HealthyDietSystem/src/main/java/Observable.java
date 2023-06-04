import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<CustomObserver> customObservers = new ArrayList<>();

    public void attach(CustomObserver customObserver) {
        customObservers.add(customObserver);
    }

    public void detach(CustomObserver customObserver) {
        customObservers.remove(customObserver);
    }

    private void notifyObservers(Customer customer) {
        for (CustomObserver customObserver : customObservers) {
            customObserver.update(customer);
        }
    }
}