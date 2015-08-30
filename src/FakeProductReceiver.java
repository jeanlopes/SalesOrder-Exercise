/**
 * Created by I848075 on 19/08/2015.
 */
public class FakeProductReceiver implements ProductReceiver {

    public boolean registrationWasSuccessful;
    public boolean informationIsInvalid;
    public boolean updateFailed = true;

    @Override
    public void registrationFailed() {
        this.registrationWasSuccessful = false;
    }

    @Override
    public void registrationWasSuccessful() {
        this.registrationWasSuccessful = true;
    }

    @Override
    public void productInformationIsInvalid() {
        this.informationIsInvalid = true;
    }
}
